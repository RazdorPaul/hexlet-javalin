package gg.jte.generated.ondemand.test;
public final class JtedebugGenerated {
	public static final String JTE_NAME = "test/debug.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,4,4,6,6,6,7,7,7,7,7,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String debug) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1>Тест XSS-защиты</h1>\r\n        <p>Переданный ID: ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent("<p>Hello</p>");
				jteOutput.writeContent("</p>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String debug = (String)params.get("debug");
		render(jteOutput, jteHtmlInterceptor, debug);
	}
}
