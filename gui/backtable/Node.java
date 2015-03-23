package backtable;

public class Node {
	String[] data =  new String[1000];
	long hashcode;
	int count = 0;
	Node next;
	Node(long da){
		hashcode = da;
		next = null;
	}
	public void add(String da) {
		data[count] = da;
		count++;
	}
	public String toString() {
		String s = "###" + hashcode + ":";
		int i;
		for (i = 0;i<count;i++){
			s = s.concat(data[i] + ',');
		}
		return s;
	}
}
