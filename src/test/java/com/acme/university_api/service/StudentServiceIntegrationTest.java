package com.acme.university_api.service;

import com.acme.university_api.database.model.Lecturer;
import com.acme.university_api.database.model.Student;
import com.acme.university_api.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional // Ensures the database is rolled back after each test
class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Test
    void testCreateStudent() {

        Student testStudent = new Student();
        testStudent.setName("Hans");
        testStudent.setSurname("Zimmer");
        Student studentWithId = this.studentService.createStudent(testStudent);
        assertNotNull(studentWithId.getStudentId());
    }

    @ParameterizedTest
    @CsvSource({
            ", test",
            "test, ",
            ",",
    })
    void testCreateStudentValidation(String name, String surname) {

        Student testStudent = new Student();
        testStudent.setName(name);
        testStudent.setSurname(surname);
        assertThrows(ConstraintViolationException.class, () -> this.studentService.createStudent(testStudent));
    }

    @Test
    void testGetStudentById() {

        Student student = this.studentService.getStudentById(1L);
        assertEquals("Thomas", student.getName());
        assertEquals("Huxley", student.getSurname());
        assertEquals(1, student.getLecturers().size());
    }

    @Test
    void testAddExistingLecturerToExistingStudent() {
        // generated ids start at 1
        long testStudentId = 1L;
        long testLecturerId = 1L;
        this.studentService.addStudentLecturer(testStudentId, testLecturerId);

        // check student
        Student student = studentService.getStudentById(testStudentId);
        Lecturer testLecturer = new Lecturer();
        testLecturer.setLecturerId(testLecturerId);
        assertEquals(1, student.getLecturers().size());
        assertTrue(student.getLecturers().contains(testLecturer));
        // check lecturer
        Lecturer lecturer = this.lecturerService.getLecturerById(testLecturerId);
        assertEquals(1, lecturer.getStudents().size());
        assertTrue(lecturer.getStudents().contains(student));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2",
            "2, 1",
            "2, 2",
    })
    void testNonExistingStudentLecturerAdd(String studentIdAsString, String lecturerIdAsString) {

        Long studentId = Long.parseLong(studentIdAsString);
        Long lecturerId = Long.parseLong(lecturerIdAsString);
        assertThrows(ResourceNotFoundException.class, () -> this.studentService.addStudentLecturer(studentId, lecturerId));
    }

}
