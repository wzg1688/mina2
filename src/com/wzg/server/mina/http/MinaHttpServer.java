package com.wzg.server.mina.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.firewall.ConnectionThrottleFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.wzg.server.mina.MinaServerAdapter;
import com.wzg.utils.ConstValue;
import com.wzg.utils.MyLog;

public class MinaHttpServer extends MinaServerAdapter {

	protected static final MyLog logger = MyLog.getLogger(MinaHttpServer.class);

	/**
	 * 停止监听HTTP服务
	 */
	public void stopServer() {
		try {
			acceptor.unbind();
			acceptor.dispose();
			System.out.println("Server is stoped.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startServer() {
		if (isStoped()) {
			try {

				logger.I("mina http Server 服务器开始启动");

				SocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
				acceptor.getSessionConfig().setReadBufferSize(100 * 1024);
				acceptor.getSessionConfig().setTcpNoDelay(true);
				acceptor.getSessionConfig().setBothIdleTime(ConstValue.Session_life);
				acceptor.getFilterChain().addFirst("conn", new ConnectionThrottleFilter(1000));
				acceptor.getFilterChain().addLast("thread_pool", new ExecutorFilter(Executors.newCachedThreadPool()));
				acceptor.getFilterChain().addLast("httpServerCodec", new MyHttpServerCodec());

				ioHandler = new HttpServerHandler();
				acceptor.setHandler(ioHandler);

				acceptor.bind(new InetSocketAddress(ConstValue.port + 100));
			} catch (IOException e) {
				logger.E("mina http 服务器启动报错", e);
			}

		} else {
			logger.I("Mina TCP Server is allready started...");
		}

	}

	@Override
	public Map<Long, IoSession> getSessions() {
		return null;
	}

	@Override
	public int getSessionCount() {
		return 0;
	}

	@Override
	public void broadcast(Object message) {
		throw new UnsupportedOperationException();
	}
}
