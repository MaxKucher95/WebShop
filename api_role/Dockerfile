FROM openjdk:12
ADD target/api_role*.jar apirole.jar
EXPOSE 8104
ENTRYPOINT ["java", "-jar", "apirole.jar"]