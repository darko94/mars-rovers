# Mars Rovers
Mars Rovers is a Spring Boot REST application. It allows to explore a plateau on Mars, by deploying and navigating rovers on it. All the actions are executed by calling REST API methods with parameters in JSON format. The response is returned also in JSON format. 

## Getting Started
These instructions will get you a copy of the project up and running on your local machine.

#### Prerequisites
- Java 8+
- Maven
- Lombok
- Rest Client (Postman or some other)

#### Maven build and running the application
You can build the application from command line, or from some IDE.
From command line, you need to navigate to root folder and execute next command:

mvn clean install spring-boot:run

You can also run the .jar file directly, by executing next commands from root folder:

mvn clean install
java -jar target/mars-rovers-0.0.1-SNAPSHOT.jar

#### API documentation
REST API is documented with Swagger. When you start the application, you can access Swagger user interface, on address root/swagger-ui.html.