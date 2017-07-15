package mx.infotec.inflector;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InflectorController {
	private static String data = System.getenv("FREELINGDIR") + File.separatorChar;
	private InflectorSpanish lmEs = new InflectorSpanish(data, "es");
	private List<String> langsSupported = Arrays.asList("es");


	@RequestMapping(method = RequestMethod.GET, path = "/{lang}")
	public Response singularize(@PathVariable String lang, @RequestParam String action, @RequestParam String word) {
		if (!langsSupported.contains(lang)) return new Response(word, lang);
		String result = "";		
		Response response = new Response(word, "es");
		if ("pluralize".equals(action)) {
			result = this.lmEs.pluralize(word);
		} else if ("singularize".equals(action)){
			result = this.lmEs.singularize(word);
		}
		if (!"".equals(result)) {
			response.setFound(true);
			response.setResult(result);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/languages")
	public List<String> getLangsSupported() {
		return this.langsSupported;
	}

}
