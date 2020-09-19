FROM openjdk:12
ADD target/comp_user*.jar compuserrole.jar
EXPOSE 8102
ENTRYPOINT ["java", "-jar", "compuserrole.jar"]