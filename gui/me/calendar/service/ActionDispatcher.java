package me.calendar.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.calendar.frame.MainFrame;

public class ActionDispatcher implements ActionListener {
	 
	  static MainFrame mf;
	  ActionType type;
	  
	  public ActionDispatcher(ActionType at)
	  {
		  type=at;
	  }
	  public void dispatch(ActionEvent e)
	  {
		  switch(type)
		{
			case Pressed:btnActionPerformed(e);break;
			case Clear:clearActionPerformed(e);break;
			case Save:saveActionPerformed(e);break;
			case Query:queryActionPerformed(e);break;
		}
	  }
		@Override
		public void actionPerformed(ActionEvent e) {
			
			dispatch(e);
		}
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
	    /*
	    filename = year+month+btn_date;
	    try
	    {
	      FileReader fr = new FileReader(filename+".txt");//讀取選擇日期記事檔案
	      BufferedReader bfr = new BufferedReader(fr);//將檔案讀到緩衝區
	      boolean flag=false;//旗標
	      while((read_str = bfr.readLine())!=null) // 每次讀取一行，直到檔案結束
	      {
	        if (flag)//從第二行開始每一行第一個位置加入斷行
	          mf.area_note.append("\n");
	        mf.area_note.append(read_str);//加入該行訊息
	        flag=true;
	       
	      }
	      mf.lab_show_test.setText("当天记事");
	      mf.lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	      fr.close();
	    }catch(FileNotFoundException e)//如果沒有指定的記事檔案就印出當日無行事曆(例外處理)
	    {
	      mf.lab_show_test.setText("当日无行事历");
	      mf.lab_show_tip.setText("已选择"+year+"年"+month+"月"+btn_date+"日");
	    }catch(IOException e)//例外處理
	    {
	      e.printStackTrace();
	    }
	    */
	  }//日期按鈕按下觸發事件結束
	  
	  private static  void clearActionPerformed(ActionEvent evt)//清除按鈕按下觸發事件
	  {
	    
	    String year,month,day,filename;
	    year = mf.lab_show_date.getText().substring(0,4);
	    month = mf.lab_show_date.getText().substring(7,9);
	    day = mf.jLabel7.getText();
	    String insert_str = mf.area_note.getText();
	    mf.area_note.setText("");//清除記事內容
	    if (day.length() == 0)
	        mf.lab_show_test.setText("未选择日期");//設定相關訊息
	    else if(insert_str.length()==0)	 
	        mf.lab_show_test.setText("当日无读书计划");
	    else {
	    	DataService ds = new DataService(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
	    	if(ds.exist()) ds.delete();
		    /*
		    filename = year+month+day;
		    File file=new File(filename+".txt");//刪除當日記事檔案
		    file.delete();
		    */
		    Utility.new_btn();//重新產生按鈕
		    mf.lab_show_test.setText("计划已清除");//設定相關訊息
		    mf.jLabel7.setText("");
		    //mf.lab_show_tip.setText("未选择日期");
	    }
	    
	  }
	 
	  private static  void saveActionPerformed(ActionEvent evt)//儲存按鈕按下觸發事件開始
	  {
	    String year,month,day,filename,insert_str;
	    year = mf.lab_show_date.getText().substring(0,4);
	    month = mf.lab_show_date.getText().substring(7,9);
	    day = mf.jLabel7.getText();
	    insert_str = mf.area_note.getText();//記事內容
	    if (day.length() == 0)
	        mf.lab_show_test.setText("未选择日期");//設定相關訊息
	    else if(insert_str.length()==0)	 
	        mf.lab_show_test.setText("当日无读书计划");
	    else {
	    	DataService ds = new DataService(year,month,day);
		    
		    if (insert_str.length() != 0 && day.length() != 0)//若記事框內有文字且有選擇日期則儲存記事檔案
		    {
		    	 if(ds.save(insert_str))
		    	 { 	mf.lab_show_test.setText("已记录计划");//設定相關訊息
		    	 //Utility.new_btn();
			     	//mf.jLabel7.setText("");
			     	//mf.lab_show_tip.setText("未选择日期");
		    	 }
		    	 else
		    	 {
		    		 mf.lab_show_test.setText("记录失败"); 
		    	 }
			     //Utility.new_btn();

		    }
		    else//若無記事內容或無選擇日期
		    {
		      
		    }
		    Utility.new_btn();
	    }
	    }
	  //儲存按鈕按下觸發事件結束
	  private static   void queryActionPerformed(ActionEvent evt)//查詢按鈕按下觸發事件開始
	  {
	    String syear,smonth;
	    try
	    {
	      mf.area_note.setText("");
	      mf.lab_show_test.setText("查询日期");
	      syear = mf.text_year.getText();
	      smonth = String.valueOf(mf.cbox_month.getSelectedIndex() + 1);
	      if (smonth.length() == 1)
	            smonth = "0"+smonth;
	      if (syear == "" || Integer.parseInt(syear)<1582)//若未輸入年份就觸發例外(1582年以前曾經改曆過，結果會不準確)
	      {
	        int[] now = new int[3];
	        now = Utility.getdate();
	        syear = String.valueOf(now[0]);//若選擇年份小於1582年則預設為當年
	        mf.lab_show_test.setText("请选择1582年以上");
	      }
	      mf.lab_show_date.setText(syear+" 年 "+smonth+" 月");
	      Utility.date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
	     mf.jLabel7.setText("");
	     mf.lab_show_tip.setText("未选择日期");
	    }catch(NumberFormatException e)//例外處理設定為當年及選擇的月份
	    {
	      int[] now = new int[3];
	      now = Utility.getdate();
	      syear = String.valueOf(now[0]);
	      smonth = String.valueOf(mf.cbox_month.getSelectedIndex() + 1);
	      if (smonth.length() == 1)
	            smonth = "0"+smonth;
	     mf.lab_show_date.setText(syear+" 年 "+smonth+" 月");
	     mf.lab_show_test.setText("请选择1582年以上");
	      Utility.date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
	     mf.jLabel7.setText("");
	     mf.lab_show_tip.setText("未选择日期");
	    }
	  }

	public static void setMainFrame(MainFrame mf) {
		ActionDispatcher.mf = mf;
	}



}
