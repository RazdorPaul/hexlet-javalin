package org.example.hexlet;

import io.javalin.Javalin;

import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.CoursePage;
import org.example.hexlet.dto.CoursesPage;
import org.example.hexlet.dto.UserPage;
import org.example.hexlet.dto.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repositories.InMemoryCourseRepository;
import org.example.hexlet.repositories.InMemoryUserRepository;

import java.util.List;

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
    });
        app.get("/courses/test", ctx -> {
            var debug = ctx.queryParam("debug");
            ctx.render("test/debug.jte",model("debug", debug));
        });
        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            courses = courseRepository.findByNameOrDescription(term);
            var header = "List of courses";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page, "term", term));
        });
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            List<User> users;
            users = usersRepository.findByFirstNameStartingWith(term);
            var header = "List of users";
            var page = new UsersPage(users, header);
            ctx.render("users/index.jte", model("page", page, "term", term));
        });
        app.get("/courses/new", ctx -> {
            ctx.render("courses/new.jte");
        });
        app.post("/courses", ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            if (name == null || name.isBlank()) {
                ctx.status(422).result("Поле обязательно для заполнения!");
                return;
            }
            if (description == null || description.isBlank()) {
                ctx.status(422).result("Поле обязательно для заполнения!");
                return;
            }
            var course = new Course(name, description);
            courseRepository.save(course);
            ctx.redirect("/courses");
        });
        app.get("/courses/{id}", ctx -> {
            var course = courseRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var header = course.getName();
            var page = new CoursePage(course, header);
            ctx.render("courses/show.jte", model("page", page));
        });
        app.get("/users/new", ctx -> {
            ctx.render("users/new.jte");
        });
        app.post("/users", ctx -> {
            var firstName = ctx.formParam("firstName");
            if (firstName == null || firstName.isBlank()) {
                ctx.status(422).result("Поле обязательно для заполнения!");
                return;
            }
            var lastName = ctx.formParam("lastName");
            if (lastName == null || lastName.isBlank()) {
                ctx.status(422).result("Поле обязательно для заполнения!");
                return;
            }
            var email = ctx.formParam("email");
            if (email == null || email.isBlank()) {
                ctx.status(422).result("Поле обязательно для заполнения!");
                return;
            } else {
                email = email.toLowerCase().strip();
            }
            Integer age;
            var  rawAge = ctx.formParam("age");
            try {
                age = Integer.parseInt(rawAge);
            } catch (NumberFormatException e) {
                ctx.status(422).result("Возраст должен быть числом");
                return;
            }
            var user = new User(firstName, lastName, email, age);
            usersRepository.save(user);
            ctx.redirect("/users");
        });
        app.get("/users/{id}", ctx -> {
            var user = usersRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var header = user.getFirstName() + " " + user.getLastName();
            var page = new UserPage(user, header);
            ctx.render("users/show.jte", model("page", page));
        });
        return app;
    }
}