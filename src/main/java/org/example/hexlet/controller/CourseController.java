package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.BuildCoursePage;
import org.example.hexlet.dto.CoursePage;
import org.example.hexlet.dto.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repositories.CourseRepository;
import org.example.hexlet.repositories.InMemoryCourseRepository;

import java.util.List;
import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class CourseController {
    private static final CourseRepository coursesRepository = new InMemoryCourseRepository();

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        var header = "Создание нового курса";
        ctx.render("courses/new.jte", model("page", page,
                "header", header,
                "buttonText", "Добавить курс",
                "method", "POST",
                "actionUrl", NamedRoutes.coursesPath()));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class).
                    check(value -> value != null
                            && !value.isBlank()
                            && value.length() > 2, "Название курса не соответствует требованиям!").
                    get();
            var description = ctx.formParamAsClass("description", String.class).
                    check(value -> value != null
                            && !value.isBlank()
                            && value.length() > 10, "Описание курса не соответствует требованиям!").
                    get();
            var course = new Course(name, description);
            coursesRepository.save(course);
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException err) {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            var page = new BuildCoursePage(name, description, err.getErrors());
            ctx.status(422);
            ctx.render("courses/new.jte", model("page", page,
                    "header", "Создание нового курса",
                    "actionUrl", NamedRoutes.coursesPath(),
                    "buttonText", "Добавить курс",
                    "method", "POST"));
        }
    }

    public static void index(Context ctx) {
        var term = ctx.queryParam("term");
        List<Course> courses;
        courses = coursesRepository.findByNameOrDescription(term);
        var header = "List of courses";
        var page = new CoursesPage(courses, header);
        ctx.render("courses/index.jte", model("page", page, "term", term));
    }

    public static void show(Context ctx) {
        var course = coursesRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                .orElseThrow(() -> new NotFoundResponse("Course not found"));
        var header = course.getName();
        var page = new CoursePage(course, header);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = coursesRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Курс не найден"));
        var page = new BuildCoursePage(course.getName(), course.getDescription(), Map.of());
        ctx.render("courses/new.jte", model("page", page,
                "header", "Редактирование курса",
                "method", "POST",
                "actionUrl", NamedRoutes.coursePath(id),
                "buttonText", "Сохранить изменения"));

    }

    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = coursesRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Course not found"));
        try {
            var name = ctx.formParamAsClass("name", String.class).
                    check(value -> value != null
                            && !value.isBlank()
                            && value.length() > 2, "Название курса не соответствует требованиям!").
                    get();
            var description = ctx.formParamAsClass("description", String.class).
                    check(value -> value != null
                            && !value.isBlank()
                            && value.length() > 10, "Описание курса не соответствует требованиям!").
                    get();
            course.setName(name);
            course.setDescription(description);
            coursesRepository.save(course);
            ctx.redirect(NamedRoutes.coursePath(id));
        } catch (ValidationException err) {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            var page = new BuildCoursePage(name, description, err.getErrors());
            ctx.status(422);
            ctx.render("courses/new.jte", model("page", page,
                    "header", "Изменение курса",
                    "actionUrl", NamedRoutes.coursePath(id),
                    "buttonText", "Сохранить изменения",
                    "method", "PUT"));
        }
    }

    public static void destroy(Context ctx) {
        var course = coursesRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                .orElseThrow(() -> new NotFoundResponse("Course not found"));
        coursesRepository.delete(course.getId());
        ctx.redirect(NamedRoutes.coursesPath());
    }
}
