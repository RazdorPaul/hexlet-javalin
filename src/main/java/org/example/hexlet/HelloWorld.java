package org.example.hexlet;

import io.javalin.Javalin;

import io.javalin.http.NotFoundResponse;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.dto.BuildCoursePage;
import org.example.hexlet.dto.BuildUserPage;
import org.example.hexlet.dto.CoursePage;
import org.example.hexlet.dto.CoursesPage;
import org.example.hexlet.dto.UserPage;
import org.example.hexlet.dto.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repositories.InMemoryCourseRepository;
import org.example.hexlet.repositories.InMemoryUserRepository;

import java.util.List;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }

    private static Javalin getApp() {
        var usersRepository = new InMemoryUserRepository();
        var courseRepository = new InMemoryCourseRepository();
        var app = Javalin.create(config -> {
        config.bundledPlugins.enableDevLogging();
        config.fileRenderer(new JavalinJte());
        config.staticFiles.add(staticFiles -> {
            staticFiles.hostedPath = "/";
            staticFiles.directory = "/public";
            staticFiles.location = Location.CLASSPATH;
            });
    });
        app.get(NamedRoutes.mainPath(), ctx ->{
            ctx.render("index.jte");
        });
        app.get(NamedRoutes.coursesTestPath(), ctx -> {
            var debug = ctx.queryParam("debug");
            ctx.render("test/debug.jte",model("debug", debug));
        });
        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            courses = courseRepository.findByNameOrDescription(term);
            var header = "List of courses";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page, "term", term));
        });
        app.get(NamedRoutes.usersPath(), ctx -> {
            var term = ctx.queryParam("term");
            List<User> users;
            users = usersRepository.findByFirstNameStartingWith(term);
            var header = "List of users";
            var page = new UsersPage(users, header);
            ctx.render("users/index.jte", model("page", page, "term", term));
        });
        app.get(NamedRoutes.coursesNewPath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/new.jte", model("page", page));
        });
        app.post(NamedRoutes.coursesPath(), ctx -> {
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
                courseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException err) {
                var name = ctx.formParam("name");
                var description = ctx.formParam("description");
                var page = new BuildCoursePage(name, description, err.getErrors());
                ctx.render("courses/new.jte", model("page", page));
            }
        });
        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var course = courseRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var header = course.getName();
            var page = new CoursePage(course, header);
            ctx.render("courses/show.jte", model("page", page));
        });
        app.get(NamedRoutes.usersNewPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/new.jte", model("page", page));
        });
        app.post(NamedRoutes.usersPath(), ctx -> {
            try {
                var firstName = ctx.formParamAsClass("firstName", String.class).
                        check(value -> value != null && !value.isBlank(), "Вы не указали имя!").
                        get();
                var lastName = ctx.formParamAsClass("lastName", String.class).
                        check(value -> value != null && !value.isBlank(), "Вы не указали фамилию!").
                        get();
                var email = ctx.formParamAsClass("email", String.class).
                        check(value -> value != null && !value.isBlank(), "Вы не указали электронную почту!").
                        get();
                var phone = ctx.formParamAsClass("phone", String.class).
                        check(value -> value != null && !value.isBlank(), "Вы не указали номер телефона!").
                        get();
                var age = ctx.formParamAsClass("age", Integer.class).
                        check(Objects::nonNull, "Вы не указали возраст!").
                        get();
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class).
                        check(pass -> Objects.equals(pass, passwordConfirmation) && !pass.isBlank(), "Пароли не совпадают").get();
                email = email.toLowerCase().strip();
                lastName = StringUtils.capitalize(lastName.toLowerCase()).strip();
                firstName = StringUtils.capitalize(firstName.toLowerCase()).strip();
                var user = new User(firstName, lastName, email, phone, age, password);
                usersRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var firstName = ctx.formParam("firstName");
                var lastName = ctx.formParam("lastName");
                var email = ctx.formParam("email");
                var phone = ctx.formParam("phone");
                Integer age = null;
                try {
                    age = Integer.parseInt(ctx.formParam("age"));
                } catch (NumberFormatException ex) {}
                var page = new BuildUserPage(firstName, lastName, email, phone, age, e.getErrors());
                ctx.render("users/new.jte", model("page", page));
            }
        });
        app.get(NamedRoutes.userPath("{id}"), ctx -> {
            var user = usersRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var header = user.getFirstName() + " " + user.getLastName();
            var page = new UserPage(user, header);
            ctx.render("users/show.jte", model("page", page));
        });
        return app;
    }
}