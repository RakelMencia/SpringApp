FROM java:8-jdk-alpine

COPY ./target/SpringApp-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch SpringApp-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","SpringApp-0.0.1-SNAPSHOT.jar"]