# Backend Innogl
This folder contains backend part of Innogl project.

## How to run
### Pre-installation
You should have pre-installed [Java 16](https://www.codejava.net/java-se/download-and-install-oracle-jdk-16) and [Docker](https://docs.docker.com/engine/install/).

#### If you want to build your own JAR file:
* Firstly, run unit tests:
```shell
 ./gradlew test
```
* Build a JAR file
```shell
 ./gradlew build -x test --stacktrace
```
* Run the project
```
 java -jar ./build/libs/application-0.0.1-SNAPSHOT.jar -Xms256M -Xmx1G
```