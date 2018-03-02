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

package mx.infotec.inflector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.infotec.inflector.engine.SpanishInflector;

@RestController
@RequestMapping("/api")
public class CommonInflectorController {
	
	@Autowired
	private SpanishInflector commonInflector;
	
	@GetMapping(path="/camelize")
	public String camelize(@RequestParam String word, @RequestParam boolean uppercaseFirstLetter, @RequestParam char...delimiterChars) {
		return commonInflector.camelCase(word, uppercaseFirstLetter, delimiterChars);
	}
	
	@GetMapping(path="/underscore")
	public String underscore(@RequestParam String word, @RequestParam char...delimiterChars) {
		return commonInflector.underscore(word, delimiterChars);
	}
	
	@GetMapping(path="/humanize")
	public String humanize(@RequestParam String word, @RequestParam String...removableTokens) {
		return commonInflector.humanize(word, removableTokens);
	}

}
