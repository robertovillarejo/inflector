package mx.infotec.inflector.engine;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * The Spanish Inflector
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class SpanishInflector implements Inflector{

	private final Dictionary dict;
	
	public SpanishInflector(BufferedReader reader) throws IOException {
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

}