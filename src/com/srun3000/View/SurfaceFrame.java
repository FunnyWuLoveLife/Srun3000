package com.srun3000.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 框架主界面
 * Created by xbc922 on 2016/8/13.
 */
public class SurfaceFrame extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int FRAME_WIDTH = 304 * 2; //默认宽
    private static int FRAME_HEIGHT = 496 * 2;//默然高
    private JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * 构造函数
     */
    public SurfaceFrame() {
//        FRAME_WIDTH = (int) (screenSize.width * 0.27);
//        FRAME_HEIGHT = (int) (screenSize.height * 0.75);
        setTitle("上网客户端-web-v1.0");//设置界面标题
        setSize(FRAME_WIDTH, FRAME_HEIGHT);//设置框架界面大小
        setResizable(false);
        setLocation((int) (screenSize.width * 0.1), (int) (screenSize.height * 0.1));
        setFont(new Font("宋体", Font.BOLD, 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭方式

        tabbedPane.add("主页", new LoginComponent());
        tabbedPane.add("功能说明", new JScrollPane(new decMenu()));
        tabbedPane.add("版本说明", new JScrollPane(new versionMenu()));
        tabbedPane.add("关于作者", new JScrollPane(new aboutMenu()));
        add(tabbedPane);

        if (SystemTray.isSupported()) { // 如果操作系统支持托盘
            this.tray();
        }

    }

    private TrayIcon trayIcon = null; // 托盘图标

    private SystemTray tray = null; // 本操作系统托盘的实例

    private void tray() {

        tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例
        ImageIcon icon = new ImageIcon("images/30.gif"); // 将要显示到托盘中的图标

        PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
        MenuItem show = new MenuItem("打开程序(s)");
        MenuItem exit = new MenuItem("退出程序(x)");
        pop.add(show);
        pop.add(exit);
        trayIcon = new TrayIcon(icon.getImage(), "车辆管理系统", pop);

        /**
         * 添加鼠标监听器，当鼠标在托盘图标上双击时，默认显示窗口
         */
        trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 鼠标双击
                    tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
                    setExtendedState(JFrame.NORMAL);
                    setVisible(true); // 显示窗口
                    toFront();
                }
            }
        });
        show.addActionListener(new ActionListener() { // 点击“显示窗口”菜单后将窗口显示出来
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
                setExtendedState(JFrame.NORMAL);
                setVisible(true); // 显示窗口
                toFront();
            }
        });
        exit.addActionListener(new ActionListener() { // 点击“退出演示”菜单后退出程序
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 退出程序
            }
        });

    }

}