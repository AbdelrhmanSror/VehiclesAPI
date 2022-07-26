FROM openjdk:11
ARG JAR_FILE=target/pricing-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/application.jar"]

