package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Map;

public class HelloWorld {
    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }

    private static Javalin getApp() {
        var app = Javalin.create(config -> config.bundledPlugins.enableDevLogging());
        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name");
            if (name != null) {
                ctx.result("Hello, " + name + "!\n");
            } else {
                ctx.result("Hello, World!\n");
            }
        });
        app.get("/dynamic/{id}",
                ctx -> ctx.result("you are on page /dynamic/"
                        + ctx.pathParamAsClass("id", Integer.class).get()));
        app.get("/dynamics/{first}/pages/{second}", ctx -> ctx.result(getPath(ctx)));
        return app;
    }

    private static Map<String, Object> getHeaders(Context ctx) {
        return Map.of(
                "Method:", ctx.method(),
                "URI: ", ctx.url(),
                "Headers", ctx.headerMap(),
                "Parameters:", ctx.queryParamMap()
        );
    }
    private static String getPath(Context ctx) {
        return new StringBuilder().append("You are on ").
                append(ctx.pathParam("first")).
                append(" page, ").
                append(ctx.pathParam("second")).
                append(" subpage").toString();
    }
}