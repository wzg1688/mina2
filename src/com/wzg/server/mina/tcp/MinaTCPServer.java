package com.wzg.server.mina.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.wzg.server.mina.MinaServerAdapter;
import com.wzg.server.mina.tcp.handler.TestHandler;
import com.wzg.server.mina.tcp.protocol.MyProtocolFactory;
import com.wzg.utils.ConstValue;
import com.wzg.utils.MyLog;

/**
 * 
 * @author mac
 *
 */
public class MinaTCPServer extends MinaServerAdapter {

	private static final MyLog logger = MyLog.getLogger(MinaTCPServer.class);

	public MinaTCPServer() {
	}

	@Override
	public void startServer() {
		logger.I("Mina TCP Server 服务器开始启动");
		if (isStoped()) {
			try {

				acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);

				acceptor.getSessionConfig().setReadBufferSize(2048);
				acceptor.getSessionConfig().setTcpNoDelay(true);
				acceptor.getSessionConfig().setBothIdleTime(ConstValue.Session_life);
				
				acceptor.getFilterChain().addLast("thread_pool", new ExecutorFilter(Executors.newCachedThreadPool()));
				acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyProtocolFactory()));

				ioHandler = new TestHandler(acceptor);
				acceptor.setHandler(ioHandler);

				acceptor.bind(new InetSocketAddress(ConstValue.port));

			} catch (IOException e) {
				logger.E("Mina TCP Server 启动异常", e);
			}
		} else {
			logger.I("Mina TCP Server is allready started...");
		}
	}

	@Override
	public void stopServer() {
		logger.I("Mina TCP Server stop ...");
		acceptor.unbind();
		acceptor.dispose();
	}

}
