@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage

@param MainPage page

@template.layout(
page = page,
title = "Главная",
content = @`
    <div class="hero">
        <h2>Добро пожаловать в Hexlet Project!</h2>
        @if(!page.isVisited())
            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
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