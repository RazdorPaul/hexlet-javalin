package gg.jte.generated.ondemand;
import gg.jte.Content;
public final class JtelayoutGenerated {
	public static final String JTE_NAME = "layout.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,19,19,19,19,24,24,24,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"ru\">\r\n<head>\r\n    <meta charset=\"utf-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n    <title>Hexlet Project</title>\r\n</head>\r\n<body>\r\n<header>\r\n    <h1>My App</h1>\r\n    <nav>\r\n        <a href=\"/\">Главная</a>\r\n        <a href=\"/courses\">Courses</a>\r\n    </nav>\r\n</header>\r\n<main>");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("</main>\r\n<footer>\r\n    <a href=\"https://github.com/RazdorPaul\">Мой GitHub</a>\r\n</footer>\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, content);
	}
}
