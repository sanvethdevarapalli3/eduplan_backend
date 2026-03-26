FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the Spring Boot project
RUN ./mvnw clean package -DskipTests

# Run the exact jar file (no wildcard)
CMD ["java", "-jar", "target/eduplan-backend-0.0.1-SNAPSHOT.jar"]
