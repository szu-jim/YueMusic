package com.yuemusic.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ParamentCheckUtils {

	/**
	 * 判断String 变量是否为空 如果为空返回true
	 * 
	 * @param strs
	 *            strs: String 或者 String 数组
	 * @return
	 */
	public static boolean isEmpty(String... strs) {
		if (strs == null || strs.length == 0)
			return true;

		for (String string : strs) {
			if (StringUtils.isBlank(string)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断字符串长度是否小于@length
	 * 
	 * @param str
	 *            字符串
	 * @param length
	 *            最大长度
	 * @return true/false
	 */
	public static boolean isValidateString(String str, Integer length) {
		if (isEmpty(str))
			return false;

		if (length == null || length < 1) {
			return false;
		}

		return str.length() <= length;
	}

	public static boolean isObjectEmpty(Object... objects) {
		if (objects.length == 0)
			return true;
		for (Object obj : objects) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否为在有效范围内的整数(闭区间)
	 * 
	 * @param intValue
	 * @param values
	 *            [minValue,maxValue]
	 * @return
	 */
	public static boolean isValidateInteger(String intValue, Integer minValue,
			Integer maxValue) {
		try {
			Integer value = Integer.parseInt(intValue);
			return isValidateInteger(value, minValue, maxValue);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 是否为在有效范围内的整数
	 * 
	 * @param intValue
	 * @param values
	 *            [minValue,maxValue]
	 * @return
	 */
	public static boolean isValidateInteger(Integer intValue, Integer minValue,
			Integer maxValue) {
		try {
			if (intValue == null)
				return false;
			if (minValue != null && intValue < minValue)
				return false;
			if (maxValue != null && intValue > maxValue)
				return false;

			return true;

		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 得到boolean值，如果不是一个boolean 则返回null
	 * 
	 * @param boolStr
	 * @return
	 */
	public static Boolean getBoolean(String boolStr) {
		if ("true".equalsIgnoreCase(boolStr)) {
			return true;
		} else if ("false".equalsIgnoreCase(boolStr)) {
			return false;
		}
		return null;
	}

	/**
	 * 判断是否为合法的double值
	 * 
	 * @param boolStr
	 * @return
	 */
	public static Boolean isValidateDouble(String doubleStr) {
		try {
			Double value = Double.valueOf(doubleStr);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * 判断是否为合法的float值
	 * 
	 * @param boolStr
	 * @return
	 */
	public static Boolean isValidateFloat(String floatStr) {
		try {
			Float value = Float.valueOf(floatStr);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * 判断是否为有效的日期格式字符串
	 * 
	 * @param dateStr
	 * @param start
	 * @param end
	 * @return
	 */
	public static Boolean isValidateDate(String dateStr, Date minDate,
			Date maxDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date d = sdf.parse(dateStr);

			if (minDate != null && d.before(minDate))
				return false;
			if (maxDate != null && d.after(maxDate))
				return false;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 判断输入是否为纯文字
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isValidateWord(String str) {
		try {
			if (isEmpty(str)) {
				System.out.println(str);
				return false;
			}
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (!((ch >= 0x0391 && ch <= 0xFFE5) // 中文字符

				|| (ch >= 0x0000 && ch <= 0x00FF))) // 英文字符
				{
					System.out.println(false);
					return false;
				}

			}
			System.out.println(true);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}
}
