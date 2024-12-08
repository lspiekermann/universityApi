package com.acme.university_api;

import com.acme.university_api.database.model.Lecturer;
import com.acme.university_api.database.model.Person;
import com.acme.university_api.service.LecturerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {

        this.lecturerService = lecturerService;
    }

    @GetMapping("/{lecturerId}")
    public Lecturer getLecturer(@PathVariable Long lecturerId) {

        return lecturerService.getLecturerById(lecturerId);
    }

    @PostMapping("/create")
    public Lecturer createLecturer(@Valid @RequestBody Person lecturer) {

        return lecturerService.createLecturer(lecturer);
    }
}
