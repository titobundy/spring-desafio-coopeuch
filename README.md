# spring-desafio-coopeuch

Prueba Tecnica Backend Java

## Instrucciones de Uso

Esta proyecto fue construido utilizando Spring Boot 3, Maven, y requiere de Java en su versión 17.
Además por simplicidad utiliza la base de datos en memoria H2 por lo que se deberán cargar datos utilizando 
el endpoint de creación de la api. Para más detalle consulte la sección [Swagger](#Documentación-API)

### Opción 1

Podemos crear el jar utilizando maven y luego ejecutarlo:

```
./mvnw clean package
java -jar target/desafio-0.0.1.jar
```

### Opción 2

Con maven podemos iniciar la aplicación con el comando:

```
./mvnw spring-boot:run
```

### Documentación API

Listado de endpoints disponibles y sus operaciones:

-  (/tareas) operaciones permitidas sobre el recurso (GET, POST, PUT, DELETE)

[Swagger](http://localhost:8080/api/v1/api-docs/index.html)