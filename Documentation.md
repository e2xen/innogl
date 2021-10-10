# Design Documentation
**Our project consists of 2 main parts: frontend and backend.**

Frontend is written on JavaScript with the use of React library. 
Backend is written on Java with the use of Spring framework. 

As a database we use Redis as in-memory db with fast I/O operations, in the memory we store 
only user sessions and chat sessions. For the chat we do not store any messages on the server,
we only resend them to the channels. 

We decided to use libraries and frameworks for the project as they have a good architecture 
inside which was tested over long time.


## Design
### SOLID and Design Patterns
During the development process we followed SOLID principals. Also, we used some of the design patterns:
*Controller*(for end-points), *Builder*(for configuration) and *Interceptor*(for filtering the requests).

### Class Diagram
The following diagram shows the structure of a system by showing the system's classes, their attributes, operations (or methods), and the relationships among objects.
![Class Diagram](./diagrams/uml_classes.png)

### Database Schema
The following schema represents the logical configuration of all or part of **Redis database**.
![Database Schema](./diagrams/uml_db_schema.jpg)

### UML Deployment Diagram(Allocation View)
The following diagram shows on which physical units the software elements reside.
![Allocation View](./diagrams/uml_allocation_view.jpg)

### Static View Diagram
The following diagram models concepts in the application domain, which is displayed in class diagrams.
![Static View](./diagrams/uml_static_view.jpg)

### Dynamic View Diagram
The following diagram models concepts in the application domain, which is displayed in class diagrams.
![Dynamic View](./diagrams/uml_dynamic_view.jpg)

### Sequence Diagram
The following diagram shows how and in what order a group of objects works together.
![Sequence Diagram](./diagrams/uml_sequence.jpg)