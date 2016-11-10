package com.srun3000.util;

public class TimeUtil
{
	public static String Sec2Hr(int sec)
	{
		String str = null;
		int h = sec/3600;
		int m = (sec%3600)/60;
		int s= (sec%3600)%60;
		str = h+"时"+m+"分"+s+"秒";
		return str;
	}
}
