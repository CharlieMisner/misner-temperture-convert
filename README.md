# Charlie Misner's Temperature Converter
API capable of converting temperature readings between Celsius and Fahrenheit, persisting those readings, and reporting
the average temperature for the past seven days.

## Problem Statement

Create a restful API that can do the following two tasks

- The first task should allow a Fahrenheit temperature to be passed in, it will convert that value to Celsius, it will then save the reading to a database along with the time and conversion value, and return the value as Celsius
- The second task should allow a Celsius temperature reading to be passed in, it will convert that value to Fahrenheit, it will then save the reading to a database along with the time and conversion value, and return the value as Fahrenheit

The rest API should be able to be run and tested with a rest API tool such as Swagger/OpenAPI, Postman or Insomnia.

The project can be built using language, frameworks, database, and IDE that you prefer.

GIT should be used as the local source control.

At the end of the exercise, zip up the project including the local .git folders and any setup scripts.   Write up a summary to send with the zip file back to your recruiting contact.

Bonus Objectives
- Use a database within a container (it is ok to restart and lose the data)
- Deploy the API within a container
- Add a third API task that calculates the average temperature over the last week and returns the average values in both Fahrenheit and Celsius

## Design notes
Tech Stack: Kotlin, Spring Boot, Postgres

The application and database run in separate containers which are orchestrated locally with docker compose.
The database should start first, followed by the spring boot api container. If the database container 
is completely shutdown (with `docker-compose down`) all database data will be cleared. The database schema is defined
by the kotlin jpa entities.

The Temperature Reading Entity stores both the celsius and fahrenheit values, along with the original unit 
that was reported (celsisus or fahrenheit). A normal kotlin class is used instead of 
a data class for this entity. [This article](https://www.baeldung.com/kotlin/jpa) explains some of the pitfalls
associated with using data classes as entities.

Source code is organized as follows. The API layer and operations are encapsulated in Controller classes. 
Business logic is encapsulated in Service classes. Persistence operations are handled with Entity and Repository
classes.

The conversion API endpoint is invoked as a `POST` because of the requirement that each temperature reading be persisted.
The seven-day average endpoint is a read-only operation, therefore is invoked with `GET`.

Also worth noting that I've commited the db credentials in the docker-compose file to make setup easier. For a real 
project, I would leverage a secrets manager and would never ever check-in secrets with source code.

## Running The API
Pre-requisites:
- Docker with running docker daemon
- docker-compose (compatible with spec version 3.3)

Build and run the application:
```
docker-compose up --build
```
This will build the application, start a containerized postgres instance, then start the application and 
connect to the database. The api will run on `localhost:8080`.

To stop both containers and clear database data:
```
docker-compose down
```

## Running The Tests

Pre-requisites:
- Java 17
- Maven 3.9.0

Run the test suite:
```
mvn clean test
```

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
```
curl http://localhost:8080/seven-day-average -H 'Content-Type: application/json'
```
Requests from Insomnia should use the following setup:
1. Set the request type to `GET`
2. Put the following url in the input box : `http://localhost:8080/seven-day-average`

## Connecting to The Database
To connect to the database directly, first make sure that the database is running via `docker-compose`. Then use the following
parameters in a database client such as a DBVisualizer:
- Database URL: `jdbc:postgresql://localhost:5432/postgres`
- Database Userid: postgres
- Database Password: postgres

The temperature reading records can be found on the db's public schema in a table called `temperature_readings`.
