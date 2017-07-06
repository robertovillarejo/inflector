package mx.infotec.inflector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.upc.freeling.Util;

@SpringBootApplication
public class InflectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InflectorApplication.class, args);
	}
	
	static {
		// Get environment variable
		String freelingPath = System.getenv("FREELINGDIR");

		// Loads required libraries for Freeling
		System.load( freelingPath + "\\java\\freeling_javaAPI.dll" );
		System.load( freelingPath + "\\bin\\foma.dll" );
		System.load( freelingPath + "\\bin\\freeling.dll" );
		System.load( freelingPath + "\\bin\\icudt49.dll" );
		System.load( freelingPath + "\\bin\\icuin49.dll" );
		System.load( freelingPath + "\\bin\\icuuc49.dll" );
		System.load( freelingPath + "\\bin\\msvcp120.dll" );
		System.load( freelingPath + "\\bin\\msvcr120.dll" );
		System.load( freelingPath + "\\bin\\zlibwapi.dll" );

		Util.initLocale( "default" );
	}

}
