# ================================
# Stage 1: Build with Maven
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source files
COPY src ./src

# Build the WAR (skip tests for faster build)
RUN mvn clean package -DskipTests -B

# ================================
# Stage 2: Run with Tomcat 9
# ================================
FROM tomcat:9.0-jdk17-temurin

# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR into Tomcat's webapps as ROOT (serves at /)
COPY --from=builder /app/target/healthcare-chatbot.war /usr/local/tomcat/webapps/healthcare.war

# Create directory for SQLite database persistence
RUN mkdir -p /data

# Set environment variable for SQLite DB path
ENV DB_PATH=/data/healthcare.db

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
