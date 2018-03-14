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

/**
 * The analysis of a Word
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class Analysis {
	private String lemma;
	private String tag;

	/**
	 * Constructor for Analysis
	 * @param lemma
	 * @param tag
	 */
	public Analysis(String lemma, String tag) {
		this.lemma = lemma;
		this.tag = tag;
	}

	/**
	 * Returns the lemma
	 * @return
	 */
	public String getLemma() {
		return lemma;
	}

	/**
	 * Set the lemma for the analysis
	 * @param lemma
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	/**
	 * Returns the tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag for the analysis
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Analysis))
			return false;
		final Analysis other = (Analysis) obj;
		return (this.lemma.equals(other.getLemma())) && (this.tag.equals(other.getTag()));
	}

	@Override
	public int hashCode() {
		return this.lemma.hashCode() + this.tag.hashCode();
	}
}
