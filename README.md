[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2224/gr2224)

# README

## Content

A companion application for discgolfing at the Trondheim local courses; Lade and Dragvoll.
The repository contains:

- Documentation
  - [docs](docs) folder directory with a folder for each release
  - A [contract](Kontrakt.md) between the members of the application team
  - This readme file
  - [PlantUML](docs/release2/release2.md) diagrams
- Configuration Files
  - Pom.xml files for Maven modules
  - Checkstyle configuration files
  - Spotbugs configuration files
  - Jacoco configuration files
- Tests
  - Junit5 test cases for the logic
  - TestFX test cases for the GUI
- Several Maven modules
  - [Json](discogolf/core/src/main/java/discoGolf/json/) module for data persistence with Jackson library
  - [Core](discogolf/core/src/main/java/discoGolf/json/) module with the logic classes for the application
  - [UI](discogolf/ui/src/main/java/ui/) module with the GUI classes for the application: controllers, fxml files and the AppClass for running the application
  - [FXUtil](discogolf/fxutil/src/main/java/fxutil/) module for future use

A full description of the project architecture can be found in the [release 3](docs/release3/release3.md) documentation

## Navigation through repository content

The application exists within the discogolf directory at path:

```gr2224/discogolf/```

and the application can be run from the DiscoGolfApp directory at path:

```gr2224/discoGolf/ui/src/main/java/ui/DiscoGolfApp.java```

The data is stored in a .json file (database.json) at the user's home directory:
```C:/Users/username/database.json```

## How to run the application and tests using Maven

All maven commands must be run from the ```/discoGolf/``` directory:

- To run the application: ```mvn javafx:run -pl ui```
- To run the tests: ```mvn test```
- To verify ```mvn verify```
- To compile project from scratch ```mvn clean install```

## How to run the server

1. Navigate into the restapi directory ```cd restapi```
2. Type ```./mvnw spring-boot:run``` to start the server


## How to convert to executable 

Navigate into the ui directory :
```powershell
cd ui
```
Compile the project to make sure all dependancies are packaged properly:
```powershell
mvn clean install
```
Convert the project into an executable:
```powershell
mvn clean compile javafx:jlink jpackage:jpackage
```