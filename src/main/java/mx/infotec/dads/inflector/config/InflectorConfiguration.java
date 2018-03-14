package mx.infotec.dads.inflector.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.inflector.core.Dictionary;

/**
 * Instantiate the Dictionaries (Spanish and English)
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@Configuration
public class InflectorConfiguration {

	private final ClassLoader classLoader = InflectorConfiguration.class.getClassLoader();

	/**
	 * Creates the Spanish Dictionary
	 * 
	 * @return the dictionary
	 * @throws IOException
	 */
	@Bean("spanishDict")
	public Dictionary spanishDictionary() throws IOException {
		InputStream in = classLoader.getResourceAsStream("data/es/MM.nom");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		return new Dictionary(bufferedReader);
	}

	/**
	 * Creates the English Dictionary
	 * 
	 * @return the dictionary
	 * @throws IOException
	 */
	@Bean("englishDict")
	public Dictionary englishDictionary() throws IOException {
		InputStream in = classLoader.getResourceAsStream("data/en/noms");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		return new Dictionary(bufferedReader);
	}

}
