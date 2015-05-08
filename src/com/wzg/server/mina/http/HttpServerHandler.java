package com.wzg.server.mina.http;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.http.api.HttpResponse;

import com.wzg.bean.Banji;

public class HttpServerHandler extends IoHandlerAdapter {

	@Override
	public void sessionOpened(IoSession session) {
		// set idle time to 60 seconds
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Http session Closed()");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageReceived(final IoSession session, Object message) {
		// HttpRequest request = (HttpRequest) message;

		Banji banji = new Banji(1, 1, "1234", "w421", "214", "21243", "214", "2314", "ab.png", 1234, 214, "1班");
		HttpResponse httpResponse = new MyHttpResponseImpl(banji);

		session.write(httpResponse);
		// .addListener(new IoFutureListener<IoFuture>() {
		// public void operationComplete(IoFuture arg0) {
		// session.close(true);
		// };
		// });
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		cause.printStackTrace();
		session.close(true);
	}
}
