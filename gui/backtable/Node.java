package backtable;

import java.math.BigInteger;

public class Node {
    BigInteger value;
    int lastPOS;
    public String toString(){
        return value.toString()+","+lastPOS+"\n";
    }
}
