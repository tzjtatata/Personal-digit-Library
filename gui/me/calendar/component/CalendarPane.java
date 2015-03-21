package me.calendar.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import me.calendar.frame.MainFrame;
import me.calendar.service.DataService;
import me.calendar.service.ActionDispatcher;
import me.calendar.service.ActionType;
import me.calendar.service.Utility;

public class CalendarPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame mf;
	JButton btn[];
	Color bg=new java.awt.Color(215,217,218);
	//DataService ds;
	public CalendarPane(MainFrame _mf)
	{
		super();
		mf=_mf;
		setLayout(null);
		setBounds(644, 245, 197, 180);
		setBackground(bg);
	}
	
	public void rebuild(int year,int month)
	{    
		int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
    String smonth,sday,filename;
    smonth=""+month;
    if(smonth.length()==1)smonth="0"+smonth;
	    switch(month)//設定月份天數
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
	        if (Utility.leap_year(year))
	          date_acc = 29;
	        else
	          date_acc = 28;
	    }
	   
	    week_of_day = Utility.dow(year,month,1);//呼叫星期函數(取得當月第一天為星期幾)
	    week_add=27*week_of_day;

	    btn = new JButton[date_acc];//建立日期按鈕陣列
	    for (int i=0;i<date_acc;i++)//建立日期按鈕陣列回圈
	    {
	      btn[i] = new JButton();//建立對應日期按鈕
	      mf.calendarPane.add(btn[i]);//加到桌面2上

	      btn[i].setText(String.valueOf(i+1));//設定按鈕文字為日期
	      if ((i-week_of_day>0 && (i+week_of_day)%7==0) || ((i+week_of_day)%7==0 && i != 0))
	      {//設定當月第一天日期按鈕位置
	        x=0;//X軸座標
	        x_add=0;//下一個按鈕座標(X軸)加值
	        y++;//Y軸座標
	        y_add+=0;//換行座標(Y軸)加值
	        week_add=0;//當月第一日按鈕座標加值
	      }//下面設定按鈕大小及加值(X為起始10+第幾個按鈕*橫向寬度25+間隔+當月第一天星期幾加值)
	      btn[i].setBounds(x*27+x_add+week_add, y*30+y_add, 27, 30);//(Y為第幾個按鈕*高度20+換行加值)按鈕寬為25高為20
	      btn[i].setFont(new java.awt.Font("Leto",1,12));//設定字體大小及樣式
	      btn[i].setForeground(new java.awt.Color(39, 158,218));
	      btn[i].setBorder(null);
	      btn[i].setBorder(BorderFactory.createTitledBorder(""));//設定按鈕文字不自動調整大小
	      int[] now = new int[3];
	      now = Utility.getdate();//取得當天日期
	      if (year == now[0] && month == now[1] && i+1 == now[2])
	        btn[i].setBackground(new java.awt.Color(100,245,50));//若為當天則設定按鈕為白色
	      else
	        btn[i].setBackground(new java.awt.Color(215,217,218));//若不是當天則設定按鈕為蓝色
	      /* 
	      sday = i+1+"";
	      filename = year+smonth+sday;//記事檔案名稱(年+月+日.txt)
	      File file=new File(filename+".txt");//建立檔案物件
	      */
	      //ds = new DataService(year,month,i+1);
	      if (DataService.exist(year, month, i+1) == true)//若當天有記事檔案則設定按鈕字體顏色為白色
	        btn[i].setForeground(new java.awt.Color(255,255,255));
	     
	      btn[i].addActionListener(new ActionDispatcher(ActionType.Pressed));
	      x++;//下一個按鈕X座標加權
	      x_add+=0;//下一個按鈕間隔X座標加權
	    }
	}
}
