#!/bin/bash

JAR_NAME=$(backend-0.0.1-SNAPSHOT.jar)
echo ">>> build 파일명: $JAR_NAME" >> /home/ubuntu/cicd/deploy.log

echo ">>> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/cicd/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo ">>> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/cicd/deploy.log
else
  echo ">>> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

JAR_PATH=$(/home/ubuntu/cicd/build/libs/)
echo ">>> 빌드 파일 경로: $DEPLOY_PATH" >> /home/ubuntu/cicd/deploy.log
DEPLOY_JAR=$JAR_PATH$JAR_NAME

echo ">>> DEPLOY_JAR 배포"    >> /home/ubuntu/cicd/deploy.log
nohup java -jar $DEPLOY_JAR >> /home/ubuntu/deploy.log 2>/home/ubuntu/cicd/deploy_err.log &
