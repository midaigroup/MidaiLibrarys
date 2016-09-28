package com.lucio.library.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhaoyi
 * @since 2015/12/30 15:11:30
 * ValidateUtils 主要用于常用的验证方法
 *
 */
public class ValidateUtil {
	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,18}$";

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^(13[0-9]|15[\\d]|17[\\d]|18[0-9]|14[\\d])[0-9]{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	//public static final String REGEX_EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	// public static final String REGEX_ID_CARD =
	// "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
	public static final String REGEX_ID_CARD = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";

	/**
	 * 正则表达式：验证URL
	 */
	//public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	public static final String REGEX_URL = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
			+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
			+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
			+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
			+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
			+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
			+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
			+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 校验用户名
	 * 
	 * @param username
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUsername(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 校验汉字
	 * 
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 校验身份证
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}


	/**
	 * 校验IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	private static boolean isMatch(String regex, String orginal) {
		if (orginal == null || orginal.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(orginal);
		return isNum.matches();
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 字符里是全为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumberic(String str) {
		return str.matches("^\\d+$");
	}

	/**
	 * 验证是否是正整数
	 * */
	public static boolean isPositiveInteger(String orginal) {
		return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
	}

	/**
	 * 验证是否是负整数
	 * */
	public static boolean isNegativeInteger(String orginal) {
		return isMatch("^-[1-9]\\d*", orginal);
	}

	/**
	 * 验证是否是整数
	 * */
	public static boolean isWholeNumber(String orginal) {
		return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal)
				|| isNegativeInteger(orginal);
	}

	/**
	 * 验证是否是正小数
	 * */
	public static boolean isPositiveDecimal(String orginal) {
		return isMatch("\\d+\\.\\d+", orginal);
	}

	/**
	 * 验证是否是负小数
	 * */
	public static boolean isNegativeDecimal(String orginal) {
		return isMatch("-\\d+\\.\\d+", orginal);
	}

	/**
	 * 验证是否是小数
	 * 
	 */
	public static boolean isDecimal(String orginal) {
		return isMatch("-?\\d+\\.\\d+", orginal);
	}

	/**
	 * 验证是否是数字
	 * */
	public static boolean isRealNumber(String orginal) {
		return isWholeNumber(orginal) || isDecimal(orginal);
	}

	/**
	 * 字符里是全为字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLetteric(String str) {
		return str.matches("^[a-zA-Z]+$");
	}

	/**
	 * 是否是英文名字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEnglishName(String str) {
		return isLetteric(str);
	}

	/**
	 * 是否是中文名字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChineseName(String str) {
		return str.matches("^[\u4e00-\u9fa5]+$");
	}

	/**
	 * 是否符合密码要求
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAvailablePwd(String str) {
//		return str.matches("^[0-9a-zA-Z]+$") && betweenLen(str, 6, 18);
		return betweenLen(str, 6, 18);
	}

	/**
	 * 姓名是否合法(中文或者英文名字，并且长度小于等于10)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAvailableName(String str) {
		boolean result = false;
		if (isEnglishName(str)) {
			result = str.length() <= 10;
		} else if (isChineseName(str)) {
			result = getWordCountCode(str, "GBK") <= 10;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 介于多少个字符之间,闭区间
	 * 
	 * @param str
	 * @param minCharLen
	 * @param maxCharLen
	 * @return
	 */
	public static boolean betweenLen(String str, int minCharLen, int maxCharLen) {
		return str.length() >= minCharLen && str.length() <= maxCharLen;
	}

	/**
	 * 是否包含特殊字符、表情等
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmoticons(String str) {
		return str.matches("^[a-zA-Z0-9\\p{Punct}_\u4e00-\u9fa5 \\n]+$");
	}

	/**
	 * 按特定的编码格式获取长度
	 * 
	 * @param str
	 * @param code
	 * @return
	 */
	public static int getWordCountCode(String str, String code) {
		int length = 0;
		try {
			length = str.getBytes(code).length;
		} catch (UnsupportedEncodingException e) {
			length = 0;
		}
		return length;
	}

	/**
	 * 判断URL是否合法
	 * 
	 * @param strURL
	 * @return
	 */
	public static boolean isURL(String strURL) {
		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(strURL);
		return m.matches();
	}

}
