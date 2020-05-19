FROM java:8
VOLUME /tmp
ADD target/biz-learning-user-service-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT [ "java","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-jar","/app.jar" ]