package me;

import java.io.File;
import backtable.Search;
import backtable.ReverseSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import me.calendar.frame.MainFrame;

public class Main {

	public static void main(String[] args) throws Exception// 主程式開始
	{
		File fr = new File("gui/backtable/flag.pdl");
		BufferedReader br = new BufferedReader(new FileReader(fr));
		if ("0".equals(br.readLine())) {
			Search search = new Search();
			search.SearchDish("/home/liyuanze/test/");
			/*File[] list = File.listRoots();
			for (File list1 : list) {
				search.SearchDish(list1.toString());
			}*/
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fr))) {
				bw.write("1");
			}
			br.close();
		}
		new ReverseSet();
		MainFrame inst = new MainFrame();
		inst.setVisible(true);
	}

}
