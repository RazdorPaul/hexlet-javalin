@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.UserPage

@param UserPage page

@template.layout(
title = page.getUser().getFirstName() + " " + page.getUser().getLastName(),
content = @`
    <div class="card">
        <h1 style="margin-bottom: 1.5rem;">${page.getHeader()}</h1>

        <div style="display: grid; gap: 0.75rem; margin-bottom: 1.5rem; font-size: 1.1rem;">
            <p><strong>Имя:</strong> ${page.getUser().getFirstName()}</p>
            <p><strong>Фамилия:</strong> ${page.getUser().getLastName()}</p>
            <p><strong>Email:</strong> ${page.getUser().getEmail()}</p>
            <p><strong>Телефон:</strong> ${page.getUser().getPhone()}</p>
            <p><strong>Возраст:</strong> ${page.getUser().getAge()} лет</p>
        </div>

        <!-- Добавил display: flex и gap, чтобы кнопки стояли ровно в ряд -->
        <div style="margin-top: 2rem; display: flex; gap: 1rem;">
            <a href="${NamedRoutes.usersPath()}" class="btn btn-secondary">← Назад к списку</a>

            <!-- КНОПКА РЕДАКТИРОВАНИЯ -->
            <a href="${NamedRoutes.editUserPath(page.getUser().getId())}" class="btn btn-primary">Редактировать</a>

            <button type="button" class="btn btn-danger"
                    onclick="if(confirm('Вы действительно хотите удалить этого пользователя?')) {
                        fetch('${NamedRoutes.userPath(page.getUser().getId())}', { method: 'DELETE' })
                        .then(() => window.location.href = '${NamedRoutes.usersPath()}');
                    }">
                Удалить
            </button>
        </div>
    </div>
`
)