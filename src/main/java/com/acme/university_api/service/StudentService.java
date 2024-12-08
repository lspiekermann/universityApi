package com.acme.university_api.service;

import com.acme.university_api.database.model.Lecturer;
import com.acme.university_api.database.model.Person;
import com.acme.university_api.database.model.Student;
import com.acme.university_api.database.repositories.StudentRepository;
import com.acme.university_api.exception.ResourceNotFoundException;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final LecturerService lecturerService;

    public StudentService(StudentRepository studentRepository, LecturerService lecturerService) {

        this.studentRepository = studentRepository;
        this.lecturerService = lecturerService;
    }

    @RateLimiter(name = "default")
    public Student getStudentById(Long studentId) {

        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @RateLimiter(name = "default")
    public Student createStudent(Person person) {

        Student student = new Student(person);
        return studentRepository.save(student);
    }

    @Transactional
    public void addStudentLecturer(Long studentId, Long lecturerId) {

        Student student = this.getStudentById(studentId);
        Lecturer lecturer = this.lecturerService.getLecturerById(lecturerId);
        student.getLecturers().add(lecturer);
        lecturer.getStudents().add(student);
    }

}
