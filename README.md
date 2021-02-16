95.9% Coverage

# To-Do List Application

This is a To-Do List Web Application. It stores Lists and list items with SQL on the local system
The application has full CRUD functionality for both database entities through a user interface.

##Getting Started

##Prerequisites 

This is a java project so java is required. If you do not already have java installed you can download it here:
https://www.oracle.com/uk/java/technologies/javase-downloads.html
Run the .exe file and choose a location to save the JDK to. In your computers settings, go to edit enviroment variables and edit system variables from there, create a new system variable 

```
JAVA_HOME 
```

point it to the jdk file. Now, in the PATH variable under 'User variables for Admin' add

```
 %JAVA_HOME%/bin
```

Now java is installed.

Secondly you will need maven which can be downloaded here:
https://maven.apache.org/download.cgi
follow similar steps to the java install, in the enviroment variables, create a new variable in system variables called 

```
MAVEN_HOME 
```

and point it to the downloaded maven file. Add this variable to the PATH variable like before.

### Installing

To install this software firstly you must clone the repo. 
Once cloned, you will need to install a web driver the chrome driver
can be done from here:

https://chromedriver.storage.googleapis.com/index.html

ensure that the version of you web driver is earlier than your chrome browser. Download
and extract this file and add it to src/main/resources/drivers/chrome. The software
should now be ready to go.

Navigate to the repository in your command line and enter,


```
mvn clean
```

followed by 

```
mvn package
```

The project is now built, run the following command to start the project.


```
java -jar ToDoList-0.0.1-SNAPSHOT.jar
```

the application should now be running. Enter http://localhost:8080/ into your web browser
and the application's interface will appear. You can enter http://localhost:8080/h2 to access 
the databse directly.

## Running the tests

To run the tests enter

```
mvn test
```

in the project directory

### Unit Tests 

These tests test all methods in the controller and services classes. They ensure that each method returns the expected
return value or is called if there is no return value. The tests have an 95.9% coverage of the code.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Will McCudden** - *Initial work* - [willmccuddenQA](https://github.com/willmccuddenQA)