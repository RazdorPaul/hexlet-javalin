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

        <div style="margin-top: 2rem;">
            <a href="${NamedRoutes.usersPath()}" class="btn btn-secondary">← Назад к списку пользователей</a>
        </div>
    </div>
`
)