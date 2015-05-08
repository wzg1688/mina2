package com.wzg.server.mina.tcp.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.wzg.utils.MyLog;

public class MyProtocolDecode extends CumulativeProtocolDecoder {

	private static final MyLog logger = MyLog.getLogger(MyProtocolDecode.class);

	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.prefixedDataAvailable(4)) {

			int i1 = in.getInt();

			int function_id = in.getInt();

			byte[] buffer = new byte[i1 - 4];
			in.get(buffer);
			String item = new String(buffer);

			ProtocolData data = new ProtocolData(function_id, item);

			out.write(data);

			return true;

		}

		return false;
	}
}