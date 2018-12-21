package pers.herveyhall.generator;

import org.dom4j.Document;

public class TemplateFactory {
	public static GeneratorTemplate getGeneratorTemplate(String templateName) {
		Document document = DomHelper.getDocument(templateName);
		return new GeneratorTemplate(document);
	}
}
