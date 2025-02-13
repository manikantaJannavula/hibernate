package entities;

/**
 * Represents an Employee entity with basic details.
 * This class contains attributes for employee ID and name
 * along with their respective getters and setters.
 */
public class Employee {

    private int empId; // Unique identifier for the employee
    private String empName; // Name of the employee

    /**
     * Constructor to initialize an Employee object.
     *
     * @param empId - Employee ID
     * @param empName - Employee Name
     */
    public Employee(int empId, String empName) {
        super(); // Calls the superclass constructor (if applicable)
        this.empId = empId;
        this.empName = empName;
    }

    /**
     * Gets the employee ID.
     *
     * @return empId - The unique ID of the employee.
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * Sets the employee ID.
     *
     * @param empId - The new employee ID to set.
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * Gets the employee name.
     *
     * @return empName - The name of the employee.
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * Sets the employee name.
     *
     * @param empName - The new employee name to set.
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
