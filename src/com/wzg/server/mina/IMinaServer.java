package com.wzg.server.mina;

import java.util.Map;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketAcceptor;

import com.wzg.server.IHttpServer;
import com.wzg.server.ITCPServer;

public interface IMinaServer extends ITCPServer, IHttpServer {

	public SocketAcceptor getAcceptor();

	public IoHandler getIoHandler();

	public Map<Long, IoSession> getSessions();

}
