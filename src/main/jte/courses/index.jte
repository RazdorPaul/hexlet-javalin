@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.CoursesPage
@import org.example.hexlet.model.Course

@param CoursesPage page
@param String term

@template.layout(
title = "Список курсов",
content = @`
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem;">
        <h1>Список курсов</h1>
        <a href="${NamedRoutes.coursesNewPath()}" class="btn btn-success">+ Создать курс</a>
    </div>

    <div class="card">
        <form method="GET" action="${NamedRoutes.coursesPath()}" style="display: flex; gap: 0.5rem;">
            <input type="text"
                   name="term"
                   value="${term != null ? term : ""}"
                   placeholder="Поиск по названию или описанию..."
                   style="flex: 1;">
            <button type="submit" class="btn">Найти</button>
        </form>
    </div>

    @if(page.getCourses() == null || page.getCourses().isEmpty())
        <div class="card" style="text-align: center; color: #64748b; padding: 2rem;">
            <p>Курсы не найдены.</p>
        </div>
    @else
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            @for(Course course : page.getCourses())
                <tr>
                    <td>${course.getId()}</td>
                    <td>${course.getName()}</td>
                    <td style="max-width: 400px;">${course.getDescription()}</td>
                    <td>
                        <a href="${NamedRoutes.coursePath(course.getId())}" class="btn" style="padding: 0.3rem 0.8rem; font-size: 0.85rem;">Просмотр</a>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    @endif
`
)