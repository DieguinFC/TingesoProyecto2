FROM openjdk:17
ARG JAR_FILE=build/libs/config-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} config-service.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/config-service.jar"]