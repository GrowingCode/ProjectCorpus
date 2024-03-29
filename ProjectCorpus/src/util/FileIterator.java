package util;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FileIterator {

	String dir = null;
	String pattern = null;
	List<File> files = new LinkedList<File>();

	public FileIterator(String dir, String pattern) {
		this.dir = dir;
		this.pattern = pattern;
		IterateAllFiles(new File(dir));
	}

	private void IterateAllFiles(File fdir) {
//		System.out.println("==== begin iterate files ====");
//		System.out.println("fdir.getAbsolutePath():" + fdir.getAbsolutePath());
		if (fdir != null && fdir.exists()) {
			File[] fall = fdir.listFiles();
			for (File f : fall) {
				if (!f.isDirectory()) {
					if (pattern != null && !pattern.equals("")) {
						if (f.getName().matches(pattern)) {
//							System.out.println("f.getName():" + f.getName());
							files.add(f);
						}
					} else {
						files.add(f);
					}
				} else {
					IterateAllFiles(f);
				}
			}
		}
	}

	public Iterator<File> EachFileIterator() {
		return files.iterator();
	}
	
}
