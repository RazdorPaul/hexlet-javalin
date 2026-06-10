package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.example.hexlet.model.Course;

import java.util.List;

@AllArgsConstructor
@Getter
public class CoursesPage extends Page{
    private List<Course> courses;
    private String header;
}