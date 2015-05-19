package backtable;

import java.math.BigInteger;

public class Node {
    BigInteger value;
    int lastPOS;
    int firstPOS;
    public String toString(){
        return value.toString()+","+lastPOS+"\n";
    }
}
