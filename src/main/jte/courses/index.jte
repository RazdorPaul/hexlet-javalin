@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page


@template.layout(
   content = @`
    <h1>${page.getHeader()}</h1>
    <ul>
        @for(var course : page.getCourses())
            <li><a href="/courses/${course.getId()}">${course.getName()}</a></li>
        @endfor
    </ul>>
`)
