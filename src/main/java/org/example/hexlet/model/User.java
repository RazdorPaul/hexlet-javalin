package org.example.hexlet.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class User {
    @ToString.Exclude
    private Long id;
    @ToString.Exclude
    private String password;
    @ToString.Exclude
    private String phone;

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    public User (String firstName,
                 String lastName,
                 String email,
                 String phone,
                 Integer age,
                 String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.password = password;
    }
}
