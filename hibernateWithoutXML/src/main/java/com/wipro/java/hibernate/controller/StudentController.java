package com.wipro.java.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.java.hibernate.entity.Student;
import com.wipro.java.hibernate.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create Students (POST)
    @PostMapping
    public List<Student> createStudents(@RequestBody List<Student> students) {
        return studentRepository.saveAll(students);
    }

    // Get All Students (GET)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student by ID (GET)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Get Student by Roll Number (GET)
    @GetMapping("/roll/{rollNo}")
    public Student getStudentByRollNo(@PathVariable("rollNo") int rollNo) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getRollNo() == rollNo)
                .findFirst()
                .orElse(null);
    }

    // Update Student (PUT)
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setRollNo(studentDetails.getRollNo());
            student.setName(studentDetails.getName());
            student.setStudentClass(studentDetails.getStudentClass());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    // Delete Student (DELETE)
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted successfully!";
        }
        return "Student not found with ID: " + id;
    }
}
