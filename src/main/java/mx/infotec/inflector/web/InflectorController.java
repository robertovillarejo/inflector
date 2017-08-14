package mx.infotec.inflector.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.infotec.inflector.engine.EnglishInflector;
import mx.infotec.inflector.engine.SpanishInflector;

@RestController
@RequestMapping("/api")
public class InflectorController {
	private SpanishInflector esInflector;
	private EnglishInflector enInflector;
	private List<String> langsSupported = Arrays.asList("es", "en");
	
	public InflectorController() throws IOException {
		InputStream in = getClass().getClassLoader().getResourceAsStream("data/es/MM.nom");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		esInflector = new SpanishInflector(bufferedReader);
		in = getClass().getClassLoader().getResourceAsStream("data/en/noms");
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		enInflector = new EnglishInflector(bufferedReader);
	}

	@GetMapping(path="/es/singularize")
	public Response esSingularize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = esInflector.singularize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/es/pluralize")
	public Response esPluralize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = esInflector.pluralize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/en/singularize")
	public Response enSingularize(@RequestParam String word) {
		Response response = new Response(word, "en");
		String result = enInflector.singularize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/en/pluralize")
	public Response enPluralize(@RequestParam String word) {
		Response response = new Response(word, "en");
		String result = enInflector.pluralize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/languages")
	public List<String> getLangsSupported() {
		return this.langsSupported;
	}

}
