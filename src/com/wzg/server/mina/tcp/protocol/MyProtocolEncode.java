package com.wzg.server.mina.tcp.protocol;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 编码器
 * 
 * @author mac
 *
 */
public class MyProtocolEncode extends ProtocolEncoderAdapter {

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		String m = (String) message;
		System.out.println(session.getId() + " message -> " + m);
		IoBuffer ioBuffer = IoBuffer.allocate(10).setAutoExpand(true);
		ioBuffer.setAutoShrink(true);
		ioBuffer.putString(m, Charset.forName("UTF-8").newEncoder());
		ioBuffer.flip();
		out.write(ioBuffer);
	}

}
