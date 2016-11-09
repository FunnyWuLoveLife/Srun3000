package com.srun3000;

import com.srun3000.View.SurfaceFrame;

import javax.swing.*;
import java.awt.*;

/**
 * srun3k的主程序 Created by xbc922 on 2016/8/13.
 */
public class Main
{
	/**
	 * 入口程序
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{

		try
		{
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			// 改变InsetsUIResource参数的值即可实现
			UIManager.put("TabbedPane.tabAreaInsets",
					new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0));
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable()
		{
			/**
			 * 多线程相关的函数。。还不懂,只会用 funnywu 2016/8/13
			 */
			@Override
			public void run()
			{
				JFrame frame = new SurfaceFrame();
				frame.setVisible(true);
			}
		});
	}
}