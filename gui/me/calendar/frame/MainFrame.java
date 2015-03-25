package me.calendar.frame;

import me.BookshelfPanel;
import me.Searchpanel;
import me.SettingPanel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.*;

import me.calendar.component.CalendarPane;
import me.calendar.component.MainDesktopPane;
import me.calendar.service.ActionDispatcher;
import me.calendar.service.Utility;

public class MainFrame extends javax.swing.JFrame //建立一視窗物件
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public MainDesktopPane mainDesktop;//相關物件宣告
	public CalendarPane calendarPane;
	public JButton btn_clear = new JButton(new ImageIcon("gui/source/清除.png"));
	public JButton btn_save = new JButton(new ImageIcon("gui/source/储存.png"));
	public JButton btn_query = new JButton(new ImageIcon("gui/source/查询.png"));
	public JLabel lab_sun;
	public JLabel lab_week;
	public JLabel lab_query_year;
	public JLabel lab_query_month;
	public JLabel lab_show_date;
	public JLabel lab_show_test;
	public JLabel jLabel7;
	public JLabel lab_show_tip;
	public JComboBox<String> cbox_month;
	public JTextField text_year;

	public JTextArea area_note;

	public JLabel j1 = new JLabel();
	public JButton jb_shelf = new JButton(new ImageIcon("gui/source/书架.jpg"));
	public JButton jb_function = new JButton(new ImageIcon("gui/source/搜索.jpg"));
	public JButton jb_about = new JButton(new ImageIcon("gui/source/关于.jpg"));
	public JButton jb_help = new JButton(new ImageIcon("gui/source/设置.jpg"));
	public JPanel jp = new JPanel();

	 //主程式結束
	public MainFrame()//建立視窗開始
	{

		super();
		ActionDispatcher.setMainFrame(this);
		Utility.setMainFrame(this);
		jb_shelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookshelfPanel();
				dispose();
			}
		});
		jb_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingPanel();
				dispose();
			}
		});
		jb_function.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Searchpanel();
				dispose();
			}
		});
		jb_shelf.addMouseListener(new CursorListener());
		jb_function.addMouseListener(new CursorListener());
		jb_help.addMouseListener(new CursorListener());
		jb_about.addMouseListener(new CursorListener());
		initGUI();//呼叫GUI函數
	}//建立視窗結束

	class CursorListener extends MouseAdapter {

		@SuppressWarnings("deprecation")
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.HAND_CURSOR);
		}

		@SuppressWarnings("deprecation")
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.DEFAULT_CURSOR);
		}
	}

	private void initGUI()//產生視覺化物件函數(Graph User Interface，圖形化使用者介面)
	{
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//設定外框視窗主要功能列為標準(縮到最小，放到最大，關閉)
			{
				this.setTitle("个人数字图书馆");//設定視窗抬頭

				mainDesktop = new MainDesktopPane(this);//建立一桌面

				mainDesktop.initNow();
			}
			getContentPane().add(mainDesktop, BorderLayout.CENTER);
			pack();

			this.setBounds(200, 70, 950, 650);//大小 位置
			Image i = this.getToolkit().getImage("gui/source/digital_library.png");//logo
			this.setIconImage(i);

			this.setResizable(false);//不可以调整大小
			//this.setUndecorated(false);//边框
			this.setVisible(true);//可见

			//this.setSize(444, 296);
		} catch (Exception e) {//例外處理
			e.printStackTrace();
		}
	}

	 //查詢按鈕按下觸發事件結束
}
