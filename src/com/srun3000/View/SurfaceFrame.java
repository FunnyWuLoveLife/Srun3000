package com.srun3000.View;

import javax.swing.*;
import java.awt.*;

/**
 * 框架界面
 * Created by xbc922 on 2016/8/13.
 */
public class SurfaceFrame extends JFrame {
    private static final int FRAME_WIDTH = 304 * 2; //默认宽
    private static final int FRAME_HEIGHT = 496 * 2;//默然高

    /**
     * 构造函数
     */
    public SurfaceFrame() {
        setTitle("上网客户端-web");//设置界面标题
        setSize(FRAME_WIDTH, FRAME_HEIGHT);//设置框架界面大小
        setFont(new Font("宋体", Font.BOLD, 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭方式
        setLayout(new GridLayout(2, 1));//给整个框架设置布局管理器
//        add(new Container());
        add(new LoginComponent());//将登录面板显示在主界面框架上中部区域

    }
}
