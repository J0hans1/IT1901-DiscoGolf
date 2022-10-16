# Release 1

At the start of the project, a group-contract was established. The contract established some project rules and biweekly meeting hours. Furthermore we discussed the application concept and produced a design for the app concept. We decided that the application would be a disc golf app, which would be used to track the score of a disc golf game. The first UI-design of the app was produced in Figma. Pictures of the UI design can be seen below.

Before we started to write code, we created a milestone for sprint 1 in gitlab. The milestone was called "Demonstrerbar app til torsdag". We used this tool to divided issues between the project participants, which helped our logistic structure while developing the application.

## Class structure description for release 1

The course is hardcoded into MainPageController.java and passed through to ScorecardPageController.java. Only the playerName and course are created here. The scorecard is then created in the ScorecardPageController.java. The basic classes are Course.java and Scorecard.java. These track all the players activity across the two scenes. The DatabaseHandler.java stores all games in Data/database.txt.

## Future funcionality ideas

For future releases we would like to some functionality in addition to the current functionality in release 1. Here are some of the ideas we have for future releases:

- A leaderboard created from the games located in the database.
- Some form of dynamic sorting of the leaderboard scores.
- A new page for the creation of new courses so the users can create their own.
- Posibility to cancel their current game by a button on the scorecard scene.
- A better overview of all the players scores on the scorecard scene.
