package com.wzg.server.mina.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.http.api.DefaultHttpResponse;
import org.apache.mina.http.api.HttpStatus;
import org.apache.mina.http.api.HttpVersion;

import com.alibaba.fastjson.JSON;

public class MyHttpResponseImpl extends DefaultHttpResponse {

	public static final Map<String, String> headers = new HashMap<String, String>();
	static {
		headers.put("Server", "HttpServer " + "Mina 2.0");
		headers.put("Cache-Control", "private");
		headers.put("Content-Type", "text/html; charset=UTF-8");
//		headers.put("Cookies", "JSESSIONID=1324123412314234123");
		headers.put("Connection", "keep-alive");
		headers.put("Keep-Alive", "2");
		headers.put("Date", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(System.currentTimeMillis())));
		headers.put("Last-Modified", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(System.currentTimeMillis())));
	}
	public String body;

	public MyHttpResponseImpl(HttpVersion version, HttpStatus status, Map<String, String> headers, String t_body) {
		super(version, status, headers);
		body = t_body;
		if (body != null) {
			int len = body.getBytes().length;
			headers.put("Content-Length", "" + len);
		} else if (body == null) {
			headers.put("Content-Length", "0");
		}
	}

	public MyHttpResponseImpl(String t_body) {
		this(HttpVersion.HTTP_1_1, HttpStatus.SUCCESS_OK, headers, t_body);
	}

	public MyHttpResponseImpl(Object obj) {
		this(HttpVersion.HTTP_1_1, HttpStatus.SUCCESS_OK, headers, JSON.toJSONString(obj));
	}

	public MyHttpResponseImpl() {
		this(HttpVersion.HTTP_1_1, HttpStatus.SUCCESS_OK, headers, "");
	}

}
