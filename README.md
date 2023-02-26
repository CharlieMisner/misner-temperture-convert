# Charlie Misner's Temperature Converter
API that converts temperature readings between Celsius and Fahrenheit

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
curl -d '{"temperature": 34.0, "temperatureUnit": "CELSIUS"}' -H 'Content-Type: application/json' http://localhost:8080/convert-temperature
```
Requests from Insomnia should use the following setup:
1. Set the request type to `POST`
2. Put the following url in the input box : `http://localhost:8080/convert-temperature`
3. Select`JSON` for the body, and paste the following sample input:
```json
{
    "temperature": 34.0,
    "temperatureUnit": "CELSIUS"
}
```

### `/last-weeks-average-temperature`

## Running The Tests
Pre-requisites:
- Java 17
- Maven 3.9.0

Run the test suite:
```
mvn clean test
```