package mx.infotec.inflector.engine;
import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.inflector.engine.Dictionary.Analysis;

/**
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class InflectorSpanish implements Inflector{

	public final Dictionary dict;
	public final Logger log = LoggerFactory.getLogger(InflectorSpanish.class);
	
	public InflectorSpanish(BufferedReader reader) throws IOException {
		dict = new Dictionary(reader);
	}

	/**
	 * Singularize a word
	 * @param word
	 * @return the response
	 */
	public String singularize(String word) {
		return this.process(word, 'S');
	}

	/**
	 * Pluralize given word
	 * @param word
	 * @return the response
	 */
	public String pluralize (String word) {
		return this.process(word, 'P');
	}
	
	private String process(String word, char number) {
		Analysis an = dict.searchForm(word);
		if (an == null) return null;
		
		char genre = an.getTag().charAt(2);	// Can be 'M' or 'F'
		String pluralTag = "NC" + genre + number + "000";
		
		String result;
		result = dict.getForms(an.getLemma(), pluralTag);
		
		if (result != null ) return result;
		
		String invariableTag = "NC" + genre + "N000";
		
		return dict.getForms(an.getLemma(), invariableTag);
	}

	@Override
	public String camelize(String word) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String underscore(String word) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String humanize(String word) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCountable(String word) {
		throw new UnsupportedOperationException();
	}

}