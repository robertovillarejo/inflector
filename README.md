# Inflector REST

This inflector is a library for Java. The linguistic data was taken from the popular library [Freeling](https://github.com/TALP-UPC/FreeLing).

## What is an inflection?
An inflection is the variation of the form of a word to denote genre, number or tense according to the case, i.e. *dogs* is the plural form for *dog* word.

## Why an inflector?
In *Natural Language Processing* is a basical component.

In software, an inflector is useful for automatical generation of names for many purposes. While designing a UML class diagram the names for the entities are written in singular. However, in REST services for example, the names for the endpoints should be in plural. In these cases, an inflector is required to accomplish the task.

## Methods
This inflector provides methods to perform the following tasks:

- Pluralize
- Singularize
- Humanize
- Underscore
- Camel case
- Capitalize

## Supported languages:
- Spanish
- English


## Getting Started
1. Add this artifact as dependency to your project
2. Add a org.springframework.context.annotation.Configuration class: 
```
@Configuration
@Import(mx.infotec.dads.nlp.inflector.config.InflectorConfiguration.class)
public class InflectorConfiguration {

	@Bean
	public SpanishInflector inflectorSpanish(@Qualifier(value = "spanishDict") Dictionary dict) {
		return new SpanishInflector(dict);
	}
	
	@Bean
	public EnglishInflector inflectorEnlgish(@Qualifier(value = "englishDict") Dictionary dict) {
		return new EnglishInflector(dict);
	}
}
```

The library will provide you two `Dictionary` beans with Qualifiers `spanishDict` and `englishDict` which must be injected to `SpanishInflector` and `EnglishInflector` respectively. Both are services that can be injected where you need.

3. Use the inflectors  

```
@Autowired
private Inflector spanishInflector;

public void testInflector() {
	System.out.println(spanishInflector.pluralize("persona"));					//personas
	System.out.println(spanishInflector.singularize("personas"));				//persona
	System.out.println(spanishInflector.humanize("employee_salary", null));		//Employee salary
	System.out.println(spanishInflector.underscore("activeRecord", null));		//active_record
	System.out.println(spanishInflector.camelCase("first_name", false, null));	//firstName
	System.out.println(spanishInflector.capitalize("persona"));					//Persona
}
```