package com.wzg.server.mina.http;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyHttpServerCodec extends ProtocolCodecFilter {

	/** Key for decoder current state */
	private static final String DECODER_STATE_ATT = "http.ds";

	/** Key for the partial HTTP requests head */
	private static final String PARTIAL_HEAD_ATT = "http.ph";

	private static ProtocolEncoder encoder = new MyHttpServerEncoder();
	private static ProtocolDecoder decoder = new MyHttpServerDecoder();

	public MyHttpServerCodec() {
		super(encoder, decoder);
	}

	@Override
	public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession session) throws Exception {
		super.sessionClosed(nextFilter, session);
		session.removeAttribute(DECODER_STATE_ATT);
		session.removeAttribute(PARTIAL_HEAD_ATT);
	}
}