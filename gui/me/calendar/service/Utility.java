package me.calendar.service;

import java.awt.Font;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import me.calendar.component.CalendarPane;
import me.calendar.frame.MainFrame;

public class Utility {
	static MainFrame mf;
	public static Font en11=new java.awt.Font("Segue",0,11);
	public static Font en12=new java.awt.Font("Leto",0,12);
	public static Font cn11=new java.awt.Font("微软雅黑",0,11);
	public static Font cn12=new java.awt.Font("微软雅黑",0,12);
	
	public static void setMainFrame(MainFrame _mf)
	{
		mf = _mf;
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
	

	   
	    mf.mainDesktop.remove(mf.calendarPane);//移除桌面2(日期按鈕附著，也就是把日期按鈕移除)
	    mf.calendarPane = new CalendarPane(mf);//產生一個新的桌面
	    mf.calendarPane.rebuild(year, month);
	    mf.mainDesktop.add(mf.calendarPane);
	    mf.mainDesktop.remove(mf.mainDesktop.lab);//移除桌面2(日期按鈕附著，也就是把日期按鈕移除)
	    mf.mainDesktop.add(mf.mainDesktop.lab);

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
	  public static int[] getdate()//取得系統日期函數開始
	  {
	    int[] date_array = new int[3];
	    Calendar ca = new GregorianCalendar();  
	    date_array[0] = ca.get(Calendar.YEAR);//年
	    date_array[1] = ca.get(Calendar.MONTH)+1;//月
	    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//日
	    return date_array;//回傳自訂日期陣列
	  }//取得系統日期函數結束
	  
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
}
