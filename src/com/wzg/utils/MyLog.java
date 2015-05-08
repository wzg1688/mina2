package com.wzg.utils;

import org.apache.log4j.Logger;

public final class MyLog {

	private Logger logger;
	private String tag;

	private MyLog(Class<?> clazz) {
		tag = clazz.getName();
		logger = Logger.getLogger(clazz);
	}

	private MyLog(Class<?> clazz, String name) {
		tag = clazz.getName();
		logger = Logger.getLogger(name);
	}

	private MyLog(String _tag, String name) {
		this.tag = _tag;
		logger = Logger.getLogger(name);
	}

	public static MyLog getLogger(Class<?> clazz) {
		return new MyLog(clazz);
	}

	public static MyLog getChongZhiLogger(String _tag) {
		return new MyLog(_tag, "chongzhi");
	}

	public static MyLog getPressureLogger(Class<?> clazz) {
		return new MyLog(clazz, "pressure");
	}

	public void E(String msg) {
		logger.error("{" + tag + "} -- " + msg);
	}

	public void E(String msg, Throwable e) {
		logger.error("{" + tag + "} -- " + msg, e);
	}

	public void D(String msg) {
		logger.debug("{" + tag + "} -- " + msg);
	}

	public void I(String msg) {
		logger.info("{" + tag + "} -- " + msg);
	}

	public void W(String msg) {
		logger.warn("{" + tag + "} -- " + msg);
	}

}
