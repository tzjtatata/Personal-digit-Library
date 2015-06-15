package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import gui.SetUp;

public class ResultPanel extends JPanel {

	private HashMap<Integer, String> JLHashMap;
	private JLabel[] labelListJLabels = new JLabel[5000];
	private JLabel labelHintJLabel = new JLabel();
	private JLabel[] labelorderJLabels = new JLabel[20];
        private JLabel next,front,tail,foreward;
        private ArrayList<JLabel> Page = new ArrayList<JLabel>();
        private int nowPage = 0;
	private int pagelength = 8, length = 8;
	private int countpage = 0;
	private static int startwidth = 0, startheight = -30, width = 400, height = 30;
	private int now = 0;
	private int size;
        private String name;

	/*protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("gui/source/搜索结果背景.png");
		img.paintIcon(this, g, 0, 0);
	}*/
        public String getName(){
            return this.name;
        }
	public ResultPanel(String str,String[] result) {
                JLabel temLabel;
                this.name = str;
                this.setBackground(new Color(215, 217, 218));
		int i;
		JLHashMap = new HashMap<>();
		this.setLayout(null);
		this.setOpaque(true);
                addMove();
		// 设定每页显示的结果序号
		for (i = 0; i < pagelength; i++) {
			if (labelorderJLabels[i] == null) {
				labelorderJLabels[i] = new JLabel();
			}
			labelorderJLabels[i].setText("" + (i + 1));
			labelorderJLabels[i].setBounds(startwidth, startheight + height * (i + 1), width, height);
			labelorderJLabels[i].setFont(new java.awt.Font("segoe", 1, 16));
			labelorderJLabels[i].setForeground(new java.awt.Color(255,255,255));;
			labelorderJLabels[i].setVisible(false);
			this.add(labelorderJLabels[i]);
		}
		//判定是否有结果，并显示结果
		//show函数用于显示第n+1页的结果
		size = result.length;
		for (i = 0; i < size; i++) {
			if (labelListJLabels[i] == null) {
				labelListJLabels[i] = new JLabel();
			}
			labelListJLabels[i].setText(result[i]);
			JLHashMap.put(labelListJLabels[i].hashCode(), labelListJLabels[i].getText());
			labelListJLabels[i].setFont(new java.awt.Font("微软雅黑", 0, 14));
			this.add(labelListJLabels[i]);
                        //判定页数，并设定表现页数的标签
		}
                do {
                    temLabel = new JLabel();
                    Page.add(temLabel);
                    Page.get(countpage).addMouseListener(new CursorListener());
                    Page.get(countpage).setText(""+ (countpage + 1));
                    Page.get(countpage).setFont(new java.awt.Font("微软雅黑", 0, 14));
                    Page.get(countpage).setVisible(false);
                    Page.get(countpage).addMouseListener(new ChangePage());
                    this.add(Page.get(countpage));
                    countpage++;
		}while (size / pagelength >= countpage && countpage * pagelength != size);
                //length = countpage;
                move(0);
		show(0);
		this.add(labelHintJLabel);
	}

	public void show(int n) {
		int i, temp;
		for (i = 0; i < pagelength; i++) {
			temp = pagelength * n + i;
			if (labelListJLabels[temp] != null) {
				labelListJLabels[temp].addMouseListener(new ShowAdapter());
				labelListJLabels[temp].addMouseListener(new CursorListener());
				labelListJLabels[temp].setBounds(startwidth + height, startheight + height * (i + 1), width, height);
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
                        labelorderJLabels[i].setVisible(false);
		}
	}
        public void move(int x){
            int i,len,size;
            size = Page.size();
            len = size>=(nowPage+length)?length:size;
            for (i = 0;i<len;i++)
            {
                Page.get(nowPage+i).setVisible(false);
            }
            nowPage = x;
            len = size>=(nowPage+length)?length:size;
            for (i = 0;i<len;i++)
            {
                Page.get(x+i).setBounds(startwidth + height * 3 +(height*i),8*height, height, height);
                Page.get(x+i).setVisible(true);
            }
            next.setBounds(startwidth + height * 3+(height*len),8*height, height, height);
            tail.setBounds(startwidth + height * 4+(height*len),8*height, height, height);
            front.setBounds(startwidth + height,8*height, height, height);
            foreward.setBounds(startwidth + height * 2,8*height, height, height);
        }
        public void addMove() {
                next = new JLabel(">");
                foreward = new JLabel("<");
                next.addMouseListener(new movePage(1));
                foreward.addMouseListener(new movePage(-1));
                foreward.addMouseListener(new CursorListener());
                next.addMouseListener(new CursorListener());
                this.add(next);
                this.add(foreward);
                tail = new JLabel(">>");
                front = new JLabel("<<");
                tail.addMouseListener(new movePage(2));
                front.addMouseListener(new movePage(3));
                front.addMouseListener(new CursorListener());
                tail.addMouseListener(new CursorListener());
                this.add(front);
                this.add(tail);
        }
        class ChangePage extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int temp = Page.indexOf(e.getSource());
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
        class movePage extends MouseAdapter {
            int change;
            public movePage(int change) {
                this.change = change;
            }
		@Override
		public void mouseClicked(MouseEvent e) {
                    int temp,t;
                    t = Page.size() - length;
                    if (change == 2) temp = t>0?t:0;
                    else if (change == 3) temp = 0;
                    else {
                        temp = nowPage+change;
                        if (temp + length > Page.size()){
                            temp = Page.size() - length;
                            temp = temp>0?temp:0;
                        }
                        else if (temp<0) temp = 0;
                    }
                    move(temp);
		}
	}
        
}
