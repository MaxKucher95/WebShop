FROM openjdk:12
ADD target/hsystrix*.jar hystrix.jar
EXPOSE 8099
ENTRYPOINT ["java", "-jar", "hystrix.jar"]