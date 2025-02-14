package com.wipro.java.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.java.hibernate.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
