package com.acme.university_api.database.repositories;

import com.acme.university_api.database.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}
