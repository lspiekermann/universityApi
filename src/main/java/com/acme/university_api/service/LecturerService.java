package com.acme.university_api.service;

import com.acme.university_api.database.model.Lecturer;
import com.acme.university_api.database.model.Person;
import com.acme.university_api.database.repositories.LecturerRepository;
import com.acme.university_api.exception.ResourceNotFoundException;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {

        this.lecturerRepository = lecturerRepository;
    }

    @RateLimiter(name = "default")
    public Lecturer getLecturerById(Long lecturerId) {

        return lecturerRepository.findById(lecturerId).orElseThrow(() -> new ResourceNotFoundException("Lecturer not found"));
    }

    @RateLimiter(name = "default")
    public Lecturer createLecturer(Person person) {

        Lecturer lecturer = new Lecturer(person);
        return lecturerRepository.save(lecturer);
    }

}
