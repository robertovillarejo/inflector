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
		System.loadLibrary( "freeling_javaAPI" );
		Util.initLocale( "default" );
	}

}
