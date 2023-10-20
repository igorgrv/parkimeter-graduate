# docker build . -t parking-meter
# docker run -p 8080:8080 --network local-network --name  parking-meter-container parking-meter
FROM maven:3-openjdk-18-slim
USER root
COPY . /usr/server
WORKDIR /usr/server
RUN mvn package -DskipTests # comment to run locally
COPY ./target/*jar /usr/server/app.jar
ENTRYPOINT ["java", "-Xmx1024m", "-jar","app.jar"]
