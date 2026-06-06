@import org.example.hexlet.dto.UserPage

@param UserPage page

@template.layout(
content = @`
    <h1>${page.getHeader()}</h1>
    <p>${"Age = " + page.getUser().getAge() + " years"}</p>
    <p>${"Email = " + page.getUser().getEmail()}</p>
`
)
