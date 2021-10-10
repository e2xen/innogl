FROM openjdk:15
VOLUME /tmp
ENV JAR=build/libs/*.jar
COPY ${JAR} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]