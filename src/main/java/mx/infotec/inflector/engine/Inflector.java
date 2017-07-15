package mx.infotec.inflector.engine;

public interface Inflector {
	
	String singularize(String word);
	
	String pluralize(String word);
	
	String camelize(String word);
	
	String underscore(String word);
	
	String humanize(String word);
	
	boolean isCountable(String word);
	
}
