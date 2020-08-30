### Idexx Assignment
SearchService provides an API resource to fetch albums and books by search criteria.
There two endpoints from where the search data is received:
1. [iTunes API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/)
2. [Google Books API](https://developers.google.com/books/docs/v1/reference/volumes/list)

The searched data is displayed using a pre-configured limit for each environment (local, dev, prod).
Change the `limit.search` in the application-*.properties files for configuring this variable, by default it is 5

#### Prerequisites
- Java 14
- run `mvn -N io.takari:maven:wrapper` command from the main folder in project

#### To build the application
./mvnw clean install

#### To run the server with Maven
./mvnw spring-boot:run

In order to run it with the profile, use an additional parameter `-Dspring-boot.run.profiles=dev or prod`

Example: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`


#### To see Swagger documentation
In your browser, open: http://localhost:8080/swagger-ui.html

#### There are several exposed API
- http://localhost:8080/actuator/health
- http://localhost:8080/actuator/info