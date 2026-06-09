@import org.example.hexlet.NamedRoutes

@template.layout(
content = @`
    <h1>Вход в систему</h1>

    <form action="${NamedRoutes.sessionsPath()}" method="post">
        <label>
            Email:<br>
            <input type="email" name="email" placeholder="Введите ваш email" required>
        </label>
        <br><br>
        <label>
            Пароль:<br>
            <input type="password" name="password" placeholder="Введите пароль">
        </label>
        <br><br>
        <button type="submit">Войти</button>
    </form>
    <br>
    <a href="${NamedRoutes.mainPath()}">На главную</a>
`
)