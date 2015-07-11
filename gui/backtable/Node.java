package backtable;

import java.io.Serializable;
import java.math.BigInteger;

public class Node implements Serializable {

    BigInteger value;
    int lastPOS;
    int firstPOS;

    @Override
    public String toString() {
        return value.toString() + "," + firstPOS + "\n";
    }
}
