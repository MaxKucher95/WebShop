FROM openjdk:12
ADD target/categorycore*.jar corecategory.jar
EXPOSE 8200
ENTRYPOINT ["java", "-jar", "corecategory.jar"]