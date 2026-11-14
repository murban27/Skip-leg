# Stage 1: build with Maven (no host Maven required)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn -q clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/target/backend-*.jar /app/backend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]
