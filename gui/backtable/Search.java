package backtable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 集合了搜索方法的类
 *
 * @author 开发
 */
public class Search {

	/**
	 * 搜索某个磁盘下所有文件
	 *
	 * @param path 路径
	 * @throws Exception
	 */
	public void SearchDish(String path) throws Exception {
		try {
			File f = new File(path);
			if (f.isDirectory() && f.listFiles().length != 0) {  //非空文件夹
				File flist[] = f.listFiles();
				boolean notFirst = false;  //写文件的标志量
				for (File flist1 : flist) {
					if (!flist1.isHidden() && flist1.isDirectory() && flist1.listFiles().length != 0) {  //非空且非隐藏则递归读取子文件夹
						SearchDish(flist1.getPath());
					}
					if (flist1.isFile() && !flist1.isHidden()) {  //判断文件
						if (flist1.getName().endsWith("txt")) {
							MakeFile(flist1, notFirst, "txtFolder\\");
						} else if (flist1.getName().endsWith("pdf") || flist1.getName().endsWith("doc")) {
							MakeFile(flist1, notFirst, "otherFolder\\");
						}
						notFirst = true;  //将标志量置为true，下次写之后的循环中写文件将不再清空
					}
				}
			}
		} catch (Exception e) {
			//C盘无法访问
		}
	}

	/**
	 * 写文件
	 *
	 * @param f 文件
	 * @param nf 是否在循环中第一次写入文件
	 * @param type 文件夹
	 * @throws IOException
	 */
	public void MakeFile(File f, boolean nf, String type) throws IOException {
		boolean notFirst = nf;
		//创建文件
		File file = new File(System.getProperty("java.class.path") + "\\backtable\\" + type + f.getParent().replaceAll("\\\\", "@@").replaceAll(":@@", "@@@"));
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, notFirst)));
		notFirst = true;
		bw.write(f.getName() + '\n');
		bw.close();
	}
}
