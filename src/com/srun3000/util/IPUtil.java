package com.srun3000.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil
{
	public static String getIP() throws UnknownHostException
	{
		String ip = InetAddress.getLocalHost().getHostAddress();
		return ip;
	}
}
