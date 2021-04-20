FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/excercise-game-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]