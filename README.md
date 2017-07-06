# Inflector REST
Inflector (singulariza y pluraliza pronombres) implementado en un servicio REST.  

# Instalación

## Descarga
Descarga Freeling desde el repositorio oficial:  
[Freeling 3.1 (Windows 64 bits)](http://devel.cpl.upc.edu/freeling/downloads/33)  

## Variable de entorno
Establece la variable de entorno `FREELINGDIR=[freeling dir absolut path]`  
Por ejemplo `FREELINGDIR=C:\freeling-3.1-win64`  

## Maven
Ejecuta el siguiente comando para agregar el jar de Freeling al repositorio local de Maven. Sustituye `[FREELINGJAR]` por la ruta adecuada.  
`mvn install:install-file -Dfile=[FREELINGJAR] -DgroupId=edu.upc.freeling -DartifactId=freeling -Dversion=3.1 -Dpackaging=jar`  

Si no estás usando un IDE, ejecuta `mvn install` dentro de la carpeta del proyecto  
