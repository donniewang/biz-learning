# biz-learning


mvn clean package -Dmaven.test.skip=true

docker build -t biz-learning-eureka-security-server .

docker network create test

docker run -dit -p 8001:8001 -e "SPRING_PROFILES_ACTIVE=slave1" --name biz-learning-eureka-security-slave1 --network test biz-learning-eureka-security-server

docker run -dit -p 8002:8002 -e "SPRING_PROFILES_ACTIVE=slave2" --name biz-learning-eureka-security-slave2 --network test biz-learning-eureka-security-server

docker run -dit -p 8003:8003 -e "SPRING_PROFILES_ACTIVE=slave3" --name biz-learning-eureka-security-slave3 --network test biz-learning-eureka-security-server



mvn clean package spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=slave1

mvn clean package spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=slave2

mvn clean package spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=slave3