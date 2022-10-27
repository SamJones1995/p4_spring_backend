FROM openjdk:8-jdk-alpine
COPY target/SpringBank-0.0.1-SNAPSHOT.jar docker-springbank-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/docker-springbank-0.0.1-SNAPSHOT.jar"]