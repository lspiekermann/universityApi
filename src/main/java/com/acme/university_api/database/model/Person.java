package com.acme.university_api.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Person {

    @Column(nullable = false)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9äöüÄÖÜß]+$", message = "Field must be alphanumeric")
    private String name;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9äöüÄÖÜß]+$", message = "Field must be alphanumeric")
    private String surname;

}
