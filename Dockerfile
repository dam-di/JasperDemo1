# Etapa 1: construir el JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

# Etapa 2: ejecutar el JAR (NO usar alpine)
FROM eclipse-temurin:17-jdk

ENV DEBIAN_FRONTEND=noninteractive

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 5599
ENTRYPOINT ["java", "-jar", "app.jar"]
