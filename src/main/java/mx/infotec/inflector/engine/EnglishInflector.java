package mx.infotec.inflector.engine;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The English Inflector
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class EnglishInflector implements Inflector{

	private final Dictionary dict;
	
	public EnglishInflector(BufferedReader reader) throws IOException {
		dict = new Dictionary(reader);
	}

	/**
	 * Singularize a word
	 * @param word
	 * @return the response
	 */
	public String singularize(String word) {
		return this.process(word, "NN");
	}

	/**
	 * Pluralize given word
	 * @param word
	 * @return the response
	 */
	public String pluralize (String word) {
		return this.process(word, "NNS");
	}
	
	private String process(String word, String tag) {
		Analysis an = dict.searchForm(word);
		if (an == null) return null;
		
		return dict.getForms(an.getLemma(), tag);
	}

}
