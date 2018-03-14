/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez <robertovillarejom@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mx.infotec.dads.nlp.inflector.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public interface Inflector {

	/**
	 * Singularize a word
	 * @param word
	 * @return the response
	 */
	String singularize(String word);

	/**
	 * Pluralize given word
	 * @param word
	 * @return the response
	 */
	String pluralize(String word);

	/**
     * Makes an underscored form from the expression in the string (the reverse
     * of the {@link #camelCase(String, boolean, char[]) camelCase} method. Also
     * changes any characters that match the supplied delimiters into
     * underscore.
     * <p>
     * Examples:
     * 
     * <pre>
     *   inflector.underscore(&quot;activeRecord&quot;)     #=&gt; &quot;active_record&quot;
     *   inflector.underscore(&quot;ActiveRecord&quot;)     #=&gt; &quot;active_record&quot;
     *   inflector.underscore(&quot;firstName&quot;)        #=&gt; &quot;first_name&quot;
     *   inflector.underscore(&quot;FirstName&quot;)        #=&gt; &quot;first_name&quot;
     *   inflector.underscore(&quot;name&quot;)             #=&gt; &quot;name&quot;
     *   inflector.underscore(&quot;The.firstName&quot;)    #=&gt; &quot;the_first_name&quot;
     * </pre>
     * 
     * </p>
     * 
     * @param camelCaseWord
     *            the camel-cased word that is to be converted;
     * @param delimiterChars
     *            optional characters that are used to delimit word boundaries
     *            (beyond capitalization)
     * @return a lower-cased version of the input, with separate words delimited
     *         by the underscore character.
     * @author jboss
     */
    public default String underscore(String camelCaseWord, char... delimiterChars) {
        if (camelCaseWord == null)
            return null;
        String result = camelCaseWord.trim();
        if (result.length() == 0)
            return "";
        result = result.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2");
        result = result.replaceAll("([a-z\\d])([A-Z])", "$1_$2");
        result = result.replace('-', '_');
        if (delimiterChars != null) {
            for (char delimiterChar : delimiterChars) {
                result = result.replace(delimiterChar, '_');
            }
        }
        return result.toLowerCase();
    }

    /**
     * Capitalizes the first word and turns underscores into spaces and strips
     * trailing "_id" and any supplied removable tokens. Like
     * {@link #titleCase(String, String[])}, this is meant for creating pretty
     * output.
     * <p>
     * Examples:
     * 
     * <pre>
     *   inflector.humanize(&quot;employee_salary&quot;)       #=&gt; &quot;Employee salary&quot;
     *   inflector.humanize(&quot;author_id&quot;)             #=&gt; &quot;Author&quot;
     * </pre>
     * 
     * </p>
     * 
     * @param lowerCaseAndUnderscoredWords
     *            the input to be humanized
     * @param removableTokens
     *            optional array of tokens that are to be removed
     * @return the humanized string
     * @author jboss
     */
    public default String humanize(String lowerCaseAndUnderscoredWords, String... removableTokens) {
        if (lowerCaseAndUnderscoredWords == null)
            return null;
        String result = lowerCaseAndUnderscoredWords.trim();
        if (result.length() == 0)
            return "";
        // Remove a trailing "_id" token
        result = result.replaceAll("_id$", "");
        // Remove all of the tokens that should be removed
        if (removableTokens != null) {
            for (String removableToken : removableTokens) {
                result = result.replaceAll(removableToken, "");
            }
        }
        result = result.replaceAll("_+", " "); // replace all adjacent
                                               // underscores with a single
                                               // space
        return capitalize(result);
    }

	/**
	 * By default, this method converts strings to UpperCamelCase. If the
	 * <code>uppercaseFirstLetter</code> argument to false, then this method
	 * produces lowerCamelCase. This method will also use any extra delimiter
	 * characters to identify word boundaries.
	 * <p>
	 * Examples:
	 * 
	 * <pre>
	 *   inflector.camelCase(&quot;active_record&quot;,false)    #=&gt; &quot;activeRecord&quot;
	 *   inflector.camelCase(&quot;active_record&quot;,true)     #=&gt; &quot;ActiveRecord&quot;
	 *   inflector.camelCase(&quot;first_name&quot;,false)       #=&gt; &quot;firstName&quot;
	 *   inflector.camelCase(&quot;first_name&quot;,true)        #=&gt; &quot;FirstName&quot;
	 *   inflector.camelCase(&quot;name&quot;,false)             #=&gt; &quot;name&quot;
	 *   inflector.camelCase(&quot;name&quot;,true)              #=&gt; &quot;Name&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param lowerCaseAndUnderscoredWord
	 *            the word that is to be converted to camel case
	 * @param uppercaseFirstLetter
	 *            true if the first character is to be uppercased, or false if
	 *            the first character is to be lowercased
	 * @param delimiterChars
	 *            optional characters that are used to delimit word boundaries
	 * @return the camel case version of the word
	 * @see #underscore(String, char[])
	 * @see #upperCamelCase(String, char[])
	 * @see #lowerCamelCase(String, char[])
	 * @author jboss
	 */
	public default String camelCase(String lowerCaseAndUnderscoredWord, boolean uppercaseFirstLetter, char... delimiterChars) {
		if (lowerCaseAndUnderscoredWord == null)
			return null;
		lowerCaseAndUnderscoredWord = lowerCaseAndUnderscoredWord.trim();
		if (lowerCaseAndUnderscoredWord.length() == 0)
			return "";
		if (uppercaseFirstLetter) {
			String result = lowerCaseAndUnderscoredWord;
			// Replace any extra delimiters with underscores (before the
			// underscores are converted in the next step)...
			if (delimiterChars != null) {
				for (char delimiterChar : delimiterChars) {
					result = result.replace(delimiterChar, '_');
				}
			}

			// Change the case at the beginning at after each underscore ...
			return replaceAllWithUppercase(result, "(^|_)(.)", 2);
		}
		if (lowerCaseAndUnderscoredWord.length() < 2)
			return lowerCaseAndUnderscoredWord;
		return "" + Character.toLowerCase(lowerCaseAndUnderscoredWord.charAt(0))
		+ camelCase(lowerCaseAndUnderscoredWord, true, delimiterChars).substring(1);
	}

	/**
	 * Utility method to replace all occurrences given by the specific
	 * backreference with its uppercased form, and remove all other
	 * backreferences.
	 * <p>
	 * The Java {@link Pattern regular expression processing} does not use the
	 * preprocessing directives <code>\l</code>, <code>&#92;u</code>,
	 * <code>\L</code>, and <code>\U</code>. If so, such directives could be
	 * used in the replacement string to uppercase or lowercase the
	 * backreferences. For example, <code>\L1</code> would lowercase the first
	 * backreference, and <code>&#92;u3</code> would uppercase the 3rd
	 * backreference.
	 * </p>
	 * 
	 * @param input
	 * @param regex
	 * @param groupNumberToUppercase
	 * @return the input string with the appropriate characters converted to
	 *         upper-case
	 * @author jboss
	 */
	default  String replaceAllWithUppercase(String input, String regex, int groupNumberToUppercase) {
		Pattern underscoreAndDotPattern = Pattern.compile(regex);
		Matcher matcher = underscoreAndDotPattern.matcher(input);
		// CHECKSTYLE IGNORE check FOR NEXT 1 LINES
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(groupNumberToUppercase).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/**
     * Returns a copy of the input with the first character converted to
     * uppercase and the remainder to lowercase.
     * 
     * @param words
     *            the word to be capitalized
     * @return the string with the first character capitalized and the remaining
     *         characters lowercased
     * @author jboss
     */
    public default String capitalize(String words) {
        if (words == null)
            return null;
        String result = words.trim();
        if (result.length() == 0)
            return "";
        if (result.length() == 1)
            return result.toUpperCase();
        return "" + Character.toUpperCase(result.charAt(0)) + result.substring(1).toLowerCase();
    }

}
