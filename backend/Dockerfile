FROM openjdk:15
VOLUME /tmp

ENV JAR=build/libs/application-0.0.1-SNAPSHOT.jar

COPY ${JAR} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]