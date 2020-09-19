FROM openjdk:12
ADD target/product*.jar coreproduct.jar
EXPOSE 8201
ENTRYPOINT ["java", "-jar", "coreproduct.jar"]