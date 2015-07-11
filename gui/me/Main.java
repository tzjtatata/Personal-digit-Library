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
        JSplashWindow.getABC();
        long pre = System.currentTimeMillis();
        long post;
        File fr = new File("gui/backtable/flag.pdl");
        BufferedReader br = new BufferedReader(new FileReader(fr));
        if ("0".equals(br.readLine())) {
            Search search = new Search();
            search.Init();
            /*File[] list = File.listRoots();
             for (File list1 : list) {
             if (!list1.toString().startsWith(String.valueOf(System.getProperty("user.home").charAt(0)))) {
             search.SearchDish(list1.toString());
             }
             }*/
            search.SearchDish("D:/");
			//search.SearchDish("E:/github/test/");
            //search.SearchDish("/home/liyuanze/test/");
            System.out.println(System.currentTimeMillis() - pre);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fr))) {
                bw.write("1");
            }
            br.close();
            //ReverseSet reverseSet = new ReverseSet();
        }
        File fi = new File("gui/me/showflag.pdl");
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
