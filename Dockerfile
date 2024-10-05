FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:18-jdk-slim
WORKDIR /app

COPY --from=build /app/target/Spring-book-server-0.0.1.jar /app/Spring-book-server-0.0.1.jar

ENTRYPOINT [ "java", "-jar", "/app/Spring-book-server-0.0.1.jar" ]