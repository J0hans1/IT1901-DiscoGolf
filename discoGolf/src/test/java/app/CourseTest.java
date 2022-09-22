package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testController() {
        assertEquals("Dragvoll", course.getCourseName());
        assertEquals(9, course.getNumberOfHoles());
        course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 3)));
        assertEquals("Lade", course.getCourseName());
        assertEquals(4, course.getNumberOfHoles());
    }

    @Test
    public void testSetParForHole() {
        assertEquals(3, course.getParForHole(1));
        assertEquals(5, course.getParForHole(9));
        course.setParForHole(9, 3);
        assertEquals(3, course.getParForHole(9),
        "The par on hole 9 is now set to 3");
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(-1, 3);
        }, "Not a valid hole number");
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(2, 2);
        }, "Not a valid par number");
    }

}
