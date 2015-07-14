/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtable;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import net.paoding.analysis.analyzer.PaodingAnalyzer;

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
            t = ts.next();
        }
        al = new ArrayList(new HashSet<>(al));
        return al;
    }
}
