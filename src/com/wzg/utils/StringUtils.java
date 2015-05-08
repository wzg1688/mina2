package com.wzg.utils;

public class StringUtils {
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 去除字符串前后的空格
	 * 
	 * @param s
	 * @return
	 */
	public static String trim(String s) {
		if (null == s) {
			return "";
		} else {
			return s.trim();
		}
	}
}
