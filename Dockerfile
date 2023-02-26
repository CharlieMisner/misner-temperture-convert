FROM openjdk:17-jdk-alpine as build
WORKDIR .

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]