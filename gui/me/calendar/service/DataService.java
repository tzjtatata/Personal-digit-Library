package me.calendar.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataService {
	int year,month,day;
	String fileName;
	File file;
	public DataService(int _year,int _month,int _day)
	{
		year=_year;month=_month;day=_day;
		String smonth=month+"";
		if(smonth.length()==1)smonth="0"+smonth;
		fileName = year+smonth+day;
		file=new File(fileName+".txt");
	}
	
	public DataService(String _year,String _month,String _day)
	{
		
		this(Integer.parseInt(_year),Integer.parseInt(_month),Integer.parseInt(_day));
		/*
		year=_year;month=_month;day=_day;
		String filename,smonth=month+"";
		if(smonth.length()==1)smonth="0"+smonth;
		filename = year+smonth+day;
		file=new File(fileName+".txt");
		*/
	}
	public static boolean exist(int _year,int _month,int _day)
	{
		
		String filename;
		String smonth=_month+"";
		if(smonth.length()==1)smonth="0"+smonth;
		 filename = _year+smonth+_day;
		File file=new File(filename+".txt");
		return file.exists();
	}
	public boolean exist()
	{
		return file.exists();
	}
	public String getContent()
	{
		if(!file.exists())return null;
		String read_str;
		StringBuffer sb = new StringBuffer();
		FileReader fr;
		 BufferedReader bfr;
		try {
			fr = new FileReader(file);
			 bfr= new BufferedReader(fr);//將檔案讀到緩衝區
		      boolean flag=false;//旗標
		      while((read_str = bfr.readLine())!=null) // 每次讀取一行，直到檔案結束
		      {
		        if (flag)//從第二行開始每一行第一個位置加入斷行
		          sb.append("\n");
		        sb.append(read_str);//加入該行訊息
		        flag=true;
		       
		      }
		      fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}//讀取選擇日期記事檔案
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	    if(sb.toString().equals(""))return null;
		return sb.toString();
	}
	
	public boolean save(String str)
	{
		 FileWriter fw;
		 BufferedWriter bfw;//啟用緩衝區寫入
		try {
			fw = new FileWriter(file);
			bfw=new BufferedWriter(fw);
			 bfw.write(str); //將Textarea內容寫入緩衝區裡
		        bfw.flush();//將緩衝區資料寫到檔案
		        fw.close();//關閉檔案
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}//啟用檔案寫入
	     return true;
	       
	}
	public boolean delete()
	{
		return file.delete();
	}

}
