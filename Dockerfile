FROM openjdk:latest
COPY ./target/setMethods-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "setMethods-0.1.0.1-jar-with-dependencies.jar"]