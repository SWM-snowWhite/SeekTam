FROM openjdk:11-jre-slim
ARG JAR_FILE=./backend/build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} seektam-backend.jar
ENTRYPOINT ["nohup","java","-jar","/seektam-backend.jar","&"] 
