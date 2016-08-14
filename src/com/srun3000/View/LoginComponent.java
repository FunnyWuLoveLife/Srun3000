package com.srun3000.View;

import com.srun3000.Controller.Connecter;
import com.srun3000.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录框的三个组件（用户名文本框，密码文本框，登录按钮）
 * Created by xbc922 on 2016/8/13.
 */
public class LoginComponent extends JPanel {

    User user = null;//定义一个用户全局域

    /**
     * 登录面板构造器，完成登录面板会话和监听登录按钮
     */
    public LoginComponent() {
        setFont(new Font("宋体", Font.BOLD, 40));
        setLayout(new GridLayout(3, 1));//设置LoginComponent面板的布局管理器
//        setBackground();//设置背景图片

        JTextArea textArea = new JTextArea(20, 20);
        textArea.setFont(new Font("宋体", Font.BOLD, 40));

        JPanel inputPanel = new JPanel();//定义输入面板
        inputPanel.setLayout(new GridLayout(2, 2));//将输入面板设置为网格布局，设置为2行2列

        JPanel usernamePanel = new JPanel();//定义用户名面板
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("宋体", Font.BOLD, 40));
        usernamePanel.add(usernameLabel);//添加用户名标签
        JTextField usernameText = new JTextField();//定义用户名输出框
        usernameText.setColumns(10);//设置输入框长度
        usernameText.setFont(new Font("宋体", Font.BOLD, 40));//设置输入框字体大小
        usernamePanel.add(usernameText);//像面板中添加组件
        inputPanel.add(usernamePanel);//将用户名面板加入输入面板


        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("密  码:");
        passwordLabel.setFont(new Font("宋体", Font.BOLD, 40));
        passwordPanel.add(passwordLabel);//添加密码标签
        JPasswordField passwordText = new JPasswordField();//定义
        passwordText.setFont(new Font("宋体", Font.BOLD, 40));//设置输入框字体大小
        passwordText.setColumns(10);
        passwordPanel.add(passwordText);
        inputPanel.add(passwordPanel);//将密码面板加入输入面板

        add(inputPanel);//将输入面板加入登录面板显示在登录面板的NORTH

        JButton button = new JButton("连接");//构造一个按钮
        button.setFont(new Font("宋体", Font.BOLD, 40));//设置按钮字体大小
        button.setEnabled(true);//将按钮设置为科选

        /**
         * 给button设置事件监听器
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                user = new User(usernameText.getText(), new String(passwordText.getPassword()));
                textArea.append("userName:" + user.getUsername());
                textArea.append("\npassword:" + user.getPassword());
                textArea.append("\n登录返回信息：" + Connecter.Login(user));
            }
        });


        add(button);//将按钮添加到登录面板，显示在登录面板的南部
        add(textArea);

    }

//    public void setBackground() {
//        ImageIcon background = new ImageIcon()
//    }
}
