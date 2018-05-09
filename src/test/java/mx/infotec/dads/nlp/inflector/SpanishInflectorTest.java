/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez <roberto.villarejo@infotec.mx>
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

package mx.infotec.dads.nlp.inflector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.nlp.inflector.service.SpanishInflector;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpanishInflectorTest {

    @Autowired
    private SpanishInflector spanishInflector;

    @Test
    public void testSingularize() {
        assertEquals("perro", spanishInflector.singularize("perros"));
        assertEquals("gato", spanishInflector.singularize("gatos"));
        assertEquals("persona", spanishInflector.singularize("personas"));
        assertEquals("recurso", spanishInflector.singularize("recursos"));
        assertEquals("celula", spanishInflector.singularize("células"));
        assertEquals("bitacora", spanishInflector.singularize("bitácoras"));
        assertEquals("bitacora", spanishInflector.singularize("Bitácoras"));
        assertEquals("bitacora", spanishInflector.singularize("Bitacora"));
        assertEquals("tesis", spanishInflector.singularize("tesis"));
    }

    @Test
    public void testPluralize() {
        assertEquals("perros", spanishInflector.pluralize("perro"));
        assertEquals("gatos", spanishInflector.pluralize("gato"));
        assertEquals("personas", spanishInflector.pluralize("persona"));
        assertEquals("recursos", spanishInflector.pluralize("recurso"));
        assertEquals("celulas", spanishInflector.pluralize("célula"));
        assertEquals("bitacoras", spanishInflector.pluralize("bitácora"));
        assertEquals("bitacoras", spanishInflector.pluralize("Bitácora"));
        assertEquals("bitacoras", spanishInflector.pluralize("Bitacora"));
        assertEquals("tesis", spanishInflector.singularize("tesis"));
    }

}
