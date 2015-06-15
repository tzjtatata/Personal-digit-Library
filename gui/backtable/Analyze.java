package backtable;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.mira.lucene.analysis.IK_CAnalyzer;
import org.mira.lucene.analysis.MIK_CAnalyzer;

/*
 import com.sohospace.lucene.analysis.xanalyzer.XAnalyzer;
 import com.sohospace.lucene.analysis.xanalyzer.XFactory;
 import com.sohospace.lucene.analysis.xanalyzer.XTokenizer;*/
public class Analyze {

    private static String testString1 = "2015国际田联钻石联赛美国尤金站比赛中， 苏炳添以9秒99成绩获得男子100米季军，成为首个跑进10秒的亚洲本土选手。";
    private static String testString2 = "比尔盖茨从事餐饮业和服务业方面的工作";

    // @SuppressWarnings("deprecation")
    public static void testStandard(String testString) throws Exception {
        //      @SuppressWarnings("deprecation")
        Analyzer analyzer = new StandardAnalyzer();
        Reader r = new StringReader(testString);
        StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
        System.err.println("=====standard analyzer====");
        System.err.println("分析方法：默认没有词只有字");
        Token t;
        while ((t = sf.next()) != null) {
            System.out.println(t.termText());
        }
    }

    // @SuppressWarnings("deprecation")
    public static void testCJK(String testString, ArrayList<String> word) throws Exception {
        //     @SuppressWarnings("deprecation")
        Analyzer analyzer = new CJKAnalyzer();
        String temp;
        Reader r = new StringReader(testString);
        StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
        //System.err.println("=====cjk analyzer====");
        //System.err.println("分析方法:交叉双字分割");
        Token t;
        while ((t = sf.next()) != null) {
            temp = t.termText();
            if (word.contains(temp)) {
                continue;
            }
            word.add(temp);
        }
        // System.out.println(word.toString());
    }
    /*public static void testPaoding(String testString) throws Exception{
     XAnalyzer analyzer = XFactory.getQueryAnalyzer();
     Reader r = new StringReader(testString);
     XTokenizer ts = (XTokenizer) analyzer.tokenStream("", r);
     //System.err.println("=====paoding analyzer====");
     //System.err.println("分析方法:字典分词,去掉停止词。在字典不能匹配的情况下使用CJKAnalyzer的分割发。");
     Token t;
     while ((t = ts.next()) != null) {
     System.out.println(t.termText());
     }
     }*/

    public static void testJe(String testString) throws Exception {
        Analyzer analyzer = new IK_CAnalyzer();
        Reader r = new StringReader(testString);
        TokenStream ts = (TokenStream) analyzer.tokenStream("", r);
        // System.err.println("=====je analyzer====");
        //System.err.println("分析方法:字典分词,正反双向搜索，具体不明");
        Token t;
        while ((t = ts.next()) != null) {
            System.out.println(t.termText());
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> word = new ArrayList<>();
        System.out.println(testString1);
        System.out.println("第一种双字分词:\n");
        testCJK(testString1, word);
        System.out.println("第二种分词:\n");
        testJe(testString1);
    }
}
