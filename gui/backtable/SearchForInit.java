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
import java.util.HashMap;

/**
 * 新的Search类，将取代原有的backtable.Search
 *
 * @author 开发
 */
public class SearchForInit {

    public static File fileJson = new File("D:/study/Personal-digit-Library/gui/backtable/fileInfo.json");
    public static HashMap<String, HashMap<String, ArrayList<String>>> fileMap = new HashMap<>();

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Init();
        SearchDish("D:/");
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
                if (flist1.isDirectory() && flist1.listFiles() != null && flist1.listFiles().length != 0) {  //符合继续搜索的要求
                    SearchDish(flist1.getPath());  //递归
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

}
