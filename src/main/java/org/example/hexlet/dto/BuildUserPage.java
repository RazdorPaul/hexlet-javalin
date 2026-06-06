package org.example.hexlet.dto;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuildUserPage {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer age;
    private Map<String, List<ValidationError<Object>>> errors;
}
