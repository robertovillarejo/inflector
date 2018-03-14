package mx.infotec.dads.inflector.core;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * A Dictionary
 * Each entry has at least a form and one or many lemma-tag pairs
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class Dictionary {

	private final BidiMap<String, Analysis> entries = new DualHashBidiMap<>();
	private final BidiMap<Analysis, String> reverseEntries;

	/**
	 * Constructor
	 * @param reader
	 * @throws IOException
	 */
	public Dictionary(BufferedReader reader) throws IOException {
		String entry = reader.readLine();
		while (entry != null) {
			String[] elements = entry.split(" ");
			String word = elements[0];
			String lemma = elements[1];
			String tag = elements[2];
			entries.put(word, new Analysis(lemma, tag));
			entry = reader.readLine();
		}
		reader.close();
		reverseEntries = entries.inverseBidiMap();
	}

	/**
	 * Search the Analysis of a Word
	 * @param word
	 * @return
	 */
	public Analysis searchForm(String word) {
		return entries.get(word.toLowerCase());
	}

	/**
	 * Search the form of a given lemma-tag pair
	 * @param lemma
	 * @param tag
	 * @return
	 */
	public String getForms(String lemma, String tag) {
		return reverseEntries.get(new Analysis(lemma, tag));
	}

}
