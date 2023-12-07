FROM openjdk:11-jre-slim
ARG JAR_FILE=./build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} seektam-backend.jar
ENTRYPOINT ["java","-jar","/seektam-backend.jar"] 
