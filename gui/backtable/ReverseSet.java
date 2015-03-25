package backtable;

import java.io.*;
import java.util.ArrayList;

import backtable.Search.*;

import backtable.Hashstr;
import backtable.Analyze;

public class ReverseSet {

	//获取txtFolder文件夹下的所有文件

	protected File ft = new File("gui/backtable/txtFolder");
	protected File[] ls = ft.listFiles();
	protected String str;
	protected File temp;
	static int round = 0x500;
	int i;
	public long[] cryptTable = new long[round];
	Hashstr[] lpTable = new Hashstr[round];

	public ReverseSet() throws IOException {
		//prepareCryptTable();
		//对目录下每个txt读取，获取子文件目录
		//读取txt文件内容
		for (i = 0;i<ls.length;i++) {
				if (!ls[i].isHidden()) {
					//获取txtFolder目录下文件
					str = ls[i].getName();
					Search zkf = new Search();
					str = zkf.nameChange(str);
					temp = new File(str);
					FileReader fr = new FileReader(ls[i]);
					BufferedReader br = new BufferedReader(fr);
					String str2 = br.readLine();
					if (str2 == null) break;
					try {
						Refile(new File(str+"/"+str2));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		//Refile(fi);
		//最后将hash数组输出到倒排索引表中
		printf();
	}

	public void Refile(File ffi) throws Exception {
		long temp = 0;
		int i;
		ArrayList<String> word = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(ffi));
		String nw;
		while ((nw = br.readLine())!=null)
		//分词
			Analyze.testCJK(nw,word);
		System.out.println(word.toString());
		//prepareCryptTable();
		for (i = 0; i < word.size(); i++) {
			/*对每个词求其hash值，存进对应的数组项
			 * 并用链表存储其所在地址
			 */
			setpos(word.get(i), ffi.getName());
		}
	}

	public void prepareCryptTable() {
		long seed = 0x001000001L, index1 = 0, index2 = 0;
		int i;
		for (index1 = 0; index1 < 0x100; index1++) {
			for (index2 = index1, i = 0; i < 5; i++, index2 += 0x100) {
				long temp1, temp2;
				seed = (seed * 125 + 3) % 0x2AAAABL;
				temp1 = (seed & 0xFFFF) << 0x10;
				seed = (seed * 125 + 3) % 0x2AAAABL;
				temp2 = (seed & 0xFFFF);
				//System.out.println(index2);
				cryptTable[(int) index2] = (temp1 | temp2);
			}
		}
	}
	/*public long HashString(String key) {
	 long seed1 = 0x7FED7FEDL;
	 long seed2 = 0xEEEEEEEEL;
	 //System.out.println(seed1 + "," +seed2);
	 int ch;
	 int len = key.length(),i;
	 for (i = 0;i<len;i++){
	 ch = key.charAt(i);
	 seed1 = cryptTable[(1<<8) + ch] ^ (seed1 + seed2);
	 seed2 = ch + seed1 + seed2 + (seed2<<5) + 3;
	 }
	 return seed1;
	 }*/

	public long HashString(String key) {
		int ch;
		int len = key.length(), i;
		long Hash = 0;
		for (i = 0; i < len; i++) {
			ch = key.charAt(i);
			Hash = Hash * 33 + ch;
		}
		return Hash;
	}

	public void printf() throws IOException {
		int i;
		File fi = new File("gui/backtable/daopai.pdl");
		fi.createNewFile();
		FileWriter fw = new FileWriter(fi);
		for (i = 0; i < round; i++) {
			if (lpTable[i] != null) {
				fw.write(lpTable[i].toString());
			}
		}
		fw.close();
	}

	public void setpos(String lpString, String file) {
		int temp;
		long nHash = HashString(lpString);
		//System.out.println(nHash);
		temp = (int) (HashString(lpString) % round);
		//System.out.println(lpString +  ", *** " +nHash);
		if (lpTable[temp] == null) {
			lpTable[temp] = new Hashstr(nHash, file);
			//System.out.println(lpString +  ",  " +nHash);
		} else {
			lpTable[temp].addhash(nHash, file);
			//System.out.println("!" + lpString);
		}
	}

	public static void main(String[] args) throws IOException {
		new ReverseSet();
	}
}
