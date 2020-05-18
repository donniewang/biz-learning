mvn clean package -Dmaven.test.skip=true

docker build -t biz-learning-eureka-server .

docker network create test

docker run -dit -p 8001:8001 -e "SPRING_PROFILES_ACTIVE=slave1" --name biz-learning-eureka-slave1 --network test biz-learning-eureka-server

docker run -dit -p 8002:8002 -e "SPRING_PROFILES_ACTIVE=slave2" --name biz-learning-eureka-slave2 --network test biz-learning-eureka-server

docker run -dit -p 8003:8003 -e "SPRING_PROFILES_ACTIVE=slave3" --name biz-learning-eureka-slave3 --network test biz-learning-eureka-server
