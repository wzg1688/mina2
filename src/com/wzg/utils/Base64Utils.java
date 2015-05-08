package com.wzg.utils;

import sun.misc.BASE64Decoder;

public class Base64Utils {

	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String a = Base64Utils.getBASE64("中文");
		System.out.println(a);
		System.out.println(Base64Utils.getFromBASE64(a));
	}

}
