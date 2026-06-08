package org.example.hexlet.controller;

import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class MainController {

    public static void mainPage(Context ctx) {
        ctx.render("index.jte");
    }

    public static void testPage(Context ctx) {
        var debug = ctx.queryParam("debug");
        ctx.render("test/debug.jte",model("debug", debug));}
}
