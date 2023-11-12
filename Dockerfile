FROM openjdk:19
LABEL authors="Seth Pal"
EXPOSE 8080
ADD target/notification-app.jar notification-app.jar
ENTRYPOINT ["java", "-jar","/notification-app.jar"]