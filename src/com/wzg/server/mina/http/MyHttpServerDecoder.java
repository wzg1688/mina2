package com.wzg.server.mina.http;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.http.ArrayUtil;
import org.apache.mina.http.DecoderState;
import org.apache.mina.http.HttpException;
import org.apache.mina.http.HttpRequestImpl;
import org.apache.mina.http.api.HttpEndOfContent;
import org.apache.mina.http.api.HttpMethod;
import org.apache.mina.http.api.HttpStatus;
import org.apache.mina.http.api.HttpVersion;

import com.wzg.utils.MyLog;

public class MyHttpServerDecoder implements ProtocolDecoder {
	private static final MyLog logger = MyLog.getLogger(MyHttpServerDecoder.class);

	private static final CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();

	/** Key for decoder current state */
	private static final String DECODER_STATE_ATT = "http.ds";

	/** Key for the partial HTTP requests head */
	private static final String PARTIAL_HEAD_ATT = "http.ph";

	/** Key for the number of bytes remaining to read for completing the body */
	private static final String BODY_REMAINING_BYTES = "http.brb";

	/** Regex to parse HttpRequest Request Line */
	public static final Pattern REQUEST_LINE_PATTERN = Pattern.compile(" ");

	/** Regex to parse out QueryString from HttpRequest */
	public static final Pattern QUERY_STRING_PATTERN = Pattern.compile("\\?");

	/** Regex to parse out parameters from query string */
	public static final Pattern PARAM_STRING_PATTERN = Pattern.compile("\\&|;");

	/** Regex to parse out key/value pairs */
	public static final Pattern KEY_VALUE_PATTERN = Pattern.compile("=");

	/** Regex to parse raw headers and body */
	public static final Pattern RAW_VALUE_PATTERN = Pattern.compile("\\r\\n\\r\\n");

	/** Regex to parse raw headers from body */
	public static final Pattern HEADERS_BODY_PATTERN = Pattern.compile("\\r\\n");

	/** Regex to parse header name and value */
	public static final Pattern HEADER_VALUE_PATTERN = Pattern.compile(":");

	/** Regex to split cookie header following RFC6265 Section 5.4 */
	public static final Pattern COOKIE_SEPARATOR_PATTERN = Pattern.compile(";");

	public void decode(IoSession session, IoBuffer msg, ProtocolDecoderOutput out) {

		DecoderState state = (DecoderState) session.getAttribute(DECODER_STATE_ATT);
		if (null == state) {
			session.setAttribute(DECODER_STATE_ATT, DecoderState.NEW);
			state = (DecoderState) session.getAttribute(DECODER_STATE_ATT);
		}
		switch (state) {
		case HEAD:
			logger.D("decoding HEAD");
			// grab the stored a partial HEAD request
			final ByteBuffer oldBuffer = (ByteBuffer) session.getAttribute(PARTIAL_HEAD_ATT);
			// concat the old buffer and the new incoming one
			// now let's decode like it was a new message
			msg = IoBuffer.allocate(oldBuffer.remaining() + msg.remaining()).put(oldBuffer).put(msg).flip();
			logger.D("position -> " + msg.position() + " , limit -> " + msg.limit() + " , cap -> " + msg.capacity());

		case NEW:
			logger.D("decoding NEW");
			logger.D("position -> " + msg.position() + " , limit -> " + msg.limit() + " , cap -> " + msg.capacity());
			HttpRequestImpl rq = parseHttpRequestHead(msg);
			logger.D("position -> " + msg.position() + " , limit -> " + msg.limit() + " , cap -> " + msg.capacity());
			if (rq == null) {
				// we copy the incoming BB because it's going to be recycled by
				// the inner IoProcessor for next reads
				ByteBuffer partial = ByteBuffer.allocate(msg.remaining());
				partial.put(msg.buf());
				partial.flip();
				// no request decoded, we accumulate
				session.setAttribute(PARTIAL_HEAD_ATT, partial);
				session.setAttribute(DECODER_STATE_ATT, DecoderState.HEAD);

				// logger.D("position -> " + msg.position() + " , limit -> " +
				// msg.limit() + " , cap -> " + msg.capacity());
				break;
			} else {
				if ("/favicon.ico".equals(rq.getRequestPath())) {
					session.close(true);
				} else {
					out.write(rq);
				}

				// is it a request with some body content ?
				String contentLen = rq.getHeader("content-length");

				if (contentLen != null) {
					logger.D("found content len -> " + contentLen);
					session.setAttribute(BODY_REMAINING_BYTES, Integer.valueOf(contentLen));
					session.setAttribute(DECODER_STATE_ATT, DecoderState.BODY);
					// fallthrough, process body immediately
				} else {
					logger.D("request without content");
					session.setAttribute(DECODER_STATE_ATT, DecoderState.NEW);
					// out.write(new HttpEndOfContent());
					break;
				}
			}

		case BODY:
			logger.D("decoding BODY: " + msg.remaining() + " bytes");
			final int chunkSize = msg.remaining();

			// logger.D("position -> " + msg.position() + " , limit -> " +
			// msg.limit() + " , cap -> " + msg.capacity());

			// send the chunk of body
			if (chunkSize != 0) {
				final IoBuffer wb = IoBuffer.allocate(msg.remaining());
				wb.put(msg);
				wb.flip();
				out.write(wb);
			}
			msg.position(msg.limit());
			// do we have reach end of body ?
			int remaining = (Integer) session.getAttribute(BODY_REMAINING_BYTES);
			remaining -= chunkSize;

			if (remaining <= 0) {
				logger.D("end of HTTP body");
				session.setAttribute(DECODER_STATE_ATT, DecoderState.NEW);
				session.removeAttribute(BODY_REMAINING_BYTES);
				out.write(new HttpEndOfContent());
			} else {
				session.setAttribute(BODY_REMAINING_BYTES, Integer.valueOf(remaining));
			}

			break;

		default:
			throw new HttpException(HttpStatus.CLIENT_ERROR_BAD_REQUEST, "Unknonwn decoder state : " + state);
		}
	}

