package com.acme.university_api.database.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "studentId"
)
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment the id
    private Long studentId;

    @Valid
    @ManyToMany
    @JoinTable(
        name = "student_lecturers",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "lecturer_id")
    )
    private Set<Lecturer> lecturers;

    public Student() {

    }

    public Student(Person person) {

        this.setName(person.getName());
        this.setSurname(person.getSurname());
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student that = (Student) o;
        return Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId);
    }

}
