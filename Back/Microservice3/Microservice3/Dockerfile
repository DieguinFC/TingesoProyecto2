FROM openjdk:17
ARG JAR_FILE=build/libs/Microservice3-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} Microservice3.jar
ENTRYPOINT ["java","-jar","/Microservice3.jar"]