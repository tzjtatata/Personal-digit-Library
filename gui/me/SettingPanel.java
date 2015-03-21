package me;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import me.calendar.frame.MainFrame;
import me.calendar.service.*;

@SuppressWarnings("serial")
public class SettingPanel extends JFrame{	
		public static final int DEFAULT_WIDTH = 950;
		public static final int DEFAULT_HEIGHT = 650;
		
		public String font_C = "Serif", font_E = "Serif";
		
		private JPanel panel;
		private ButtonGroup group;
		private JPanel buttonPanel;
		private JLabel label1, label2, label3, dateLabel; 
		private JCheckBox bold, plain, italic, font1, font2, font3;
		private JCheckBox[] Gongxiang = new JCheckBox[10];
		private int number_Gongxiang = 0;
		private String[] string_Gongxiang = new String[10];
		private DataService dts;
		private int[] date;
		
		
		private final String Font1_C = "Serif", Font1_E = "Serif", Font2_C = "微软雅黑", Font2_E = "Segoe UI", Font3_C = "华文行楷", Font3_E = "Pristina 常规";
		private final int FONT_SIZE1 = 30, FONT_SIZE3 = 15, FONT_SIZE2 = 10;
		private final int[] X = {315, 500, 680}, Y = {200, 275, 312, 350};
		
		private static final String DEFAULT_TYPE = "normal";
		
		public SettingPanel(){		 
			this.setTitle("设置");
			this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			
			panel = new JPanel(){
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					ImageIcon img = new ImageIcon(System.getProperty("java.class.path")+"/source/设置2.png");
					img.paintIcon(this, g, 0, 0);
				}
			};
			
			panel.setLayout(null);
			
			//add the labels
			add_bigLabel();
					
			//add 风格checkboxs
			add_FenggeCheckbox();
			
			//add 字体checkboxs
			add_ZitiCheckbox();
			
			//add 共享checkboxs
			number_Gongxiang += 1;
			string_Gongxiang[number_Gongxiang] = "Ada";
			add_GongxiangCheckbox();

			add(panel);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					MainFrame inst = new MainFrame();
				    inst.setVisible(true);
					dispose();
				}
			});
			this.setResizable(false);
			this.setVisible(true);
			this.setBounds(200,70,950,650);
			Image i=this.getToolkit().getImage(System.getProperty("java.class.path")+"/source/digital_library.png");//logo
			this.setIconImage(i);
		}
		
		void add_bigLabel(){
			label1 = new JLabel(new ImageIcon(System.getProperty("java.class.path")+"/source/风格.png"));
			label1.setFont(new Font(font_C, Font.BOLD, FONT_SIZE1));
			label2 = new JLabel(new ImageIcon(System.getProperty("java.class.path")+"/source/字体.png"));
			label2.setFont(new Font(font_C, Font.BOLD, FONT_SIZE1));
			label3 = new JLabel(new ImageIcon(System.getProperty("java.class.path")+"/source/共享.png"));
			label3.setFont(new Font(font_C, Font.BOLD, FONT_SIZE1));
			date = Utility.getdate();
			dts = new DataService(date[0],date[1],date[2]);
			if (dts.getContent() == null)
			{
				dateLabel = new JLabel("当前日期暂无读书计划");
			}
			else dateLabel = new JLabel(dts.getContent());		
			dateLabel.setFont(new Font(font_E, Font.BOLD, FONT_SIZE2));
			
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(dateLabel);
			
			label1.setBounds(X[0], Y[0], 100, 50);
			label2.setBounds(X[1], Y[0], 100, 50);
			label3.setBounds(X[2], Y[0], 100, 50);
			dateLabel.setBounds(440, 470, 500, 50);
		}
		
		void add_FenggeCheckbox(){
			ActionListener FenggeCheckbox_listener = new ActionListener(){
				public void actionPerformed(ActionEvent event){
					int mode = 0;
					if (bold.isSelected()) mode += Font.BOLD;
					if (italic.isSelected()) mode += Font.ITALIC;
					label1.setFont(new Font(font_C, mode, FONT_SIZE1));
					label2.setFont(new Font(font_C, mode, FONT_SIZE1));
					label3.setFont(new Font(font_C, mode, FONT_SIZE1));
					dateLabel.setFont(new Font(font_E, mode, FONT_SIZE3));
				}
			};		
			bold = new JCheckBox("Bold");
			bold.addActionListener(FenggeCheckbox_listener);
			plain = new JCheckBox("Plain");
			plain.addActionListener(FenggeCheckbox_listener);
			italic = new JCheckBox("Italic");
			italic.addActionListener(FenggeCheckbox_listener);
		
			panel.add(plain);
			panel.add(bold);
			panel.add(italic);
			plain.setBounds(X[0], Y[1], 100, 50);
			bold.setBounds(X[0], Y[2], 100, 50);
			italic.setBounds(X[0], Y[3], 100, 50);
		}

		void add_ZitiCheckbox(){
			ActionListener ZitiCheckbox_listener = new ActionListener(){
				public void actionPerformed(ActionEvent event){
					font_C = Font1_C;
					font_E = Font1_E;
					if (font1.isSelected()){
						font_C = Font1_C;
						font_E = Font1_E;
					}else
						if (font2.isSelected()){
							font_C = Font2_C;
							font_E = Font2_E;
						}else
							if (font3.isSelected()){
								font_C = Font3_C;
								font_E = Font3_E;
							}
					int mode = 0;
					if (bold.isSelected()) mode += Font.BOLD;
					if (italic.isSelected()) mode += Font.ITALIC;
					label1.setFont(new Font(font_C, mode, FONT_SIZE1));
					label2.setFont(new Font(font_C, mode, FONT_SIZE1));
					label3.setFont(new Font(font_C, mode, FONT_SIZE1));
					dateLabel.setFont(new Font(font_E, mode, FONT_SIZE3));
				}
			};		
			font1 = new JCheckBox("Serif");
			font1.addActionListener(ZitiCheckbox_listener);
			font2 = new JCheckBox("微软雅黑");
			font2.addActionListener(ZitiCheckbox_listener);
			font3 = new JCheckBox("华文行楷");
			font3.addActionListener(ZitiCheckbox_listener);
		
			panel.add(font1);
			panel.add(font2);
			panel.add(font3);
			font1.setBounds(X[1], Y[1], 100, 50);
			font2.setBounds(X[1], Y[2], 100, 50);
			font2.setBounds(X[1], Y[3], 100, 50);
		}	
		void add_GongxiangCheckbox(){
			ActionListener GongxiangCheckbox_listener = new ActionListener(){
				public void actionPerformed(ActionEvent event){
					//
				}
			};			
			for (int i = 1; i <= number_Gongxiang; i++){
				Gongxiang[i] = new JCheckBox(string_Gongxiang[i]);
				Gongxiang[i].addActionListener(GongxiangCheckbox_listener);

				panel.add(Gongxiang[i]);
				Gongxiang[i].setBounds(X[2], Y[i], 100, 50);
			}
		}

	}