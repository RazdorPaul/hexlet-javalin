@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.UsersPage
@import org.example.hexlet.model.User

@param UsersPage page
@param String term

@template.layout(
title = "Список пользователей",
content = @`
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem;">
        <h1>Список пользователей</h1>
        <a href="${NamedRoutes.usersNewPath()}" class="btn btn-success">+ Создать пользователя</a>
    </div>

    <div class="card">
        <form method="GET" action="${NamedRoutes.usersPath()}" style="display: flex; gap: 0.5rem;">
            <input type="text"
                   name="term"
                   value="${term != null ? term : ""}"
                   placeholder="Поиск по имени..."
                   style="flex: 1;">
            <button type="submit" class="btn">Найти</button>
        </form>
    </div>

    @if(page.getUsers() == null || page.getUsers().isEmpty())
        <div class="card" style="text-align: center; color: #64748b; padding: 2rem;">
            <p>Пользователи не найдены.</p>
        </div>
    @else
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            @for(User user : page.getUsers())
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                    <td>
                        <a href="${NamedRoutes.userPath(user.getId())}" class="btn" style="padding: 0.3rem 0.8rem; font-size: 0.85rem;">Просмотр</a>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    @endif
`
)