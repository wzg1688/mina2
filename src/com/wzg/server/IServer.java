package com.wzg.server;

public interface IServer {

	public void startServer();

	public void stopServer();

	public boolean isRunning();

	public boolean isStoped();

	public int getSessionCount();

}
