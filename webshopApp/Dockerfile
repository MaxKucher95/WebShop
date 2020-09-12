#FROM maven:8-jdk-alpine as builder
FROM maven:3.5.4-jdk-8-alpine as builder

COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package

FROM tomcat:9.0
COPY --from=builder /target/EShop-1.0.0.war /usr/local/tomcat/webapps/
COPY ./conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

EXPOSE 8080


#ADD target/EShop-*.jar /target/EShop.jar
#ENTRYPOINT ["java","-jar", "EShop.jar"]

#FROM maven:3.5.4-jdk-8-alpine as builder

##COPY ./pom.xml ./pom.xml
#COPY ./src ./src
#RUN mvn clean package

#FROM tomcat:8.0
#COPY --from=builder /target/EShop-1.0.0.war /usr/local/tomcat/webapps/
#COPY ./conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

#EXPOSE 8080
