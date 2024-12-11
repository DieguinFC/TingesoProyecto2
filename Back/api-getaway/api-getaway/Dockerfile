FROM openjdk:17
ARG JAR_FILE=build/libs/api-getaway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} api-getaway.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/api-getaway.jar"]