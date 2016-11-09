package com.srun3000.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by xbc922 on 2016/8/14.
 */
public class versionMenu extends JTextPane
{
	public versionMenu()
	{
		setFont(new Font("宋体", Font.BOLD, 20));
		setFocusable(false);
		setEditable(false);
		setText("v2.1版：\n基础版。搭建基础框架。使用了Swing技术，由于Swing太丑太丑太丑，"
				+ "哎丑得我都不想吐槽，所以用了beautyeye美化。\n欢迎访问");
		JButton github = new JButton("Github fork");
		github.setBounds(95, 130, 140, 30);
		github.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Desktop desktop = Desktop.getDesktop();
				try
				{
					desktop.browse(new URI(
							"https://github.com/FunnyWuLoveLife/Srun3000"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				} catch (URISyntaxException e1)
				{
					e1.printStackTrace();
				}

			}
		});
		add(github);

	}
}
