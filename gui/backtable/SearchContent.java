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
    private HashMap<BigInteger,ArrayList<BigInteger>> data = new HashMap<>();
    private  ArrayList<String> Paths = new ArrayList<>();
    public ArrayList<String> result = new ArrayList<>();
    BigInteger  target;
    public SearchContent(String target)  throws Exception{
        hash = ReverseSet.HashString(target);
        String jsonString;
        this.target = ReverseSet.HashString(target);
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
        for (BigInteger key:data.get(target)){
            result.add(Paths.get(key.intValue()));
        }
    }
}
