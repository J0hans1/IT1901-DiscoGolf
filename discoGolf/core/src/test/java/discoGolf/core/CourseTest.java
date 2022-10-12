package discoGolf.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import discoGolf.core.Course;

/**
 * TestFX course class test
 */
public class CourseTest {
    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5)));
    }
    
    @Test
    @DisplayName("Test the controller when creating a Course object")
    public void testController() {
        assertEquals("Dragvoll", course.getCourseName());
        assertEquals(9, course.getNumberOfHoles());

        course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 3)));
        assertEquals("Lade", course.getCourseName());
        assertEquals(4, course.getNumberOfHoles());
    }

    @Test
    @DisplayName("Test setParForHole method and test for invalid input")
    public void testSetParForHole() {
        assertEquals(5, course.getParForHole(9),
        "Hole 9 should have par of 5");
        course.setParForHole(9, 3);
        assertEquals(3, course.getParForHole(9),
        "The par on hole 9 should now be 3");
        
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(-1, 3);
        }, "-1 id not a valid hole number");
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(2, 1);
        }, "1 is not a valid par number");
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(2, 8);
        }, "8 is not a valid par number");
    }

}

