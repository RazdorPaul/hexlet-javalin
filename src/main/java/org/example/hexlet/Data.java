package org.example.hexlet;

import org.example.hexlet.model.Course;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Data {
    private static List<Course> courses = List.of(new Course(1L, "Java",
                                                     "course of programming on java-language"),
                new Course(2L, "C++ developer",
                                   "course of programming on c++ language"),
                new Course(3L, "Python-developer",
                                   "course of programming on python language"));

    public static Optional<Course> getCourse(Long id) {
        return courses.
                stream().
                filter(course -> Objects.equals(course.getId(), id)).
                    findFirst();
    }

    public static List<Course> getCourses() {
        return courses;
    }
}
