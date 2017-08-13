package mx.infotec.inflector.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.infotec.inflector.engine.InflectorSpanish;

@RestController
@RequestMapping("/api")
public class InflectorController {
	private InflectorSpanish esInflector;
	private List<String> langsSupported = Arrays.asList("es");
	
	public InflectorController() throws IOException {
		InputStream in = getClass().getClassLoader().getResourceAsStream("data/es/MM.nom");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		esInflector = new InflectorSpanish(bufferedReader);
	}

	@GetMapping(path="/es/singularize")
	public Response singularize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = esInflector.singularize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@GetMapping(path="/es/pluralize")
	public Response pluralize(@RequestParam String word) {
		Response response = new Response(word, "es");
		String result = esInflector.pluralize(word);
		if (result != null) response.isFound(true);
		response.setResult(result);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/languages")
	public List<String> getLangsSupported() {
		return this.langsSupported;
	}

}