	public void finishDecode(final IoSession session, final ProtocolDecoderOutput out) throws Exception {
	}

	public void dispose(final IoSession session) throws Exception {
	}

	private HttpRequestImpl parseHttpRequestHead(final IoBuffer buffer) {
		// Java 6 >> String raw = new String(buffer.array(), 0, buffer.limit(),
		// Charset.forName("UTF-8"));
		// final String raw = new String(buffer.array(), buffer.position(),
		// buffer.limit(), Charset.forName("UTF-8"));
		try {
			final String raw = buffer.getString(decoder);
			logger.D("raw -> " + raw);
			final String[] headersAndBody = RAW_VALUE_PATTERN.split(raw, -1);
			// logger.D("position -> " + buffer.position() + " , limit -> " +
			// buffer.limit() + " , cap -> " + buffer.capacity());
			if (headersAndBody.length <= 1) {
				// we didn't receive the full HTTP head
				return null;
			}

			String[] headerFields = HEADERS_BODY_PATTERN.split(headersAndBody[0]);
			headerFields = ArrayUtil.dropFromEndWhile(headerFields, "");

			final String requestLine = headerFields[0];
			final Map<String, String> generalHeaders = new HashMap<String, String>();

			for (int i = 1; i < headerFields.length; i++) {
				final String[] header = HEADER_VALUE_PATTERN.split(headerFields[i]);
				generalHeaders.put(header[0].toLowerCase(), header[1].trim());
			}

			final String[] elements = REQUEST_LINE_PATTERN.split(requestLine);
			final HttpMethod method = HttpMethod.valueOf(elements[0]);
			final HttpVersion version = HttpVersion.fromString(elements[2]);
			final String[] pathFrags = QUERY_STRING_PATTERN.split(elements[1]);
			final String requestedPath = pathFrags[0];
			final String queryString = pathFrags.length == 2 ? pathFrags[1] : "";

			// we put the buffer position where we found the beginning of the
			// HTTP
			// body
			// buffer.position(headersAndBody[0].length() + 4);
			// logger.D("position -> " + buffer.position() + " , limit -> " +
			// buffer.limit() + " , cap -> " + buffer.capacity());

			return new HttpRequestImpl(version, method, requestedPath, queryString, generalHeaders);
		} catch (CharacterCodingException e) {

		}
		return null;
	}
}
