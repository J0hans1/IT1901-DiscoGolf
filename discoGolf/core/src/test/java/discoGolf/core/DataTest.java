package discoGolf.core;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * J-unit test for Data class
 * @author Jakob Opland
 * @version 1.0
 * @since 2022-10-09
 */
public class DataTest {
  private Data data;
  private Course course;

  @BeforeEach
  public void setUp() {
    data = new Data();
    course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 6, 7, 5)));
  }

  /**
   * Test that the method setData updates the arraylist in data class
   */
  @Test
  public void testSetData() {
    ArrayList<ScorecardInterface> scorecardList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ScorecardDAO scorecard = new ScorecardDAO(course, "Billy", i, i - 1);
      scorecardList.add(scorecard);
    }
    assertEquals(new ArrayList<>(), data.getData());
    data.setData(scorecardList);
    assertEquals(scorecardList, data.getData());
  }

  /**
   * Test that data.getData() list gets updated when adding scorecard object
   */
  @Test
  public void testAddData() {
    assertEquals(new ArrayList<>(), data.getData());
    ScorecardDAO scorecard = new ScorecardDAO(course, "Arne", 30, 0);
    data.add(scorecard);
    assertEquals(scorecard, data.getData().get(0));
  }
}
