package discoGolf.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * J-unit for Course class
 * @author Ulrik Isdahl and Jakob Opland
 * @version 1.0
 * @since 2022-09-28
 */
public class CourseTest {
    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3, 4, 5, 3)));
    }
    
    /**
     * Test constructor to see if the fields are set correct, also
     * test for invalid input to the constructor 
     */
    @Test
    public void testConstructor() {
        assertEquals("Dragvoll", course.getCourseName());
        assertEquals(4, course.getNumberOfHoles());
        assertEquals(new ArrayList<>(Arrays.asList(3, 4, 5, 3)), course.getParValues());
        assertEquals(5, course.getParForHole(3));
        assertThrows(IllegalArgumentException.class, () -> {
            course = new Course("Lade", new ArrayList<>(Arrays.asList(1)));
        }, "1 is not a valid par number");
        assertThrows(IllegalArgumentException.class, () -> {
            course = new Course("Lade", new ArrayList<>(Arrays.asList(8)));
        }, "8 is not a valid par number");
    }
    

    /**
     * Test set par for hole value, and invalid input
     */
    @Test
    public void testSetParForHole() {
        assertEquals(4, course.getParForHole(2));
        course.setParForHole(4, 7);
        assertEquals(7, course.getParForHole(4)); 
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(-1, 3);
        }, "-1 id not a valid hole number");
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(2, 1);
        }, "1 is not a valid par number");
        assertThrows(IllegalArgumentException.class, () -> {
            course.setParForHole(2, 8);
        }, "9 is not a valid par number");
    }

}

