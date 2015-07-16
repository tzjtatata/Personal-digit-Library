package backtable;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import backtable.Analyze;
import static backtable.NewSearch.fileJson;
import static backtable.NewSearch.fileMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.sun.webkit.ContextMenu.ShowContext;

import backtable.ReverseSet;*/

public class SearchContent {
    private BigInteger hash;
    private static HashMap<BigInteger,ArrayList<BigInteger>> data = new HashMap<>();
    private static ArrayList<String> Paths = new ArrayList<>();
    public ArrayList<String> result = new ArrayList<>();
    BigInteger  target;
    public SearchContent(String target)  throws Exception{
        hash = ReverseSet.HashString(target);
        this.target = ReverseSet.HashString(target);
        if (data.containsKey(this.target)) {
            for (BigInteger key:data.get(this.target)){
                result.add(Paths.get(key.intValue()));
            }
        }
        
    }
    public static void Readall() throws Exception{
        String jsonString;
        File fileJson = new File("gui/backtable/data.json");
        try (BufferedReader br = new BufferedReader(new FileReader(fileJson))) {
            jsonString = br.readLine();
        }
        Paths = JSON.parseObject(jsonString, new TypeReference<ArrayList<String>>() {
        });
        fileJson = new File("gui/backtable/dict.json");
        try (BufferedReader br = new BufferedReader(new FileReader(fileJson))) {
            jsonString = br.readLine();
        }
        data = JSON.parseObject(jsonString, new TypeReference<HashMap<BigInteger,ArrayList<BigInteger>>>() {
        });
    }
}
