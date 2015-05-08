package com.wzg.server.mina.http;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.http.HttpServerCodec;
import org.apache.mina.http.api.HttpEndOfContent;
import org.apache.mina.http.api.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpServerEncoder implements ProtocolEncoder {
	private static final Logger LOG = LoggerFactory.getLogger(HttpServerCodec.class);
	private static final CharsetEncoder ENCODER = Charset.forName("UTF-8").newEncoder();

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

		LOG.debug("encode {}", message.getClass().getCanonicalName());
		if (message instanceof MyHttpResponseImpl) {
			MyHttpResponseImpl responseImpl = (MyHttpResponseImpl) message;
			LOG.debug("MyHttpResponseImpl");
			StringBuilder sb = new StringBuilder(responseImpl.getStatus().line());

			for (Map.Entry<String, String> header : responseImpl.getHeaders().entrySet()) {
				sb.append(header.getKey());
				sb.append(": ").append(' ');
				sb.append(header.getValue());
				sb.append("\r\n");
			}
			sb.append("\r\n");
			sb.append(responseImpl.body);
			sb.append("\r\n");
			sb.append("\r\n");
			IoBuffer buf = IoBuffer.allocate(sb.length()).setAutoExpand(true);
			buf.putString(sb.toString(), ENCODER);
			buf.flip();
			out.write(buf);
		} else if (message instanceof HttpResponse) {
			LOG.debug("HttpResponse");
			HttpResponse msg = (HttpResponse) message;
			StringBuilder sb = new StringBuilder(msg.getStatus().line());

			for (Map.Entry<String, String> header : msg.getHeaders().entrySet()) {
				sb.append(header.getKey());
				sb.append(": ");
				sb.append(header.getValue());
				sb.append("\r\n");
			}
			sb.append("\r\n");
			IoBuffer buf = IoBuffer.allocate(sb.length()).setAutoExpand(true);
			buf.putString(sb.toString(), ENCODER);
			buf.flip();
			out.write(buf);
		} else if (message instanceof ByteBuffer) {
			LOG.debug("Body {}", message);
			out.write(message);
		} else if (message instanceof HttpEndOfContent) {
			LOG.debug("End of Content");
			// end of HTTP content
			// keep alive ?
		}

	}

	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	}
}
