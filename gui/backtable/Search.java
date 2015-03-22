package backtable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Search {

	public void SearchDish(String path) throws Exception {
		try {
			File f = new File(path);
			if (f.isDirectory() && f.listFiles().length != 0) {
				File flist[] = f.listFiles();
				boolean notFirst = false;
				for (File flist1 : flist) {
					if (!flist1.isHidden() && flist1.isDirectory() && flist1.listFiles().length != 0) {
						SearchDish(flist1.getPath());
					}
					if (flist1.isFile() && !flist1.isHidden()) {
						if (flist1.getName().endsWith("txt")) {
							MakeFile(flist1, notFirst, "txtFolder\\");
						} else if (flist1.getName().endsWith("pdf") || flist1.getName().endsWith("doc")) {
							MakeFile(flist1, notFirst, "otherFolder\\");
						}
						notFirst = true;
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public void MakeFile(File f, boolean nf, String type) throws IOException {
		boolean notFirst = nf;
		File file = new File(System.getProperty("java.class.path") + "\\backtable\\" + type + f.getParent().replaceAll("\\\\", "@@").replaceAll(":@@", "@@@"));
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, notFirst)));
		notFirst = true;
		bw.write(f.getPath() + '\n');
		bw.close();
	}
}
