package org.example.hexlet;

import io.javalin.Javalin;

import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }

    private static Javalin getApp() {
        var app = Javalin.create(config -> {
        config.bundledPlugins.enableDevLogging();
        config.fileRenderer(new JavalinJte());
    });
        app.get("/courses/test", ctx -> {
            var debug = ctx.queryParam("debug");
            ctx.render("test/debug.jte",model("debug", debug));
        });
        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            if (term != null && !term.isBlank()) {
                var query = term.toLowerCase().strip();
                courses = Data.getCourses().stream().filter(course -> {
                    var checkName = course.getName() !=null && course.getName().
                            toLowerCase().
                            strip().
                            contains(query);
                    var checkDescription = course.getDescription() != null && course.
                            getDescription().
                            toLowerCase().
                            strip().
                            contains(query);
                    return checkName || checkDescription;
                }).toList();
            } else {
                courses = Data.getCourses();
            }
            var header = "List of courses";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page, "term", term));
        });
        app.get("/courses/{id}", ctx -> {
            var course = Data.getCourse(ctx.pathParamAsClass("id", Long.class).get())
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var header = course.getName();
            var page = new CoursePage(course, header);
            ctx.render("courses/show.jte", model("page", page));
        });
        return app;
    }
}