@import gg.jte.Content
@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.Page

@param Content content
@param String title = "Hexlet Project"
@param Page page

<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title != null ? title : "Hexlet Project"}</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
@if (page != null)
    @if (page.getFlash() != null)
        <p class="alert alert-success">
            ${page.getFlash()}
        </p>
    @endif
@endif
<header class="header">
    <div class="container">
        <h1 class="header-title">My App</h1>
        <nav class="nav">
            <a href="${NamedRoutes.mainPath()}" class="nav-link">Главная</a>
            <a href="${NamedRoutes.coursesPath()}" class="nav-link">Courses</a>
            <a href="${NamedRoutes.usersPath()}" class="nav-link">Users</a>
        </nav>
    </div>
</header>

<main class="main">
    <div class="container">
        ${content}
    </div>
</main>

<footer class="footer">
    <div class="container">
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        <a href="https://github.com/RazdorPaul" class="footer-link">Мой GitHub</a>
    </div>
</footer>
</body>
</html>