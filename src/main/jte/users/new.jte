@template.layout(
content = @`
    <h1>Новый пользователь</h1>
    <form method="POST" action="/users">
        <label>
            Имя
            <input type="text"
                   name="firstName"
                   placeholder="Имя"
                   required>
        </label>
        <label>
            Фамилия
            <input type="text"
                   name="lastName"
                   placeholder="Фамилия"
                   required>
        </label>
        <label>
            Электронная почта
            <input type="email"
                   name="email"
                   placeholder="Электронная почта"
                   required>
        </label>
        <label>
            Возраст
            <input type="number"
                   name="age"
                   placeholder="Возраст"
                   required>
        </label>
        <button type="submit">Добавить пользователя</button>
    </form>
`)
