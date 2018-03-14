/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez <robertovillarejom@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mx.infotec.dads.nlp.inflector.core;

import static mx.infotec.dads.nlp.inflector.util.StringUtils.unaccent;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * A Dictionary Each entry has at least a form and one or many lemma-tag pairs
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class Dictionary {

	private final BidiMap<String, Analysis> entries = new DualHashBidiMap<>();
	private final BidiMap<Analysis, String> reverseEntries;

	/**
	 * Constructor
	 * 
	 * @param reader
	 * @throws IOException
	 */
	public Dictionary(BufferedReader reader) throws IOException {
		String entry = reader.readLine();
		while (entry != null) {
			String[] elements = entry.split(" ");
			String word = unaccent(elements[0]);
			String lemma = unaccent(elements[1]);
			String tag = elements[2];
			entries.put(word, new Analysis(lemma, tag));
			entry = reader.readLine();
		}
		reader.close();
		reverseEntries = entries.inverseBidiMap();
	}

	/**
	 * Search the Analysis of a Word
	 * 
	 * @param word
	 * @return
	 */
	public Analysis searchForm(String word) {
		return entries.get(word.toLowerCase());
	}

	/**
	 * Search the form of a given lemma-tag pair
	 * 
	 * @param lemma
	 * @param tag
	 * @return
	 */
	public String getForms(String lemma, String tag) {
		return reverseEntries.get(new Analysis(lemma, tag));
	}

}
