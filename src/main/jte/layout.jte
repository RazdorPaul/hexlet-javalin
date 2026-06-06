@import gg.jte.Content

@param Content content

<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hexlet Project</title>
</head>
<body>
<header>
    <h1>My App</h1>
    <nav>
        <a href="/">Главная</a>
        <a href="/courses">Courses</a>
        <a href="/users">Users</a>
    </nav>
</header>
<main>${content}</main>
<footer>
    <a href="https://github.com/RazdorPaul">Мой GitHub</a>
</footer>
</body>
</html>