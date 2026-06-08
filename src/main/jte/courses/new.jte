@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BuildCoursePage

@param BuildCoursePage page
@param String header
@param String actionUrl
@param String method
@param String buttonText

@template.layout(
title = header,
content = @`
    <div class="card" style="max-width: 600px; margin: 0 auto;">
        <h1 style="margin-bottom: 1.5rem;">"${header}"</h1>
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

        <form method="${method}" action="${actionUrl}">
            <div class="form-group">
                <label for="name">Название курса</label>
                <input type="text" id="name" name="name"
                       value="${page.getName() != null ? page.getName() : ""}"
                       placeholder="Название курса" required minlength="3">
            </div>

            <div class="form-group">
                <label for="description">Описание</label>
                <textarea id="description" name="description" rows="5"
                          placeholder="Описание курса"
                          required minlength="11">${page.getDescription() != null ? page.getDescription() : ""}
                </textarea>
            </div>

            <div style="margin-top: 1.5rem; display: flex; gap: 1rem;">
                <button type="submit" class="btn btn-success">${buttonText}</button>
                <a href="${NamedRoutes.coursesPath()}" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
`
)