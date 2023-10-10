FROM openjdk:21-jdk
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app.jar"]