package discoGolf.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Leadboard class that holds alle the saved Scorecard
 * objects in a hashmap connected to their course, in sorted order. 
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
   * in the database. Each key maps to an arrayList that holds each Scorecard
   * connected to that unique course. 
   * @return
   */
  private HashMap<String, ArrayList<Scorecard>> setLeaderboard() {
    HashMap<String, ArrayList<Scorecard>> leaderboardHashMap = new HashMap<>();
  
    for (Scorecard scorecard : this.dataObject.getData()) {
      String courseName = scorecard.getCourseName();
      ArrayList<Scorecard> courseLeaderboard = new ArrayList<>();
  
      if (leaderboardHashMap.containsKey(courseName)) {
        courseLeaderboard = leaderboardHashMap.get(courseName);
      } 
      courseLeaderboard.add(scorecard);
      sortLeaderboardList(courseLeaderboard);
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
   * @param courseName the coursesName for a spesific course.
   * @return the leaderboard for the unique course name.
   */
  public ArrayList<Scorecard> getLeaderboardForCourse(String courseName) {
    if (!leaderboardForAllCourses.containsKey(courseName)) {
      return new ArrayList<>();
    }
    return leaderboardForAllCourses.get(courseName);
  }
  
  /**
   * returns scorecardLeaderboard sorted by LeaderboardComparator 
   * @param scorecardLeaderboard a list containing unsorted Scorecard objects
   */
  private ArrayList<Scorecard> sortLeaderboardList(ArrayList<Scorecard> scorecardLeaderboard) {
    Collections.sort(scorecardLeaderboard, new LeaderboardComparator());
    return scorecardLeaderboard;
  }
  
  /**
   * Add scorecard to relevant list in leaderboardForAllCourses based 
   * on which course the scorecard object contains. Also sort the relevant list.
   * @param scorecard the new scorecard object
   */
  public void updateLeaderboard(Scorecard scorecard) {
    String courseName = scorecard.getCourseName();
    ArrayList<Scorecard> courseLeaderboard = new ArrayList<>();

    if (getLeaderboardForAllCourses().containsKey(courseName)) {
      courseLeaderboard = getLeaderboardForAllCourses().get(courseName);
    } 
    courseLeaderboard.add(scorecard);
    sortLeaderboardList(courseLeaderboard);
    getLeaderboardForAllCourses().put(courseName, courseLeaderboard);
  }

}
