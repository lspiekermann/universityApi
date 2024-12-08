package com.acme.university_api.database.repositories;

import com.acme.university_api.database.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
