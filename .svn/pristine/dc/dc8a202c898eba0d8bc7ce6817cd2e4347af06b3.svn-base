package com.webside.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	 * 字符串通过逗号拆分成List集合
	 * 
	 * @param 字符串
	 * @return List集合
	 */
	public static List<String> toList(String valueString) {
		List<String> values = new ArrayList<String>();
		if (StringUtils.isNotEmpty(valueString)) {
			String[] tokens = valueString.split(",");
			if (tokens != null) {
				for (String token : tokens) {
					values.add(token);
				}
			}
		}

		return values;
	}

	/**
	 * 判断字符串（去空格后）是否是空串
	 * 
	 * @param 字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		// 全角的unicode 12288
		str = str.replace((char) 12288, ' ');
		if ("".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断两个字符串是否不相等
	 * 
	 * @param 字符串
	 * @param 字符串
	 * @return boolean
	 */
	public static boolean notEquals(String str1, String str2) {
		return !equals(str1, str2);
	}

	public static boolean indexOf(String str, String[] keys) {
		if (keys != null) {
			for (String key : keys) {
				if (str.indexOf(key) >= 0) {
					return true;
				}
			}
		}

		return false;
	}

	public static String valueOf(Object value) {
		return String.valueOf(value);
	}

	public static String toCharset(String src, String chartset) {
		try {
			return new String(src.getBytes(), chartset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return src;
		}
	}

//	public static String toPinyin(String name) {
//		String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0));
//		if (pinyin == null) {
//			return name.substring(0, 1);
//		} else {
//			return pinyin[0].substring(0, 1);
//		}
//	}

}
