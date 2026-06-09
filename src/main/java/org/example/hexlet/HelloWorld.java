package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.controller.CourseController;
import org.example.hexlet.controller.MainController;
import org.example.hexlet.controller.SessionController;
import org.example.hexlet.controller.UserController;

import java.time.LocalDate;
import java.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }

    private static Javalin getApp() {
        var app = Javalin.create(config -> {
        config.bundledPlugins.enableDevLogging();
        config.fileRenderer(new JavalinJte());
        config.staticFiles.add(staticFiles -> {
            staticFiles.hostedPath = "/";
            staticFiles.directory = "/public";
            staticFiles.location = Location.CLASSPATH;
            });
    });
        app.before(ctx -> {
            System.out.println("Дата и время запроса " + ctx.method() + " " +LocalDate.now() + " " + LocalTime.now());
        });
        app.get(NamedRoutes.mainPath(), MainController::mainPage);
        app.get(NamedRoutes.coursesTestPath(), MainController::testPage);
        app.get(NamedRoutes.coursesPath(), CourseController::index);
        app.get(NamedRoutes.usersPath(), UserController::index);
        app.get(NamedRoutes.coursesNewPath(), CourseController::build);
        app.post(NamedRoutes.coursesPath(), CourseController::create);
        app.get(NamedRoutes.coursePath("{id}"), CourseController::show);
        app.get(NamedRoutes.editCoursePath("{id}"), CourseController::edit);
        app.post(NamedRoutes.coursePath("{id}"), CourseController::update);
        app.delete(NamedRoutes.coursePath("{id}"), CourseController::destroy);
        app.get(NamedRoutes.usersNewPath(), UserController::build);
        app.post(NamedRoutes.usersPath(), UserController::create);
        app.get(NamedRoutes.userPath("{id}"), UserController::show);
        app.get(NamedRoutes.editUserPath("{id}"), UserController::edit);
        app.post(NamedRoutes.userPath("{id}"), UserController::update);
        app.delete(NamedRoutes.userPath("{id}"), UserController::destroy);
        app.get(NamedRoutes.buildSessionPath(), SessionController::build);
        app.post(NamedRoutes.sessionsPath(), SessionController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionController::destroy);
        return app;
    }
}