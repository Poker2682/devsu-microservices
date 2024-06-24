FROM maven:3.9.7-amazoncorretto-21-debian-bookworm AS build

WORKDIR /build

RUN mkdir account-transaction-microservice
RUN mkdir microservice-commons

COPY account-transaction-microservice/pom.xml ./account-transaction-microservice
COPY account-transaction-microservice/src ./account-transaction-microservice/src
COPY microservice-commons/pom.xml ./microservice-commons/pom.xml
COPY microservice-commons/src ./microservice-commons/src

WORKDIR /build/microservice-commons
RUN mvn clean install

WORKDIR /build/account-transaction-microservice
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /build/account-transaction-microservice/target/*.jar app.jar
COPY --from=build /root/.m2 /root/.m2

ENTRYPOINT ["java", "-jar", "app.jar"]
