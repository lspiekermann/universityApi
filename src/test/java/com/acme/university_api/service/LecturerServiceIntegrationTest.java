package com.acme.university_api.service;

import com.acme.university_api.database.model.Lecturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional // Ensures the database is rolled back after each test
class LecturerServiceIntegrationTest {

    @Autowired
    private LecturerService lecturerService;

    @Test
    void testCreateLecturer() {

        Lecturer testLecturer = new Lecturer();
        testLecturer.setName("Lurking");
        testLecturer.setSurname("Louis");
        Lecturer lecturerWithId = this.lecturerService.createLecturer(testLecturer);
        assertNotNull(lecturerWithId.getLecturerId());
    }

    @Test
    void testGetLecturerById() {

        Lecturer lecturer = this.lecturerService.getLecturerById(1L);
        assertEquals("James", lecturer.getName());
        assertEquals("Ross", lecturer.getSurname());
        assertEquals(1, lecturer.getStudents().size());
    }

}
