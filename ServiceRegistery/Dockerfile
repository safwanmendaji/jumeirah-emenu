# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk/openjdk17:alpine-jre

# ARG variable for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file to the container
COPY ${JAR_FILE} /app.jar

# Environment variable for Java options
ENV JAVA_OPTS=""

# Expose the application port
EXPOSE 8761

# Set the entry point for the container
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
