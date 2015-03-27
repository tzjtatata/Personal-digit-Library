package backtable;

import java.math.*;
import java.util.ArrayList;

public class Node {

	ArrayList<String> data = new ArrayList();
	BigInteger hashcode;
	int count = 0;
	Node next;

	Node(BigInteger da) {
		hashcode = da;
		next = null;
	}

	public void add(String da) {
		data.add(da);
		count++;
	}

	public String toString() {
		String s = "###" + hashcode + ":";
		int i;
		for (i = 0; i < count; i++) {
			s = s.concat(data.get(i) + ',');
		}
		return s;
	}
}
