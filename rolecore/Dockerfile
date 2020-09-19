FROM openjdk:12
ADD target/role*.jar corerole.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "corerole.jar"]