package com.srun3000.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by xbc922 on 2016/8/14.
 */
public class versionMenu extends JTextArea {
    public versionMenu() {
        setFont(new Font("宋体", Font.BOLD, 40));
        setText("v1.0版：\n基础版，首次开发。搭建基础框架。使用了Swing技术，由于Swing太丑太丑太丑，" +
                "哎丑得我都不想吐槽，所以用了beautyeye美化。" +
                "\n欢迎访问Github fork，地址：https://github.com/FunnyWuLoveLife/Srun3000");
        setLineWrap(true);
        setEditable(false);
        setWrapStyleWord(true);
    }
}
