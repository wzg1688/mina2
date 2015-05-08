package com.wzg.server.mina.tcp.handler;

import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketAcceptor;

import com.wzg.server.mina.tcp.protocol.ProtocolData;
import com.wzg.utils.ConstValue;
import com.wzg.utils.MyLog;

public class TestHandler extends IoHandlerAdapter {

	private static final MyLog logger = MyLog.getLogger(TestHandler.class);
	protected SocketAcceptor acceptor;

	public TestHandler() {
		super();
	}

	public TestHandler(SocketAcceptor acceptor) {
		super();
		this.acceptor = acceptor;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		ProtocolData data = (ProtocolData) message;
		logger.I("messageReceived -> " + data.getJsonString());

//		session.write(data.getJsonString());
//		.addListener(new IoFutureListener<IoFuture>() {
//			public void operationComplete(IoFuture future) {
//				logger.I("发送成功");
//			};
//		});
		// session.getService().broadcast(data.getJsonString());
		 acceptor.broadcast(data.getJsonString());
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		logger.I("sessionCreated ");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		int count = acceptor.getManagedSessionCount();

		logger.I(" session Closed , 连接数 -> " + count);
		if (count <= 0) {
		}
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		session.getConfig().setBothIdleTime(ConstValue.Session_life);
		logger.I(" session Opened ,连接数 -> " + acceptor.getManagedSessionCount());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		logger.E(" 有错误了 " + cause.getLocalizedMessage());
		session.close(true);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		// logger.D(" sessionIdle ");
		session.close(true);
	}
}
