package mx.infotec.inflector;
import java.io.File;
import java.util.HashSet;
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
public class Inflector {

	private final Dictionary dict;
	private final String lang;

	/**
	 * 
	 * @param data The Freeling data folder
	 * @param lang The language in ISO639-1 format
	 */
	public Inflector(String data, String lang) {
		this.lang = lang;
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
	public Response singularize(String word) {
		Response response = new Response(word, lang);
		TreeMap<String, String> lemmaTag = this.searchOnDictionary(word);

		if (lemmaTag.size() > 0) {		
			response.setFound(true);
			response.setResult(lemmaTag.keySet());
		}
		return response;
	}

	/**
	 * Pluralize given word
	 * @param word
	 * @return the response
	 */
	public Response pluralize (String word) {	
		Response response = new Response(word, lang);
		TreeMap<String, String> lemmaTag = this.searchOnDictionary(word);

		Set<String> lemas = lemmaTag.keySet();
		Set<String> plurals = new HashSet<>();
		for (String lemma : lemas) {
			if (lemmaTag.get(lemma).length() > 2) {	// Ignores some tags
				char genre = lemmaTag.get(lemma).charAt(2);	// Can be 'M' or 'F'

				String pluralTag = "NC" + genre + "P000";
				String invariableTag = "NC" + genre + "N000";

				ListString forms = dict.getForms(lemma, pluralTag);
				ListString formsNeutral = dict.getForms(lemma, invariableTag);	
				forms.pushBack(formsNeutral.front());	// Invariable words only return one result			

				while (!forms.empty()) {
					if (!"".equals(forms.front())) {
						plurals.add(forms.front());
					}					
					forms.popFront();
				}
			}

		}
		response.setResult(plurals);
		return response;
	}

}