package com.wzg.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	// /**
	// * 从一个JSON数组得到一个java对象数组，形如：
	// * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" :
	// nameValue}, ...]
	// * @param object
	// * @param clazz
	// * @return
	// */
	// public static Object[] getDTOArray(String jsonString, Class clazz){
	// JSONArray array = JSONArray.fromObject(jsonString);
	// Object[] obj = new Object[array.size()];
	// for(int i = 0; i < array.size(); i++){
	// JSONObject jsonObject = array.getJSONObject(i);
	// obj[i] = JSONObject.toBean(jsonObject, clazz);
	// }
	// return obj;
	// }
}
