# Backend Innogl
This folder contains backend part of Innogl project.

## How to run
### Pre-installation
You should have pre-installed [Java 16](https://www.codejava.net/java-se/download-and-install-oracle-jdk-16) and [Docker](https://docs.docker.com/engine/install/).

#### If you want to build your own JAR file:
* Pull this repo from github and navigate to **innogl/backend**:
```shell
 cd innogl/backend
```
* Build a JAR file
```shell
 ./gradlew clean build
```
* Compose a docker image and run it
```
 docker-compose up
```
*Important:* for this step you need docker running in background.
#### Now you're up and running! Backend is accessible at a default 8080 port on your machine.

## API
For reference, if you are interested in an API provided by the backend, you may proceed to http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs after running the application.
![AutoSwagger Image](./swagger.png)