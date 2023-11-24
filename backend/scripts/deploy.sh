#!/bin/bash
 
ROOT_DIRECTORY=/home/ubuntu/cicd
BUILD_PATH=/home/ubuntu/cicd/build/libs
BUILD_FILE_NAME="backend-0.0.1-SNAPSHOT.jar"

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

echo ">>> 새 빌드파일 덮어쓰기" >> /home/ubuntu/cicd/deploy.log
cp $BUILD_PATH/$BUILD_FILE_NAME $ROOT_DIRECTORY

echo ">>> 빌드 파일 경로로 이동" >> /home/ubuntu/cicd/deploy.log
cd $ROOT_DIRECTORY

echo ">>> 백그라운드 배포 시작" >> /home/ubuntu/cicd/deploy.log
nohup java -jar $BUILD_FILE_NAME >> /home/ubuntu/deploy.log 2>/home/ubuntu/cicd/deploy_err.log &
