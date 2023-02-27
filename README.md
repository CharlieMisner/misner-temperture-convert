# Charlie Misner's Temperature Converter
API that converts temperature readings between Celsius and Fahrenheit.

## Design notes
Tech Stack: Kotlin, Spring Boot, Postgres

The application and database run in separate containers which are orchestrated locally with docker compose.
The database should start first followed by the spring boot api container. When 

## Running The API
Pre-requisites:
- Docker with running docker daemon
- docker-compose
Build and run the application:
```
docker-compose up --build
```
This will build the application, start a containerized postgres instance, then start the application and 
connect to the database. The api will run on `localhost:8080`.

## Using The API
The application has two endpoints:

### `/convert-temperature`
Ingests temperature readings in either Celsius or Fahrenheit, and converts them.

Request with curl:
```
curl -d '{"value": 34.0, "temperatureUnit": "CELSIUS"}' -H 'Content-Type: application/json' http://localhost:8080/convert-temperature
```
Requests from Insomnia should use the following setup:
1. Set the request type to `POST`
2. Put the following url in the input box : `http://localhost:8080/convert-temperature`
3. Select`JSON` for the body, and paste the following sample input:
```json
{
    "value": 34.0,
    "temperatureUnit": "CELSIUS"
}
```

### `/seven-day-average`
Will return the temperature average from the last seven days in both Celsius and Fahrenheit.
This endpoint requires that temperature readings have already been submitted via the `/convert-temperature`
api endpoint.

Request with curl:


## Running The Tests
Pre-requisites:
- Java 17
- Maven 3.9.0

Run the test suite:
```
mvn clean test
```