package org.example.hexlet.repositories;

import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InMemoryCourseRepository implements CourseRepository{
    private ArrayList<Course> courses = new ArrayList<>();
    private Long nextId = 1L;

    public InMemoryCourseRepository() {
        initCourses();
    }

    private void initCourses() {
        save(new Course("Java", "course of programming on java-language"));
        save(new Course("C++ developer", "course of programming on c++ language"));
        save(new Course("Python-developer", "course of programming on python language"));
    }

    @Override
    public List<Course> findByNameOrDescription(String term) {
        if (term != null && !term.isBlank()) {
            var substr = term.strip().toLowerCase();
            return courses.stream().filter(course -> {
                var checkName = course.getName() !=null && course.getName().
                        toLowerCase().
                        strip().
                        contains(substr);
                var checkDescription = course.getDescription() != null && course.
                        getDescription().
                        toLowerCase().
                        strip().
                        contains(substr);
                return checkName || checkDescription;
            }).toList();
        } else {
            return courses;
        }
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courses.
                stream().
                filter(course -> Objects.equals(course.getId(), id)).
                findFirst();
    }

    @Override
    public Course save(Course course) {
        course.setId(nextId++);
        courses.add(course);
        return course;
    }

    @Override
    public boolean delete(Long id) {
        return courses.removeIf(course -> Objects.equals(course.getId(), id));
    }
}
