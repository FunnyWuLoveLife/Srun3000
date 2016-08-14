package com.srun3000.View;

import javax.swing.*;
import java.awt.*;

/**
 * 框架主界面
 * Created by xbc922 on 2016/8/13.
 */
public class SurfaceFrame extends JFrame {
    private static final int FRAME_WIDTH = 304 * 2; //默认宽
    private static final int FRAME_HEIGHT = 496 * 2;//默然高
    private JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * 构造函数
     */
    public SurfaceFrame() {
        setTitle("上网客户端-web-v1.0");//设置界面标题
        setSize(FRAME_WIDTH, FRAME_HEIGHT);//设置框架界面大小
        setResizable(false);
        setLocation(200, 300);
        setFont(new Font("宋体", Font.BOLD, 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭方式

        tabbedPane.add("主页", new LoginComponent());
        tabbedPane.add("功能说明", new JScrollPane(new decMenu()));
        tabbedPane.add("版本说明", new JScrollPane(new versionMenu()));
        tabbedPane.add("关于作者", new JScrollPane(new aboutMenu()));
        add(tabbedPane);
    }

}