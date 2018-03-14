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

package mx.infotec.dads.nlp.inflector.service;

import static mx.infotec.dads.nlp.inflector.util.StringUtils.unaccent;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.infotec.dads.nlp.inflector.core.Analysis;
import mx.infotec.dads.nlp.inflector.core.Dictionary;
import mx.infotec.dads.nlp.inflector.core.Inflector;

/**
 * The Spanish Inflector
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@Service
public class SpanishInflector implements Inflector {

	private final Dictionary dict;

	public SpanishInflector(@Qualifier(value = "spanishDict") Dictionary dict) {
		this.dict = dict;
	}

	/**
	 * Singularize a word
	 * 
	 * @param word
	 * @return the response
	 */
	public String singularize(String word) {
		return this.process(word, 'S');
	}

	/**
	 * Pluralize given word
	 * 
	 * @param word
	 * @return the response
	 */
	public String pluralize(String word) {
		return this.process(word, 'P');
	}

	private String process(String word, char number) {
		Analysis an = dict.searchForm(unaccent(word));
		if (an == null)
			return null;

		char genre = an.getTag().charAt(2); // Can be 'M' or 'F'
		String pluralTag = "NC" + genre + number + "000";

		String result;
		result = dict.getForms(an.getLemma(), pluralTag);

		if (result != null)
			return result;

		String invariableTag = "NC" + genre + "N000";

		return dict.getForms(an.getLemma(), invariableTag);
	}

}