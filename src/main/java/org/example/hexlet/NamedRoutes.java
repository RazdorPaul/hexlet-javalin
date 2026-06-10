package org.example.hexlet;

public final class NamedRoutes {
    public static String mainPath() {
        return "/";
    }

    public static String coursesPath() {
        return "/courses";
    }

    public static String coursePath(String id) {
        return coursesPath() + "/" + id;
    }

    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursesTestPath() {
        return "/test";
    }

    public static String coursesNewPath() {
        return "/courses/new";
    }

    public static String editCoursePath(String id) {
        return coursesPath() + "/" + id + "/edit";
    }

    public static String editCoursePath(Long id) {
        return editCoursePath(String.valueOf(id));
    }

    public static String usersPath() {
        return "/users";
    }

    public static String userPath(String id) {
        return usersPath() + "/" + id;
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String usersNewPath() {
        return "/users/new";
    }

    public static String editUserPath(String id) {
        return usersPath() + "/" + id + "/edit";
    }

    public static String editUserPath(Long id) {
        return editUserPath(String.valueOf(id));
    }

    public static String buildSessionPath() {
        return "/sessions/build";
    }

    public static String sessionsPath() {
        return "/sessions";
    }

    public static String deleteSessionPath() {
        return "/sessions/delete";
    }
}
