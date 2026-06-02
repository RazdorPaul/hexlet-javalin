@import org.example.hexlet.dto.courses.CoursesPage

@param CoursesPage page
@param String term

@template.layout(
content = @`
<h1>${page.getHeader()}</h1>
<form method="GET" action="/courses">
    <input type="text"
           name="term"
           value="${term}"
           placeholder="Поиск по названию или описанию...">
    <button type="submit">Найти</button>
</form>
@if(page.getCourses() == null || page.getCourses().isEmpty())
    <p>Курсы не найдены.</p>
@else
    <ul>
        @for(var course : page.getCourses())
            <li><a href="/courses/${course.getId()}">${course.getName()}</a></li>
        @endfor
    </ul>
@endif
`
)