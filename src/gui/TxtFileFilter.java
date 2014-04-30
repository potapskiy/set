package gui;

import java.io.File;
import java.io.FilenameFilter;

class TxtFileFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return (name.endsWith(".txt"));
	}
}
