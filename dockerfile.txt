FROM openjdk:8-jre-slim
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]