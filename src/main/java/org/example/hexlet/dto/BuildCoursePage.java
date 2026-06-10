package org.example.hexlet.dto;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildCoursePage extends Page{
    private String name;
    private String description;
    private Map<String, List<ValidationError<Object>>> errors;
}
