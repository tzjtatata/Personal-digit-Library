import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class Searchpanel extends JFrame{
	private JLabel option1,option2,option3,option4;
	private JLabel point1,point2,point3,point4,line1,hint;
	private JLabel back1,back2,back3,back4;
	private JTextField entry1,entry2,entry3,entry4;
	private JButton bt1,bt2;
	private JPanel jpanel;
	public Searchpanel() {
		this.setUndecorated(true);
		jpanel = new JPanel(){
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon(System.getProperty("java.class.path")+"\\source\\��������.png");
			//	img.paintIcon(this, g, 0, 0);
			}
		};
		jpanel.setLayout(null);
		jpanel.setOpaque(true);
		this.setTitle("����");
		bt1 = new JButton("����");
		bt2 = new JButton("����");
		option1 = new JLabel("����������");
		option2 = new JLabel("����������");
		option3 = new JLabel("���ļ�������");
		option4 = new JLabel("���ؼ�������");
		entry1 = new JTextField(15);
		entry2 = new JTextField(15);
		entry3 = new JTextField(15);
		entry4 = new JTextField(15);
		point1 = new JLabel();
		point2 = new JLabel();
		point3 = new JLabel();
		point4 = new JLabel();
		back1 = new JLabel();
		back2 = new JLabel();
		back3 = new JLabel();
		back4 = new JLabel();
		line1 = new JLabel();
		hint = new JLabel("2015-1-9     ����Ϣ�������ۡ������п����Ҷ�������");
		point1.addMouseListener(new ActionLis());
		point2.addMouseListener(new ActionLis());
		point3.addMouseListener(new ActionLis());
		point4.addMouseListener(new ActionLis());
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	                dispose();
	        }
	        public void windowClosed(WindowEvent we) {
	                System.exit(0);
	        }
		});
		jpanel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
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
		
		point1.setBounds(160,180,5,5);
		point1.setOpaque(true);
		point1.setBackground(Color.gray);
		point2.setBounds(160,230,5,5);
		point2.setOpaque(true);
		point2.setBackground(Color.gray);
		point3.setBounds(160,280,5,5);
		point3.setOpaque(true);
		point3.setBackground(Color.gray);
		point4.setBounds(160,330,5,5);
		point4.setOpaque(true);
		point4.setBackground(Color.gray);
		
		line1.setBounds(190,165,2,190);
		line1.setOpaque(true);
		line1.setBackground(Color.gray);
		
		hint.setBounds(103,480,744,40);
		hint.setOpaque(true);
		hint.setBackground(new Color(215,217,218));
		hint.setHorizontalAlignment(SwingConstants.CENTER);
		
		bt1.setBounds(270,373,60,20);
		bt2.setBounds(350,373,60,20);
		option1.setBounds(220,173,200,20);
		option2.setBounds(220,223,200,20);
		option3.setBounds(220,273,200,20);
		option4.setBounds(220,323,200,20);
		entry1.setBounds(320,173,200,20);
		entry2.setBounds(320,223,200,20);
		entry3.setBounds(320,273,200,20);
		entry4.setBounds(320,323,200,20);
		
		this.setContentPane(jpanel);
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(900,650);
	}
	class ActionLis extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == point1){
				if (entry1.isVisible() == true){
					entry1.setVisible(false);
					point1.setBackground(Color.gray);
				}
				else {
					entry1.setVisible(true);
					point1.setBackground(Color.WHITE);
				}
			}
			if (e.getSource() == point2){
				if (entry2.isVisible() == true){
					entry2.setVisible(false);
					point2.setBackground(Color.gray);
				}
				else {
					entry2.setVisible(true);
					point2.setBackground(Color.WHITE);
				}
			}
			if (e.getSource() == point3){
				if (entry3.isVisible() == true){
					entry3.setVisible(false);
					point3.setBackground(Color.gray);
				}
				else {
					entry3.setVisible(true);
					point3.setBackground(Color.WHITE);
				}
			}
			if (e.getSource() == point4){
				if (entry4.isVisible() == true){
					entry4.setVisible(false);
					point4.setBackground(Color.gray);
				}
				else {
					entry4.setVisible(true);
					point4.setBackground(Color.WHITE);
				}
			}
		}
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Searchpanel();

	}

}
