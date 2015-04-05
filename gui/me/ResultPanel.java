package me;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {

	private HashMap<Integer, String> JLHashMap;
	private JLabel[] labelListJLabels = new JLabel[1000];
	private ArrayList<JLabel> labelPageJLabels = new ArrayList<JLabel>();
	private JLabel labelHintJLabel = new JLabel();
	private JLabel[] labelorderJLabels = new JLabel[20];
	private int pagelength = 8;
	private int countpage = 0;
	private JButton back = new JButton("返回搜索界面");
	private static int startwidth = 200, startheight = 163, width = 400, height = 30;
	private int now = 0;
	private JLabel temLabel;
	private int size;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("gui/source/搜索结果背景.png");
		img.paintIcon(this, g, 0, 0);
	}

	public ResultPanel(String query, ArrayList<String> result) {
		back.addMouseListener(new CursorListener());
		int i;
		JLHashMap = new HashMap<>();
		this.setLayout(null);
		this.setOpaque(true);
		//back是按钮，负责返回搜索界面
		back.setBounds(startwidth, startheight + 10 * height, height * 4, height);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Searchpanel sp = (Searchpanel) back.getRootPane().getParent();
				sp.card.first(sp.jpanelroot);
			}
		});
		this.add(back);
		// 设定每页显示的结果序号
		for (i = 0; i < pagelength; i++) {
			if (labelorderJLabels[i] == null) {
				labelorderJLabels[i] = new JLabel();
			}
			labelorderJLabels[i].setText("" + (i + 1));
			labelorderJLabels[i].setBounds(startwidth - height * 2, startheight + height * (i + 1), width, height);
			labelorderJLabels[i].setFont(new java.awt.Font("微软雅黑", 1, 18));
			labelorderJLabels[i].setVisible(false);
			this.add(labelorderJLabels[i]);
		}
		//判定是否有结果，并显示结果
		//show函数用于显示第n+1页的结果
		if (result == null || result.isEmpty()) {
			labelHintJLabel.setText("对于" + query + "没有找到相应的结果");
		} else {
			size = result.size();
			labelHintJLabel.setText("对搜索\"" + query + "\"找到" + size + "个结果.");
			for (i = 0; i < size; i++) {
				if (labelListJLabels[i] == null) {
					labelListJLabels[i] = new JLabel();
				}
				labelListJLabels[i].setText(result.get(i));
				JLHashMap.put(labelListJLabels[i].hashCode(), labelListJLabels[i].getText());
				labelListJLabels[i].setFont(new java.awt.Font("微软雅黑", 1, 18));
				this.add(labelListJLabels[i]);
				//判定页数，并设定表现页数的标签
				if (i / pagelength >= countpage) {
					temLabel = new JLabel();
					labelPageJLabels.add(temLabel);
					labelPageJLabels.get(countpage).addMouseListener(new CursorListener());
					labelPageJLabels.get(countpage).setText("" + (countpage + 1));
					labelPageJLabels.get(countpage).setFont(new java.awt.Font("微软雅黑", 1, 18));
					labelPageJLabels.get(countpage).setBounds(startwidth + height * (countpage), startheight - height, height, height);
					labelPageJLabels.get(countpage).addMouseListener(new ChangePage());
					this.add(labelPageJLabels.get(countpage));
					countpage++;
				}
			}
			show(0);
		}
		labelHintJLabel.setBounds(startwidth - height * 3, startheight, width * 3, height);
		labelHintJLabel.setFont(new java.awt.Font("微软雅黑", 1, 18));
		this.add(labelHintJLabel);
	}

	public void show(int n) {
		int i, temp;
		for (i = 0; i < pagelength; i++) {
			temp = pagelength * n + i;
			if (labelListJLabels[temp] != null) {
				labelListJLabels[temp].addMouseListener(new ShowAdapter());
				labelListJLabels[temp].addMouseListener(new CursorListener());
				labelListJLabels[temp].setBounds(startwidth, startheight + height * (i + 1), width, height);
				labelListJLabels[temp].setVisible(true);
				labelorderJLabels[i].setVisible(true);
			}
		}
	}

	public void hide(int n) {
		int i, temp;
		for (i = 0; i < pagelength; i++) {
			temp = pagelength * n + i;
			if (temp < size) {
				labelListJLabels[temp].setVisible(false);
			}
		}
	}

	class ChangePage extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int temp = labelPageJLabels.indexOf(e.getSource());
			hide(now);
			show(temp);
			now = temp;
		}
	}

	class ShowAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent me) {
			String cmd = "cmd.exe /c start " + JLHashMap.get(me.getSource().hashCode());
			Runtime run = Runtime.getRuntime();
			try {
				run.exec(cmd);
			} catch (IOException ex) {
				Logger.getLogger(ResultPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	class CursorListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
