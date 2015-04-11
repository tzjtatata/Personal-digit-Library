package me;

import backtable.Search;
import backtable.SearchContent;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sun.misc.FDBigInteger;

import me.calendar.frame.MainFrame;
import me.calendar.service.*;

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
	JPanel jpanelroot = new JPanel();
	CardLayout card = new CardLayout();

	public Searchpanel() {
		search = new Search();
		jpanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("gui/source/搜索背景.png");
				img.paintIcon(this, g, 0, 0);
			}
		};
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrame inst = new MainFrame();
				inst.setVisible(true);
				dispose();
			}
		});
		jpanelroot.setLayout(card);
		jpanelroot.setOpaque(true);
		jpanel.setLayout(null);
		jpanel.setOpaque(true);
		this.setTitle("搜索");
		bt1 = new JButton(new ImageIcon("gui/source/设置.jpg"));
		bt1.setBorder(null);
		bt1.addMouseListener(new CursorListener());
		bt2 = new JButton(new ImageIcon("gui/source/搜索.jpg"));
		bt2.addMouseListener(new CursorListener());
		bt2.setBorder(null);
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(entry3.getText())) {
					try {
						search.NameSearch(entry3.getText(), "txtFolder/");
						search.NameSearch(entry3.getText(), "otherFolder/");
						Container f = entry4.getRootPane().getParent();
						jpanelroot.add(new ResultPanel(entry3.getText(), search.getresult()));
						card.last(jpanelroot);
					} catch (Exception ex) {
					}
				} else if (!"".equals(entry4.getText())) {
					SearchContent result;
					try {
						result = new SearchContent(entry4.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return;
					}
					//System.out.println(result.getresult);
					Container f = entry4.getRootPane().getParent();
					jpanelroot.add(new ResultPanel(entry4.getText(), result.getresult));
					card.last(jpanelroot);
				} else if (!"".equals(entry1.getText())) {
					try {
						Container f = entry4.getRootPane().getParent();
						jpanelroot.add(new ResultPanel(entry1.getText(), search.AuthorSearch(entry1.getText())));
						card.last(jpanelroot);
					} catch (Exception ex) {
					}
				}
			}
		});
		option1 = new JLabel("按作者搜索");
		option1.setFont(new java.awt.Font("微软雅黑", 1, 18));
		option1.setForeground(new Color(39,158,218));
		option2 = new JLabel("按类型搜索");
		option2.setFont(new java.awt.Font("微软雅黑", 1, 18));
		option2.setForeground(new Color(39,158,218));
		option3 = new JLabel("按文件名搜索");
		option3.setFont(new java.awt.Font("微软雅黑", 1, 18));
		option3.setForeground(new Color(39,158,218));
		option4 = new JLabel("按关键字搜索");
		option4.setFont(new java.awt.Font("微软雅黑", 1, 18));
		option4.setForeground(new Color(39,158,218));
		entry1 = new JTextField(15);
		entry1.addMouseListener(new EntryListener());
		entry2 = new JTextField(15);
		entry2.addMouseListener(new EntryListener());
		entry3 = new JTextField(15);
		entry3.addMouseListener(new EntryListener());
		entry4 = new JTextField(15);
		entry4.addMouseListener(new EntryListener());
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
		point1.setBackground(new Color(39,158,218));
		point2.setBounds(160, 230, 5, 5);
		point2.setOpaque(true);
		point2.setBackground(new Color(39,158,218));
		point3.setBounds(160, 280, 5, 5);
		point3.setOpaque(true);
		point3.setBackground(new Color(39,158,218));
		point4.setBounds(160, 330, 5, 5);
		point4.setOpaque(true);
		point4.setBackground(new Color(39,158,218));

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
		line1.setBackground(new Color(39,158,218));

		hint.setBounds(103, 480, 744, 40);
		hint.setOpaque(true);
		hint.setBackground(new Color(215, 217, 218));
		hint.setHorizontalAlignment(SwingConstants.CENTER);

		bt1.setBounds(240, 393, 50, 22);
		bt2.setBounds(380, 393, 50, 22);
		option1.setBounds(220, 173, 200, 20);
		option2.setBounds(220, 223, 200, 20);
		option3.setBounds(220, 273, 200, 20);
		option4.setBounds(220, 323, 200, 20);
		entry1.setBounds(350, 173, 200, 20);
		entry2.setBounds(350, 223, 200, 20);
		entry3.setBounds(350, 273, 200, 20);
		entry4.setBounds(350, 323, 200, 20);
		if (dts.getContent() == null) {
			hint.setText("当前日期暂无读书计划");
		} else {
			hint.setText(dts.getContent());
		}
		Image i = this.getToolkit().getImage("gui/source/digital_library.png");//logo
		this.setIconImage(i);
		this.setContentPane(jpanelroot);
		jpanelroot.add(jpanel, "panel");
		/*ArrayList<String> test = new ArrayList<String>();
		 test.add("1");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 test.add("2");
		 jpanelroot.add(new ResultPanel("abda",  test));*/

		this.setResizable(false);
		this.setVisible(true);
		this.setBounds(200, 70, 950, 650);
	}

	public Searchpanel(GraphicsConfiguration gc) {
		super(gc);
	}

	class EntryListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	class CursorListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@SuppressWarnings("deprecation")
		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
