package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.NamedRoutes;

public class SessionController {
    public static void build(Context ctx) {
        ctx.render("sessions/session.jte");
    }

    public static void create(Context ctx) {
        var email = ctx.formParam("email");
        ctx.sessionAttribute("email", email);
        ctx.redirect(NamedRoutes.mainPath());
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("email", null);
        ctx.redirect(NamedRoutes.mainPath());
    }
}
