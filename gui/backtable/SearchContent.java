package backtable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.sun.webkit.ContextMenu.ShowContext;

import backtable.ReverseSet;

public class SearchContent {

	private BigInteger hash;
	private String result;

	public SearchContent(String query) throws FileNotFoundException, IOException {
		this.hash = ReverseSet.HashString(query);
		System.out.println(hash);
		result = Retable(hash);
		System.out.println(show(result).toString());
	}

	public String Retable(BigInteger hashcode) throws IOException, FileNotFoundException {
		String temp;
		int i, mark;
		BigInteger hash;
		File fi = new File("gui/backtable/daopai.pdl");
		BufferedReader br = new BufferedReader(new FileReader(fi));
		temp = br.readLine();
		while (temp != null) {
			if (temp.startsWith("###")) {
				if ((mark = temp.indexOf(":")) != -1) {
					//获取hash值
					hash = new BigInteger(temp.substring(3, mark));
					if (hash.equals(hashcode)) {
						return temp.substring(mark + 1);
					}
				}
			}
			temp = br.readLine();
		}
		return null;
	}

	public ArrayList<String> show(String result) {
		int i;
		String[] str;
		ArrayList<String> temp = new ArrayList<>();
		System.out.println(result);
		if (result == null || result.length() == 0) {
			temp.add("未查询到相关结果！");
		} else {
			str = result.split(",");
			for (i = 0; i < str.length; i++) {
				temp.add(str[i]);
			}
		}
		return temp;
	}
	/*public static void main(String[] args) {
	 // TODO Auto-generated method stub
	 try {
	 new SearchContent("邹开");
	 } catch (FileNotFoundException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 }*/

}
