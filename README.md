# Inflector REST
Inflector implemented in REST service

# Usage

## Singularize
`http://localhost:8080/api/es/singularize?word=alumnos`  
returns `{"word":"alumnos","lang":"es","found":true,"result":"alumno"}`

## Pluralize
`http://localhost:8080/api/es/pluralize?word=alumno`  
returns `{"word":"alumno","lang":"es","found":true,"result":"alumnos"}`
