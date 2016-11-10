package com.srun3000;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Config implements Serializable
{
	private String DEFAULT_USERNAME = "";// 默认账号
	private String DEFAULT_PASSWORD = "";// 默认密码
	private boolean REM_PASSWORD = false; // 记住密码
	private boolean AUTO_LOGIN = false; // 自动登录

	public static final String verseion = "v2.2";

	private File file = null;

	public Config()
	{
		String path = System.getProperty("user.home") + File.separator
				+ ".srun300";

		if (!new File(path).exists())
		{
			new File(path).mkdir();
			file = new File(path + File.separator + "config.config");
		} else
		{
			file = new File(path + File.separator + "config.config");
		}
		readConfigFromFile();
	}

	public void readConfigFromFile()
	{
		FileInputStream in;
		Config mConfig = null;
		try
		{
			in = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(in);
			mConfig = (Config) objIn.readObject();
			this.DEFAULT_USERNAME = mConfig.getDEFAULT_USERNAME();
			this.DEFAULT_PASSWORD = mConfig.getDEFAULT_PASSWORD();
			this.AUTO_LOGIN = mConfig.isAUTO_LOGIN();
			this.REM_PASSWORD = mConfig.isREM_PASSWORD();
			objIn.close();
			System.out.println("read config success!");
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public void writeConfigToFile()
	{
		FileOutputStream out;
		try
		{
			out = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(this);
			objOut.close();
			System.out.println("write config success!");
		} catch (IOException e)
		{
			System.out.println("write config failed");
			e.printStackTrace();
		}
	}

	@Override
	public String toString()
	{
		return "Config [DEFAULT_USERNAME=" + DEFAULT_USERNAME
				+ ", DEFAULT_PASSWORD=" + DEFAULT_PASSWORD + ", REM_PASSWORD="
				+ REM_PASSWORD + ", AUTO_LOGIN=" + AUTO_LOGIN + ", verseion="
				+ verseion + "]";
	}

	public String getDEFAULT_USERNAME()
	{
		return DEFAULT_USERNAME;
	}

	public void setDEFAULT_USERNAME(String dEFAULT_USERNAME)
	{
		DEFAULT_USERNAME = dEFAULT_USERNAME;
	}

	public String getDEFAULT_PASSWORD()
	{
		return DEFAULT_PASSWORD;
	}

	public void setDEFAULT_PASSWORD(String dEFAULT_PASSWORD)
	{
		DEFAULT_PASSWORD = dEFAULT_PASSWORD;
	}

	public boolean isREM_PASSWORD()
	{
		return REM_PASSWORD;
	}

	public void setREM_PASSWORD(boolean rEM_PASSWORD)
	{
		REM_PASSWORD = rEM_PASSWORD;
	}

	public boolean isAUTO_LOGIN()
	{
		return AUTO_LOGIN;
	}

	public void setAUTO_LOGIN(boolean aUTO_LOGIN)
	{
		AUTO_LOGIN = aUTO_LOGIN;
	}

	public static String getVerseion()
	{
		return verseion;
	}

}
