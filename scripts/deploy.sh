#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_DIR_NAME=jojoldu-aws-spring-book
PROJECT_NAME=AwsSpringBook

echo ">Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo ">현재 구동 중인 애플리케이션 pid 확인"

# 현재 수행 중인 스프링 부트 애플리케이션의 프로세스 ID를 찾음
# 실행 중이면 종료 하기 위함
# 스프링 부트 애플리케이션 이름으로 된 다른 프로그램들이 있을 수 있어
# aws-spring-book 으로 된 jar(pgrep -fl aws-spring-book | grep jar) 프로세스를 찾은 뒤
# ID 를 찾음(awk '{print $1}')
CURRENT_PID=$(pgrep -fl aws-spring-book | grep jar | awk '{print $1}')

echo "현재 구동 중인 애플리케이션 pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo ">kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

# Jar 파일은 실행 권한이 없는 상태이기 때문에 nohup으로 실행 할 수 있게 권한을 부여
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

# nohup 실행 시 CodeDeploy는 무한 대기
# 해당 이슈를 해결하기 위해 nohup.out 파일을 표준입출력용으로 별도로 사용
# 이렇게 하지 않으면 nohup.out 파일이 생기지 않고, CodeDeploy 로그에 표준 입출력이 출력
# nohup이 끝나기 전까지 CodeDeploy도 끝나지 않으니 꼭 이렇게 실행
nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties,classpath:/application-real.properties \
        -Dspring.profiles.active=real \
        $$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &