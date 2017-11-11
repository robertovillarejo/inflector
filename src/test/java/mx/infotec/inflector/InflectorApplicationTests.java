package mx.infotec.inflector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.inflector.engine.EnglishInflector;
import mx.infotec.inflector.engine.SpanishInflector;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InflectorApplicationTests {
	
	@Autowired
	private SpanishInflector spanishInflector;
	
	@Autowired
	private EnglishInflector englishInflector;
	
	@Before
	public void setup() {
	}
	
	@Test	
	public void testSingularizeSpanish() {
		assertThat("niño").isEqualTo(spanishInflector.singularize("niños"));
		assertThat("tesis").isEqualTo(spanishInflector.singularize("tesis"));
	}
	
	@Test	
	public void testSingularizeSEnglish() {
		assertThat("boy").isEqualTo(englishInflector.singularize("boys"));
		assertThat("tennis").isEqualTo(englishInflector.singularize("tennis"));
	}
	
	@Test	
	public void testPluralizeSpanish() {
		assertThat("profesores").isEqualTo(spanishInflector.pluralize("profesor"));
		assertThat("tesis").isEqualTo(spanishInflector.pluralize("tesis"));
	}
	
	@Test	
	public void testPluralizeSEnglish() {
		assertThat("teachers").isEqualTo(englishInflector.pluralize("teacher"));
		assertThat("computers").isEqualTo(englishInflector.pluralize("computer"));
	}

}
