package com.lucio.library.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 主要用于常用的和时间相关的方法
 * 
 * @author zhaoyi
 * @since 2015/12/30 15:11:30
 * 
 */
public class TimeFormatUtil {
	/**
	 * 时间格式转换
	 * 
	 * @param time
	 * @param oldpattern
	 * @param newpattern
	 * @return
	 */
	public static String formatTimeByPattern(String time, String oldpattern,
			String newpattern) {
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat(oldpattern);
		try {
			Date d = sdf.parse(time);
			sdf = new SimpleDateFormat(newpattern);
			result = sdf.format(d);
		} catch (ParseException e) {
			result = null;
		}
		return result;
	}

	/**
	 * 时间戳转成string显示（单位秒）
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String formatTimeToString(Long time, String pattern) {
		Date d = new Date();
		d.setTime(time * 1000);
		SimpleDateFormat s = new SimpleDateFormat(pattern);
		String result = s.format(d);
		return result;
	}

	/**
	 * String类型时间转成时间戳(单位是毫秒)
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static Long formatTimeToLong(String time, String pattern) {
		Long result = 0L;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date d = sdf.parse(time);
			result = d.getTime();
		} catch (ParseException e) {

		}
		return result;
	}

	/**
	 * 时间比较：比较两个日期型时间
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareTime(String date1, String date2) {
		long time1 = formatTimeToLong(date1, "yyyyMMdd");
		long time2 = 0;
		if (date2 != null) {
			time2 = formatTimeToLong(date2, "yyyyMMdd");
		} else {
			Date nowDate = new Date();
			time2 = nowDate.getTime();
		}
		return time1 > time2;
	}

	/**
	 * 毫秒转为时间格式00:00:00
	 */
	public static String ms2Format(long ms) {
		DecimalFormat timerFormat = new DecimalFormat("#00");
		long hour = ms / 1000 / 60 / 60;
		long min = ms / 1000 / 60 - hour * 60;
		long sec = ms / 1000 - min * 60 - hour * 60 * 60;
		return timerFormat.format(hour) + ":" + timerFormat.format(min) + ":"
				+ timerFormat.format(sec);
	}

	/**
	 * 时间格式00:00:00转为毫秒
	 */
	public static long format2Ms(String time) {
		String[] arrayTimeStr = time.split(":");
		int hour = Integer.parseInt(arrayTimeStr[0]);
		int min = Integer.parseInt(arrayTimeStr[1]);
		int sec = Integer.parseInt(arrayTimeStr[2]);
		return hour * 60 * 60 * 1000 + min * 60 * 1000 + sec * 1000;
	}

	// 时间戳转换为日期
	public static String timestamp2Date(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(timestamp));
		return date;
	}

	// 日期转换为时间戳
	public static String date2Timestamp(String string) {
		String timestamp = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(string);
			long l = date.getTime();
			timestamp = String.valueOf(l).substring(0, 10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	// 获取当前时间
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}
}
