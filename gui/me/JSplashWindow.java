package me;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.sun.prism.Image;

/**
 * 几乎所有时髦的应用都有一个欢迎屏幕。欢迎屏幕既是宣传产品的方法之一， 而且在长时间的应用启动过程中，欢迎屏幕还用来表示应用正在准备过程中。
 */
/**
 * 本例子实现一个欢迎屏幕，常用作应用软件的启动画面。
 */
public class JSplashWindow extends JWindow {

	/**
	 * 构造函数
	 *
	 * @param filename 欢迎屏幕所用的图片
	 * @param frame 欢迎屏幕所属的窗体
	 * @param waitTime 欢迎屏幕显示的事件
	 */
	public JSplashWindow(String filename, JFrame frame) {
		super(frame);
		ImageIcon img = new ImageIcon(filename);
		img.setImage(img.getImage().getScaledInstance(500, 400, 1));
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
		// 增加一个鼠标事件处理器，如果用户用鼠标点击了欢迎屏幕，则关闭。
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}

	public static void getABC() {
		ThreadToStop th = new ThreadToStop();
		th.start();
	}
}

class ThreadToStop extends Thread {

	//共享变量
	private volatile boolean isStop = false;
	JFrame frame = new JFrame("欢迎屏幕");

	@Override
	public void interrupt() {
		//调用interrupt之前，把isStop置为false
		isStop = true;
		frame.dispose();
		super.interrupt();
	}

	@Override
	public void run() {
		// double check，检查stop的状态
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);
		JSplashWindow splash = new JSplashWindow("gui/me/start.gif", frame);
		splash.setVisible(true);
		frame.pack();
		frame.setVisible(true);
		while (!isStop) {
			File fi = new File("gui/me/showflag.pdl");
			try {
				BufferedReader br = new BufferedReader(new FileReader(fi));
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
