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
    property = "lecturerId"
)
public class Lecturer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment the id
    private Long lecturerId;

    @Valid
    @ManyToMany(mappedBy = "lecturers")
    private Set<Student> students;

    public Lecturer() {

    }

    public Lecturer(Person person) {

        this.setName(person.getName());
        this.setSurname(person.getSurname());
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lecturer that = (Lecturer) o;
        return Objects.equals(lecturerId, that.lecturerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lecturerId);
    }
}
