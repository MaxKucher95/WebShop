FROM openjdk:12
ADD target/api_user*.jar apiuser.jar
EXPOSE 8103
ENTRYPOINT ["java", "-jar", "apiuser.jar"]