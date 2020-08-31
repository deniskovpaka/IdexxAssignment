### Idexx Assignment
SearchService provides an API resource to fetch albums and books by search criteria.
There are two endpoints from where the search data is received:
1. [iTunes API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/)
2. [Google Books API](https://developers.google.com/books/docs/v1/reference/volumes/list)

The searched data is displayed using a pre-configured limit for each environment (local, dev, prod).
Change the `limit.search` in the application-*.properties files for configuring this variable, by default it is 5

#### Prerequisites
- Java 11
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

#### Search API monitoring using Micrometer Prometheus
_Prerequisites_:
- docker pull prom/prometheus
- paste your local IP address instead of `192.168.0.102` in the resources/prometheus.yml file the scrape_configs.static_configs.targets field

_Run_:
- `docker run -d --name=prometheus -p 9090:9090 -v <PATH_TO_resource/prometheus.yml_FILE>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml`
- navigate to http://localhost:9090 to explore the Prometheus dashboard
- run one of the command, like `http_server_requests_seconds_max{uri="/search"}`
![Prometheus_example](https://github.com/deniskovpaka/IdexxAssignment/blob/master/src/main/resources/img/prometheus_example.png)