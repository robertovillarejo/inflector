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

package mx.infotec.dads.nlp.inflector.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.nlp.inflector.core.Dictionary;
import mx.infotec.dads.nlp.inflector.core.DictionaryUnaccented;

/**
 * Instantiate the Dictionaries (Spanish and English)
 * 
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
@Configuration
public class InflectorConfiguration {

    private final ClassLoader classLoader = InflectorConfiguration.class.getClassLoader();

    /**
     * Creates the Spanish Dictionary
     * 
     * @return the dictionary
     * @throws IOException
     */
    @Bean("spanishDict")
    public Dictionary spanishDictionary() throws IOException {
        InputStream in = classLoader.getResourceAsStream("data/es/MM.nom");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        return new DictionaryUnaccented(bufferedReader);
    }

    /**
     * Creates the English Dictionary
     * 
     * @return the dictionary
     * @throws IOException
     */
    @Bean("englishDict")
    public Dictionary englishDictionary() throws IOException {
        InputStream in = classLoader.getResourceAsStream("data/en/noms");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        return new DictionaryUnaccented(bufferedReader);
    }

}
