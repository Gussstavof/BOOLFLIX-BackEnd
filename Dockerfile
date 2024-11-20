FROM openjdk:17

WORKDIR /src

COPY target/AluraFlix-1.0.0-SNAPSHOT.jar /src/AluraFlix-1.0.0-SNAPSHOT.jar

ENTRYPOINT  ["sh", "-c", "java -jar AluraFlix-1.0.0-SNAPSHOT.jar -DDB_URI=${DB_URI:-mongodb://database:27017/aluraflix} -DJWT_KEY=${JWT_KEY:-'secret-key'}"]
