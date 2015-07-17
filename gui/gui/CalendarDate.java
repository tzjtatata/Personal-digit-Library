package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.*;


public class CalendarDate extends JPanel{
	/**
	 * 
	 *  @author 77
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btn_clear,btn_save,btn_query,btn[];
	private JLabel lab_sun,lab_week,lab_query_year,lab_query_month,lab_show_date,lab_show_test,jLabel7,lab_show_tip;
	private JComboBox<String> cbox_month;
	private JTextField text_year;
    private JTextArea area_note;
    private JDesktopPane date;
    private JScrollPane scrollPane;
    private ComboBoxModel<String> jComboBox1Model = new DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});//內容設定1~12
	String smonth, sday,syear ,filename;
	File file;
	int[] now = new int[3];
	
	public CalendarDate() {
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
        cbox_month = new JComboBox<>();
        area_note = new JTextArea(182,42);
        scrollPane = new JScrollPane(area_note, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text_year = new JTextField();
        lab_show_date = new JLabel();
        
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

        cbox_month.setModel(jComboBox1Model);
        cbox_month.setBackground(new Color(215, 217, 218));
        cbox_month.setForeground(new java.awt.Color(39, 158, 218));
        cbox_month.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 11));
        cbox_month.setBounds(68,6, 40, 18);
        cbox_month.setFont(new java.awt.Font("微软雅黑", 1, 10));
        cbox_month.setSelectedItem(String.valueOf(getdate()[1]));
        
        area_note.setText("");//預設內容清空
        area_note.setBounds(3, 274, 182, 42);//設定大小
        area_note.setForeground(new java.awt.Color(39, 158, 218));
        area_note.setFont(new java.awt.Font("黑体", Font.BOLD, 18));//設定字體樣式大小
        area_note.setLineWrap(true);//文字過長自動換行
        area_note.setWrapStyleWord(true);//文字過長自動換行
        area_note.setBackground(new Color(215, 217, 218));
        scrollPane.setBackground(Color.red);
        scrollPane.setBounds(3, 274, 182, 42);//設定大小及位置
       
        text_year.setText(String.valueOf(getdate()[0]));
        text_year.setBounds(3, 3,45, 25);
        text_year.setBackground(new Color(215, 217, 218));
        text_year.setForeground(new java.awt.Color(39, 158, 218));
        text_year.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 10));
        
        now = getdate();//預設為當年當月
        syear = String.valueOf(now[0]);
        smonth = String.valueOf(now[1]);
        if (smonth.length() == 1) {
            smonth = "0" + smonth;
        }
        System.out.println(syear + " 年 " + smonth + " 月");
        lab_show_date.setText(syear + " 年 " + smonth + " 月");
        lab_show_date.setBounds(3, 22, 120, 21);
        lab_show_date.setForeground(new java.awt.Color(215, 217, 218));//設定字體為白色
        
        new_btn();//產生日期按鈕
        btn_clear.addMouseListener(new CursorListener());
        btn_clear.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent event) {  
            	ClearActionPerformed(event);  
            }  
        });
        btn_query.addMouseListener(new CursorListener());
        btn_query.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent event) {  
            	QueryActionPerformed(event);  
            }  
        });
        btn_save.addMouseListener(new CursorListener());
        btn_save.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent event) {  
            	SaveActionPerformed(event);  
            }  
        });  
        //642, 178
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
        add(cbox_month);
        add(area_note);
        add(scrollPane);
        add(text_year);
        add(lab_show_date);
        

	}
	
	public void date_btn_create(int year,int month)
	  {
	    int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
	    
	    syear = String.valueOf(year);
	    smonth = String.valueOf(month);
	    if (smonth.length() == 1)
	      smonth = "0"+smonth;
	    System.out.println(syear);
	    if (null != date)	
	    {   	remove(date);
	    System.out.println("move");}
	    date = new JDesktopPane();
	    this.add(date);
	    date.setBounds(0, 300, 200, 330);
	    date.setBackground(null);
        date.setOpaque(false);
        
	    switch(month)
	    {
	      case 1:
	      case 3:
	      case 5:
	      case 7:
	      case 8:
	      case 10:
	      case 12:
	        date_acc = 31;
	        break;
	       
	      case 4:
	      case 6:
	      case 9:
	      case 11:
	        date_acc = 30;
	        break;
	       
	      case 2:
	        if (leap_year(year))
	          date_acc = 29;
	        else
	          date_acc = 28;
	    }
	    week_of_day = dow(year,month,1);
	    switch(week_of_day)
	    {
	      	case 0:
	      		week_add = 0;
	      		break;
	      	case 1:
	      		week_add = 34;
	      		break;
	      	case 2:
	      		week_add = 68;
	      		break;
	      	case 3:
	      		week_add = 102;
	      		break;
	      	case 4:
	      		week_add = 136;
	      		break;
	      	case 5:
	      		week_add = 170;
	      		break;
	      	case 6:
	      		week_add = 204;
	      		break;
	    }
	    JButton btn[] = new JButton[date_acc];
	    for (int i = 0; i < date_acc; i++)//建立日期按鈕陣列回圈
		{
			btn[i] = new JButton();//建立對應日期按鈕
			//date.add(btn[i]);//加到桌面2上

			btn[i].setText(String.valueOf(i + 1));//設定按鈕文字為日期
			if ((i - week_of_day > 0 && (i + week_of_day) % 7 == 0) || ((i + week_of_day) % 7 == 0 && i != 0)) {//設定當月第一天日期按鈕位置
				x = 0;//X軸座標
				x_add = 0;//下一個按鈕座標(X軸)加值
				y++;//Y軸座標
				y_add += 0;//換行座標(Y軸)加值
				week_add = 0;//當月第一日按鈕座標加值
			}//下面設定按鈕大小及加值(X為起始10+第幾個按鈕*橫向寬度25+間隔+當月第一天星期幾加值)
			btn[i].setBounds(x * 27 + x_add + week_add, y * 30 + y_add+75, 27, 30);//(Y為第幾個按鈕*高度20+換行加值)按鈕寬為25高為20
			btn[i].setFont(new java.awt.Font("Leto", 1, 12));//設定字體大小及樣式
			btn[i].setForeground(new java.awt.Color(39, 158, 218));
			btn[i].setBorder(null);
			btn[i].setBorder(BorderFactory.createTitledBorder(""));//設定按鈕文字不自動調整大小
			add(btn[i]);
			//int[] now = new int[3];
			now = getdate();//取得當天日期
			if (year == now[0] && month == now[1] && i + 1 == now[2]) {
				btn[i].setBackground(new java.awt.Color(255, 255, 255));//若為當天則設定按鈕為白色
			} else {
				btn[i].setBackground(new java.awt.Color(215, 217, 218));//若不是當天則設定按鈕為蓝色
			}
			//System.out.println(now[0] + now[1] + now[2]);     	
	     	sday = String.valueOf(i+1);
	     	filename = syear+smonth+sday;
	     	File file=new File(filename+".txt");
	     	if (exist(year, month, i + 1) == true) {//若當天有記事檔案則設定按鈕字體顏色為白色
				btn[i].setForeground(new java.awt.Color(255, 255, 255));
			}
	     	//System.out.println("!");
			btn[i].addMouseListener(new MouseAdapter() {  
	            public void actionPerformed(ActionEvent evt) {  
	            	btnActionPerformed(evt);  
	            	System.out.println("!");
	            }  
	        });  
			x++;//下一個按鈕X座標加權
			x_add += 0;
		}
	}
	
	public static boolean exist(int _year, int _month, int _day) {

		String filename;
		String smonth = _month + "";
		if (smonth.length() == 1) {
			smonth = "0" + smonth;
		}
		filename = _year + smonth + _day;
		File file = new File("gui/me/calendar/" + filename + ".txt");
		return file.exists();
	}
	

	public boolean exist() {
		return file.exists();
	}
	
	private void btnActionPerformed(ActionEvent evt)
	{
	    area_note.setText("");
	    String year,month,btn_date,filename,read_str;
	    year = lab_show_date.getText().substring(0,4);
	    month = lab_show_date.getText().substring(7,9);//label5 = lab_show_date
	    btn_date = evt.getActionCommand();
	    filename = year+month+btn_date;
	    jLabel7.setText(btn_date);
	    try
	    {
	    	FileReader fr = new FileReader(filename+".txt");
	    	BufferedReader bfr = new BufferedReader(fr);
	    	boolean flag=false;
	    	while((read_str = bfr.readLine())!=null) 
	    	{
	    		if (flag)
	    			area_note.append("\n");
	    		area_note.append(read_str);
	    		flag=true;
	       
	    	}
	    	lab_show_test.setText("读书日历");
	    	lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	    	fr.close();
	    }catch(FileNotFoundException e)
	    {
	    	lab_show_test.setText("当日读书计划");
		    lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	    }catch(IOException e)
	    {
	      e.printStackTrace();
	    }
	}

	public int[] getdate(){
		int[] date_array = new int[3];
		Calendar ca = new GregorianCalendar();
		date_array[0] = ca.get(Calendar.YEAR);
		date_array[1] = ca.get(Calendar.MONTH)+1;
		date_array[2] = ca.get(Calendar.DAY_OF_MONTH);
	  return date_array;
	}//取得系統日期函數結束
	
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
	
	public static int dow(int y,int m,int d)//判斷星期幾
	{
	    int[] ww={6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//天文星體運行值
	    int w;
	    w=ww[m-1]+y+(y/4)-(y/100)+(y/400);//閏年設定
	    if( ((y%4)==0) && (m<3) )//公式
	    {
		      w--;
		      if((y%100)==0) w++;
		      if((y%400)==0) w--;
	    }
	    return (w+d)%7;//回傳星期幾(0為星期日，1為星期一以此類推)
	}

	private void ClearActionPerformed(MouseEvent event)
	  {
	    area_note.setText("");
	    String year,month,day,filename;
	    year = lab_show_date.getText().substring(0,4);
	    month =lab_show_date.getText().substring(7,9);
	    day = jLabel7.getText();
	    String insert_str = area_note.getText();
	    area_note.setText("");
	    if (day.length() == 0)
	        lab_show_test.setText("未选择日期");
	    else if(insert_str.length()==0)	 
	        lab_show_test.setText("当日无读书计划");
	    else {
		    day = jLabel7.getText();
		    filename = year+month+day;
		    File file=new File(filename+".txt");//�h������ӛ�n��
		    file.delete();
		    new_btn();
		    lab_show_test.setText("计划已清除");
		    jLabel7.setText("");
		 }
		    //System.out.println("clear successfully");
	  }
	
	private void SaveActionPerformed(MouseEvent event)
	{
	    String year,month,day,filename,insert_str;
	    year = lab_show_date.getText().substring(0,4);
	    month = lab_show_date.getText().substring(7,9);
	    day = jLabel7.getText();
	    System.out.println(null == jLabel7);
	    insert_str = area_note.getText();//記事內容
	    if (day.length() == 0)
	        lab_show_test.setText("未选择日期");//設定相關訊息
	    else if(insert_str.length()==0)	 
	        lab_show_test.setText("当日无读书计划");
	    else {
	    	
		    day = jLabel7.getText();
		    filename = year+month+day;
		    File file=new File(filename+".txt");
		    System.out.println(" ??");
		    file.delete();
		    if (insert_str.length() != 0 && day.length() != 0)//若記事框內有文字且有選擇日期則儲存記事檔案
		    {
		    	System.out.println(" !!");
		    	lab_show_test.setText("已记录计划");
		    }
		    new_btn();
	    }
	    //System.out.println("save successfully");
	}
	
	private void QueryActionPerformed(MouseEvent event)
	{
	    String syear,smonth;
	    try
	    {
			area_note.setText("");
			lab_show_test.setText("查询日期");
			syear = text_year.getText();
			smonth = String.valueOf(cbox_month.getSelectedIndex() + 1);
			if (smonth.length() == 1)
				smonth = "0"+smonth;
			if (syear == "" || Integer.parseInt(syear)<1582)//若未輸入年份就觸發例外(1582年以前曾經改曆過，結果會不準確)
			{
				int[] now = new int[3];
				now = getdate();
				syear = String.valueOf(now[0]);//若選擇年份小於1582年則預設為當年
				lab_show_test.setText("请选择1582年以上");
			}
			lab_show_date.setText(syear+" 年 "+smonth+" 月");
			date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
			jLabel7.setText("");
			lab_show_tip.setText("未选择日期");
	    }catch(NumberFormatException e)//例外處理設定為當年及選擇的月份
	    {
	    	int[] now = new int[3];
	    	now = getdate();
	    	syear = String.valueOf(now[0]);
	    	smonth = String.valueOf(cbox_month.getSelectedIndex() + 1);
	    	if (smonth.length() == 1)
	    		smonth = "0"+smonth;
	    lab_show_date.setText(syear+" 年 "+smonth+" 月");
	    lab_show_test.setText("请选择1582年以上");
	    lab_show_tip.setText("未选择日期");
	    jLabel7.setText("");
	    date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
	    }
	    System.out.println("query successfully");
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

	public void new_btn()
	{
	  area_note.setText("");
	  int year,month;
	  
	  
	  year = Integer.parseInt(lab_show_date.getText().substring(0,4));
	  month = Integer.parseInt(lab_show_date.getText().substring(7,9));
	  System.out.println(year);
	  System.out.println(month);
	  date_btn_create(year,month);
	}

}
/*
	private static  void btnActionPerformed(ActionEvent evt)//日期按鈕按下觸發事件開始
	  {
	    mf.area_note.setText("");
	    String year,month,btn_date,filename,read_str;
	    year = mf.lab_show_date.getText().substring(0,4);//取得年
	    month = mf.lab_show_date.getText().substring(7,9);//取得月
	    btn_date = evt.getActionCommand();//取得按下按鈕文字(日)
	    
	    mf.jLabel7.setText(btn_date);
	    DataService ds = new DataService(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(btn_date));
	    
	    if(!ds.exist())
	    {
	    	 mf.lab_show_test.setText("当日无读书计划");
		      mf.lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	    }
	    else
	    {
	    	String content = ds.getContent();
	    	 mf.area_note.setText(content);
	    	 if(content!=null&&!content.equals(""))
	    	 {	mf.lab_show_test.setText("读书日历");
		      	mf.lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	    	 }
	    	 else
	    	 {
	    		 mf.lab_show_test.setText("当日读书计划");
			      mf.lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	    	 }
		      
	    }
	*/
/*    public void rebuild(int year, int month) {
		
    	
    	smonth = "" + month;
		btn = new JButton[date_acc];
		
		if (smonth.length() == 1){
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
		week_of_day = dow(year, month, 1);//呼叫星期函數(取得當月第一天為星期幾)
		week_add = 27 * week_of_day;
		//建立日期按鈕陣列
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
			
			if (DataService.exist(year, month, i + 1) == true) {//若當天有記事檔案則設定按鈕字體顏色為白色
				btn[i].setForeground(new java.awt.Color(255, 255, 255));
			}

			btn[i].addActionListener(new ActionDispatcher(ActionType.Pressed));
			x++;//下一個按鈕X座標加權
			x_add += 0;//下一個按鈕間隔X座標加權
		}
    }	
	
	
	
	
	public static void new_btn()//重新產生日期按鈕函數開始
	{
	    //mf.area_note.setText("");//清空記事
		int year,month;
	    year = Integer.parseInt(mf.lab_show_date.getText().substring(0,4));//設定為已選擇的年
	    month = Integer.parseInt(mf.lab_show_date.getText().substring(7,9));//設定為已選擇的月
	    date_btn_create(year,month);//呼叫產生日期按鈕函數
	}//重新產生日期按鈕函數結束
	
	public static void date_btn_create(int year,int month)//產生日期按鈕
	{
		remove(mf.calendarPane);//移除桌面2(日期按鈕附著，也就是把日期按鈕移除)
	    calendarPane = new CalendarPane(mf);//產生一個新的桌面
	    calendarPane.rebuild(year, month);
	    mainDesktop.add(mf.calendarPane);
	    mainDesktop.remove(mf.mainDesktop.lab);//移除桌面2(日期按鈕附著，也就是把日期按鈕移除)
	    mainDesktop.add(mf.mainDesktop.lab);
	}
	*/
	