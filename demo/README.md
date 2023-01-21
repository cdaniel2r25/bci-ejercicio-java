#  Proyecto de ejemplo "Evaluación: Creacion de usuarios" Spring Boot

Esta es una aplicación Java/Maven/Spring Boot (versión 2.6.2) de muestra que se puede usar como iniciador para que exponga una API RESTful de creación de usuarios.

##  Cómo ejecutar


* Clonar este repositorio
* Asegúrese de estar usando Java 11 y Maven 3.x
* Puede limpiar el proyecto ```mvn clean```
* Una vez limpiado con éxito, puede ejecutar el servicio mediante uno de estos dos métodos:
```
        mvn spring-boot:run 
```

Una vez que se ejecute la aplicación, debería ver algo como esto

```
INFO 20272 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat started on port(s): 8080 (http) with context path ''
INFO 20272 --- [  restartedMain] d.s.w.p.DocumentationPluginsBootstrapper : Context refreshed
INFO 20272 --- [  restartedMain] d.s.w.p.DocumentationPluginsBootstrapper : Found 1 custom documentation plugin(s)
INFO 20272 --- [  restartedMain] s.d.s.w.s.ApiListingReferenceScanner     : Scanning for api listing references
INFO 20272 --- [  restartedMain] c.e.b.d.DemoApplication                  : Started DemoApplication in 4.796 seconds (JVM running for 5.6)
```

##  Sobre el Servicio

El servicio es simplemente un servicio REST de Creacion de usuarios. Utiliza una base de datos en memoria (H2) para almacenar los datos. puede acceder los recursos REST definidos en ```com.example.bci.demoApi.controller.UserController``` Y ```com.example.bci.demoApi.controller.HealthCheckController``` en el **puerto 8080** . (vea abajo)
 
Esto es lo que demuestra esta pequeña aplicación:

* Integración completa con el último Framework **Spring** : inversión de control, inyección de dependencia, etc.
* Puede verificar el estado del servicio (solo si el proyecto esta levantado podra acceder a los recursos disponibles)
* Escribir un servicio RESTful usando anotación: admite solicitudes/respuestas JSON
* Asignación de excepciones  de aplicaciones a la respuesta HTTP correcta con detalles de excepción en el cuerpo
*  *Spring Data* Integración con JPA/Hibernate con solo unas pocas líneas de configuración y anotaciones familiares.
* Funcionalidad CRUD automática contra la fuente de datos usando el patrón Spring *Repository*
* Todas las API están "autodocumentadas" por Swagger2 usando anotaciones

Estos son los endPoint a los que puede llamar:

###  Creacion de usuario y consulta de usuario.

```
http://localhost:8080/api/user/register
POST /api/user/register
accept: aplicación/json
content-type: aplicación/json

{
	"name": "Juan Rodriguez",
	"email": "juan@rodriguez.org",
	"password": "hunter2",
	"phones": [
		{
			"number": "1234567",
			"citycode": "1",
			"contrycode": "57"
		}
	]
}
RESPUESTA: HTTP 200 (OK)
{
  "id": "402881d185d2d9830185d2ee01a60002",
  "email": "juan@rodriguez.orgs",
  "created": "2023-01-21T06:04:51.5016136",
  "modified": "2023-01-21T06:04:51.4916117",
  "last_login": "2023-01-21T06:04:51.4096145",
  "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmdzIiwiaWF0IjoxNjc0MjgxMDkxfQ.UyTlFUvvZeUFqhPxuTVkG4T5HS-3ZUE1Ipa45fgBFK2IKbark4QIwFv6IgcqSPq0oSzHU_ao6_w0qcbp7Z2wFQ",
  "isactive": true,
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}

```

### Buscar usuario por token (pasandolo por el header de la peticion)

```
http://localhost:8080/api/user/byToken
GET api/user/byToken
accept: aplicación/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmdzIiwiaWF0IjoxNjc0MjgxMDkxfQ.UyTlFUvvZeUFqhPxuTVkG4T5HS-3ZUE1Ipa45fgBFK2IKbark4QIwFv6IgcqSPq0oSzHU_ao6_w0qcbp7Z2wFQ

Respuesta: HTTP 200 (OK)
{
  "id": "402881d185d2d9830185d2ee01a60002",
  "email": "juan@rodriguez.orgs",
  "created": "2023-01-21T06:04:51.501614",
  "modified": "2023-01-21T06:04:51.491612",
  "last_login": "2023-01-21T06:04:51.409615",
  "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmdzIiwiaWF0IjoxNjc0MjgxMDkxfQ.UyTlFUvvZeUFqhPxuTVkG4T5HS-3ZUE1Ipa45fgBFK2IKbark4QIwFv6IgcqSPq0oSzHU_ao6_w0qcbp7Z2wFQ",
  "isactive": true,
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```

###  Estado del servicio

```
http://localhost:8080/healthcheck
GET /accept
accept: aplicación/json
RESPUESTA: HTTP 200 (OK)
Contenido: healthcheck ok
```
###  Para ver la documentación de la API de Swagger 2

Ejecute el servidor y busque http://localhost:8080/swagger-ui.html


###  Para ver su base de datos en memoria H2

El perfil de "testdb" se ejecuta en la base de datos en memoria H2. Para ver y consultar la base de datos, puede navegar a http://localhost:8080/console. El nombre de usuario predeterminado es 'sa' con una contraseña '123456'


#  Preguntas y Comentarios: cdaniel2r.dr@gmail.com