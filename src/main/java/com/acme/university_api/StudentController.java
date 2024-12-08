package com.acme.university_api;

import com.acme.university_api.database.model.Person;
import com.acme.university_api.database.model.Student;
import com.acme.university_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {

        return studentService.getStudentById(studentId);
    }

    @PostMapping("/create")
    public Student createStudent(@Valid @RequestBody Person student) {

        return studentService.createStudent(student);
    }

    @PatchMapping("{studentId}/lecturers/add/{lecturerId}")
    public void addStudentToLecturer(@PathVariable Long studentId, @PathVariable Long lecturerId) {

        studentService.addStudentLecturer(studentId, lecturerId);
    }
}
