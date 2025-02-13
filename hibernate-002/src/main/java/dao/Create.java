package dao;

import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utilities.SessionFactoryProvider;

/**
 * This class is responsible for creating and saving an Employee entity
 * into the database using Hibernate.
 */
public class Create {
    public static void main(String[] args) {
        try {
            // Obtaining a SessionFactory instance from the utility class
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();

            // Opening a new session to interact with the database
            Session session = sessionFactory.openSession();

            // Beginning a transaction to ensure data consistency
            Transaction t = session.beginTransaction();

            // Creating an Employee object with sample data
            Employee emp = new Employee(101, "John");

            // Saving the Employee object into the database
            session.save(emp);

            // Committing the transaction to persist the changes
            t.commit();

            // Closing the session factory to release resources
            sessionFactory.close();
        } catch (Exception e) {
            // Handling any exceptions and printing the error message
            System.out.println("Error occurred: " + e);
        }
    }
}
