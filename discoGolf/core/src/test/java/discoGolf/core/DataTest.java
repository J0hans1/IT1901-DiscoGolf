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

    @BeforeEach
    public void setUp() {
        data = new Data();
    }

    /**
     * Test that the method setData updates the arraylist in data class
     */
    @Test
    public void testSetData() {
        ArrayList<Scorecard> scorecardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 6, 7, 5)));
            Scorecard scorecard = new Scorecard(course, "Billy");
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
        Course course = new Course("Lade", new ArrayList<>(Arrays.asList(5, 2, 6, 7, 3)));
        Scorecard scorecard = new Scorecard(course, "Arne");
        data.add(scorecard);
        assertEquals(scorecard, data.getData().get(0));
    }
}
