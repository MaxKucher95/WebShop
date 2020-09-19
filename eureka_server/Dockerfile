FROM openjdk:12
ADD target/eureka_*.jar eureka.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka.jar"]