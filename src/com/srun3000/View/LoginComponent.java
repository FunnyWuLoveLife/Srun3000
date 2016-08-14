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
    JTextArea textArea = null;

    /**
     * 登录面板构造器，完成登录面板会话和监听登录按钮
     */
    public LoginComponent() {
        setFont(new Font("宋体", Font.BOLD, 40));
        //设置整个显示框为上下部分，上部分显示输入面板展示（用户名标签，用户名输入框，密码标签，密码输入框，和两个按钮）
        //下部分又分为两个区域，上部分显示选项，下部分显示信息
        setLayout(new GridLayout(2, 1));

        //选项面板占据,底部bottomPanel面板的1/2，相当于占据整个框架的1/4
        JPanel bottomPanel = new JPanel();//底部面板包括信息显示区域和选项区域
        bottomPanel.setLayout(new GridLayout(2, 1));

        //文本框占据,底部bottomPanel面板的1/2，相当于占据整个框架的1/4
        textArea = new JTextArea(12, 12);//信息展示区域
        textArea.setFont(new Font("宋体", Font.BOLD, 40));
        textArea.setLineWrap(true);//让文本区域自动换行
        textArea.setWrapStyleWord(true);//激活断行不断字

        //选项面板使用网格布局管理器，内部每个面板占据一个位置
        JPanel optionPanel = new JPanel();//选项区域面板
        optionPanel.setLayout(new GridLayout(2, 2));

        //输入面板使用网格布局管理器定义三行1列，内部每个面板占据一行
        JPanel inputPanel = new JPanel();//定义输入面板
        inputPanel.setLayout(new GridLayout(3, 1));//将输入面板设置为网格布局，设置为3行1列

        //输入你面板内部的三个面板使用默认的流布局管理器，以便让所有内容居中
        JPanel usernamePanel = new JPanel();//定义用户名面板
        JPanel passwordPanel = new JPanel();//密码面板
        JPanel buttonpanel = new JPanel();//按钮面板

        //输入模块
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("宋体", Font.BOLD, 40));
        usernamePanel.add(usernameLabel);//添加用户名标签
        JTextField usernameText = new JTextField();//定义用户名输出框
        usernameText.setColumns(10);//设置输入框长度
        usernameText.setFont(new Font("宋体", Font.BOLD, 40));//设置输入框字体大小
        usernamePanel.add(usernameText);//像面板中添加组件
        JLabel passwordLabel = new JLabel("密  码:");//密码标签
        passwordLabel.setFont(new Font("宋体", Font.BOLD, 40));
        passwordPanel.add(passwordLabel);//添加密码标签
        JPasswordField passwordText = new JPasswordField();//定义
        passwordText.setFont(new Font("宋体", Font.BOLD, 40));//设置输入框字体大小
        passwordText.setColumns(10);
        passwordPanel.add(passwordText);
        //按钮模块
        JButton linkbutton = new JButton("连接");//构造一个按钮
        linkbutton.setFont(new Font("宋体", Font.BOLD, 40));//设置按钮字体大小
        linkbutton.setEnabled(true);//将按钮设置为可选
        JButton logoutbutton = new JButton("注销");
        logoutbutton.setFont(new Font("宋体", Font.BOLD, 40));
        buttonpanel.add(linkbutton);
        buttonpanel.add(new Container());//添加占位容器
        buttonpanel.add(logoutbutton);
        //将用户名和密码面板添加到输入面板
        inputPanel.add(usernamePanel);//将用户名面板加入输入面板
        inputPanel.add(passwordPanel);//将密码面板加入输入面板
        inputPanel.add(buttonpanel);//将按钮面板添加到输入面板

        //给linkbutton设置事件监听器
        linkbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                user = new User(usernameText.getText(), new String(passwordText.getPassword()));
//                textArea.append("userName:" + user.getUsername());
//                textArea.append("\npassword:" + user.getPassword());//测试时功能
                textArea.append("\n登录返回信息：" + Connecter.Login(user));
            }
        });

        //添加logout按钮事件监听器
        logoutbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textArea.append("注销结果：" + Connecter.logout(user));
            }
        });



        add(inputPanel);
        bottomPanel.add(new Container());
        bottomPanel.add(new JScrollPane(textArea));//将选项框添加到底部面板
        add(bottomPanel);

    }

}
