# ICD0004 Project

## Technological stack
Java

Maven

Mockito

AssertJ

Lombok

Jersey

## Building and running the application

### Maven

Ensure that Maven has been installed on your machine. To check that, type the following command
into the terminal.
 ```bash
mvn -version
 ```
The installation guide can be found here: http://maven.apache.org/install.html

### Building the app

First make sure you are in the correct directory, which in our case is **icd0004-project**.
Then, compile and create a JAR file out of the project.
 ```bash
mvn clean compile assembly:single
 ```

### Running the app
  ```bash
java -cp target/icd0004-project-1.0-SNAPSHOT-jar-with-dependencies.jar ee.icd0004.mavozd.Main
  ```

### Testing the app
  ```bash
mvn test
  ```


