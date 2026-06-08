@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.CoursePage

@param CoursePage page

@template.layout(
title = page.getCourse().getName(),
content = @`
    <div class="card">
        <h1 style="margin-bottom: 1rem;">${page.getHeader()}</h1>

        <h2 style="color: #475569; margin-bottom: 1rem; font-size: 1.5rem;">
            ${page.getCourse().getName()}
        </h2>

        <p style="line-height: 1.6; color: #334155; white-space: pre-wrap;">
            ${page.getCourse().getDescription()}
        </p>

        <div style="margin-top: 2rem;">
            <a href="${NamedRoutes.coursesPath()}" class="btn btn-secondary">← Назад к списку курсов</a>
            <a href="${NamedRoutes.editCoursePath(page.getCourse().getId())}" class="btn btn-primary">Редактировать курс</a>
            <button type="button" class="btn btn-danger"
                    onclick="if(confirm('Вы действительно хотите удалить этот курс?')) {
                        fetch('${NamedRoutes.coursePath(page.getCourse().getId())}', { method: 'DELETE' })
                        .then(() => window.location.href = '${NamedRoutes.coursesPath()}');
                    }">
                Удалить курс
            </button>
        </div>
    </div>
`
)




