FROM openjdk:17-alpine
WORKDIR /app
COPY target/docker_mongodb_rabbitmq_java_spring-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "./docker_mongodb_rabbitmq_java_spring-0.0.1-SNAPSHOT.jar"]