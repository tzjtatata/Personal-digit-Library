package me;

import java.io.File;
import backtable.Search;
import backtable.ReverseSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import me.calendar.frame.MainFrame;
import me.*;

public class Main {

	public static void main(String[] args) throws Exception// 主程式開始
	{
		JSplashWindow.getABC();
		long pre=System.currentTimeMillis();
		long post;
		File fr = new File("gui/backtable/flag.pdl");
		BufferedReader br = new BufferedReader(new FileReader(fr));
		if ("0".equals(br.readLine())) {
			Search search = new Search();
			search.SearchDish("/home/liyuanze/test/");
			new ReverseSet();
			/*File[] list = File.listRoots();
			for (File list1 : list) {
				search.SearchDish(list1.toString());
			}*/
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fr))) {
				bw.write("1");
			}
			br.close();
		}
		Thread.sleep(4000);//测试欢迎窗口用;
		post = System.currentTimeMillis();
		System.out.println(post-pre);
		File fi = new File("gui/me/showflag.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fi));
		bw.write("0");
		bw.close();
		MainFrame inst = new MainFrame();
		bw = new BufferedWriter(new FileWriter(fi));
		bw.write("1");
		bw.close();
		inst.setVisible(true);
	}

}
