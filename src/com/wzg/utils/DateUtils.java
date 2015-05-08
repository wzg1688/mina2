package com.wzg.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 把Date类型转换成String类型
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return format.format(date);
	}

	public static String newTestTime() {
		Date now = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(now);
	}

	/**
	 * 把String类型转换成Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date) {
		Date d = null;
		try {
			d = format.parse(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 天数加一
	 * 
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public static String dateFamte(String dateTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dateTime);
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.DAY_OF_MONTH, 1);// 天数加一
		return format.format(calendar.getTime());
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String secondToDateString(long time) {
		long minute = 60;
		long hour = 3600;
		long day = 3600 * 24;

		long dayCount = 0;
		long hourCount = 0;
		long minuteCount = 0;

		if (time >= day) {
			dayCount = time / day;
			time -= day * dayCount;
		}

		if (time >= hour) {
			hourCount = time / hour;
			time -= hour * hourCount;
		}

		if (time >= minute) {
			minuteCount = time / minute;
			time -= minute * minuteCount;
		}

		return dayCount + " 天 ，" + hourCount + " 小时 ，" + minuteCount + " 分钟 , " + time + " 秒";
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String timeToDateString(long time) {
		time /= 1000;

		long minute = 60;
		long hour = 3600;
		long day = 3600 * 24;

		long dayCount = 0;
		long hourCount = 0;
		long minuteCount = 0;

		if (time >= day) {
			dayCount = time / day;
			time -= day * dayCount;
		}

		if (time >= hour) {
			hourCount = time / hour;
			time -= hour * hourCount;
		}

		if (time >= minute) {
			minuteCount = time / minute;
			time -= minute * minuteCount;
		}

		return dayCount + " 天 ，" + hourCount + " 小时 ，" + minuteCount + " 分钟 , " + time + " 秒";
	}

	public static class YMD {
		public int year;
		public int month;
		public int day;

	}

	public static YMD getDateNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		YMD ymd = new YMD();
		ymd.year = year;
		ymd.month = month;
		ymd.day = calendar.get(Calendar.DAY_OF_MONTH);

		return ymd;
	}

	public static YMD getDateForGive(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		YMD ymd = new YMD();
		ymd.year = year;
		ymd.month = month;
		ymd.day = calendar.get(Calendar.DAY_OF_MONTH);

		return ymd;
	}

	public static YMD getDateMax() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		YMD ymd = new YMD();
		ymd.year = year;
		ymd.month = month;
		ymd.day = getDayCount(year, month);

		return ymd;
	}

	public static int getDayCount(int year, int month) {
		int max_day = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			max_day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			max_day = 30;
			break;

		default:
			boolean isRun = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
			if (isRun) {
				max_day = 29;
			} else {
				max_day = 28;
			}
			break;
		}

		return max_day;
	}

	public static void main(String[] args) {
		System.out.println(secondToDateString(36601));
	}

}
