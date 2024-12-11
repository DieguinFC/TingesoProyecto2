FROM openjdk:17
ARG JAR_FILE=build/libs/MicroService1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} MicroService1.jar
ENTRYPOINT ["java","-jar","/MicroService1.jar"]