package backtable;

public class Hashstr {
	static int round = 0x500;
	public List nHashA = new List();
	public int bExists;
	public Hashstr() {
		this.bExists = 0;
	}
	public Hashstr(long hashString,String file) {
		this.bExists = 1; 
		nHashA.insert(hashString,file);
	}
	public  void addhash(long hashString,String file) {
		nHashA.insert(hashString,file);
	}
	public String toString() {
		return nHashA.toString();
	}
}
