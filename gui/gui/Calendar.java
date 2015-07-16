package gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import me.calendar.service.ActionDispatcher;
import me.calendar.service.ActionType;
import me.calendar.service.DataService;
import me.calendar.service.Utility;


public class Calendar extends JPanel{
	private JButton btn_clear,btn_save,btn_query,day[],btn[];
	private JLabel lab_sun,lab_week,lab_query_year,lab_query_month,lab_show_date,lab_show_test,jLabel7,lab_show_tip;
	private JComboBox<String> cbox_month;
	private JTextField text_year;
    private JTextArea area_note;
	Color bg = new java.awt.Color(215, 217, 218);
    int y = 0, x = 0, x_add = 0, y_add = 0, week_add = 0, date_acc = 0, week_of_day = 0;
	String smonth, sday, filename;
    
	public Calendar() {
        this.setLayout(null);
        this.setBackground(null);
        this.setOpaque(false);
        
        btn_clear = new JButton(new ImageIcon(SetUp.imageForCleanButton));
        btn_save = new JButton(new ImageIcon(SetUp.imageForSaveButton));
        btn_query = new JButton(new ImageIcon(SetUp.imageForQueryButton));
        lab_sun = new JLabel("Sun");
        lab_week = new JLabel("Mon   Tue   Wed   Thu   Fri   Sat");
        lab_show_test = new JLabel("个人数字图书馆");
        lab_show_tip = new JLabel();
        lab_query_month = new JLabel();
        lab_query_year = new JLabel();
        jLabel7 = new JLabel();
        
        btn_clear.setBorder(null);
        btn_clear.setBounds(153, 252, 40,20);
        btn_save.setBorder(null);
        btn_save.setBounds(118, 252, 40,20);
        btn_query.setBorder(null);
        btn_query.setBounds(143,6, 40,20);
        
        lab_sun.setBounds(3,44, 23, 21);
        lab_sun.setFont(new Font("Segue",Font.PLAIN,12));
        lab_sun.setForeground(new java.awt.Color(255,255,255));
        
        lab_week.setForeground(new java.awt.Color(39, 158, 218));
        lab_week.setBounds(33,44, 189, 21);
        lab_week.setFont(new Font("Segue",Font.PLAIN,12));
        
        lab_show_test.setBounds(3,252, 200, 21);
        lab_show_test.setFont(new java.awt.Font("微软雅黑", 1, 13));
        lab_show_test.setForeground(new java.awt.Color(39, 158, 218));
      //642,178
        lab_show_tip.setText("--------------------------------");
        lab_show_tip.setBounds(3,30, 200, 14);
        lab_show_tip.setFont(new java.awt.Font("微软雅黑", Font.BOLD,14));
        lab_show_tip.setForeground(new java.awt.Color(255, 255, 255));
        
        lab_query_month.setText("月");
        lab_query_month.setBounds(116, 5, 20, 21);
        lab_query_month.setFont(new java.awt.Font("微软雅黑", 1, 14));
        lab_query_month.setForeground(new java.awt.Color(39, 158, 218));
        
        lab_query_year.setText("年");
        lab_query_year.setBounds(48,5, 14, 21);
        lab_query_year.setFont(new java.awt.Font("微软雅黑", 1, 14));
        lab_query_year.setForeground(new java.awt.Color(39, 158, 218));
        
        jLabel7.setText("");
        jLabel7.setBounds(0, 0, 0, 0);//設定大小為0
        
        btn_clear.addMouseListener(new CursorListener());
        btn_query.addMouseListener(new CursorListener());
        btn_save.addMouseListener(new CursorListener());
        
        add(btn_clear);
        add(btn_save);
        add(btn_query);
        add(lab_sun);
        add(lab_week);
        add(lab_show_test);
        add(lab_show_tip);
        add(lab_query_month);
        add(lab_query_year);
        add(jLabel7);
      
	}
        public void rebuild(int year, int month) {
    		smonth = "" + month;
    		if (smonth.length() == 1) {
    			smonth = "0" + smonth;
    		}
    		switch (month)//設定月份天數
    		{
    			case 1://大月31天
    			case 3:
    			case 5:
    			case 7:
    			case 8:
    			case 10:
    			case 12:
    				date_acc = 31;
    				break;
    			case 4://小月30天
    			case 6:
    			case 9:
    			case 11:
    				date_acc = 30;
    				break;
    			case 2:
    				if (leap_year(year)) {
    					date_acc = 29;
    				} else {
    					date_acc = 28;
    				}
    		}
    		week_of_day = Utility.dow(year, month, 1);//呼叫星期函數(取得當月第一天為星期幾)
    		week_add = 27 * week_of_day;
    		btn = new JButton[date_acc];//建立日期按鈕陣列
    		for (int i = 0; i < date_acc; i++)//建立日期按鈕陣列回圈
    		{
    			btn[i] = new JButton();//建立對應日期按鈕
    			add(btn[i]);//加到桌面2上

    			btn[i].setText(String.valueOf(i + 1));//設定按鈕文字為日期
    			if ((i - week_of_day > 0 && (i + week_of_day) % 7 == 0) || ((i + week_of_day) % 7 == 0 && i != 0)) {//設定當月第一天日期按鈕位置
    				x = 0;//X軸座標
    				x_add = 0;//下一個按鈕座標(X軸)加值
    				y++;//Y軸座標
    				y_add += 0;//換行座標(Y軸)加值
    				week_add = 0;//當月第一日按鈕座標加值
    			}//下面設定按鈕大小及加值(X為起始10+第幾個按鈕*橫向寬度25+間隔+當月第一天星期幾加值)
    			btn[i].setBounds(x * 27 + x_add + week_add, y * 30 + y_add, 27, 30);//(Y為第幾個按鈕*高度20+換行加值)按鈕寬為25高為20
    			btn[i].setFont(new java.awt.Font("Leto", 1, 12));//設定字體大小及樣式
    			btn[i].setForeground(new java.awt.Color(39, 158, 218));
    			btn[i].setBorder(null);
    			btn[i].setBorder(BorderFactory.createTitledBorder(""));//設定按鈕文字不自動調整大小
    			int[] now = new int[3];
    			now = Utility.getdate();//取得當天日期
    			if (year == now[0] && month == now[1] && i + 1 == now[2]) {
    				btn[i].setBackground(new java.awt.Color(255, 255, 255));//若為當天則設定按鈕為白色
    			} else {
    				btn[i].setBackground(new java.awt.Color(215, 217, 218));//若不是當天則設定按鈕為蓝色
    			}
    			/*
    			 sday = i+1+"";
    			 filename = year+smonth+sday;//記事檔案名稱(年+月+日.txt)
    			 File file=new File(filename+".txt");//建立檔案物件
    			 */
    			//ds = new DataService(year,month,i+1);
    			if (DataService.exist(year, month, i + 1) == true) {//若當天有記事檔案則設定按鈕字體顏色為白色
    				btn[i].setForeground(new java.awt.Color(255, 255, 255));
    			}

    			btn[i].addActionListener(new ActionDispatcher(ActionType.Pressed));
    			x++;//下一個按鈕X座標加權
    			x_add += 0;//下一個按鈕間隔X座標加權
    		}
        }	
    	public static  boolean leap_year(int year)//判斷閏年函數開始
    		  {
    		    boolean leap_year;//4的倍數，若為100的倍數則必須為4的倍數才是閏年
    		    if (year%4 == 0)
    		    {
    		      if (year%100 == 0)
    		      {
    		        if (year%400 == 0)
    		          leap_year = true;
    		        else
    		          leap_year = false;
    		      }
    		      else
    		        leap_year = true;
    		    }
    		    else
    		      leap_year = false;
    		    return leap_year;
    		  }//判斷閏年函數結束
	

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
