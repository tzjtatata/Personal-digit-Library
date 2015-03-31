package me;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel{

	private JLabel[] labelListJLabels = new JLabel[1000];
	private JLabel[] labelPageJLabels = new JLabel[100];
	private JLabel labelHintJLabel = new JLabel();
	private JLabel[] labelorderJLabels  = new JLabel[20];
	private int pagelength = 8;
	private int countpage = 0;
	private JButton back = new JButton("返回搜索界面");
	private static int startwidth = 200,startheight = 163,width = 200,height= 30;
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("gui/source/搜索结果背景.png");
		img.paintIcon(this, g, 0, 0);
	}
	
	
	public ResultPanel(String query,ArrayList<String> result) {
		int i;
		int size;
		this.setLayout(null);
		this.setOpaque(true);
		//back是按钮，负责返回搜索界面
		back.setBounds(startwidth, startheight+10*height,height*4,height);
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
		for (i = 0;i<pagelength;i++)
		{
			if (labelListJLabels[i] == null) labelorderJLabels[i] = new JLabel();
			labelorderJLabels[i].setText("" + (i+1));
			labelorderJLabels[i].setBounds(startwidth-height*2, startheight + height*(i+1), width, height);
			labelorderJLabels[i].setFont(new java.awt.Font("微软雅黑", 1, 18));
			labelorderJLabels[i].setVisible(false);
			this.add(labelorderJLabels[i]);
		}
		//判定是否有结果，并显示结果
		//show函数用于显示第n+1页的结果
		if (result == null || result.isEmpty())
		{
			labelHintJLabel.setText("对于"+query +"没有找到相应的结果");
		}
		else {
			size = result.size();
			labelHintJLabel.setText("对搜索\""+query + "\"找到"+size+"个结果.");
			for (i = 0;i<size;i++) {
				if (labelListJLabels[i] == null) labelListJLabels[i] = new JLabel();
				labelListJLabels[i].setText(result.get(i));
				labelListJLabels[i].setFont(new java.awt.Font("微软雅黑", 1, 18));
				//判定页数，并设定表现页数的标签
				if (i /pagelength >= countpage) {
					countpage++;
					if (labelPageJLabels[countpage] == null) labelPageJLabels[countpage] = new JLabel();
					labelPageJLabels[countpage].setText("" + countpage);
					labelPageJLabels[countpage].setFont(new java.awt.Font("微软雅黑", 1, 18));
					labelPageJLabels[countpage].setBounds(startwidth+height*(countpage-1), startheight-height, width,height);
					this.add(labelPageJLabels[countpage]);
				}
			}
			show(0);
		}
		labelHintJLabel.setBounds(startwidth-height*3, startheight, width*3, height);
		labelHintJLabel.setFont(new java.awt.Font("微软雅黑", 1, 18));
		this.add(labelHintJLabel);
	}
	public void  show(int n) {
		int i;
		for (i = 0;i<pagelength;i++) {
			if (labelListJLabels[i*(n+1)] != null) 
				{
					labelListJLabels[i*(n+1)].setBounds(startwidth,startheight+height *(i+1),width,height);
					this.add(labelListJLabels[i*(n+1)]);
					labelorderJLabels[i].setVisible(true);
				}
		}
	}
}
