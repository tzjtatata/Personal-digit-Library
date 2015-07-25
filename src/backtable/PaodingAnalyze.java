/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtable;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import net.paoding.analysis.analyzer.PaodingAnalyzer;
import net.paoding.analysis.analyzer.estimate.TryPaodingAnalyzer;

/**
 *
 * @author 开发
 */
public class PaodingAnalyze {

    public static ArrayList Zanalyze(String string) throws Exception {
        Analyzer analyzer = new PaodingAnalyzer();
        ArrayList<String> al = new ArrayList<>();
        StringReader reader = new StringReader(string);
        TokenStream ts = analyzer.tokenStream(string, reader);
        Token t = ts.next();
        while (t != null) {
            al.add(t.termText());
            //System.out.println(al);
            t = ts.next();
        }
        al = new ArrayList(new HashSet<>(al));
        return al;
    }
    /*
     public static void main(String[] args) throws IOException, Exception {
     long ty = System.currentTimeMillis();
     long m;
     File f = new File("D:/yyy/a.txt");
     ArrayList<String> al = new ArrayList<>();
     Analyzer analyzer = new PaodingAnalyzer();
     TokenStream ts = analyzer.tokenStream("", (Reader) TryPaodingAnalyzer.read(f.getPath(), "gbk", false));
     //m = System.currentTimeMillis();
     //System.err.println("设置完毕，耗时" + (m - ty) + "ms.");
     Token t;
     LinkedList list = new LinkedList();
     while ((t = ts.next()) != null) {
     list.add(t);
     //System.err.println(list);
     }
     m = System.currentTimeMillis();
     System.err.println("分词完毕，耗时" + (ty - m) + "ms.");
     System.err.println(list.size());
     }
     */
}
