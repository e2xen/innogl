# Innogl
## Description
This project is a fully anonymous web and video chats with strangers. Users can choose an online companion to discuss anything. Besides, if they agree, they can turn on their cameras to see each other leaving disregarding anonymity. The back-end part of the project is going to be written in Java 11 on Spring framework with the use of WebRTC library. The front-end part is going to be written in React. They will communicate via Rest API style.


## Glossary
- **Spring** - one of the most famous frameworks for Java projects.
- **Application Programming Interface (API)** - an API is a well-defined interface through which two software applications can communicate with each other and abstract the inner workings.
- **Rest API** - an application programming interface (API or web API) that conforms to the constraints of REST architectural style and allows for interaction with RESTful web services.
- **Unit testing** - the process of testing individual units of code.
- **Integration testing** - the phase in software testing in which individual software modules are combined and tested as a group.


## Installation
### Pre-installation
You should have installed Java 16 and Docker.

### Running a project
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
 java -jar ./build/libs/incidents-sso.jar -Xms256M -Xmx1G
```

## Code style
The code will be written using best practices of Spring and will be documented.

## Features
* Fully anonymous. 
* The back-end part of the project is going to be written with the use of one of the best frameworks in Java - Spring.
* Video streaming will be available with the direct connection between 2 users.
* The front-end part of the project is going to be writtern with the use of React.
* The design of the project will be adaptive and responsive: available on any device.
* Code will be tested via unit and integration testing. 
* Easy to run a project - it will be inside Docker container.
* The project will be working inside a cyber cluster.

## How to use
Open the following link in your browser [http://localhost:8080/](http://localhost:8080/).

## Contribution
You can contribute in your project - we are glad to new ideas. Just open pull requests. However, be sure to follow our style guids for the code.

## Credits
This project is going to be written by [@mcflydesigner, back-end dev](https://github.com/mcflydesigner), [@e2xen, back-end dev](https://github.com/e2xen) and [@Khalil19-99, front-end dev](https://github.com/Khalil19-99).

## License
The project is released and distributed under [MIT License](https://en.wikipedia.org/wiki/MIT_License).
