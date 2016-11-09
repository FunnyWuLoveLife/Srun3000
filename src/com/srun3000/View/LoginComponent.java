package com.srun3000.View;

import com.srun3000.Config;
import com.srun3000.Controller.Connecter;
import com.srun3000.Model.User;
import com.srun3000.util.StringUtil;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

/**
 * 登录框的三个组件（用户名文本框，密码文本框，登录按钮） Created by xbc922 on 2016/8/13.
 */
public class LoginComponent extends JPanel implements ActionListener
{

	User user = null;// 定义一个用户全局域
	Config config = null;

	private JPanel msgPanel;
	private JPanel loginPanel;

	// 登录
	private JButton btn_login;
	private JButton btn_logout;
	private JTextField tf_username;
	private JTextField tf_password;
	private JCheckBox cb_rem_password;
	private JCheckBox cb_auto_login;

	// 信息展示
	private JLabel ret_la_ip_address;
	private JLabel ret_la_connectTime;

	/**
	 * 登录面板构造器，完成登录面板会话和监听登录按钮
	 */
	public LoginComponent()
	{
		config = new Config();
		initView();
		writeConfigToWindow();

		if (config.isAUTO_LOGIN())
		{
			login();
		}
	}

	private void initView()
	{
		setLayout(new GridLayout(2, 1));

		// 初始化登录
		loginPanel = new JPanel();
		msgPanel = new JPanel();

		loginPanel.setLayout(null);
		msgPanel.setLayout(null);

		JLabel lab_username = new JLabel("用户名:");
		JLabel lab_pwd = new JLabel("密  码:");
		lab_username.setBounds(30, 40, 60, 30);
		lab_pwd.setBounds(30, 80, 60, 30);

		tf_username = new JTextField();
		tf_password = new JPasswordField();
		tf_username.setBounds(90, 40, 140, 30);
		tf_password.setBounds(90, 80, 140, 30);
		tf_username.setText(config.getDEFAULT_USERNAME());
		tf_password.setText(config.getDEFAULT_PASSWORD());

		cb_rem_password = new JCheckBox("记住密码");
		cb_auto_login = new JCheckBox("自动登录");
		JButton serves = new JButton("自助服务");
		cb_rem_password.setBounds(250, 40, 100, 20);
		cb_auto_login.setBounds(250, 80, 100, 20);
		serves.setBounds(250, 115, 100, 20);

		btn_login = new JButton("登录");
		btn_logout = new JButton("注销");
		btn_login.setBounds(110, 140, 60, 30);
		btn_logout.setBounds(180, 140, 60, 30);

		loginPanel.add(lab_username);
		loginPanel.add(lab_pwd);
		loginPanel.add(tf_username);
		loginPanel.add(tf_password);
		loginPanel.add(cb_rem_password);
		loginPanel.add(cb_auto_login);
		loginPanel.add(btn_login);
		loginPanel.add(btn_logout);
		loginPanel.add(serves);
		// 初始化信息展示
		JLabel la_ip_address = new JLabel("IP地址:");
		JLabel la_connectTime = new JLabel("时长:");
		la_ip_address.setBounds(40, 40, 60, 30);
		la_connectTime.setBounds(40, 80, 60, 30);

		ret_la_ip_address = new JLabel();
		ret_la_connectTime = new JLabel();
		ret_la_ip_address.setBounds(110, 40, 120, 30);
		ret_la_connectTime.setBounds(110, 80, 120, 30);
		msgPanel.add(la_ip_address);
		msgPanel.add(la_connectTime);

		msgPanel.setVisible(false);
		add(loginPanel);
		add(msgPanel);

		// 给相应的组件添加监听器
		btn_login.addActionListener(this);
		btn_logout.addActionListener(this);
		cb_rem_password.addActionListener(this);
		cb_auto_login.addActionListener(this);
		serves.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "登录":
			login();
			readConfigOnWindow();
			config.writeConfigToFile();
			break;
		case "注销":
			String out_username = tf_username.getText();
			String out_password = tf_password.getText();
			User out_user = new User(out_username, out_password);
			if (!Connecter.isLinked)
			{
				JOptionPane.showMessageDialog(this, "您还未登录！");
				break;
			}
			String response_logout = Connecter.logout(out_user);
			if (response_logout.contains("logout_ok"))
			{
				JOptionPane.showMessageDialog(this, "注销成功！");
			} else
			{
				JOptionPane.showMessageDialog(this, response_logout, "注销失败",
						JOptionPane.ERROR_MESSAGE);
			}
			msgPanel.setVisible(false);
			break;
		case "记住密码":
			readConfigOnWindow();
			config.writeConfigToFile();
			break;
		case "自动登录":
			readConfigOnWindow();
			config.writeConfigToFile();
			break;
		case "自助服务":
			Desktop desktop = Desktop.getDesktop();
			try
			{
				desktop.browse(new URI("http://172.16.154.130:8800/"));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			} catch (URISyntaxException e1)
			{
				e1.printStackTrace();
			}
			break;
		}

	}

	public void readConfigOnWindow()
	{
		config.setREM_PASSWORD(cb_rem_password.isSelected());
		config.setAUTO_LOGIN(cb_auto_login.isSelected());

		if (cb_rem_password.isSelected())
		{
			String username = tf_username.getText();
			String password = tf_password.getText();
			config.setDEFAULT_USERNAME(username);
			config.setDEFAULT_PASSWORD(password);
		} else
		{
			config.setDEFAULT_USERNAME("");
			config.setDEFAULT_PASSWORD("");
		}
	}

	public void writeConfigToWindow()
	{
		if (config.isREM_PASSWORD())
		{
			cb_rem_password.setSelected(true);
		} else
		{
			cb_rem_password.setSelected(false);
		}
		if (config.isAUTO_LOGIN())
		{
			cb_auto_login.setSelected(true);
		} else
		{
			cb_auto_login.setSelected(false);
		}
		tf_username.setText(config.getDEFAULT_USERNAME());
		tf_password.setText(config.getDEFAULT_PASSWORD());
	}

	private void login()
	{
		String username = tf_username.getText();
		String password = tf_password.getText();
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password))
		{
			JOptionPane.showMessageDialog(this, "用户名或密码不能为空！");
			return;
		} else if (!StringUtil.isNumeric(username))
		{
			JOptionPane.showMessageDialog(this, "用户名是您的学号！");
			return;
		} else if (Connecter.isLinked)
		{
			JOptionPane.showMessageDialog(this, "已经登录");
			return;
		}
		User user = new User(username, password);
		String response_login = Connecter.Login(user);
		if (response_login.contains("success"))
		{
			JOptionPane.showMessageDialog(this, "登录成功！");
			ret_la_ip_address.setText(Connecter.ip_address);
			ret_la_connectTime.setText(String.valueOf(Connecter.connectTime));
			msgPanel.setVisible(true);
		} else
		{
			JOptionPane.showMessageDialog(this, response_login, "登录失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
