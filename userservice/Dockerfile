FROM openjdk:8
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE
RUN echo $JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]