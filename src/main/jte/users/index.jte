@import org.example.hexlet.dto.UsersPage

@param UsersPage page
@param String term

@template.layout(
content = @`
    <h1>${page.getHeader()}</h1>
    <form method="GET" action="/users">
        <input type="text"
               name="term"
               value="${term}"
               placeholder="Поиск по имени">
        <button type="submit">Найти</button>
    </form>
    @if(page.getUsers() == null || page.getUsers().isEmpty())
        <p>Пользователи не найдены.</p>
    @else
        <ul>
            @for(var user : page.getUsers())
                <li><a href="/users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a></li>
            @endfor
        </ul>
    @endif
`
)