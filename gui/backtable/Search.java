package backtable;

import java.io.File;

public class Search {

	public void SearchDish(String path) throws Exception {
		try {
			File f = new File(path);
			if (!f.isHidden() && f.isDirectory() && f.listFiles().length != 0) {
				File flist[] = f.listFiles();
				for (File flist1 : flist) {
					if ((flist1.isHidden() || !flist1.isDirectory()) || flist1.listFiles().length == 0) {
					} else {
						SearchDish(flist1.getPath());
					}
				}
			}
		} catch (Exception e) {
		}
	}
}
