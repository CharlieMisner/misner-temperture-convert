FROM maven:3.8.3-openjdk-17-slim as build
WORKDIR .

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn install

FROM gcr.io/distroless/java17-debian11
VOLUME /tmp
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]