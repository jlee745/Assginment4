/**
 * CourseDBElement implements Comparable, and consists of five attributes: the Course ID (a String),
 * the CRN (an int), the number of credits (an int), the room number (a String), and the instructor
 * name (a String). Normally the CourseDBElement will be an object consisting of these five
 * attributes, and is referred to as a CDE.
 * @author Jaegyoon Lee
 */
public class CourseDBElement implements Comparable {
	private String courseID;
	private int CRN;
	private int Credits;
	private String roomNumber;
	private String instructorName;
	
	/**
     * Creates a new CourseDBElement with the specified details.
     * 
     * @param courseID the identifier for the course
     * @param CRN the unique course registration number
     * @param numOfCredits the number of credits for the course
     * @param roomNumber the room where the course is conducted
     * @param instructorName the name of the instructor for the course
     */
	public CourseDBElement(String courseID, int CRN, int Credits, String roomNumber,
							String instructorName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.Credits = Credits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}
	
	/**
     * Default constructor for CourseDBElement by Initializing fields with default values.
     */
	public CourseDBElement() {
		 this.courseID = "";
		    this.CRN = 0;
		    this.Credits = 0;
		    this.roomNumber = "";
		    this.instructorName = "";
    }
	
	/**
     * Retrieves the course ID.
     * 
     * @return the string value of ID of the course
     */
	public String getID() {
	    return courseID;
	}

	/**
     * Retrieves the course registration number (CRN).
     * 
     * @return the integer value of  CRN
     */
	public int getCRN() {
	    return CRN;
	}
	
	/**
     * Sets the course registration number (CRN).
     * 
     * @param CRN the new CRN value
     */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}

	/**
     * Retrieves the number of credits for the course.
     * 
     * @return integer of the number of credits
     */
	public int getCredits() {
	    return Credits;
	}

	/**
     * Retrieves the room number where the course is held.
     * 
     * @return String value of the room number
     */
	public String getRoomNum() {
	    return roomNumber;
	}

	/**
     * Retrieves the name of the course instructor.
     * 
     * @return the instructor's name
     */
	public String getInstructorName() {
	    return instructorName;
	}

	/**
     * Compares this course with another based on the instructor's name.
     * 
     * @param other the course to compare with
     * @Return a negative integer, zero, or a positive integer if the name of this course's instructor is less than, equal to, or greater than the name of the specified course's instructor.
     */
	public int compareTo(CourseDBElement o) {
	    return this.instructorName.compareTo(o.instructorName);
	}

	/**
     * Provides a string representation of the course.
     * 
     * @return a string describing the course's details
     */
    @Override
    public String toString() {
        return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + Credits + " Instructor:" + instructorName + " Room:" + roomNumber;
    }
    
    /**
     * Computes the hash code for this course based on its CRN.
     * This ensures that the hash code is consistent with the CRN, allowing for efficient storage and retrieval in a hash table.
     * 
     * @return the hash code for the course
     */
    @Override
    public int hashCode() {
        return Integer.toString(CRN).hashCode();
    }

}