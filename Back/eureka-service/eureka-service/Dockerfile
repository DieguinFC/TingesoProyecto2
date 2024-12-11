FROM openjdk:17
ARG JAR_FILE=build/libs/eureka-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka-service.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/eureka-service.jar"]