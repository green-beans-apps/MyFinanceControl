
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia primeiro arquivos de configuração para aproveitar cache
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk

LABEL authors="Cristiano-Nogueira"

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]