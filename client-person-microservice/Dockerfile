FROM maven:3.9.7-amazoncorretto-21-debian-bookworm AS build

WORKDIR /build

RUN mkdir client-person-microservice
RUN mkdir microservice-commons

COPY client-person-microservice/pom.xml ./client-person-microservice
COPY client-person-microservice/src ./client-person-microservice/src
COPY microservice-commons/pom.xml ./microservice-commons/pom.xml
COPY microservice-commons/src ./microservice-commons/src

WORKDIR /build/microservice-commons
RUN mvn clean install

WORKDIR /build/client-person-microservice
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /build/client-person-microservice/target/*.jar app.jar
COPY --from=build /root/.m2 /root/.m2


ENTRYPOINT ["java", "-jar", "app.jar"]