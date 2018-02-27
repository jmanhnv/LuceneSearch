package com.lucene.search;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 * This class is used to test the indexing and search capability of lucene
 * library.
 * 
 * @author Johny
 * @see https://www.tutorialspoint.com/lucene/lucene_first_application.htm
 */
public class LuceneTester {
	// directory to indexing
	String indexDir = "C:\\lucene\\index";

	// directory to store files
	String dataDir = "C:\\lucene\\data";

	Indexer indexer;
	Searcher searcher;

	public static void main(String[] args) {
		LuceneTester tester;
		try {
			tester = new LuceneTester();
			// 1. re-index
			tester.createIndex();

			// 2. search any file with content contains "event-driven" value
			tester.search("event-driven");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void createIndex() throws IOException {
		indexer = new Indexer(indexDir);
		int numIndexed;
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed + " File indexed, time taken: " + (endTime - startTime) + " ms");
	}

	private void search(String searchQuery) throws IOException, ParseException {
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
		}
		searcher.close();
	}
}
