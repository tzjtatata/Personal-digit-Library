package backtable;
import java.math.*;
import java.io.*;

public class Hashstr implements Serializable{
    String Filepath;
    int next;
    @Override
    public String toString(){
        return "{"+Filepath+","+next+"}";
    }
}
