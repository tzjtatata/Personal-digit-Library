package backtable;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.math.BigInteger;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import backtable.Node;
import backtable.Hashstr;

public class ReverseSet {

	//获取txtFolder文件夹下的所有文件
	protected File ft = new File("gui/backtable/txtFolder/");
	protected File[] ls = ft.listFiles();
	protected String str;
	protected File temp;
	protected int num = 0,i;
        protected int Count = 100*1024*1024;
        protected int count = 0;
        protected ArrayList<Node> List = new ArrayList();
        protected ArrayList<Hashstr> data = new ArrayList();
	private String len;

	public void change() {
		if (System.getProperty("os.name").startsWith("W")) {
			len = "\\\\";
		} else {
			len = "/";
		}
	}

	public ReverseSet() throws IOException {
		//prepareCryptTable();
		//对目录下每个txt读取，获取子文件目录
		//读取txt文件内容
            long startTime=System.currentTimeMillis();
		change();
		num = ls.length;
		for (i = 0; i < num; i++) {
			if (!ls[i].isHidden()) {
				//获取txtFolder目录下文件
				System.out.println(i + "/" + num);
				//System.out.println(ls[i].getPath());
				str = ls[i].getName();
				Search zkf = new Search();
				str = zkf.nameChange(str);
				temp = new File(str);
				FileReader fr = new FileReader(ls[i]);
				BufferedReader br = new BufferedReader(fr);
				String str2 = br.readLine();
				while (str2 != null) {
					try {
						//String codeString = EncodingDetect(str + len + str2);
						//System.out.println(codeString);
						Refile(new File(str + len + str2));
					} catch (Exception e) {
						e.printStackTrace();
					}
					str2 = br.readLine();
				}
			}
		}
		//Refile(fi);
		//最后将hash数组输出到倒排索引表中
		printf();
                long endTime=System.currentTimeMillis();
                System.out.println("程序运行时间： "+(endTime-startTime)/1000.0 +"s");
	}

	public void Refile(File ffi) throws Exception {
		long temp = 0;
		int i;
		ArrayList<String> word = new ArrayList<>();
		//测试段，使用EncodingDetect类按编码读入；
		/*BufferedReader br = new BufferedReader(new FileReader(ffi));
		 String nw;
		 while ((nw = br.readLine()) != null) {
		 //分词
		 Analene.testCJK(nw, word);

		 //System.out.println(nw);
		 }*/
		String filePath = ffi.getPath();
		String fileEncode = EncodingDetect.getJavaEncode(filePath);
		String fileContent = FileUtils.readFileToString(new File(filePath), fileEncode);
		Analyze.testCJK(fileContent, word);
		//System.out.println(word);
		//prepareCryptTable();
		for (i = 0; i < word.size(); i++) {
                        //System.out.println("\r"+i+"/"+word.size());
			setpos(word.get(i), filePath,count);
                        count++;
		}
	}

	public static BigInteger HashString(String key) {
		int ch;
		int len = key.length(), i;
		BigInteger Hash = BigInteger.valueOf(0);
		for (i = 0; i < len; i++) {
			ch = key.charAt(i);
			Hash = Hash.multiply(BigInteger.valueOf(33)).add(BigInteger.valueOf((long) ch));
		}
		return Hash;
	}

	public void printf() throws IOException {
		int i,length,ptr;
                /*Node NODE;
                Hashstr DATA;
                length = List.size();
                try {
                    for (i = 0;i<length;i++) {
                        NODE = List.get(i);
                        File f = new File("gui/backtable/workInfo/"+NODE.value+".pdl");
                        if (!f.exists()) f.createNewFile();
                        ptr = NODE.firstPOS;
                        ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(f));
                        DATA = data.get(ptr);
                        while (DATA.next != -1) {
                            OOS.writeObject(DATA);
                            ptr = DATA.next;
                            DATA = data.get(ptr);
                        }
                        OOS.writeObject(DATA);
                        OOS.close();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }*/
                /*
		ObjectOutputStream f1 = new ObjectOutputStream(new FileOutputStream("gui/backtable/dic.pdl"));
                ObjectOutputStream f2 = new ObjectOutputStream(new FileOutputStream("gui/backtable/data.pdl"));
                for (i = 0;i<count;i++) {
                    f1.writeObject(data.get(i));
                }
                for (i = 0;i<length;i++)
                    f2.writeObject(List.get(i));
		f1.close();
                f2.close();*/
	}
        protected class result {
            int position;
            boolean finded;
            public result(){
                
            }
        }
	public void setpos(String lpString, String file,int i) {
		result pos;
                Node aNode = new Node();
                Hashstr adata = new Hashstr();
		BigInteger HashValue = HashString(lpString);
                pos =find(HashValue);
                //System.out.println(lpString + " pass the find function.");
                //System.out.println(pos.position);
                adata.Filepath = file;
                adata.next = -1;
                data.add(adata);
		if (pos.finded == true) {
                    Node temp = List.get(pos.position);
                    data.get(temp.lastPOS).next = i;
                    temp.lastPOS = i;
                }
                else {
                    aNode.value = HashValue;
                    aNode.lastPOS = i;
                    aNode.firstPOS = i;
                    List.add(pos.position, aNode);
                }
	}
        public result find(BigInteger Hashint) {
            result a = new result();
            int length = List.size(),temp;
            //System.out.println("List length is "+length);
            if (length != 0){
                int head = 0,tail = length-1,mid = (head+tail)/2;
                while (head != tail){
                    //System.out.println(head + " " + tail);
                    temp = Hashint.compareTo(List.get(mid).value);
                    if (temp==0) {
                        a.position = mid;
                        a.finded = true;
                        return a;
                    }
                    else if (temp<0){
                        head = mid+1;
                    }
                    else tail = mid;
                    mid = (head+tail)/2;
                }
                a.finded = false;
                a.position = head;
                return a;
            }
            else {
                a.finded = false;
                a.position = 0;
                return a;
            }
        }
	public static void main(String[] args) throws IOException {
	 new ReverseSet();
	 }
}
