package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.BuildUserPage;
import org.example.hexlet.dto.UserPage;
import org.example.hexlet.dto.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repositories.InMemoryUserRepository;
import org.example.hexlet.repositories.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UserController {
    private static final UserRepository usersRepository = new InMemoryUserRepository();

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        var header = "Добавление нового пользователя";
        ctx.render("users/new.jte", model("page", page,
                                                        "header", header,
                                                        "buttonText", "Добавить пользователя",
                                                        "method", "POST",
                                                        "actionUrl", NamedRoutes.usersPath()));
    }

    public static void create(Context ctx) {
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
            ctx.status(422);
            var header = "Добавление нового пользователя";
            ctx.render("users/new.jte", model("page", page,
                                                            "header", header,
                                                            "buttonText", "Добавить пользователя",
                                                            "method", "POST",
                                                            "actionUrl", NamedRoutes.usersPath()));
        }
    }

    public static void index(Context ctx) {
        var term = ctx.queryParam("term");
        List<User> users;
        users = usersRepository.findByFirstNameStartingWith(term);
        var header = "List of users";
        var page = new UsersPage(users, header);
        ctx.render("users/index.jte", model("page", page, "term", term));
    }

    public static void show(Context ctx) {
        var user = usersRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                .orElseThrow(() -> new NotFoundResponse("User not found"));
        var header = user.getFirstName() + " " + user.getLastName();
        var page = new UserPage(user, header);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Пользователь не найден"));
        var page = new BuildUserPage(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAge(),
                Map.of()
        );
        var header = "Редактирование пользователя";
        ctx.render("users/new.jte", model("page", page,
                                                             "header", header,
                                                             "actionUrl", NamedRoutes.userPath(id),
                                                             "buttonText", "Сохранить изменения",
                                                             "method", "PUT"));

    }

    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));
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
            var password = ctx.formParam("password");
            if (password != null && !password.isBlank()) {
                var passwordConfirmation = ctx.formParamAsClass("passwordConfirmation", String.class).
                        check(value -> Objects.equals(password, value), "Пароли не совпадают!").
                        get();
                user.setPassword(password);
            }
            email = email.toLowerCase().strip();
            lastName = StringUtils.capitalize(lastName.toLowerCase()).strip();
            firstName = StringUtils.capitalize(firstName.toLowerCase()).strip();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAge(age);
            usersRepository.save(user);
            ctx.redirect(NamedRoutes.userPath(id));
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
            ctx.status(422);
            var header = "Обновление данных пользователя";
            ctx.render("users/new.jte", model("page", page,
                    "header", header,
                    "buttonText", "Сохранить изменения",
                    "method", "PUT",
                    "actionUrl", NamedRoutes.userPath(id)));
        }
    }

    public static void destroy(Context ctx) {
        var user = usersRepository.findById(ctx.pathParamAsClass("id", Long.class).get())
                .orElseThrow(() -> new NotFoundResponse("User not found"));
        usersRepository.delete(user.getId());
        ctx.redirect(NamedRoutes.usersPath());
    }
}
