package com.wzg.server.mina.tcp.filter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

import com.wzg.utils.MyLog;

public class FilterSample extends IoFilterAdapter {

	protected static final MyLog log = MyLog.getLogger(FilterSample.class);

	@Override
	public void filterClose(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.I("filterClose before");
		
		super.filterClose(nextFilter, session);

		log.I("filterClose after");
	}

	@Override
	public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		// TODO Auto-generated method stub
		log.I("filterWrite");
		super.filterWrite(nextFilter, session, writeRequest);
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		log.I("destroy");
		super.destroy();
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		log.I("init");
		super.init();
	}

	@Override
	public void onPreAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
		// TODO Auto-generated method stub
		log.I("onPreAdd");
		super.onPreAdd(parent, name, nextFilter);
	}

	@Override
	public void onPostAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
		// TODO Auto-generated method stub
		log.I("onPostAdd");
		super.onPostAdd(parent, name, nextFilter);
	}

	@Override
	public void onPreRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
		// TODO Auto-generated method stub
		log.I("onPreRemove");
		super.onPreRemove(parent, name, nextFilter);
	}

	@Override
	public void onPostRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
		// TODO Auto-generated method stub
		log.I("onPostRemove");
		super.onPostRemove(parent, name, nextFilter);
	}

	@Override
	public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.I("sessionCreated");
		super.sessionCreated(nextFilter, session);
	}

	@Override
	public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.I("sessionOpened");
		super.sessionOpened(nextFilter, session);
	}

	@Override
	public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.I("sessionClosed");
		super.sessionClosed(nextFilter, session);
	}

	@Override
	public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		log.I("sessionIdle");
		super.sessionIdle(nextFilter, session, status);
	}

	@Override
	public void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		log.I("exceptionCaught");
		super.exceptionCaught(nextFilter, session, cause);
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		log.I("messageReceived");
		super.messageReceived(nextFilter, session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		// TODO Auto-generated method stub
		log.I("messageSent");
		super.messageSent(nextFilter, session, writeRequest);
	}

	@Override
	public void inputClosed(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.I("inputClosed");
		super.inputClosed(nextFilter, session);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
