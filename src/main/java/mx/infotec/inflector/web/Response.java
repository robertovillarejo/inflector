package mx.infotec.inflector.web;

/**
 * Wrapper for the result of processing with Inflector
 * @author Roberto Villarejo Mart�nez <roberto.villarejo@infotec.mx>
 *
 */
public class Response {

	/**
	 * The analyzed word itself
	 */
	private String word = "";
	
	/**
	 * Language in which word was analyzed
	 */
	private String lang = "";
	
	/**
	 * Word was found on dictionary or not
	 */
	private boolean found = false;
	
	/**
	 * Results for the analysis
	 */
	private String result = "";
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isFound() {
		return found;
	}

	public void isFound(boolean found) {
		this.found = found;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Response (String word, String lang) {
		this.word = word;
		this.lang = lang;
	}
	
	@Override
	public String toString() {
		return "Word: " + this.word + " Lang: " + this.lang + ", Found: " + this.found + " ,Results: " + this.result;
	}
	
}
