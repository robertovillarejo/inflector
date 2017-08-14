package mx.infotec.inflector.engine;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.inflector.engine.Dictionary.Analysis;

public class EnglishInflector implements Inflector{

	public final Dictionary dict;
	public final Logger log = LoggerFactory.getLogger(EnglishInflector.class);
	
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
