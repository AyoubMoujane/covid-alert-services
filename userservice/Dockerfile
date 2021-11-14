FROM openjdk:16-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR build/libs
COPY userservice-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]