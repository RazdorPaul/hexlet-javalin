@import gg.jte.Content

@param Content content
@param String title = "Hexlet Project"

<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title != null ? title : "Hexlet Project"}</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header class="header">
    <div class="container">
        <h1 class="header-title">My App</h1>
        <nav class="nav">
            <a href="/" class="nav-link">Главная</a>
            <a href="/courses" class="nav-link">Courses</a>
            <a href="/users" class="nav-link">Users</a>
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
        <a href="https://github.com/RazdorPaul" class="footer-link">Мой GitHub</a>
    </div>
</footer>
</body>
</html>