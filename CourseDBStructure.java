import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A hash table-based data structure designed to store and manage course information.
 * Supports operations for adding, retrieving, and listing courses. Courses are stored
 * in buckets within the hash table, using CRN as the key.
 * 
 * Author: Jaegyoon Lee
 */
public class CourseDBStructure implements CourseDBStructureInterface {
    private LinkedList<CourseDBElement>[] hashTable;
    private int tableSize;

    /**
     * Initializes a CourseDBStructure with a capacity determined by the given number of elements.
     * The table size is adjusted to a prime number to optimize hash distribution.
     * 
     * @param n the initial capacity estimate
     */
    public CourseDBStructure(int n) {
        double loadFactor = 1.5;
        tableSize = (int) (n / loadFactor);
        while (!isPrime(tableSize) || (tableSize - 3) % 4 != 0) {
            tableSize++;
        }
        hashTable = new LinkedList[tableSize];
    }

    /**
     * Initializes a CourseDBStructure with a specific size, mainly used for testing purposes.
     * 
     * @param testing a string flag for testing mode (not used in the logic)
     * @param size the size of the hash table
     */
    public CourseDBStructure(String testing, int size) {
        tableSize = size;
        hashTable = new LinkedList[tableSize];
    }

    /**
     * Adds a course to the hash table. If a course with the same CRN already exists,
     * it replaces the existing course.
     * 
     * @param element the CourseDBElement to add
     */
    @Override
    public void add(CourseDBElement element) {
        int index = computeHashCode(element.getCRN());

        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }

        hashTable[index].removeIf(e -> e.getCRN() == element.getCRN());
        hashTable[index].add(element);
    }

    /**
     * Retrieves a course by its CRN.
     * 
     * @param crn the CRN of the desired course
     * @return the CourseDBElement corresponding to the given CRN
     * @throws IOException if no course with the given CRN is found
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int index = computeHashCode(crn);
        if (hashTable[index] != null) {
            for (CourseDBElement element : hashTable[index]) {
                if (element.getCRN() == crn) {
                    return element;
                }
            }
        }
        throw new IOException("Course with CRN " + crn + " not found.");
    }

    /**
     * Lists all courses stored in the hash table, sorted by the instructor's name.
     * 
     * @return a list of course details as strings
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<CourseDBElement> elements = new ArrayList<>();
        for (LinkedList<CourseDBElement> bucket : hashTable) {
            if (bucket != null) {
                elements.addAll(bucket);
            }
        }
        elements.sort(null);
        ArrayList<String> courseList = new ArrayList<>();
        for (CourseDBElement element : elements) {
            courseList.add(element.toString());
        }
        return courseList;
    }

    /**
     * Returns the size of the hash table.
     * 
     * @return the size of the hash table
     */
    @Override
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Checks if a given number is prime.
     * 
     * @param number the number to check
     * @return true if the number is prime, false otherwise
     */
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the hash code for a given CRN, adjusted to fit within the hash table's size.
     * 
     * @param crn the CRN to hash
     * @return the hash code for the CRN
     */
    private int computeHashCode(int crn) {
        int hashCode = Integer.toString(crn).hashCode();
        return Math.abs(hashCode % tableSize);
    }
}
