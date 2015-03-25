package backtable;

import java.io.Reader;
import java.io.StringReader;

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

public class Analyze  {
    private static String testString1 = "2008年8月8日晚举世瞩目目的北京第二十九届奥林匹克运动会开幕式在国国家体育场隆重举行";
    private static String testString2 = "比尔盖茨从事餐饮业和服务业方面的工作";
   // @SuppressWarnings("deprecation")
	public static void testStandard(String testString) throws Exception{
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
	public static void testCJK(String testString) throws Exception{
   //     @SuppressWarnings("deprecation")
		Analyzer analyzer = new CJKAnalyzer();      
        Reader r = new StringReader(testString);      
        StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
        System.err.println("=====cjk analyzer====");
        System.err.println("分析方法:交叉双字分割");
        Token t;      
        while ((t = sf.next()) != null) {      
            System.out.println(t.termText());      
        }     
    }
    public static void testPaoding(String testString) throws Exception{
        XAnalyzer analyzer = XFactory.getQueryAnalyzer();   
        Reader r = new StringReader(testString);   
        XTokenizer ts = (XTokenizer) analyzer.tokenStream("", r);   
        System.err.println("=====paoding analyzer====");
        System.err.println("分析方法:字典分词,去掉停止词。在字典不能匹配的情况下使用CJKAnalyzer的分割发。");
        Token t;   
        while ((t = ts.next()) != null) {   
           System.out.println(t.termText());   
        }   
    }
    public static void testJe(String testString) throws Exception{
        Analyzer analyzer = new IK_CAnalyzer();
        Reader r = new StringReader(testString); 
        TokenStream ts = (TokenStream)analyzer.tokenStream("", r);
        System.err.println("=====je analyzer====");
        System.err.println("分析方法:字典分词,正反双向搜索，具体不明");
        Token t;   
        while ((t = ts.next()) != null) {   
           System.out.println(t.termText());   
        }   
    }
    public static void main(String[] args) throws Exception{
        String testString = testString1;
        System.out.println(testString);
        
        testStandard(testString);
        testCJK(testString);
     //   testPaoding(testString);
        
        testJe(testString);
    }

}