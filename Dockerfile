FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} jpaSB-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/jpaSB-1.0.0.jar"]