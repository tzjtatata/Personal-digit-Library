import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SettingPanel {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				SettingFrame frame = new SettingFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class SettingFrame extends JFrame{	
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 650;
	
	private JPanel panel;
	private ButtonGroup group;
	private JPanel buttonPanel;
	private String font = "Serif";
	private JLabel label1, label2, label3, dateLabel, Fengge1, Fengge2, Fengge3; 
	private JCheckBox bold, plain, italic;
	private JCheckBox[] Gongxiang = new JCheckBox[10];
	private int number_Gongxiang = 0;
	private String[] string_Gongxiang = new String[10];
	
	private final int FONT_SIZE1 = 30, FONT_SIZE3 = 15, FONT_SIZE2 = 10;
	private final int[] X = {315, 500, 680}, Y = {200, 275, 312, 350};
	
	private static final String DEFAULT_TYPE = "normal";
	
	public SettingFrame(){		 
		this.setTitle("设置");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon img = new ImageIcon(System.getProperty("java.class.path")+"\\source\\设置2.png");
				img.paintIcon(this, g, 0, 0);
			}
		};
		
		panel.setLayout(null);
		
		//add the labels
		add_bigLabel();
		
		//add 风格labels
		add_FenggeLabel();
		
		//add 风格 radio buttons
		//add_FenggeButton();
		
		//add 字体checkboxs
		add_ZitiCheckbox();
		
		//add 共享checkboxs
		number_Gongxiang += 1;
		string_Gongxiang[number_Gongxiang] = "Ada";
		add_GongxiangCheckbox();

		add(panel);
	}
	
	void add_bigLabel(){
		label1 = new JLabel("风格");
		label1.setFont(new Font(font, Font.BOLD, FONT_SIZE1));
		label2 = new JLabel("字体");
		label2.setFont(new Font(font, Font.BOLD, FONT_SIZE1));
		label3 = new JLabel("共享");
		label3.setFont(new Font(font, Font.BOLD, FONT_SIZE1));
		dateLabel = new JLabel("2015.2.26");		
		dateLabel.setFont(new Font(font, Font.BOLD, FONT_SIZE2));
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(dateLabel);
		
		label1.setBounds(X[0], Y[0], 100, 50);
		label2.setBounds(X[1], Y[0], 100, 50);
		label3.setBounds(X[2], Y[0], 100, 50);
		dateLabel.setBounds(440, 470, 100, 50);
	}
	void add_FenggeLabel(){
		Fengge1 = new JLabel("风格1");
		Fengge1.setFont(new Font(font, Font.PLAIN, FONT_SIZE2));
		Fengge2 = new JLabel("风格2");
		Fengge2.setFont(new Font(font, Font.PLAIN, FONT_SIZE2));
		Fengge3 = new JLabel("风格3");
		Fengge3.setFont(new Font(font, Font.PLAIN, FONT_SIZE2));
		panel.add(Fengge1);
		panel.add(Fengge2);
		panel.add(Fengge3);
		Fengge1.setBounds(X[0], Y[1], 100, 50);
		Fengge2.setBounds(X[0], Y[2], 100, 50);
		Fengge3.setBounds(X[0], Y[3], 100, 50);
	}	
	void add_FenggeButton(){
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		
		addRadioButton("风格1", "oneFeng");
		addRadioButton("风格2", "twoFeng");
		addRadioButton("风格3", "threeFeng");
		
		add(buttonPanel);
	}	
	void add_ZitiCheckbox(){
		ActionListener FenggeCheckbox_listener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				int mode = 0;
				if (bold.isSelected()) mode += Font.BOLD;
				if (italic.isSelected()) mode += Font.ITALIC;
				label1.setFont(new Font(font, mode, FONT_SIZE1));
				label2.setFont(new Font(font, mode, FONT_SIZE1));
				label3.setFont(new Font(font, mode, FONT_SIZE1));
				dateLabel.setFont(new Font(font, mode, FONT_SIZE3));
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
		plain.setBounds(X[1], Y[1], 100, 50);
		bold.setBounds(X[1], Y[2], 100, 50);
		italic.setBounds(X[1], Y[3], 100, 50);
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


