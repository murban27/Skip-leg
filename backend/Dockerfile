FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy Maven project files
COPY pom.xml ./
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Copy the built JAR
COPY target/backend-*.jar /app/backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend.jar"]