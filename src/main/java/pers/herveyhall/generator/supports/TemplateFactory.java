package pers.herveyhall.generator.supports;

import org.dom4j.Document;

import pers.herveyhall.generator.templates.GeneratorTemplate;

/**
 * 模板工厂
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class TemplateFactory {
	public static GeneratorTemplate getGeneratorTemplate(String templateName) {
		Document document = DomHelper.getDocument(templateName);
		return new GeneratorTemplate(document);
	}
}
