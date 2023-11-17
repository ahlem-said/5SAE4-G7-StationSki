FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN curl -u admin:nexus -o gestion-station-ski-1.0.jar "http://192.168.10.7:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar" -L
ENTRYPOINT ["java","-jar","gestion-station-ski-1.0.jar"]
EXPOSE 8080