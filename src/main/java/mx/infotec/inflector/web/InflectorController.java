package mx.infotec.inflector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@Autowired
	private SpanishInflector spanishInflector;
	
	@Autowired
	private EnglishInflector englishInflector;

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

}
