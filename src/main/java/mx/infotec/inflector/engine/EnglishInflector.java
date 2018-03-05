package mx.infotec.inflector.engine;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * The English Inflector
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@Service
public class EnglishInflector implements Inflector {

	private final Dictionary dict;

	public EnglishInflector(@Qualifier(value="englishDict") Dictionary dict) {
		this.dict = dict;
	}

	/**
	 * Singularize a word
	 * 
	 * @param word
	 * @return the response
	 */
	public String singularize(String word) {
		return this.process(word, "NN");
	}

	/**
	 * Pluralize given word
	 * 
	 * @param word
	 * @return the response
	 */
	public String pluralize(String word) {
		return this.process(word, "NNS");
	}

	private String process(String word, String tag) {
		Analysis an = dict.searchForm(word);
		if (an == null)
			return null;

		return dict.getForms(an.getLemma(), tag);
	}

}
