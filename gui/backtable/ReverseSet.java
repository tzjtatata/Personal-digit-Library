package backtable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import backtable.Hashstr;

public class ReverseSet {
	//获取txtFolder文件夹下的所有文件
	protected File ft = new File(System.getProperty("java.class.path")+"/backtable/txtFolder");
	protected File[] ls = ft.listFiles();
	static int round = 0x500;
	int i ;
	public long[] cryptTable = new long[round];
	Hashstr[] lpTable = new  Hashstr[round];
	public ReverseSet() throws FileNotFoundException {
		//prepareCryptTable();
		//对目录下每个txt读取，获取子文件目录
		//File fi = new File("test.txt");
		//读取txt文件内容
		Refile();
		//Refile(fi);
		//最后将hash数组输出到倒排索引表中
		//printf();
	}
	public void Refile() throws FileNotFoundException {
		long temp = 0;
		int i;
		String[] word =  {"zoukaifa","dadoubi","dahuaidan","kosting","zoukaifa","dadoubi","zoukaifa","dadoubi","liyuanze"};
		prepareCryptTable();
		for (i = 0;i<word.length;i++)
		{	
		//分词
			
		/*对每个词求其hash值，存进对应的数组项
		 * 并用链表存储其所在地址
		*/
		setpos(word[i],lpTable);
		}
	}
	public void prepareCryptTable() {
			long seed = 0x001000001L ,  index1 = 0 , index2 = 0 ;
			int i;
			for (index1 = 0;index1<0x100;index1++){
				for (index2 = index1, i = 0;i<5;i++,index2 += 0x100){
					long temp1,temp2;
					seed = (seed *125 + 3) % 0x2AAAABL;
					temp1 = (seed & 0xFFFF) << 0x10;
					seed = (seed *125 + 3) % 0x2AAAABL;
					temp2= (seed & 0xFFFF) ;
					//System.out.println(index2);
					cryptTable[(int)index2] = ( temp1 | temp2);
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
		int len = key.length(),i;
		long Hash = 0;
		for (i = 0;i<len;i++){
			ch = key.charAt(i);
			Hash = Hash * 33 + ch;
		}
		return Hash;
	}
	public void printf()
	{
		
	}
	public void setpos(String lpString, Hashstr[] lpTable){
		int temp;
		long nHash = HashString(lpString);
		//System.out.println(nHash);
		temp = (int)(HashString(lpString) % round);
		//System.out.println(lpString +  ", *** " +nHash);
		if (lpTable[temp] == null)
		{
			lpTable[temp]= new Hashstr(nHash);
			//System.out.println(lpString +  ",  " +nHash);
		}
		else {
			
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		new ReverseSet();
	}
}
