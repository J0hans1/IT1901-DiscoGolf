package discoGolf.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

  public static void main(String[] args) {
      Course course1 = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 3)));
      Scorecard scorecard1 = new Scorecard(course1, "Jakob", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 3)));
      Scorecard scorecard2 = new Scorecard(course1, "Markus", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 4, 4, 2)));
      Scorecard scorecard3 = new Scorecard(course1, "Ulrik", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 9)));
      ArrayList<Scorecard> leaderboard = new ArrayList<>();
      leaderboard.add(scorecard3);
      leaderboard.add(scorecard2);
      leaderboard.add(scorecard1);

      LeaderboardComparator comparator = new LeaderboardComparator();
      Collections.sort(leaderboard, comparator);
      for (Scorecard card : leaderboard) {
        System.out.println(card.getPlayerName());
        System.out.println(card.getTotalScore());
      }
  }

}
