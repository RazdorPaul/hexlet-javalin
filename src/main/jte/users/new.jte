@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BuildUserPage

@param BuildUserPage page

@template.layout(
title = "Новый пользователь",
content = @`
    <div class="card" style="max-width: 600px; margin: 0 auto;">
        <h1 style="margin-bottom: 1.5rem;">Новый пользователь</h1>

        @if(page.getErrors() != null && !page.getErrors().isEmpty())
            <div class="alert-error">
                <strong>Пожалуйста, исправьте следующие ошибки:</strong>
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            </div>
        @endif

        <form method="POST" action="${NamedRoutes.usersPath()}">
            <div class="form-group">
                <label for="firstName">Имя</label>
                <input type="text"
                       id="firstName"
                       name="firstName"
                       value="${page.getFirstName() != null ? page.getFirstName() : ""}"
                       placeholder="Имя" required>
            </div>

            <div class="form-group">
                <label for="lastName">Фамилия</label>
                <input type="text"
                       id="lastName"
                       name="lastName"
                       value="${page.getLastName() != null ? page.getLastName() : ""}"
                       placeholder="Фамилия" required>
            </div>

            <div class="form-group">
                <label for="email">Электронная почта</label>
                <input type="email"
                       id="email"
                       name="email"
                       value="${page.getEmail() != null ? page.getEmail() : ""}"
                       placeholder="Электронная почта" required>
            </div>

            <div class="form-group">
                <label for="age">Возраст</label>
                <input type="number"
                       id="age"
                       name="age"
                       value="${page.getAge() != null ? page.getAge().toString() : ""}"
                       placeholder="Возраст" required>
            </div>

            <div class="form-group">
                <label for="phone">Телефон</label>
                <input type="text"
                       id="phone"
                       name="phone"
                       value="${page.getPhone() != null ? page.getPhone() : ""}"
                       placeholder="Телефон" required>
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" placeholder="Пароль" required>
            </div>

            <div class="form-group">
                <label for="passwordConfirmation">Подтверждение пароля</label>
                <input type="password" id="passwordConfirmation" name="passwordConfirmation" placeholder="Подтверждение пароля" required>
            </div>

            <div style="margin-top: 1.5rem; display: flex; gap: 1rem;">
                <button type="submit" class="btn btn-success">Добавить пользователя</button>
                <a href="${NamedRoutes.usersPath()}" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
`
)
