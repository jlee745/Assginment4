import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CourseDBManager is responsible for handling a collection of courses.
 * It interacts with the CourseDBStructure to add, retrieve, and list courses,
 * as well as to read course data from a file.
 * 
 * Author: JLi
 */
public class CourseDBManager implements CourseDBManagerInterface {
    private CourseDBStructure courseDatabase;

    /**
     * Constructor that initializes the CourseDBStructure with a given size.
     */
    public CourseDBManager() {
        courseDatabase = new CourseDBStructure(20);
    }

    /**
     * Adds a new course to the course database.
     * 
     * @param id         the course ID
     * @param crn        the course registration number
     * @param credits    the number of credits for the course
     * @param roomNum    the room number where the course takes place
     * @param instructor the name of the instructor
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement courseElement = new CourseDBElement(id, crn, credits, roomNum, instructor);
        courseDatabase.add(courseElement);
    }

    /**
     * Retrieves a course from the database using the CRN.
     * 
     * @param crn the course registration number
     * @return the CourseDBElement corresponding to the CRN, or null if not found
     */
    @Override
    public CourseDBElement get(int crn) {
        try {
            return courseDatabase.get(crn);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads course data from a specified file and adds the courses to the database.
     * The file should contain lines with course details in the following order:
     * ID CRN CREDITS ROOM INSTRUCTOR
     * 
     * @param inputFile the file containing course data
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    public void readFile(File inputFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String courseLine = scanner.nextLine();
                String[] courseDetails = courseLine.split(" ", 5);

                if (courseDetails.length == 5) {
                    String id = courseDetails[0];
                    int crn = Integer.parseInt(courseDetails[1]);
                    int credits = Integer.parseInt(courseDetails[2]);
                    String roomNum = courseDetails[3];
                    String instructor = courseDetails[4];

                    add(id, crn, credits, roomNum, instructor);
                }
            }
        }
    }

    /**
     * Lists all the courses present in the course database.
     * 
     * @return a list of strings, each representing a course in the format:
     *         "Course: ID CRN: CRN Credits: CREDITS Instructor: INSTRUCTOR Room: ROOM"
     */
    @Override
    public ArrayList<String> showAll() {
        return courseDatabase.showAll();
    }
}
