package backtable;

public class Hashstr {
	public long nHashA;
	public int nHashB;
	public int bExists;
	public Hashstr() {
		this.bExists = 0;
	}
	public Hashstr(long hashString) {
		this.bExists = 1; 
	}
}
