package pers.herveyhall.generator.templates;

import org.dom4j.Document;
import org.dom4j.Element;

import pers.herveyhall.generator.tools.DomUtils;

/**
 * 生成器默认模板格式
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class GeneratorTemplate extends XmlTemplate {
	private String format;
	private String source;
	private String outFilePath;
	private String outFileName;

	public GeneratorTemplate(Document document) {
		super();
		Element root = document.getRootElement();
		this.format = DomUtils.getElementText(root.element("format"));
		this.source = DomUtils.getElementText(root.element("source"));
		if (null == root.element("outFile")) {
			return;
		}
		this.outFilePath = DomUtils.getElementText(root.element("outFile").element("path"));
		this.outFileName = DomUtils.getElementText(root.element("outFile").element("name"));
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOutFilePath() {
		return outFilePath;
	}

	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

	public String getOutFileName() {
		return outFileName;
	}

	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

}
