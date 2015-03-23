package me;

import backtable.Search;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.calendar.frame.MainFrame;
import me.calendar.service.*;
import me.calendar.service.DataService;

@SuppressWarnings("serial")
public class Searchpanel extends JFrame {

	private JLabel option1, option2, option3, option4;
	private JLabel point1, point2, point3, point4, backg1, backg2, backg3, backg4, line1, hint;
	private JTextField entry1, entry2, entry3, entry4;
	private JButton bt1, bt2;
	private JPanel jpanel;
	private DataService dts;
	private int[] date;
	private Search search;

	public Searchpanel() {
		search = new Search();
		jpanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon(System.getProperty("java.class.path") + "/source/搜索背景.png");
				img.paintIcon(this, g, 0, 0);
			}
		};
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainFrame inst = new MainFrame();
				inst.setVisible(true);
				dispose();
			}
		});
		jpanel.setLayout(null);
		jpanel.setOpaque(true);
		this.setTitle("搜索");
		bt1 = new JButton("设置");
		bt2 = new JButton("搜索");
		bt2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!"".equals(entry3.getText())) {
					try {
						search.NameSearch(entry3.getText(), "txtFolder/");
						search.NameSearch(entry3.getText(), "otherFolder/");

					} catch (Exception ex) {
					}
				}
			}
		});
		option1 = new JLabel("按作者搜索");
		option2 = new JLabel("按类型搜索");
		option3 = new JLabel("按文件名搜索");
		option4 = new JLabel("按关键字搜索");
		entry1 = new JTextField(15);
		entry2 = new JTextField(15);
		entry3 = new JTextField(15);
		entry4 = new JTextField(15);
		point1 = new JLabel();
		point2 = new JLabel();
		point3 = new JLabel();
		point4 = new JLabel();
		backg1 = new JLabel();
		backg2 = new JLabel();
		backg3 = new JLabel();
		backg4 = new JLabel();
		line1 = new JLabel();
		date = Utility.getdate();
		dts = new DataService(date[0], date[1], date[2]);
		hint = new JLabel("2015-1-9     《信息检索导论》唐梦研快点给我读！！！");
		point1.addMouseListener(new ActionLis());
		point2.addMouseListener(new ActionLis());
		point3.addMouseListener(new ActionLis());
		point4.addMouseListener(new ActionLis());
		backg1.addMouseListener(new ActionLis());
		backg2.addMouseListener(new ActionLis());
		backg3.addMouseListener(new ActionLis());
		backg4.addMouseListener(new ActionLis());
		backg1.addMouseListener(new CursorListener());
		backg2.addMouseListener(new CursorListener());
		backg3.addMouseListener(new CursorListener());
		backg4.addMouseListener(new CursorListener());
		point1.addMouseListener(new CursorListener());
		point2.addMouseListener(new CursorListener());
		point3.addMouseListener(new CursorListener());
		point4.addMouseListener(new CursorListener());
		jpanel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}

			public void keyReleased(KeyEvent e) {
				System.out.println("bang!");
			}

			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
			}
		});

		jpanel.add(bt1);
		jpanel.add(bt2);
		jpanel.add(option1);
		jpanel.add(option2);
		jpanel.add(option3);
		jpanel.add(option4);
		jpanel.add(point1);
		jpanel.add(point2);
		jpanel.add(point3);
		jpanel.add(point4);
		jpanel.add(backg1);
		jpanel.add(backg2);
		jpanel.add(backg3);
		jpanel.add(backg4);
		jpanel.add(line1);
		jpanel.add(hint);

		jpanel.add(entry1);
		jpanel.add(entry2);
		jpanel.add(entry3);
		jpanel.add(entry4);
		entry1.setVisible(false);
		entry2.setVisible(false);
		entry3.setVisible(false);
		entry4.setVisible(false);

		point1.setBounds(160, 180, 5, 5);
		point1.setOpaque(true);
		point1.setBackground(Color.gray);
		point2.setBounds(160, 230, 5, 5);
		point2.setOpaque(true);
		point2.setBackground(Color.gray);
		point3.setBounds(160, 280, 5, 5);
		point3.setOpaque(true);
		point3.setBackground(Color.gray);
		point4.setBounds(160, 330, 5, 5);
		point4.setOpaque(true);
		point4.setBackground(Color.gray);

		backg1.setBounds(150, 170, 30, 30);
		backg1.setOpaque(true);
		backg1.setBackground(new Color(215, 217, 218));
		backg2.setBounds(150, 220, 30, 30);
		backg2.setOpaque(true);
		backg2.setBackground(new Color(215, 217, 218));
		backg3.setBounds(150, 270, 30, 30);
		backg3.setOpaque(true);
		backg3.setBackground(new Color(215, 217, 218));
		backg4.setBounds(150, 320, 30, 30);
		backg4.setOpaque(true);
		backg4.setBackground(new Color(215, 217, 218));

		line1.setBounds(190, 165, 2, 190);
		line1.setOpaque(true);
		line1.setBackground(Color.gray);

		hint.setBounds(103, 480, 744, 40);
		hint.setOpaque(true);
		hint.setBackground(new Color(215, 217, 218));
		hint.setHorizontalAlignment(SwingConstants.CENTER);

		bt1.setBounds(270, 373, 60, 20);
		bt2.setBounds(350, 373, 60, 20);
		option1.setBounds(220, 173, 200, 20);
		option2.setBounds(220, 223, 200, 20);
		option3.setBounds(220, 273, 200, 20);
		option4.setBounds(220, 323, 200, 20);
		entry1.setBounds(320, 173, 200, 20);
		entry2.setBounds(320, 223, 200, 20);
		entry3.setBounds(320, 273, 200, 20);
		entry4.setBounds(320, 323, 200, 20);
		if (dts.getContent() == null) {
			hint.setText("当前日期暂无读书计划");
		} else {
			hint.setText(dts.getContent());
		}
		Image i = this.getToolkit().getImage(System.getProperty("java.class.path") + "/source/digital_library.png");//logo
		this.setIconImage(i);
		this.setContentPane(jpanel);
		this.setResizable(false);
		this.setVisible(true);
		this.setBounds(200, 70, 950, 650);
	}

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

	class ActionLis extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == point1 || e.getSource() == backg1) {
				if (entry1.isVisible() == true) {
					entry1.setVisible(false);
					point1.setBackground(Color.gray);
				} else {
					entry1.setVisible(true);
					point1.setBackground(Color.WHITE);
				}
			}
			if (e.getSource() == point2 || e.getSource() == backg2) {
				if (entry2.isVisible() == true) {
					entry2.setVisible(false);
					point2.setBackground(Color.gray);
				} else {
					entry2.setVisible(true);
					point2.setBackground(Color.WHITE);
				}
			}
			if (e.getSource() == point3 || e.getSource() == backg3) {
				if (entry3.isVisible() == true) {
					entry3.setVisible(false);
					point3.setBackground(Color.gray);
				} else {
					entry3.setVisible(true);
					point3.setBackground(Color.WHITE);
				}
			}
			if (e.getSource() == point4 || e.getSource() == backg4) {
				if (entry4.isVisible() == true) {
					entry4.setVisible(false);
					point4.setBackground(Color.gray);
				} else {
					entry4.setVisible(true);
					point4.setBackground(Color.WHITE);
				}
			}
		}
	}
}
