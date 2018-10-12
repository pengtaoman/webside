package com.webside.util;

import java.security.SecureRandom;

public class UUID {
	/**
	 * 每位允许的字符
	 */
	private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 大写英文字母
	 */
	private static final String UPPER_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * 每位允许的数字字符
	 */
	private static final String NUM_CHARS = "0123456789";
	/**
	 *  获取随机uuid值
	 * @return uuid值
	 */
	public static String randomUUID() {
		java.util.UUID uuid = java.util.UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	/**
	 *  由数字字母组成的指定长度的字符串
	 * @param 指定长度
	 * @return 字符串
	 */
	public static String randomUUID(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
		}
		return sb.toString();
	}
	/**
	 *  由数字组成的指定长度的字符串
	 * @param 指定长度
	 * @return 字符串
	 */
	public static String randomNumberUUID(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(NUM_CHARS.charAt(random.nextInt(NUM_CHARS.length())));
		}
		return sb.toString();
	}
	/**
	 *  由大写字母组成的指定长度的字符串
	 * @param 指定长度
	 * @return 字符串
	 */
	public static String randomUpperUUID(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(UPPER_CHARS.charAt(random.nextInt(UPPER_CHARS.length())));
		}
		return sb.toString();
	}
}
