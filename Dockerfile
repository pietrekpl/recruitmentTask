FROM maven:3.8.1-openjdk-17-slim as builder
COPY ./pom.xml pom.xml
COPY ./src src/
RUN apt-get update && apt-get install -y fontconfig libfreetype6
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:17.0-oraclelinux7
COPY --from=builder target/recruitmentTask-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar