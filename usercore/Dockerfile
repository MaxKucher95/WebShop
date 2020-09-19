FROM openjdk:12
ADD target/user*.jar coreuser.jar
EXPOSE 8101
ENTRYPOINT ["java", "-jar", "coreuser.jar"]