package com.wzg.server.mina.tcp.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyProtocolFactory implements ProtocolCodecFactory {

	protected MyProtocolDecode decode;
	protected MyProtocolEncode encode;

	public MyProtocolFactory() {
		// TODO Auto-generated constructor stub
		decode = new MyProtocolDecode();
		encode = new MyProtocolEncode();
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return encode;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return decode;
	}

}
