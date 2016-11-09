package com.srun3000.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by xbc922 on 2016/8/14.
 */
public class decMenu extends JTextPane	
{
	public decMenu()
	{
		setFont(new Font("宋体", Font.BOLD, 20));
		setFocusable(false);
		setEditable(false);
		setText("v1.0基础功能：\n  \t1).校园网登录\n\t2)."
				+ "校园网注销\n福利功能(此功能未亲测)绕了过校园网路由器验证");

	}
}
