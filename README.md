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
connect to the database. The application will run on `localhost:8080`.

## Using The API
The application has three endpoints:

### `/convert-celsius-to-fahrenheit`
Ingests temperature readings in Celsius and converts to Fahrenheit.

Test with curl:
```
 curl -d '15' -H 'Content-Type: application/json' http://localhost:8080/convert-celsius-to-fahrenheit
```
### `/convert-fahrenheit-to-celsius`
### `/last-weeks-average-temperature`