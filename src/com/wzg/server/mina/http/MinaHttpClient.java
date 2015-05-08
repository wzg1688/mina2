package com.wzg.server.mina.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wzg.utils.ConstValue;

public class MinaHttpClient {

	public static void main(String[] args) throws IOException {
		String url = "http://127.0.0.1:" + (ConstValue.port + 100) + "/?a=a&b=b";
		URL url2 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url2.openConnection();

		connection.connect();

		InputStream is = connection.getInputStream();

		int len = is.available();
		byte[] buffer = new byte[len];

		is.read(buffer);

		is.close();
		is = null;

		System.out.println(new String(buffer));
		
		connection.disconnect();
		connection = null;
		
	}
}
