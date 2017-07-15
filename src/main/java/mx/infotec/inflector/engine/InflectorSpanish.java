package mx.infotec.inflector.engine;
import java.io.File;
import java.util.Set;
import java.util.TreeMap;

import edu.upc.freeling.Analysis;
import edu.upc.freeling.Dictionary;
import edu.upc.freeling.ListAnalysis;
import edu.upc.freeling.ListString;

/**
 * Provides some methods to singularize or pluralize a word
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class InflectorSpanish implements Inflector{

	private final Dictionary dict;

	/**
	 * 
	 * @param data The Freeling data folder
	 * @param lang The language in ISO639-1 format
	 */
	public InflectorSpanish(String data, String lang) {
		this.dict = new Dictionary(
				lang, 
				data + lang + File.separatorChar + "dicc_nouns.src", 
				true, 
				data + lang + File.separatorChar + "afixos.dat", 
				true,
				true);
	}

	private TreeMap<String, String> searchOnDictionary(String word) {
		TreeMap<String, String> lemmaTag = new TreeMap<>();
		ListAnalysis analysis;
		analysis = new ListAnalysis();
		dict.searchForm(word.toLowerCase(), analysis);
		while (!analysis.empty()) {
			Analysis a = analysis.front();
			lemmaTag.put(a.getLemma(), a.getTag());
			analysis.popFront();
		}
		return lemmaTag;
	}

	/**
	 * Singularize a word (returns the lemma)
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
		
		TreeMap<String, String> lemmaTag = this.searchOnDictionary(word);

		Set<String> lemas = lemmaTag.keySet();
		String result = null;
		for (String lemma : lemas) {
			if (lemmaTag.get(lemma).length() > 2) {	// Ignores some tags
				char genre = lemmaTag.get(lemma).charAt(2);	// Can be 'M' or 'F'

				String pluralTag = "NC" + genre + number + "000";
				String invariableTag = "NC" + genre + number + "000";

				ListString forms = dict.getForms(lemma, pluralTag);
				ListString formsNeutral = dict.getForms(lemma, invariableTag);	

				if (!formsNeutral.empty()) {
					result = formsNeutral.front();	// Invariable words only return one result
				} else {
					if (!forms.empty()) {
						result = forms.front();
					}
				}
			}

		}
		return result;
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