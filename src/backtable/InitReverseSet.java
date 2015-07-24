package backtable;

import java.io.*;
import com.alibaba.fastjson.*;
import java.math.BigInteger;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import backtable.NewSearch;
import static backtable.NewSearch.fileMap;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitReverseSet {

    private HashMap<BigInteger, ArrayList<BigInteger>> data = new HashMap<>();
    private ArrayList<String> Paths = new ArrayList<>();
    public CountDownLatch threadSignal;

    public InitReverseSet() throws Exception {
        long startTime = System.currentTimeMillis();
        //change();
        getPath();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) / 1000.0 + "s");
    }

    public static BigInteger HashString(String key) {
        int ch;
        int len = key.length(), i;
        BigInteger Hash = BigInteger.valueOf(0);
        for (i = 0; i < len; i++) {
            ch = key.charAt(i);
            Hash = Hash.multiply(BigInteger.valueOf(33)).add(BigInteger.valueOf((long) ch));
        }
        return Hash;
    }

    public void getPath() throws Exception {
        NewSearch.ReadJson();
        int counter = 0;
        for (String key : fileMap.get(".txt").keySet()) {
            counter += fileMap.get(".txt").get(key).size();
        }
        threadSignal = new CountDownLatch(counter);
        fileMap.get(".txt").keySet().stream().forEach((s1) -> {
            fileMap.get(".txt").get(s1).stream().forEach(s2 -> {
                try {
                    new Thread(new ReadFile(new File(s1 + "\\" + s2), threadSignal)).start();
                } catch (Exception ex) {
                    Logger.getLogger(InitReverseSet.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
        threadSignal.await();
        File fileJson = new File("setFile/dict.json");
        System.out.println("搜索完毕，开始写入json！");
        String jsonString = JSON.toJSONString(data);
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileJson), "UTF-8"))) {
            br.write(jsonString);
            System.out.println("json写入完成！");
        }
        fileJson = new File("setFile/data.json");
        System.out.println("搜索完毕，开始写入json！");
        jsonString = JSON.toJSONString(Paths);
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileJson), "UTF-8"))) {
            br.write(jsonString);
            System.out.println("json写入完成！");
        }
    }
//        public void Read(File file)  throws Exception{
//            ArrayList<String> words = new ArrayList<>();
//            String filePath = file.getPath();
//            System.out.println(file);
//            words = NewSearch.analyzerOfPdl.fileAnalyze(file);
//            Paths.add(filePath);
//            Add(words,Paths.indexOf(filePath));
//        }

    public class ReadFile implements Runnable {

        private File file;
        private ArrayList<String> words = new ArrayList<>();
        private CountDownLatch threadsSignal;

        public ReadFile(File file, CountDownLatch threadsSignal) {
            this.file = file;
            this.threadsSignal = threadsSignal;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始...");
                String filePath = file.getPath();
                String fileEncode = EncodingDetect.getJavaEncode(filePath);
                String fileContent = FileUtils.readFileToString(new File(filePath), fileEncode);
                if (fileContent.length() <= 1000000) {
                    Analyze.testCJK(fileContent, words);
                }
                Paths.add(filePath);
                Add(words, Paths.indexOf(filePath));
                System.out.println(Thread.currentThread().getName() + "结束. 还有" + threadsSignal.getCount() + " 个线程");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            threadsSignal.countDown();
        }
    }

    public synchronized void Add(ArrayList<String> words, int Num) {
        for (String word : words) {
            BigInteger Hashc = HashString(word);
            BigInteger No;
            No = new BigInteger(String.valueOf(Num));
            if (data.containsKey(Hashc)) {
                data.get(Hashc).add(No);
            } else {
                ArrayList<BigInteger> temp = new ArrayList<>();
                temp.add(No);
                data.put(Hashc, temp);
            }
        }
    }
    /*
     public static void main(String[] args) throws Exception {
     new InitReverseSet();
     }
     */
}
