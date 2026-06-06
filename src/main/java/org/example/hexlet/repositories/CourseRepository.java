package org.example.hexlet.repositories;

import org.example.hexlet.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    List<Course> findByNameOrDescription(String term);
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    boolean delete(Long id);
}
