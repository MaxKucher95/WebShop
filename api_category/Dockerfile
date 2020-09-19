FROM openjdk:12
ADD target/api_category*.jar apicategory.jar
EXPOSE 8204
ENTRYPOINT ["java", "-jar", "apicategory.jar"]