# Release 2

## New features

This release we have implemented the following features:

* The submit button on the scorecard scene now saves the scorecard to the database in json format.

## Project architecture

The code architechture has been improved by following the Maven standard. The code is now split into multiple modules, which are all located in the discoGolf directory. The modules are:

* fxutil
* ui
* core

### fxutil

This module is not in use yet, but it is a module which will be used later.
It is a maven prepared module based on further expansion of the application in release 3/4

### UI

UI is the module which contains all the JavaFX related code.
This module contains all the controllers and the DiscoGolfApp.java class.
All fxml and UI- layer related code is located here.

### core

This module is the physical representation of the logic layer of the application. All logical classes are located here. This module is divided into two submodules, which are:

* core.core
* json

#### json

This submodule contains all the classes that handle the data-persistence in the application (serializing and deserializing the data, to and from JSON). The module is heavily reliant on the Jackson library. In detail it contains three pairs of serializers and deserializers, which are:

* CourseSerializer.java and CourseDeserializer.java
* ScorecardSerializer.java and ScorecardDeserializer.java
* DataArraySerializer.java and DataArrayDeserializer.java

These serializers are loaded into a Disco Golf module which is used to add the (de)serializers to the ObjectMapper. The main class of this module is Discogolf persistence.java, which is used to load and save the data from the database. This class together with its methods handle all interaction with the database.json file.

#### core.core

This submodule contains all the classes that are used to represent the data in the application. It contains the central logic in the application. The classes are: Course.java, Scorecard.java and Data.java. Data.java was added as a supporting class to the application, to make it easier to handle the data in the database. The class is used to store the scorecard in the database.json file as a list. There is only one Data.java object in the database.json file, and it is located at root level.
