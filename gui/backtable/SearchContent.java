package backtable;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import backtable.Analyze;
import java.util.logging.Level;
import java.util.logging.Logger;

/*import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.sun.webkit.ContextMenu.ShowContext;

import backtable.ReverseSet;*/

public class SearchContent {
    private BigInteger hash;
    
    public SearchContent(String target)  throws Exception{
        hash = ReverseSet.HashString(target);
        ObjectInputStream f1 = new ObjectInputStream(new FileInputStream("gui/backtable/dic.pdl"));
        ObjectInputStream f2 = new ObjectInputStream(new FileInputStream("gui/backtable/data.pdl"));
        
    }
}
