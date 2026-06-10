@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.CoursesPage
@import org.example.hexlet.model.Course

@param CoursesPage page
@param String term

@template.layout(
page = page,
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
                    <!-- Добавил display: flex и gap для ровного расположения кнопок -->
                    <td style="display: flex; gap: 0.5rem;">
                        <a href="${NamedRoutes.coursePath(course.getId())}" class="btn" style="padding: 0.3rem 0.8rem; font-size: 0.85rem;">Просмотр</a>

                        <!-- ВОТ ОНА, ДОБАВЛЕННАЯ КНОПКА РЕДАКТИРОВАНИЯ -->
                        <a href="${NamedRoutes.editCoursePath(course.getId())}" class="btn btn-primary" style="padding: 0.3rem 0.8rem; font-size: 0.85rem;">Редактировать</a>

                        <button type="button" class="btn btn-danger" style="padding: 0.3rem 0.8rem; font-size: 0.85rem;"
                                onclick="if(confirm('Вы действительно хотите удалить этот курс?')) {
                                    fetch('${NamedRoutes.coursePath(course.getId())}', { method: 'DELETE' })
                                    .then(() => window.location.href = '${NamedRoutes.coursesPath()}');
                                }">
                            Удалить
                        </button>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    @endif
`
)