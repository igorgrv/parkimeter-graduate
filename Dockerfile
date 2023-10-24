# mvn clean install
# docker-compose build
# docker-compose up
FROM maven:3-openjdk-18-slim
USER root
COPY . /usr/server
WORKDIR /usr/server
COPY ./target/*jar /usr/server/app.jar
ENTRYPOINT ["java", "-Xmx1024m", "-jar","app.jar"]
