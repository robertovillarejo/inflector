package mx.infotec.inflector;

import java.io.File;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{lang}")
public class InflectorController {
	private static String data;
	private Inflector lmEs = new Inflector(data, "es");
	private static final Logger LOGGER = Logger.getLogger(InflectorController.class.getName());

	static {
		ClassLoader loader = InflectorController.class.getClassLoader();

		// Search data folder containing dictionaries
		try {
			File dataDir = new File(loader.getResource("static").toURI());
			data = dataDir.toPath().toString() + File.separatorChar;
		} catch (URISyntaxException e) {
			LOGGER.warn("Data folder not found!");
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response singularize(@PathVariable String lang,  @RequestParam String action, @RequestParam String word) {
		Response r = new Response(word, lang);
		if ("pluralize".equals(action)) {
			r = this.lmEs.pluralize(word);
		} else if ("singularize".equals(action)){
			r = this.lmEs.singularize(word);
		}
		return r;
	}

}
