FROM maven:3.8-jdk-8

WORKDIR /usr/src/app
COPY . ./

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]