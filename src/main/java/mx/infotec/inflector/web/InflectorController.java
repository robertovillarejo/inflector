package mx.infotec.inflector.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.infotec.inflector.engine.EnglishInflector;
import mx.infotec.inflector.engine.SpanishInflector;


/**
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@RestController
@RequestMapping("/api")
public class InflectorController {
	private SpanishInflector spanishInflector;
	private EnglishInflector englishInflector;
	private List<String> langsSupported = Arrays.asList("es", "en");
	
	public InflectorController(SpanishInflector spanishInflector, EnglishInflector englishInflector) {
		this.spanishInflector = spanishInflector;
		this.englishInflector = englishInflector;
	}

	@GetMapping(path="/es/singularize")
	public Response esSingularize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = spanishInflector.singularize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/es/pluralize")
	public Response esPluralize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = spanishInflector.pluralize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/camelize")
	public String camelize(@RequestParam String word, @RequestParam boolean uppercaseFirstLetter, @RequestParam char...delimiterChars) {
		return spanishInflector.camelCase(word, uppercaseFirstLetter, delimiterChars);
	}
	
	@GetMapping(path="/underscore")
	public String underscore(@RequestParam String word, @RequestParam char...delimiterChars) {
		return spanishInflector.underscore(word, delimiterChars);
	}
	
	@GetMapping(path="/humanize")
	public String humanize(@RequestParam String word, @RequestParam String...removableTokens) {
		return spanishInflector.humanize(word, removableTokens);
	}
	
	@GetMapping(path="/en/singularize")
	public Response enSingularize(@RequestParam String word) {
		Response response = new Response(word, "en");
		String result = englishInflector.singularize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/en/pluralize")
	public Response enPluralize(@RequestParam String word) {
		Response response = new Response(word, "en");
		String result = englishInflector.pluralize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/languages")
	public List<String> getLangsSupported() {
		return this.langsSupported;
	}

}
