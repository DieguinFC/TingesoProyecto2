FROM openjdk:17
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/backend.jar"]
