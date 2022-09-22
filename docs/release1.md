### Release 1
When starting the project we first met to establish a group contract, group rules and the biweekly meeting hours. Furthermore,which we used to discuss and design the app concept. We decided on the creation of a disc golf app, which would be used to track the score of a disc golf game. The first visual design of the app was created in Figma and can be seen in the README.md file in the src directory.

Before we started to write code, we first went into gitlab and created the first milestone of this project called "Demonstrerbar app til torsdag". We divided the things we had to do into smaller problems, and created relevant issues under this milestone. 

Class structure description for release 1:

The course is hardcoded into MainPageController.java and passed through to ScorecardPageController.java. Only the playerName and course are created here. The scorecard is then created in the ScorecardPageController.java. The basic classes are Course.java and Scorecard.java. These track all the players activity across the two scenes. The DatabaseHandler.java stores all games in Data/database.txt.

Future funcionality ideas:
* A leaderboard created from the games located in the database.
    * Some form of dynamic sorting of the leaderboard scores.
* A new page for the creation of new courses so the users can create their own.
* Posibility to cancel their current game by a button on the scorecard scene.
* A better overview of all the players scores on the scorecard scene.
