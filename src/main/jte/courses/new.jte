
@template.layout(
content = @`
    <h1>Новый курс</h1>
    <form method="POST" action="/courses">
        <label>
            Название
            <input type="text"
                   name="name"
                   placeholder="Название курса"
                   required>
        </label>
        <label>
            Описание
            <textarea name="description"
                      placeholder="Описание курса"
                      required>
            </textarea>
        </label>
        <button type="submit">Добавить курс</button>
    </form>
`)
