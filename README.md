[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2224/gr2224)

# Content

A companion application for discgolfing at the Trondheim local courses; Lade and Dragvoll.
The repository contains:

- Documentation
- Pom.xml files for Maven modules
- Junit5 test cases for the logic
- TestFX test cases for the GUI
- A Json module for data persistence with Jackson library
- A Core module with the logic classes for the application
- A UI module with the GUI classes for the application: controllers, fxml files and the App class
- Checkstyle configuration files
- Spotbugs configuration files
- Jacoco configuration files
- A contract between the members of the application team
- This readme file
- PlantUML diagram

## Navigation through the repository

The application exists within the discogolf directory at path:

```gr2224/discogolf/```

and the application can be run from the DiscoGolfApp directory at path:

```gr2224/discoGolf/ui/src/main/java/ui/DiscoGolfApp.java```

The data is stored in a .json file (database.json) at the user's home directory:
```C:/Users/username/database.json```
