package discoGolf.core;

import java.util.ArrayList;
//import java.util.Comparator;
import java.util.HashMap;

/**
 * Leadboard class that holds alle the saved Scorecard 
 * objects in sorted order.
 */
public class Leaderboard {
  private Data dataObject;
  private HashMap<String, ArrayList<Scorecard>> leaderboardForAllCourses;

  /**
   * Inizialice leaderboard object by reading Data getData() list
   * @param leaderboardList Data object that contains saved Scorecards
   */
  public Leaderboard(Data dataObject) {
    this.dataObject = dataObject;
    this.leaderboardForAllCourses = setLeaderboard();
  }

  /**
   * Create a HashMap where each key is a unique courseName saved 
   * in the database, mapping to an arrayList that holds each Scorecard
   * connected to that unique course. 
   * @return
   */
  private HashMap<String, ArrayList<Scorecard>> setLeaderboard() {
    HashMap<String, ArrayList<Scorecard>> leaderboardHashMap = new HashMap<>();

    for (Scorecard scorecard : dataObject.getData()) {
      String courseName = scorecard.getCourseName();
      ArrayList<Scorecard> courseLeaderboard = new ArrayList<>();

      if (leaderboardHashMap.containsKey(courseName)) {
        courseLeaderboard = leaderboardHashMap.get(courseName);
      } 
      courseLeaderboard.add(scorecard);
      leaderboardHashMap.put(courseName, courseLeaderboard);
    }
    return leaderboardHashMap;
  }

  /**
   * @return leaderboardForAllCourses hashamap containing courseNames
   * as keys, and realated arraylists with scorecard objects as values
   */
  public HashMap<String, ArrayList<Scorecard>> getLeaderboardForAllCourses() {
    return leaderboardForAllCourses;
  }

  /**
   * 
   * @param courseName the coursesName for a spesific course.
   * @return the leaderboard for the unique course name.
   * @throws IllegalArgumentException if the courseName key doesnt excist in the hashmap.
   */
  public ArrayList<Scorecard> getLeaderboardForCourse(String courseName) {
    if (!leaderboardForAllCourses.containsKey(courseName)) {
      throw new IllegalArgumentException("The course does not excist!");
    }
    return leaderboardForAllCourses.get(courseName);
  }

  /**
   * returns leaderboardList sorted by 
   * @param leaderboardList
   */
  private ArrayList<Scorecard> sortLeaderboardList(ArrayList<Scorecard> scorecardLeaderboard) {
    //return scorecardLeaderboard.sort(null);
  }

}
