FROM openjdk:11
ARG JAR_FILE=target/vehicles-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/application.jar"]

