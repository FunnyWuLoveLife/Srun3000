package com.srun3000.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @author FunnyWu
	 * @time 2016年11月9日下午4:32:51
	 * @param str
	 * @return str为空或者null返回true否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否全为数字
	 * 
	 * @param str
	 * @return 如果是数字返回true,反之返回false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
