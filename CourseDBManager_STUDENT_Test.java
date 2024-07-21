import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Test class for CourseDBManager.
 * This class tests the functionalities of CourseDBManager, CourseDBElement, and CourseDBStructure.
 */
public class CourseDBManager_STUDENT_Test {
    private CourseDBElement course1;
    private CourseDBElement course2;
    private CourseDBStructure dbStructure;
    private CourseDBManager dbManager;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        course1 = new CourseDBElement("CS101", 12345, 4, "Room101", "Dr. Smith");
        course2 = new CourseDBElement("ENG202", 23456, 3, "Room202", "Prof. Jones");
        dbStructure = new CourseDBStructure(10);
        dbManager = new CourseDBManager();
    }

    /**
     * Tear down the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        course1 = null;
        course2 = null;
        dbStructure = null;
        dbManager = null;
    }

    /**
     * Test the getID method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_GetID() {
        assertEquals("CS101", course1.getID());
    }

    /**
     * Test the getCRN method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_GetCRN() {
        assertEquals(12345, course1.getCRN());
    }

    /**
     * Test the getCredits method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_GetCredits() {
        assertEquals(4, course1.getCredits());
    }

    /**
     * Test the getRoomNum method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_GetRoomNum() {
        assertEquals("Room101", course1.getRoomNum());
    }

    /**
     * Test the getInstructorName method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_GetInstructorName() {
        assertEquals("Dr. Smith", course1.getInstructorName());
    }

    /**
     * Test the compareTo method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_CompareTo() {
        assertTrue(course1.compareTo(course2) < 0);
    }

    /**
     * Test the toString method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_ToString() {
        String expected = "\nCourse:CS101 CRN:12345 Credits:4 Instructor:Dr. Smith Room:Room101";
        assertEquals(expected, course1.toString());
    }

    /**
     * Test the hashCode method of CourseDBElement.
     */
    @Test
    public void testCourseDBElement_HashCode() {
        CourseDBElement element = new CourseDBElement("CS101", 12345, 4, "Room101", "Dr. Smith");
        assertEquals(Integer.toString(12345).hashCode(), element.hashCode());
    }

    /**
     * Test adding and getting a course in CourseDBStructure.
     */
    @Test
    public void testCourseDBStructure_AddAndGet() throws IOException {
        dbStructure.add(course1);
        assertEquals(course1, dbStructure.get(12345));
    }

    /**
     * Test getting a non-existent CRN from CourseDBStructure.
     */
    @Test
    public void testCourseDBStructure_GetNonExistentCRN() {
        assertThrows(IOException.class, () -> {
            dbStructure.get(99999);
        });
    }

    /**
     * Test showing all courses in CourseDBStructure.
     */
    @Test
    public void testCourseDBStructure_ShowAll() {
        dbStructure.add(course1);
        dbStructure.add(course2);

        ArrayList<String> allCourses = dbStructure.showAll();
        assertTrue(allCourses.contains(course1.toString()));
        assertTrue(allCourses.contains(course2.toString()));
    }

    /**
     * Test the getTableSize method of CourseDBStructure.
     */
    @Test
    public void testCourseDBStructure_GetTableSize() {
        assertEquals(7, dbStructure.getTableSize()); // Adjust based on your logic
    }

    /**
     * Test adding and getting a course in CourseDBManager.
     */
    @Test
    public void testCourseDBManager_AddAndGet() {
        dbManager.add("CS101", 12345, 4, "Room101", "Dr. Smith");
        CourseDBElement element = dbManager.get(12345);
        assertNotNull(element);
        assertEquals("CS101", element.getID());
    }

}

