FROM openjdk:17

WORKDIR /src

COPY target/AluraFlix-0.0.1-SNAPSHOT.jar /src/AluraFlix-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "AluraFlix-0.0.1-SNAPSHOT.jar"]
