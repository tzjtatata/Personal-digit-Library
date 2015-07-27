package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSplashWindow extends JWindow {

    /**
     * 构造函数
     *
     * @param filename 欢迎屏幕所用的图片
     * @param frame 欢迎屏幕所属的窗体
     */
    public JSplashWindow(JFrame frame) {
        super(frame);
        ImageIcon img = new ImageIcon(SetUp.imageForWelcome);
        img.setImage(img.getImage().getScaledInstance(600, 350, 1));
        frame.setIconImage(this.getToolkit().getImage(SetUp.imageForLogo));
        // 建立一个标签，标签中显示图片。
        JLabel label = new JLabel(img);
        // 将标签放在欢迎屏幕中间
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
        // 获取屏幕的分辨率大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 获取标签大小
        Dimension labelSize = label.getPreferredSize();
        // 将欢迎屏幕放在屏幕中间
        setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));
    }

    public static void getABC() {
        ThreadToStop th = new ThreadToStop();
        th.start();
    }
}

class ThreadToStop extends Thread {

    //共享变量
    private volatile boolean isStop = false;
    JFrame frame = new JFrame("个人数字图书馆");
    JSplashWindow splash;

    @Override
    public void interrupt() {
        //调用interrupt之前，把isStop置为false
        isStop = true;
        //splash.setVisible(false);
        splash.dispose();
        frame.dispose();
        super.interrupt();
        BufferedWriter br;
        try {
            br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("setFile/showflag.pdl"), "UTF-8"));
            br.write("1");
        } catch (Exception ex) {
            Logger.getLogger(ThreadToStop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        // double check，检查stop的状态
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        splash = new JSplashWindow(frame);
        splash.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        while (!isStop) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("setFile/showflag.pdl"), "UTF-8"));
                if ("1".equals(br.readLine())) {
                    //System.out.println("abc");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    this.interrupt();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
