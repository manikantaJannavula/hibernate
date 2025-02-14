package com.wipro.java.hibernate.entity;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageStudent {
	private static SessionFactory factory;

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		} catch (Throwable ex) {
			// TODO: handle exception
			System.err.println("Failed to create sessionFactory object: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
		ManageStudent student = new ManageStudent();
		Integer sID1 = student.addStudent("Mani", "19HU1A04433", "5thClass");
		Integer sID2 = student.addStudent("Ravi", "19HU1A04434", "5thClass");
		Integer sID3 = student.addStudent("Mounica", "19HU1A04435", "5thClass");
		// total Students list
		student.listOfStudents();
		// update particular student data
		student.updateStudent(sID1, "Manikanta");
		// Deleting particular student
		student.deleteStudent(sID3);
		// after deleting printing list of employees
		student.listOfStudents();

	}

	public Integer addStudent(String sName, String sRollNo, String sClass) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = null;
		Integer StdudentID = null;
		try {
			tx = session.beginTransaction();
			Student st = new Student(sName, sRollNo, sClass);
			// Save employee object to database
			StdudentID = (Integer) session.save(st);
			// Commit transaction
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Rollback transaction in case of failure
			e.printStackTrace();
		} finally {
			session.close(); // Close session
		}
		return StdudentID;
	}

	private void listOfStudents() {
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			List students = session.createQuery("FROM Student").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				Student student = (Student) iterator.next();
				System.out.println("Stduent ID:" + student.getsId());
				System.out.println("Student Name:" + student.getsName());
				System.out.println("Student RollNo:" + student.getsRollNo());
				System.out.println("Student Class:" + student.getsClass());

			}
			System.out.println("------------------------");
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Rollback transaction in case of failure
			e.printStackTrace();
		} finally {
			session.close(); // Close session
		}

	}

	private void updateStudent(Integer sID1, String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = session.get(Student.class, sID1);
			student.setsName(name);
			session.save(student);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Rollback transaction in case of failure
			e.printStackTrace();
		} finally {
			session.close(); // Close session
		}

	}

	private void deleteStudent(Integer sID3) {
		// TODO Auto-generated method stub

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, sID3);
			session.delete(student);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	
	}

}
