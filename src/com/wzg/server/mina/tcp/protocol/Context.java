package com.wzg.server.mina.tcp.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public class Context {
	private IoBuffer buf;
	private int msgLength = 0;
	private int overflowPosition = 0;

	/**
		 * 
		 * 
		 */
	public Context() {
		buf = IoBuffer.allocate(80).setAutoExpand(true);
	}

	/**
	 * 
	 * 
	 * @return IoBuffer
	 */
	public IoBuffer getBuffer() {
		return buf;
	}

	/**
	 * 
	 * 
	 * @return overflowPosition
	 */
	public int getOverflowPosition() {
		return overflowPosition;
	}

	/**
	 * 
	 *
	 * @return matchCount
	 */
	public int getMsgLength() {
		return msgLength;
	}

	/**
	 * 
	 * 
	 * @param matchCount
	 *            报文长度
	 */
	public void setMsgLength(int msgLength) {
		this.msgLength = msgLength;
	}

	/**
		 * 
		 * 
		 */
	public void reset() {
		this.buf.clear();
		this.overflowPosition = 0;
		this.msgLength = 0;
	}

	/**
	 * 
	 * @param in
	 *            输入流
	 */
	public void append(IoBuffer in) {
		getBuffer().put(in);

	}

}
