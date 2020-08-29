FROM openjdk:12
ADD target/eureka_server-0.0.1-SNAPSHOT.jar eureka_server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka_server-0.0.1-SNAPSHOT.jar"]