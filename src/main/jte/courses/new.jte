@import org.example.hexlet.dto.BuildCoursePage

@param BuildCoursePage page

@template.layout(
title = "Новый курс",
content = @`
    <div class="card" style="max-width: 600px; margin: 0 auto;">
        <h1 style="margin-bottom: 1.5rem;">Новый курс</h1>

        <!-- Блок вывода ошибок валидации -->
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

        <form method="POST" action="/courses">
            <div class="form-group">
                <label for="name">Название курса</label>
                <!-- minlength="3" означает "длиннее 2 символов" -->
                <input type="text" id="name" name="name"
                       value="${page.getName() != null ? page.getName() : ""}"
                       placeholder="Название курса" required minlength="3">
            </div>

            <div class="form-group">
                <label for="description">Описание</label>
                <textarea id="description" name="description" rows="5"
                          value="${page.getDescription() != null ? page.getDescription() : ""}"
                          placeholder="Описание курса" required minlength="11"></textarea>
            </div>

            <div style="margin-top: 1.5rem; display: flex; gap: 1rem;">
                <button type="submit" class="btn btn-success">Добавить курс</button>
                <a href="/courses" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
`
)