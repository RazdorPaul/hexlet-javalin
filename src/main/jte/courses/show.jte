@import org.example.hexlet.dto.CoursePage

@param CoursePage page

@template.layout(
    content = @`
        <h1>${page.getHeader()}</h1>
        <ul>
            <li>${page.getCourse().getName()}
                <p>${page.getCourse().getDescription()}</p>
            </li>
        </ul>
    `
)





