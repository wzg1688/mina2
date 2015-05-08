package com.wzg.server.mina;

import com.wzg.server.mina.http.MinaHttpServer;
import com.wzg.server.mina.tcp.MinaTCPServer;

public class MinaServerManager {

	protected MinaTCPServer tcpServer;
	protected MinaHttpServer httpServer;

	protected MinaServerManager() {
		tcpServer = new MinaTCPServer();
		httpServer = new MinaHttpServer();
	}

	public void startMinaTCPServer() {
		tcpServer.startServer();
	}

	public void startMinaHTTPServer() {
		httpServer.startServer();
	}

	public void stopMinaTCPServer() {
		tcpServer.stopServer();
	}

	public void stopMinaHTTPServer() {
		httpServer.stopServer();
	}

	public void broadcastMessage2TCPClient(Object message) {
		tcpServer.broadcast(message);
	}

	public void startMinaServer() {
		tcpServer.startServer();
		httpServer.startServer();
	}

	public void stopMinaServer() {
		tcpServer.stopServer();
		httpServer.startServer();
	}

	public static void main(String[] args) {
		MinaServerManager minaManager = new MinaServerManager();
		minaManager.startMinaServer();
	}
}
