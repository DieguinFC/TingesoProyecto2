FROM openjdk:17
ARG JAR_FILE=build/libs/Microservice2-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} Microservice2.jar
ENTRYPOINT ["java","-jar","/Microservice2.jar"]