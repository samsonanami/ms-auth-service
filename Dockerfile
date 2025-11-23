FROM openjdk:26-ea-jdk
VOLUME /tmp
ARG JAR_FILE=target/ms-auth-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} ms-auth-service.jar
ENTRYPOINT ["java","-jar","/ms-auth-service.jar"]
EXPOSE 8001
LABEL maintainer="samsonanami@gmail.com"
