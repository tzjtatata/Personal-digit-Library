/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtable;

import com.alibaba.fastjson.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 新的Search类，将取代原有的backtable.Search
 *
 * @author 开发
 */
public class SearchForInit {

    public static File fileJson = new File("D:/study/Personal-digit-Library/gui/backtable/fileInfo.json");
    public static HashMap<String, HashMap<String, ArrayList<String>>> fileMap = new HashMap<>();
    private static int count = 0;
    private static ExecutorService executors = Executors.newCachedThreadPool();

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //线程版本
        /*
         long pre = System.currentTimeMillis();
         Init();
         File[] roots = File.listRoots();
         for (File root : roots) {
         if (!root.toString().startsWith(String.valueOf(System.getProperty("user.home").charAt(0))) && root.listFiles() != null) {
         File[] files = root.listFiles((File pathname) -> !pathname.isHidden());
         for (File file : files) {
         if (file.isDirectory() && file.listFiles() != null) {
         SearchDish(file);
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
         System.out.println(System.currentTimeMillis() - pre);

         System.out.println(System.currentTimeMillis() - pre);*/
        Init();

    }

    /**
     * 一些初始化操作，创建必须的文件等
     *
     * @throws IOException
     * @throws java.lang.InterruptedException
     */
    @SuppressWarnings("empty-statement")
    public static void Init() throws IOException, InterruptedException {
        if (!fileJson.exists()) {
            fileJson.createNewFile();
        }
        fileMap.put(".txt", new HashMap<>());
        fileMap.put(".pdf", new HashMap<>());
        fileMap.put(".doc", new HashMap<>());
        //迭代版本
        File[] roots = File.listRoots();//获取所有磁盘盘符
        for (int i = roots.length - 1; i >= 0; i--) {
            if (!roots[i].toString().startsWith(String.valueOf(System.getProperty("user.home").charAt(0))) && roots[i].listFiles() != null) {
                for (File root : roots[i].listFiles((File pathname) -> !pathname.isHidden())) {
                    if (root.isDirectory()) {
                        executors.execute(new ThreadImpl(root));
                    } else if (root.getName().endsWith(".txt")) {
                        PutToMap(root, ".txt");
                    } else if (root.getName().endsWith(".pdf")) {
                        PutToMap(root, ".pdf");
                    } else if (root.getName().endsWith(".doc")) {
                        PutToMap(root, ".doc");
                    }
                }
            }
        }
        executors.shutdown();
        executors.awaitTermination(1000000000, TimeUnit.DAYS);
        String jsonString = JSON.toJSONString(fileMap);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileJson))) {
            br.write(jsonString);
        }
    }

    /**
     * 根据传入的path搜索该路径下所有子文件及其子文件夹（递归版）
     *
     * @param file
     * @throws java.lang.Exception
     */
    public static void SearchDish(File file) throws Exception {
        if (file.isDirectory()) {
            File flist[] = file.listFiles((File pathname) -> !pathname.isHidden()); // 过滤掉隐藏文件夹
            for (File flist1 : flist) {
                if (flist1.isDirectory() && flist1.listFiles() != null) {  //符合继续搜索的要

                    executors.execute(new Thread() {
                        @Override
                        public void run() {
                            try {
                                SearchDish(flist1);
                            } catch (Exception ex) {
                                Logger.getLogger(SearchForInit.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                }
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

    /**
     * 非递归版搜全盘
     *
     * @param root
     */
    public static void InitSearch(File root) {
        Stack<String> folderStack = new Stack<>();//存放文件夹的临时栈

        folderStack.push(root.toString());
        while (!folderStack.isEmpty())// 遍历
        {
            File[] fl = new File(folderStack.pop()).listFiles();
            if (fl == null) {
                continue;
            }
            for (File fl1 : fl) {
                if (fl1.isDirectory()) {
                    folderStack.push(fl1.getAbsolutePath());
                } else {
                    if (fl1.getName().endsWith(".txt")) {
                        PutToMap(fl1, ".txt");
                    } else if (fl1.getName().endsWith(".pdf")) {
                        PutToMap(fl1, ".pdf");
                    } else if (fl1.getName().endsWith(".doc")) {
                        PutToMap(fl1, ".doc");
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

    private static class ThreadImpl extends Thread {

        private final File root;

        public ThreadImpl(File root) {
            this.root = root;
        }

        @Override
        public void run() {
            InitSearch(root);
        }
    }

}
