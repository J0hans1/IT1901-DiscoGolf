package discoGolf.core;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        dataList.add(new ScorecardDAO(course1, "Lars", 13, 1));
        dataList.add(new ScorecardDAO(course1, "Ane", 18, 2));
        dataList.add(new ScorecardDAO(course1, "Arne", 18, 2));
        dataList.add(new ScorecardDAO(course1, "Maren", 13, -1));
        dataList.add(new ScorecardDAO(course2, "Oskar", 23, 3));
        dataList.add(new ScorecardDAO(course2, "Kari", 15, 1));
        dataList.add(new ScorecardDAO(course2, "Karin", 15, 0));
        dataList.add(new ScorecardDAO(course2, "Kai", 15, 0));
        dataList.add(new ScorecardDAO(course2, "Nora", 23, 2));
        data.setData(dataList);
        leaderboard = new Leaderboard(data);
    }

    /**
     * Test setLeaderboard() by creating new Leaderboard object and check that
     * getLeaderboardForAllCourse is correct and all the leaderboard (lists) are sorted correct
    */
    @Test
    public void testSetLeaderboard() {
        HashMap<String, ArrayList<ScorecardInterface>> leaderboards = leaderboard.getLeaderboardForAllCourses();
        assertEquals(4, leaderboards.get("Lade").size());
        assertEquals("Maren", leaderboards.get("Lade").get(0).getPlayerName());
        assertEquals("Lars", leaderboards.get("Lade").get(1).getPlayerName());
        assertEquals("Ane", leaderboards.get("Lade").get(2).getPlayerName());
        assertEquals("Arne", leaderboards.get("Lade").get(3).getPlayerName());

        assertEquals(5, leaderboards.get("Gløshaugen").size());
        assertEquals("Kai", leaderboards.get("Gløshaugen").get(0).getPlayerName());
        assertEquals("Karin", leaderboards.get("Gløshaugen").get(1).getPlayerName());
        assertEquals("Kari", leaderboards.get("Gløshaugen").get(2).getPlayerName());
        assertEquals("Nora", leaderboards.get("Gløshaugen").get(3).getPlayerName());
        assertEquals("Oskar", leaderboards.get("Gløshaugen").get(4).getPlayerName());
    }

    /**
     * Test getLeaderboardForCourse by checking that the list returned just contains
     * scorecard objects with the correct course name, and if the course name not excist
     * it should return an empty arrayList
    */
    @Test
    public void testGetLeaderboardForCourse() {
        ArrayList<ScorecardInterface> leaderboard1 = leaderboard.getLeaderboardForAllCourses().get("Lade");
        assertEquals(4, leaderboard1.size());
        assertTrue(leaderboard1.stream().allMatch(p -> p.getCourseName().equals("Lade")));
        ArrayList<ScorecardInterface> leaderboard2 = leaderboard.getLeaderboardForAllCourses().get("Gløshaugen");
        assertEquals(5, leaderboard2.size());
        assertTrue(leaderboard2.stream().allMatch(p -> p.getCourseName().equals("Gløshaugen")));
        assertEquals(new ArrayList<>(Arrays.asList()), leaderboard.getLeaderboardForCourse("Dragvoll"));
    }

    /**
     * Test that updateLeaderboard updates the relevant leaderboard correct, and if
     * a scorecard with a new course is added, a new leaderboard list should appear
    */
    @Test
    public void testUpdateLeaderboard() {
      
      leaderboard.updateLeaderboard(new ScorecardDAO(course1, "Lars", 12, 1));
      assertEquals(5, leaderboard.getLeaderboardForCourse("Lade").size());
      assertEquals("Lars", leaderboard.getLeaderboardForCourse("Lade").get(0).getPlayerName());
      
      leaderboard.updateLeaderboard(new ScorecardDAO(course2, "Jakob", 15, -1));
      assertEquals(6, leaderboard.getLeaderboardForCourse("Gløshaugen").size());
      assertEquals("Jakob", leaderboard.getLeaderboardForCourse("Gløshaugen").get(0).getPlayerName());
      
      Course newCourse = new Course("NewCourse", new ArrayList<>(Arrays.asList(3)));
      leaderboard.updateLeaderboard(new ScorecardDAO(newCourse, "Lars", 13, 1));
      leaderboard.updateLeaderboard(new ScorecardDAO(newCourse, "Mari", 13, 0));
      assertEquals(2, leaderboard.getLeaderboardForCourse("NewCourse").size());
      assertEquals("Mari", leaderboard.getLeaderboardForCourse("NewCourse").get(0).getPlayerName());
      assertEquals("Lars", leaderboard.getLeaderboardForCourse("NewCourse").get(1).getPlayerName());
      assertTrue(leaderboard.getLeaderboardForCourse("NewCourse").stream().allMatch(p -> p.getCourseName().equals("NewCourse")));
    }   
}
  
