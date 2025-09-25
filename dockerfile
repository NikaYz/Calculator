# Use an OpenJDK image
FROM openjdk:11-jdk

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Set the entry point
CMD ["java", "-jar", "target/CalculatorProject-1.0-SNAPSHOT.jar"]