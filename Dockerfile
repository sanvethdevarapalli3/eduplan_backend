FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# Fix permission issue for mvnw
RUN chmod +x mvnw

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Run the generated jar
CMD ["java", "-jar", "target/*.jar"]
