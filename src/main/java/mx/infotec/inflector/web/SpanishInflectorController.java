package mx.infotec.inflector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.infotec.inflector.engine.SpanishInflector;


/**
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@RestController
@RequestMapping("/api/es")
public class SpanishInflectorController {
	
	@Autowired
	private SpanishInflector spanishInflector;

	@GetMapping(path="/singularize")
	public Response esSingularize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = spanishInflector.singularize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/pluralize")
	public Response esPluralize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = spanishInflector.pluralize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}

}
