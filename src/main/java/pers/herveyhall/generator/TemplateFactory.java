package pers.herveyhall.generator;

import org.dom4j.Document;

import pers.herveyhall.generator.tools.DomHelper;

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
