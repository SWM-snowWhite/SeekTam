#!/bin/bash

ROOT_DIRECTORY=/home/ubuntu/cicd
BUILD_PATH=/home/ubuntu/cicd/build/libs
BUILD_FILE_NAME="backend-0.0.1-SNAPSHOT.jar"

echo ">>> gradlew 권한 부여" >> /home/ubuntu/cicd/deploy.log
cd $ROOT_DIRECTORY
chmod +x ./gradlew

echo ">>> 프로젝트 빌드" >> /home/ubuntu/cicd/deploy.log
./gradlew build

echo ">>> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/cicd/deploy.log
CURRENT_PID=$(pgrep -f $BUILD_FILE_NAME)

if [ -z $CURRENT_PID ]
then
  echo ">>> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/cicd/deploy.log
else
  echo ">>> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo ">>> 빌드 파일 경로로 이동" >> /home/ubuntu/cicd/deploy.log
cd $BUILD_PATH

echo ">>> 백그라운드 배포 시작" >> /home/ubuntu/cicd/deploy.log
java -jar $BUILD_FILE_NAME >> /home/ubuntu/deploy.log 2>/home/ubuntu/cicd/deploy_err.log &
