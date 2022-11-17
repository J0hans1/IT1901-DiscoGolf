package discoGolf.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
   * Test constructor to see if the fields are set correct, also test for invalid input to the
   * constructor
   */
  @Test
  public void testConstructor() {
    assertEquals("Dragvoll", course.getCourseName());
    assertEquals(4, course.getNumberOfHoles());
    assertEquals(new ArrayList<>(Arrays.asList(3, 4, 5, 3)), course.getParValues());
    assertEquals(5, course.getHole(3).getPar());

    ArrayList<Hole> excpectedList = new ArrayList<>();
    excpectedList.add(new Hole(3));
    excpectedList.add(new Hole(4));
    excpectedList.add(new Hole(5));
    excpectedList.add(new Hole(3));
    assertTrue(compareCourseHolesLists(course.getCourseHoles(), excpectedList));

    assertThrows(IllegalArgumentException.class, () -> {
      course = new Course("Lade", new ArrayList<>(Arrays.asList(1)));
    }, "1 is not a valid par number");
    assertThrows(IllegalArgumentException.class, () -> {
      course = new Course("Lade", new ArrayList<>(Arrays.asList(8)));
    }, "8 is not a valid par number");
    assertThrows(IllegalArgumentException.class, () -> {
      course = new Course("Lade", new ArrayList<>(Arrays.asList()));
    }, "The parValues list cannot be empty");
    assertThrows(IllegalArgumentException.class, () -> {
      course = new Course("?Q:..", new ArrayList<>(Arrays.asList(8)));
    }, "Not a valid course name");
  }

  /**
   * check if two ArrayList<Hole> lists are equal
   * 
   * @param list1 getCourseHole list in course object
   * @param list2 created list
   * @return true if they are eqaul, false otherwise
   */
  private boolean compareCourseHolesLists(ArrayList<Hole> list1, ArrayList<Hole> list2) {
    boolean listsIsEqual = true;
    if (list1.size() != list2.size()) {
      return false;
    }
    for (int i = 0; i < list1.size(); i++) {
      if (list1.get(i).getPar() != list2.get(i).getPar()
          || list1.get(i).getHoleThrows() != list2.get(i).getHoleThrows()
          || list1.get(i).getHoleScore() != list2.get(i).getHoleScore()) {
        listsIsEqual = false;
      }
    }
    return listsIsEqual;
  }

  /**
   * Test set a new Hole in the course.
   */
  @Test
  public void testSetHole() {
    assertEquals(3, course.getHole(4).getPar());
    course.setHole(new Hole(7), 4);
    assertEquals(7, course.getHole(4).getPar());
    assertThrows(IllegalArgumentException.class, () -> {
      course.setHole(new Hole(3), 0);
    }, "0 is not a valid holeNumber");
  }

  /**
   * Test set coursename and test for invalid inputs
   */
  @Test
  public void testSetCourseName() {
    course.setCourseName("Lade");
    assertEquals("Lade", course.getCourseName());
    course.setCourseName("Gløshaugen123");
    assertEquals("Gløshaugen123", course.getCourseName());
    assertThrows(IllegalArgumentException.class, () -> {
      course = new Course("", new ArrayList<>(Arrays.asList(8)));
    }, "Not a valid course name");
    assertThrows(IllegalArgumentException.class, () -> {
      course = new Course("Gløs??", new ArrayList<>(Arrays.asList(8)));
    }, "Not a valid course name");
  }

  /**
   * Test get a Hole based on holeNumber in the course.
   */
  @Test
  public void testGetHole() {
    assertEquals(new Hole(3).getPar(), course.getHole(4).getPar());
    assertThrows(IllegalArgumentException.class, () -> {
      course.getHole(0);
    }, "The hole number is not valid");
    assertThrows(IllegalArgumentException.class, () -> {
      course.getHole(5);
    }, "The hole number is not valid");
  }
}

