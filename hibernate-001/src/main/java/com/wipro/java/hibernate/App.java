package com.wipro.java.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Load Hibernate configuration
        Configuration config = new Configuration().configure();
        SessionFactory factory = config.buildSessionFactory();

        // Create session
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Create Employee object
        Employee emp = new Employee(101, "Manikanta");

        // Save employee record
        session.save(emp);
        tx.commit();

        // Close session
        session.close();
        factory.close();

        System.out.println("Record saved successfully: " + emp);
    }
}
