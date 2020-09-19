FROM openjdk:12
ADD target/zuul_*.jar zuul.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "zuul.jar"]