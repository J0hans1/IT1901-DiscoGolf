# Springboot REST API for discoGolf application

This document describes the format for discoGolf REST API instructions.

## Retrieve data from server

Methods:

* GET - retrieves a Data.java object containing all scorecards, from the server.
  * URI: host:port/data <http://localhost:8080/data>
  * Parameters: none
  * Available: springboot
  * Returns JSON representation of Data object
  
  ```json
  {
    "data" : [{
      "playerName" : "Maven",
      "score" : 13,
      "bestHole" : -3,
      "course" : {
        "courseName" : "Dragvoll",
        "numberOfHoles" : 18,
        "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4 ]
      }
    }, {
      "playerName" : "Jonas",
      "score" : 5,
      "bestHole" : 0,
      "course" : {
        "courseName" : "Lade",
        "numberOfHoles" : 9,
        "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3 ]
      }
    }]
  }
  ```

## Save scorecard to server

Methods:

* PUT - saves a scorecard to the server.
  * URI: host:port/add-scorecard <http://localhost:8080/add-scorecard>
  * Parameters:
    * Scorecard object to be saved at server, sent in http-request body
  
    ```json
    {
        "playerName" : "Per",
        "score" : 6,
        "bestHole" : 0,
        "course" : {
            "courseName" : "Lade",
            "numberOfHoles" : 9,
            "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3 ]
        }
    } 
    ```

  * Available: springboot
  * Returns: true if scorecard was saved successfully

  ```json
  true
  ```

## Test

Methods:

* GET - returns all scorecards from temporary test-database
  * URI: host:port/test/data <http://localhost:8080/test/data>
  * Parameters: none
  * Available: springboot
  * Returns JSON representation of Data object
  
  ```json
  {
    "data" : [{
      "playerName" : "Maven",
      "score" : 13,
      "bestHole" : -3,
      "course" : {
        "courseName" : "Dragvoll",
        "numberOfHoles" : 18,
        "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4 ]
      }
    }, {
      "playerName" : "Jonas",
      "score" : 5,
      "bestHole" : 0,
      "course" : {
        "courseName" : "Lade",
        "numberOfHoles" : 9,
        "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3 ]
      }
    }]
  }
  ```

* PUT - saves a scorecard to the temporary test-database
  * URI: host:port/test/add-scorecard <http://localhost:8080/test/add-scorecard>
  * parameters: Scorecard object to be saved at server, sent in http-request body
  
    ```json
    {
        "playerName" : "Per",
        "score" : 6,
        "bestHole" : 0,
        "course" : {
            "courseName" : "Lade",
            "numberOfHoles" : 9,
            "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3 ]
        }
    } 
    ```

  * Available: springboot
  * Returns: true if scorecard was saved successfully in test-database
  
    ```json
    true
    ```

* DELETE - deletes all scorecards from the temporary test-database
  * URI: host:port/test/delete-database <http://localhost:8080/test/delete-database>
  * Parameters: none
  * Available: springboot
  * Returns: true if all scorecards were deleted successfully from test-database
  
    ```json
    true
    ```
