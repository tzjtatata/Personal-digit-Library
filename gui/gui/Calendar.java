package gui;


import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class Calendar extends JPanel{
	private JButton btn_clear,btn_save,btn_query,day[];
	private JLabel lab_sun,lab_week,lab_query_year,lab_query_month,lab_show_date,lab_show_test,jLabel7,lab_show_tip;
	private JComboBox<String> cbox_month;
	private JTextField text_year;
    private JTextArea area_note;
    
	public Calendar() {
        this.setLayout(null);
        this.setBackground(null);
        this.setOpaque(false);
        
        btn_clear = new JButton(new ImageIcon("gui/source/清除.png"));
        btn_save = new JButton(new ImageIcon("gui/source/储存.png"));
        btn_query = new JButton(new ImageIcon("gui/source/查询.png"));
        lab_sun = new JLabel("Sun");
        lab_week = new JLabel("Mon   Tue   Wed   Thu   Fri   Sat");
        //642,178
        //btn_clear = new DefaultButton(ActionType.Clear);
        btn_clear.setBorder(null);
        btn_clear.setBounds(153, 252, 40,20);
        btn_save.setBorder(null);
        btn_save.setBounds(118, 252, 40,20);
        btn_query.setBorder(null);
        btn_query.setBounds(143,6, 40,20);
        
        lab_sun.setBounds(3,44, 21, 21);
        lab_sun.setFont(new Font("Segue",Font.PLAIN,8));
        lab_sun.setForeground(new java.awt.Color(255,255,255));
        
        lab_week.setForeground(new java.awt.Color(39, 158, 218));
        lab_week.setBounds(33,44, 189, 21);
        lab_week.setFont(new Font("Segue",Font.PLAIN,30));
        add(lab_sun);
        add(lab_week);
/*
        mf.lab_show_test = new JLabel();

        mf.lab_show_test.setText("个人数字图书馆");
        mf.lab_show_test.setBounds(645, 430, 200, 21);
        mf.lab_show_test.setFont(new java.awt.Font("微软雅黑", 1, 13));
        mf.lab_show_test.setForeground(new java.awt.Color(39, 158, 218));
        add(mf.lab_show_test);

        mf.lab_show_tip = new JLabel();
        add(mf.lab_show_tip);
        mf.lab_show_tip.setText("------------------------------------");
        mf.lab_show_tip.setForeground(new java.awt.Color(255, 255, 255));
        mf.lab_show_tip.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 18));
        mf.lab_show_tip.setBounds(645, 208, 200, 14);
        mf.lab_show_tip.setFont(Utility.cn11);

        mf.lab_query_month = new JLabel();

        mf.lab_query_month.setText("月");
        mf.lab_query_month.setForeground(new java.awt.Color(39, 158, 218));
        mf.lab_query_month.setFont(new java.awt.Font("微软雅黑", 1, 14));
        mf.lab_query_month.setBounds(758, 183, 20, 21);

        //jLabel3.setBackground(Color.blue);
        //jLabel3.setOpaque(true);
        //mf.lab_query_month.setFont(Utility.cn12);
        add(mf.lab_query_month);

        mf.lab_query_year = new JLabel();
        add(mf.lab_query_year);
        mf.lab_query_year.setText("年");
        mf.lab_query_year.setForeground(new java.awt.Color(39, 158, 218));
        mf.lab_query_year.setFont(new java.awt.Font("微软雅黑", 1, 14));
        //mf.lab_query_year.setFont(Utility.cn12);
        mf.lab_query_year.setBounds(690, 182, 14, 21);

        mf.jLabel7 = new JLabel();
        add(mf.jLabel7);
        mf.jLabel7.setText("");
        mf.jLabel7.setBounds(0, 0, 0, 0);//設定大小為0
        */
        btn_clear.addMouseListener(new CursorListener());
        btn_query.addMouseListener(new CursorListener());
        btn_save.addMouseListener(new CursorListener());
        
        add(btn_clear);
        add(btn_save);
        add(btn_query);
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
