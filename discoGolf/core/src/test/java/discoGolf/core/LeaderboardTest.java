package discoGolf.core;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * J-unit test for Leaderboard class
 * @author Jakob Opland and Markus Johansen
 * @version 1.0
 * @since 2022-11-07
 */
public class LeaderboardTest {
    private Leaderboard leaderboard;
    private Data data;
    Course course1;
    Course course2;
  
    /**
     * Create create ScorecardDAO objects with two differents courses
     * and add all of them to the Data object
     */
    @BeforeEach
    public void setUp() {
        data = new Data();
        course1 = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 3)));
        course2 = new Course("Gløshaugen", new ArrayList<>(Arrays.asList(5, 4, 5, 3, 6, 3)));
        ArrayList<ScorecardInterface> dataList = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Lars", "Ane", "Arne", "Maren", "Oskar", "Kari", "Karin", "Kai", "Nora"));
        ArrayList<Integer> totalScores = new ArrayList<>(Arrays.asList(13, 18, 18, 13, 23, 15, 15, 15, 23));
        ArrayList<Integer> bestHoles = new ArrayList<>(Arrays.asList(1, -1, -1, -1, 3, 4, 0, 0, 2));
        for (int i = 0; i < names.size(); i++) {
            dataList.add(new ScorecardDAO(course1, names.get(i), totalScores.get(i), bestHoles.get(i)));
            dataList.add(new ScorecardDAO(course2, names.get(i), totalScores.get(i), bestHoles.get(i)));
        }
        data.setData(dataList);
        leaderboard = new Leaderboard(data);
    }

    /**
     * Test setLeaderboard() by creating new Leaderboard object and check that
     * getLeaderboardForAllCourse is correct and all the leaderboard (lists) are sorted correct
    */
    @Test
    public void testSetLeaderboard() {
        ArrayList<ScorecardInterface> leaderboardsCourse1 = leaderboard.getLeaderboardForCourse("Lade");
        ArrayList<String> namesSorted = new ArrayList<>(Arrays.asList("Maren", "Lars", "Kai", "Karin", "Kari", "Ane", "Arne", "Nora", "Oskar"));
        ArrayList<Integer> totalScoresSorted = new ArrayList<>(Arrays.asList(13, 13, 15, 15, 15, 18, 18, 23, 23));
        ArrayList<Integer> bestHolesSorted = new ArrayList<>(Arrays.asList(-1, 1, 0, 0, 4, -1, -1, 2, 3));
        
        for (int i = 0; i < namesSorted.size(); i++) {
            int expectedScore = totalScoresSorted.get(i);
            int expectedBestHole = bestHolesSorted.get(i);
            assertEquals(namesSorted.get(i), leaderboardsCourse1.get(i).getPlayerName());
            assertEquals(expectedScore, leaderboardsCourse1.get(i).getScore());
            assertEquals(expectedBestHole, leaderboardsCourse1.get(i).getBestHole());
        }
    }

    /**
     * Test getLeaderboardForCourse by checking that the list returned just contains
     * scorecard objects with the correct course name, and if the course name not excist
     * it should return an empty arrayList
    */
    @Test
    public void testGetLeaderboardForCourse() {
        ArrayList<ScorecardInterface> leaderboard1 = leaderboard.getLeaderboardForCourse("Lade");
        assertEquals(9, leaderboard1.size());
        assertTrue(leaderboard1.stream().allMatch(p -> p.getCourseName().equals("Lade")));
        ArrayList<ScorecardInterface> leaderboard2 = leaderboard.getLeaderboardForCourse("Gløshaugen");
        assertEquals(9, leaderboard2.size());
        assertTrue(leaderboard2.stream().allMatch(p -> p.getCourseName().equals("Gløshaugen")));
        assertEquals(new ArrayList<>(Arrays.asList()), leaderboard.getLeaderboardForCourse("Dragvoll"));
    }

    /**
     * Test that updateLeaderboard updates the relevant leaderboard correct, and if
     * a scorecard with a new course is added, a new leaderboard list should appear
    */
    @Test
    public void testUpdateLeaderboard() {
      
      leaderboard.updateLeaderboard(new ScorecardDAO(course1, "Jakob", 12, 1));
      assertEquals(10, leaderboard.getLeaderboardForCourse("Lade").size());
      assertEquals("Jakob", leaderboard.getLeaderboardForCourse("Lade").get(0).getPlayerName());
      
      leaderboard.updateLeaderboard(new ScorecardDAO(course2, "Bob", 13, 2));
      assertEquals(10, leaderboard.getLeaderboardForCourse("Gløshaugen").size());
      assertEquals("Bob", leaderboard.getLeaderboardForCourse("Gløshaugen").get(2).getPlayerName());
      
      Course newCourse = new Course("NewCourse", new ArrayList<>(Arrays.asList(3)));
      leaderboard.updateLeaderboard(new ScorecardDAO(newCourse, "Lars", 13, 1));
      leaderboard.updateLeaderboard(new ScorecardDAO(newCourse, "Mari", 13, 0));
      assertEquals(2, leaderboard.getLeaderboardForCourse("NewCourse").size());
      assertEquals("Mari", leaderboard.getLeaderboardForCourse("NewCourse").get(0).getPlayerName());
      assertEquals("Lars", leaderboard.getLeaderboardForCourse("NewCourse").get(1).getPlayerName());
      assertTrue(leaderboard.getLeaderboardForCourse("NewCourse").stream().allMatch(p -> p.getCourseName().equals("NewCourse")));
    }   
}
  
