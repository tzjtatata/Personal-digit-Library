/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtable;

import com.alibaba.fastjson.*;
import gui.SetUp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * 新的Search类，将取代原有的backtable.Search
 *
 * @author 开发
 */
public class NewSearch {

    public static File fileJson = new File("gui/backtable/fileInfo.json");
    public static HashMap<String, HashMap<String, ArrayList<String>>> fileMap = new HashMap<>();
    private static final ExecutorService executors = Executors.newCachedThreadPool();

    /**
     * 一些初始化操作，创建必须的文件等
     *
     * @param how 为0需要搜索全盘
     * @throws IOException
     * @throws java.lang.InterruptedException
     */
    @SuppressWarnings("empty-statement")
    public static void Init(int how) throws Exception {
        //迭代版本
        if (how == 0) {
            long pre = System.currentTimeMillis();
            System.out.println("开始设置哈希map！");
            if (!fileJson.exists()) {
                fileJson.createNewFile();
            }
            fileMap.put(".txt", new HashMap<>());
            fileMap.put(".pdf", new HashMap<>());
            fileMap.put(".doc", new HashMap<>());
            System.out.println("哈希map设置完毕!" + (System.currentTimeMillis() - pre));
            pre = System.currentTimeMillis();
            System.out.println("开始搜索全盘！" + (System.currentTimeMillis() - pre));
            pre = System.currentTimeMillis();
            File[] roots;
            roots = File.listRoots();//获取所有磁盘盘符
            if (!SetUp.setMap.get("range").get("range").equals("all")) {
                String s = (String) SetUp.setMap.get("range").get("range");
                File f = new File(s);
                roots = new File[]{f};
            }
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
            System.out.println("搜索完毕，开始写入json！" + (System.currentTimeMillis() - pre));
            pre = System.currentTimeMillis();
            String jsonString = JSON.toJSONString(fileMap);
            try (BufferedWriter br = new BufferedWriter(new FileWriter(fileJson))) {
                br.write(jsonString);
                System.out.println("json写入完成！" + (System.currentTimeMillis() - pre));
                pre = System.currentTimeMillis();
            }

            System.out.println("进行自动分类！" + (System.currentTimeMillis() - pre));
            pre = System.currentTimeMillis();
            Classify();
            System.out.println("自动分类完成，程序结束！" + (System.currentTimeMillis() - pre));
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

    /**
     * 搜索
     *
     * @param title
     * @return 动态数组
     */
    public static ArrayList<String> SearchTitle(String title) {
        ArrayList<String> al = new ArrayList<>();
        fileMap.keySet().stream().forEach((String s) -> {
            //txt等类别
            fileMap.get(s).keySet().stream().forEach((String s1) -> {
                //路径
                fileMap.get(s).get(s1).stream().filter((s2) -> (s2.contains(title))).forEach((s2) -> {
                    al.add(s1 + "\\" + s2);
                });
            });
        });
        return al;
    }

    /**
     * 从fileJson文件中读取json字符串获得HashMap
     *
     * @return
     * @throws Exception
     */
    public static HashMap ReadJson() throws Exception {
        String jsonString;
        try (BufferedReader br = new BufferedReader(new FileReader(fileJson))) {
            jsonString = br.readLine();
        }
        fileMap = JSON.parseObject(jsonString, new TypeReference<HashMap<String, HashMap<String, ArrayList<String>>>>() {
        });
        return fileMap;
    }

    /**
     * 自动分类
     *
     * @throws Exception
     */
    public static void Classify() throws Exception {
        HashMap<String, String[]> classDataMap = new HashMap<>();  //分类用到的原始数据的Map
        HashMap<String, ArrayList<String>> classMap = new HashMap<>();  //要写入的map
        try (BufferedReader br = new BufferedReader(new FileReader("gui/backtable/classData.pdl"))) {
            while (br.ready()) {
                String line = br.readLine();
                String lines[] = line.split("/");
                if (lines[2].length() > 2) {
                    //将classData.pdl的分类作为key，正则表达式和书籍构成的数组作为value。
                    classDataMap.put(lines[0], new String[]{lines[1], lines[2]});
                    classMap.put(lines[0], new ArrayList<>());
                }
            }
        }
        classMap.put("TXT", new ArrayList<>());
        //分类，对每个文件名，进行分词匹配。将文件名两两分词，如果有词匹配到正则表达式，则匹配率视为1。否则匹配后面的书，匹配数/分词总数为匹配率，
        //匹配率最高的分类且大于一定比例，归入此分类。否则归入其他/TXT。
        fileMap.keySet().stream().forEach((type) -> {
            fileMap.get(type).keySet().stream().forEach((path) -> {
                fileMap.get(type).get(path).stream().forEach((file) -> {
                    int flag = 1;
                    if (!file.endsWith(".txt")) { // txt单分一类
                        int find = 0;
                        double num = 0; //匹配率
                        int correct = 0;
                        String ofCate = "其它"; //最终决定所属分类
                        ArrayList<String> word = new ArrayList<>();
                        //分词
                        try {
                            Analyze.testCJK(file, word);
                        } catch (Exception ex) {
                            Logger.getLogger(NewSearch.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (String cate : classDataMap.keySet()) {
                            correct = 0;
                            //用该分类下的正则作为正则匹配文件名
                            if (!cate.equals("其它") && Pattern.compile(classDataMap.get(cate)[0]).matcher(file).find()) {
                                num = 1;
                                find = 1;
                                ofCate = cate;
                            } else {
                                for (String word1 : word) {
                                    if (Pattern.compile(word1).matcher(classDataMap.get(cate)[1]).find()) {
                                        correct++;
                                    }
                                }
                            }
                            if (find == 1) {  //找到正则的匹配，跳出循环
                                break;
                            } else {//否则筛选正确率
                                double zhengque = correct * 1.0 / word.size();
                                if (zhengque > num && zhengque > 0.4) {
                                    num = zhengque;
                                    ofCate = cate;
                                    flag = 0;
                                }
                            }
                            //分类需要修改的地方
                        }
                        if (num > 0) {
                            classMap.get(ofCate).add(path + "\\" + file);
                            flag = 0;
                        }
                    } else {
                        classMap.get("TXT").add(path + "\\" + file);
                        flag = 0;
                    }
                    if (flag == 1) {
                        classMap.get("其它").add(path + "\\" + file);
                    }
                });
            });
        });
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("gui/backtable/class.pdl"))) {
            classMap.keySet().stream().forEach((cate) -> {
                try {
                    if (classMap.get(cate).size() > 0) {
                        bw.write(cate + "/" + classMap.get(cate).toString().substring(1, classMap.get(cate).toString().length() - 2) + "\n");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(NewSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
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
