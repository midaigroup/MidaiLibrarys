package com.lucio.library.util;

import android.util.Log;

/**
 * Log 日志工具类 通过此类可以管理所有Log日志
 * 
 * @author zhaoyi
 * 
 */
public class LogUtil {

	/**
	 * 私有化构造函数，不允许直接创建对象
	 */
	private LogUtil() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 日志打印的标记
	 */
	private static boolean flag = true;

	/**
	 * 设置标记为真时，Log打印
	 */
	public static void show() {
		flag = true;
	}

	/**
	 * 设置标记为假时，Log不打印
	 */
	public static void hide() {
		flag = false;
	}

	/**
	 * 打印verbose级别日志
	 * 
	 * @param tag
	 * @param content
	 */
	public static void v(String tag, String content) {
		if (flag) {
			Log.v(tag, content);
		}
	}

	/**
	 * 打印debug级别日志
	 * 
	 * @param tag
	 * @param content
	 */
	public static void d(String tag, String content) {
		if (flag) {
			Log.d(tag, content);
		}
	}

	/**
	 * 打印info级别日志
	 * 
	 * @param tag
	 * @param content
	 */
	public static void i(String tag, String content) {
		if (flag) {
			Log.i(tag, content);
		}
	}

	/**
	 * 打印warn级别日志
	 * 
	 * @param tag
	 * @param content
	 */
	public static void w(String tag, String content) {
		if (flag) {
			Log.w(tag, content);
		}
	}

	/**
	 * 打印error级别日志
	 * 
	 * @param tag
	 * @param content
	 */
	public static void e(String tag, String content) {
		if (flag) {
			Log.e(tag, content);
		}
	}

}
