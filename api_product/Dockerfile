FROM openjdk:12
ADD target/api_product*.jar apiproduct.jar
EXPOSE 8203
ENTRYPOINT ["java", "-jar", "apiproduct.jar"]