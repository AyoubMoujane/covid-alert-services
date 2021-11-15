FROM openjdk:16-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
#ARG JAR_FILE
COPY build/libs/apigatewayservice-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]