import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
@SuppressWarnings("serial")
public class BookshelfPanel extends JFrame {
	private JPanel panel;
	private JLabel[] jump,list;
	private JCheckBox[] book;
	private JLabel[][] operate;
	private JLabel left,right,add,menu,calendar,pass1,pass2;
	private int page,leftNumber,maxPage,rightNumber,category,maxCategory,leftCategory,rightCategory;
	private TextArea note;
	private HashMap<Integer,Integer> jumpMap,listMap;
	public BookshelfPanel() {
		panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
<<<<<<< HEAD
				ImageIcon img = new ImageIcon(System.getProperty("java.class.path")+"\\source\\书架背景.PNG");
=======
				ImageIcon img = new ImageIcon(System.getProperty("java.class.path")+"/source/书架背景.PNG");
>>>>>>> e0f757794abf6edd533ce91a579df1ab1b49db88
				img.paintIcon(this,g,0,0);
			}
		};
		page = -1;
		category = -1;
		jumpMap = new HashMap<Integer,Integer>();
		listMap = new HashMap<Integer,Integer>();
		
		pass1 = new JLabel("...",JLabel.CENTER);
		pass2 = new JLabel("...",JLabel.CENTER);
		pass1.setBounds(270, 430, 40, 30);
		pass2.setBounds(490, 430, 40, 30);
		pass1.setFont(new Font("黑体",Font.BOLD,24));
		pass2.setFont(new Font("黑体",Font.BOLD,24));
		pass1.addMouseListener(new JumpListener());
		pass1.addMouseListener(new CursorListener());
		pass2.addMouseListener(new JumpListener());
		pass2.addMouseListener(new CursorListener());
		
		add = new JLabel();
		add.setBounds(609, 138, 20, 20);
		add.addMouseListener(new CursorListener());
		panel.add(add);
		
		menu = new JLabel();
		menu.setBounds(633, 138, 20, 20);
		menu.addMouseListener(new CursorListener());
		panel.add(menu);

		left = new JLabel();
		left.setBounds(250, 138, 20, 20);
		left.addMouseListener(new CursorListener());
		left.addMouseListener(new ListListener());
		panel.add(left);
		
		right = new JLabel();
		right.setBounds(555, 138, 20, 20);
		right.addMouseListener(new CursorListener());
		right.addMouseListener(new ListListener());
		panel.add(right);
		
		note = new TextArea();
		note.setBounds(673, 162, 176, 298);
		panel.add(note);
		
		calendar = new JLabel("Feb 8st:oh,shit!",JLabel.CENTER);
		calendar.setBounds(103, 478, 750, 37);
		calendar.addMouseListener(new CursorListener());
		panel.add(calendar);
		
		setCategory(20);  //测试
		
		panel.setLayout(null);
		setTitle("书架");
		setContentPane(panel);
		setResizable(false);
		setVisible(true);
		setSize(900,650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setBookName(int c, int p) {
		int books = 30+20*c;
		if (books - (p-1)*7 > 7) {
			book = new JCheckBox[7];
			operate = new JLabel[7][3];
		}
		else {
			book = new JCheckBox[books - (p-1)*7];
			operate = new JLabel[books - (p-1)*7][3];
		}
		for (int i=0;i<book.length;i++) {
			book[i] = new JCheckBox("《第"+String.valueOf(c)+"类的第"+String.valueOf((p-1)*7+i+1)+"本书.pdf》");
			book[i].setOpaque(false);
			book[i].setBounds(150, 185+35*i, 280, 24);
			book[i].addMouseListener(new CursorListener());
			operate[i][0] = new JLabel("打开",JLabel.CENTER);
			operate[i][0].setBounds(446, 185+35*i, 40, 24);
			operate[i][0].addMouseListener(new CursorListener());
			operate[i][1] = new JLabel("删除",JLabel.CENTER);
			operate[i][1].setBounds(491, 185+35*i, 40, 24);
			operate[i][1].addMouseListener(new CursorListener());
			operate[i][2] = new JLabel("导入",JLabel.CENTER);
			operate[i][2].setBounds(536, 185+35*i, 40, 24);
			operate[i][2].addMouseListener(new CursorListener());
		}
	}
	public void addPage(int c, int p) {
		maxPage = (int) Math.ceil((30+20*c)/7.0);
		setBookName(c, p);
		for (int i=0;i<book.length;i++) {
			panel.add(book[i]);
			panel.add(operate[i][0]);
			panel.add(operate[i][1]);
			panel.add(operate[i][2]);
		}
		page = p;
		jump[p-1].setText("<html><u>"+jump[p-1].getText()+"</u></html>");
		panel.repaint();
	}
	public void setCategory(int c) {
		maxCategory = c;
		maxPage = (int) Math.ceil((30+20*c)/7.0);
		list = new JLabel[c];
		listMap.clear();
		for (int i=0;i<list.length;i++) {
			list[i] = new JLabel("第"+String.valueOf(i+1)+"类",JLabel.CENTER);
			list[i].setBackground(Color.white);
			list[i].addMouseListener(new CursorListener());
			list[i].addMouseListener(new ListListener());
			listMap.put(list[i].hashCode(), i+1);
			if (i < 5) {
				list[i].setBounds(270+i*58, 133, 55, 28);
				panel.add(list[i]);
			}
		}
		leftCategory = 1;
		if (c > 5) {
			rightCategory = 5;
		}
		else
			rightCategory = c;
		addCategory(1);
	}
	public void evertCategory(String d) {
		if (d == "left") {
			int temp;
			temp = category;
			removeCategory(category);
			category = temp;
			if (category > rightCategory-1) {
				category = rightCategory-1;
			}
			for (int i=leftCategory-1;i<rightCategory;i++) {
				panel.remove(list[i]);
			}
			for (int i=leftCategory-1,j=0;i<rightCategory;i++,j++) {
				list[i-1].setBounds(270+j*58, 133, 55, 28);
				panel.add(list[i-1]);
			}
			leftCategory -= 1;
			rightCategory -= 1;
			addCategory(category);
			panel.repaint();
		}
		else if (d == "right") {
			int temp;
			temp = category;
			removeCategory(category);
			category = temp;
			if (category < leftCategory+1) {
				category = leftCategory+1;
			}
			for (int i=leftCategory-1;i<rightCategory;i++) {
				panel.remove(list[i]);
			}
			for (int i=leftCategory-1,j=0;i<rightCategory;i++,j++) {
				list[i+1].setBounds(270+j*58, 133, 55, 28);
				panel.add(list[i+1]);
			}
			leftCategory += 1;
			rightCategory += 1;
			addCategory(category);
			panel.repaint();
		}
		
	}
	public void addCategory(int c) {
		list[c-1].setOpaque(true);
		if (page != -1) {
			removePage(category,page);
		}
		category = c;
		maxPage = (int) Math.ceil((30+20*c)/7.0);
		jump = new JLabel[maxPage];
		jumpMap.clear();
		for (int i=0;i<jump.length;i++) {
			jump[i] = new JLabel(String.valueOf(i+1),JLabel.CENTER);
			jump[i].setFont(new Font("黑体",Font.BOLD,24));
			jump[i].addMouseListener(new JumpListener());
			jump[i].addMouseListener(new CursorListener());
			jumpMap.put(jump[i].hashCode(), i+1);
			if (i < 7) {
				jump[i].setBounds(280+i*30,430,30,30);
				panel.add(jump[i]);
			}
		}
		leftNumber = 1;
		if (jump.length>7) {
			panel.add(pass2);
			rightNumber = 7;
		}
		else {
			rightNumber = maxPage;
		}
		addPage(c, 1);
		panel.repaint();
	}
	public void removeCategory(int c) {
		list[c-1].setOpaque(false);
		removePage(c, page);
		category = -1;
		panel.remove(pass1);
		panel.remove(pass2);
		for (int i=leftNumber-1;i<rightNumber;i++) {
			panel.remove(jump[i]);
		}
		panel.repaint();
	}
	public void removePage(int c,int p) {
		for (int i=0;i<book.length;i++) {
			panel.remove(book[i]);
			panel.remove(operate[i][0]);
			panel.remove(operate[i][1]);
			panel.remove(operate[i][2]);
		}
		jump[p-1].setText(String.valueOf(p));
		panel.repaint();
		page = -1;
	}
	public void evertJump(String d) {
		removePage(category,page);
		if (d == "right" && rightNumber!=maxPage) {
			if (rightNumber+7 >= maxPage) {
				for (int i=leftNumber;i<=rightNumber;i++) {
					panel.remove(jump[i-1]);
				}
				panel.remove(pass2);
				for (int i=maxPage-7,j=1;i<maxPage;i++,j++) {
					jump[i].setBounds(280+j*30, 430, 30, 30);
					panel.add(jump[i]);
				}
				addPage(category,maxPage);
				panel.add(pass1);
				rightNumber = maxPage;
				leftNumber = rightNumber-6;
			}
			else {
				for (int i=leftNumber;i<=rightNumber;i++) {
					panel.remove(jump[i-1]);
				}
				for (int i=rightNumber,j=1;i<rightNumber+6;i++,j++) {
					jump[i].setBounds(280+j*30, 430, 30, 30);
					panel.add(jump[i]);
				}
				panel.add(pass1);
				leftNumber = rightNumber+1;
				rightNumber = leftNumber+5;
				addPage(category,leftNumber);
			}
		}
		else if (d == "left" && leftNumber != 1){
			if (leftNumber-7 <= 1) {
				for (int i=leftNumber;i<=rightNumber;i++) {
					panel.remove(jump[i-1]);
				}
				panel.remove(pass1);
				for (int i=0,j=0;i<7;i++,j++) {
					jump[i].setBounds(280+j*30, 430, 30, 30);
					panel.add(jump[i]);
				}
				addPage(category,1);
				panel.add(pass2);
				rightNumber = 7;
				leftNumber = 1;
			}
			else {
				for (int i=leftNumber;i<=rightNumber;i++) {
					panel.remove(jump[i-1]);
				}
				for (int i=leftNumber-7,j=1;i<leftNumber-1;i++,j++) {
					jump[i].setBounds(280+j*30, 430, 30, 30);
					panel.add(jump[i]);
				}
				panel.add(pass2);
				rightNumber = leftNumber-1;
				leftNumber = rightNumber-5;
				addPage(category,rightNumber);
			}
		}
	}
	public static void main(String[] args) {
		new BookshelfPanel();
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
	class ListListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(left) && leftCategory > 1) {
				evertCategory("left");
			}
			else if(e.getSource().equals(right) && rightCategory < maxCategory) {
				evertCategory("right");
			}
			else {
				int listHash = listMap.get(e.getSource().hashCode());
				if (listHash != category) {
					removeCategory(category);
					addCategory(listHash);
				}
			}
		}
	}
	class JumpListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(pass2)) {
				evertJump("right");
			}
			else if (e.getSource().equals(pass1)) {
				evertJump("left");
			}
			else {
				int pageHash = jumpMap.get(e.getSource().hashCode());
				if (pageHash != page) {
					removePage(category,page);
					addPage(category,pageHash);
				}
			}
		}
	}
}
