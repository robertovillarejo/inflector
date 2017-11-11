package mx.infotec.inflector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.inflector.engine.EnglishInflector;
import mx.infotec.inflector.engine.SpanishInflector;

/**
 * Instantiate the Inflectors (Spanish and English)
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@Configuration
public class InflectorConfiguration {
	
	private final ClassLoader classLoader = InflectorConfiguration.class.getClassLoader();
	
	/**
	 * Creates the Spanish Inflector
	 * @return the Spanish Inflector
	 * @throws IOException
	 */
	@Bean
	public SpanishInflector spanishInflectorBean() throws IOException {
		InputStream in = classLoader.getResourceAsStream("data/es/MM.nom");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		return new SpanishInflector(bufferedReader);
	}
	
	/**
	 * Creates the English Inflector
	 * @return the English Inflector
	 * @throws IOException
	 */
	@Bean
	public EnglishInflector englishInflectorBean() throws IOException {
		InputStream in = classLoader.getResourceAsStream("data/en/noms");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		return new EnglishInflector(bufferedReader);
	}

}
