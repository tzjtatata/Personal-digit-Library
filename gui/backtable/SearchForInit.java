/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtable;

import com.alibaba.fastjson.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 新的Search类，将取代原有的backtable.Search
 *
 * @author 开发
 */
public class SearchForInit {

    public static File fileJson = new File("D:/study/Personal-digit-Library/gui/backtable/fileInfo.json");
    public static HashMap<String, HashMap<String, ArrayList<String>>> fileMap = new HashMap<>();
    public static Thread[] threads = new Thread[1000];
    public static int count = 0;

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Init();
        /*
         File[] roots = File.listRoots();
         for (File root : roots) {
         if (!root.toString().startsWith(String.valueOf(System.getProperty("user.home").charAt(0))) && root.listFiles() != null) {
         File[] files = root.listFiles((File pathname) -> !pathname.isHidden());
         for (File file : files) {
         if (file.isDirectory() && file.listFiles() != null) {
         threads[count] = new Thread(new ThreadTest(file));
         threads[count].start();
         count++;
         } else if (file.getName().endsWith(".txt")) {
         PutToMap(file, ".txt");
         } else if (file.getName().endsWith(".pdf")) {
         PutToMap(file, ".pdf");
         } else if (file.getName().endsWith(".doc")) {
         PutToMap(file, ".doc");
         }
         }
         }
         }
         */
        String jsonString = JSON.toJSONString(fileMap);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileJson))) {
            br.write(jsonString);
        }
    }

    /**
     * 一些初始化操作，创建必须的文件等
     *
     * @throws IOException
     */
    public static void Init() throws IOException {
        if (!fileJson.exists()) {
            fileJson.createNewFile();
        }
        fileMap.put(".txt", new HashMap<>());
        fileMap.put(".pdf", new HashMap<>());
        fileMap.put(".doc", new HashMap<>());
    }

    /**
     * 根据传入的path搜索该路径下所有子文件及其子文件夹
     *
     * @param path 搜索路径
     * @throws java.lang.Exception
     */
    public static void SearchDish(String path) throws Exception {
        File file = new File(path);
        if (file.isDirectory()) {
            File flist[] = file.listFiles((File pathname) -> !pathname.isHidden()); // 过滤掉隐藏文件夹
            for (File flist1 : flist) {
                if (flist1.isDirectory() && flist1.listFiles() != null && flist1.listFiles().length != 0) {  //符合继续搜索的要
                    SearchDish(flist1.getPath());
                } else if (flist1.isFile()) {
                    if (flist1.getName().endsWith(".txt")) {
                        PutToMap(flist1, ".txt");
                    } else if (flist1.getName().endsWith(".pdf")) {
                        PutToMap(flist1, ".pdf");
                    } else if (flist1.getName().endsWith(".doc")) {
                        PutToMap(flist1, ".doc");
                    }
                }
            }
        }
    }

    public static void PutToMap(File flist1, String s) {
        if (!fileMap.get(s).containsKey(flist1.getParent())) {
            fileMap.get(s).put(flist1.getParent(), new ArrayList<>());
        }
        fileMap.get(s).get(flist1.getParent()).add(flist1.getName());
    }

    static class ThreadTest implements Runnable {

        File flist1;

        public ThreadTest(File flist1) {
            this.flist1 = flist1;
        }

        @Override
        public void run() {
            try {
                SearchDish(flist1.getPath());
            } catch (Exception ex) {
                System.err.println(flist1.getPath());
            }
        }

    }
}
