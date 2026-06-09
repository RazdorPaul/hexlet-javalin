package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.MainPage;

import static io.javalin.rendering.template.TemplateUtil.model;

public class MainController {

    public static void mainPage(Context ctx) {
        var visited = Boolean.valueOf(ctx.cookie("visited"));
        var page = new MainPage(visited);
        ctx.render("index.jte", model("page", page));
        ctx.cookie("visited", String.valueOf(true));
    }

    public static void testPage(Context ctx) {
        var debug = ctx.queryParam("debug");
        ctx.render("test/debug.jte",model("debug", debug));}
}
