package dao;

import java.util.List;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Employee;

/**
 * The ManageEmployee class handles CRUD operations for Employee objects using Hibernate.
 */
public class ManageEmployee {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            // Configure and build the Hibernate SessionFactory using hibernate.cfg.xml
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageEmployee ME = new ManageEmployee();

        // Adding a few employee records in the database
        Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        Integer empID3 = ME.addEmployee("John", "Paul", 10000);

        // Listing all employees
        ME.listEmployees();

        // Updating an employee's salary
        ME.updateEmployee(empID1, 5000);

        // Deleting an employee record
        ME.deleteEmployee(empID2);

        // Listing employees after deletion
        ME.listEmployees();
    }

    /**
     * Method to CREATE an employee record in the database.
     * @param fname Employee's first name.
     * @param lname Employee's last name.
     * @param salary Employee's salary.
     * @return The generated employee ID.
     */
    public Integer addEmployee(String fname, String lname, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            // Begin transaction
            tx = session.beginTransaction();
            
            // Create new Employee object
            Employee employee = new Employee(fname, lname, salary);
            
            // Save employee object to database
            employeeID = (Integer) session.save(employee);
            
            // Commit transaction
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();  // Rollback transaction in case of failure
            e.printStackTrace();
        } finally {
            session.close();  // Close session
        }
        return employeeID;
    }

    /**
     * Method to READ all employees from the database.
     */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            // Begin transaction
            tx = session.beginTransaction();
            
            // Retrieve all Employee records from the database
            List employees = session.createQuery("FROM Employee").list();
            
            // Iterate through the list and print employee details
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }
            
            // Commit transaction
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();  // Rollback transaction if an error occurs
            e.printStackTrace();
        } finally {
            session.close();  // Close session
        }
    }

    /**
     * Method to UPDATE an employee's salary in the database.
     * @param EmployeeID The ID of the employee to update.
     * @param salary The new salary to be set.
     */
    public void updateEmployee(Integer EmployeeID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            // Begin transaction
            tx = session.beginTransaction();
            
            // Retrieve employee object by ID
            Employee employee = (Employee) session.get(Employee.class, EmployeeID);
            
            // Update employee salary
            employee.setSalary(salary);
            session.update(employee);
            
            // Commit transaction
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();  // Rollback transaction in case of failure
            e.printStackTrace();
        } finally {
            session.close();  // Close session
        }
    }

    /**
     * Method to DELETE an employee record from the database.
     * @param EmployeeID The ID of the employee to delete.
     */
    public void deleteEmployee(Integer EmployeeID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            // Begin transaction
            tx = session.beginTransaction();
            
            // Retrieve employee object by ID
            Employee employee = (Employee) session.get(Employee.class, EmployeeID);
            
            // Delete employee record
            session.delete(employee);
            
            // Commit transaction
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();  // Rollback transaction in case of failure
            e.printStackTrace();
        } finally {
            session.close();  // Close session
        }
    }
}
