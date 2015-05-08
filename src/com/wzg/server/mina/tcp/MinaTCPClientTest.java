package com.wzg.server.mina.tcp;

import java.net.InetSocketAddress;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.alibaba.fastjson.JSON;
import com.wzg.bean.Banji;
import com.wzg.utils.ConstValue;
import com.wzg.utils.MyLog;

public class MinaTCPClientTest extends IoHandlerAdapter {

	private static final MyLog logger = MyLog.getLogger(MinaTCPClientTest.class);
	private static final AtomicInteger ports = new AtomicInteger(50000);
	/** The connector */
	public IoConnector connector;

	/** The session */
	public IoSession session;

	/**
	 * Create the UdpClient's instance
	 */
	public MinaTCPClientTest() {
		connector = new NioSocketConnector();

//		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		connector.setHandler(this);
		ConnectFuture connFuture = connector.connect(new InetSocketAddress("localhost", ConstValue.port));

		connFuture.awaitUninterruptibly();

		session = connFuture.getSession();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
		session.close(true);
		logger.E("错误", cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.I(session.getId() + " messageReceived -> " + message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.I("message sented msg -> " + message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.getService().dispose(true);
		connector.dispose();
		logger.I("sessionClosed");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.I("sessionCreated");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.I("sessionIdle");
		session.close(true);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.I("sessionOpened");
	}

	public static void main(String[] args) throws CharacterCodingException {

		Banji banji = new Banji(0, 0, "abdfa", "fdsaf", "fda", "wrwqw", "rqwefa", "wqerq", "dsafs.png", 213, 214, "fdfadfa");
		String json = JSON.toJSONString(banji);

		MinaTCPClientTest client = new MinaTCPClientTest();
		IoBuffer ioBuffer = IoBuffer.allocate(10).setAutoExpand(true).setAutoShrink(true);
		ioBuffer.putInt(json.getBytes().length + 4);
		ioBuffer.putInt(1);
		ioBuffer.putString(json, Charset.forName("UTF-8").newEncoder());
		// ioBuffer.put("1234567890".getBytes());
		// ioBuffer.put("0987654321".getBytes());
		// ioBuffer.put("1234567890".getBytes());
		ioBuffer.flip();
		client.session.write(ioBuffer);
	}

	// /**
	// * The main method : instanciates a client, and send N messages. We sleep
	// * between each K messages sent, to avoid the server saturation.
	// *
	// * @param args
	// * @throws Exception
	// */
	// public static void main(String[] args) throws Exception {
	//
	// int ThreadCount = 1;
	// final int SendCount = 1;
	//
	// System.err.println("客户端 开始创建");
	// for (int i = 0; i < ThreadCount; i++) {
	//
	// Thread t = new Thread() {
	// int count = 0;
	//
	// public void run() {
	//
	// int time = new Random(System.currentTimeMillis()).nextInt(1000) + 1000;
	//
	// MinaTCPClientTest client = new MinaTCPClientTest();
	// while (client.session.isConnected()) {
	// IoBuffer ioBuffer =
	// IoBuffer.allocate(10).setAutoExpand(true).setAutoShrink(true);
	// ioBuffer.putInt(34);
	// ioBuffer.putInt(1);
	// ioBuffer.put("1234567890".getBytes());
	// ioBuffer.put("0987654321".getBytes());
	// ioBuffer.put("1234567890".getBytes());
	// ioBuffer.flip();
	// client.session.write(ioBuffer);
	// count++;
	// if (count >= SendCount) {
	// client.session.close(true);
	// client.session.getService().dispose(true);
	// client.connector.dispose();
	// }
	// try {
	// Thread.sleep(time);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// };
	// };
	//
	// t.start();
	//
	// }
	// }
}
