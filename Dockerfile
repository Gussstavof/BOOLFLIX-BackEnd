FROM openjdk:17

WORKDIR /src

COPY target/AluraFlix-1.0.0-SNAPSHOT.jar /src/AluraFlix-1.0.0-SNAPSHOT.jar

CMD ["java", "-jar", "AluraFlix-1.0.0-SNAPSHOT.jar"]
