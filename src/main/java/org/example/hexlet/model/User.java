package org.example.hexlet.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class User {
    private Long id;

    @ToString.Include
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    public User (String name, String surname, String email, Integer age) {
        firstName = name;
        lastName = surname;
        this.email = email;
        this.age = age;
    }
}
