# Inflector REST

This inflector is a microservice written in Java (Spring Boot Framework) implemented in a REST service. The linguistic data was taken from the popular library [Freeling](https://github.com/TALP-UPC/FreeLing).

## What is an inflection?
An inflection is the variation of the form of a word to denote genre, number or tense according to the case, i.e. *dogs* is the plural form for *dog* word.

## Why an inflector?
In *Natural Language Processing* is a basical component.

In software, an inflector is useful for automatical generation of names for many purposes. While designing a UML class diagram the names for the entities are written in singular. However, in REST services for example, the names for the endpoints should be in plural. In these cases, an inflector is required to accomplish the task.

## Methods
This inflector provides an API REST to perform the following tasks:

- Pluralize
- Singularize
- Humanize
- Underscore

## Supported languages:
- Spanish
- English


## Getting Started
1. Clone this repository
`git clone https://github.com/robertovillarejo/Inflector-REST`

2. Go to cloned folder and run the project:
`cd Inflector-REST
mvn spring-boot:run`

3. Test the service using the following endpoint structure:
`SERVER:/api/{lang}/METHOD/?PARAM=VALUE`

## Examples
### Get the singular form of a word in english
`curl 'http://localhost:8080/api/en/singularize?word=dogs'`  

Returns:  
`{"word":"dogs","lang":"en","found":true,"result":"dog"}`

### Get the plural form of a word in spanish:
`curl 'http://localhost:8080/api/es/pluralize?word=perro'`  

Returns:  
`{"word":"perro","lang":"es","found":true,"result":"perros"}`  

### Camelize a String
The following command formats `tareas_alumno` to `TareasAlumno`.  
You can set `uppercaseFirstLetter` to `false` as appropiate to your application.
Only underscore `_` is passed as delimiter but can be more characters.  

`curl 'http://localhost:8080/api/camelize?word=alumno_id&uppercaseFirstLetter=true&delimiterChars=_'`  

Returns:  
`AlumnoId`

## Underscore a String
`curl 'http://localhost:8080/api/underscore?word=AlumnoId&delimiterChars= '`  

Returns:  
`alumno_id`

## Humanize a String
`curl 'http://localhost:8080/api/humanize?word=alumno_id&removableTokens=_'`  

Returns:  
`Alumno`
