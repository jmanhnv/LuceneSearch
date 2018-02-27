package com.lucene.search;

import java.io.File;
import java.io.FileFilter;

/**
 * This class is used as a .txt file filter.
 * 
 * @author Johny
 *
 */
public class TextFileFilter implements FileFilter, LuceneConstants {

	public boolean accept(File pathname) {
		return pathname.getName().toLowerCase().endsWith(".txt");
	}

}
