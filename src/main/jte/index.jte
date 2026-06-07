@import org.example.hexlet.NamedRoutes

@template.layout(
title = "Главная",
content = @`
    <div class="hero">
        <h2>Добро пожаловать в Hexlet Project!</h2>
        <p class="hero-text">
            Это учебное приложение для управления пользователями и курсами.
        </p>
        <div class="hero-buttons">
            <a href="${NamedRoutes.usersPath()}" class="btn btn-primary">Пользователи</a>
            <a href="${NamedRoutes.coursesPath()}" class="btn btn-secondary">Курсы</a>
        </div>
    </div>
`
)