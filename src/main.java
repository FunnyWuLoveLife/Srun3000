import com.srun3000.View.SurfaceFrame;

import javax.swing.*;
import java.awt.*;

/**
 * srun3k的主程序
 * Created by xbc922 on 2016/8/13.
 */
public class main {
    /**
     * 入口程序
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            /**
             * 多线程相关的函数。。还不懂,只会用
             * funnywu 2016/8/13
             */
            @Override
            public void run() {
                JFrame frame = new SurfaceFrame();
                frame.setVisible(true);
            }
        });
    }
}