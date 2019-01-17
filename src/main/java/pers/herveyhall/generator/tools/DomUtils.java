package pers.herveyhall.generator.tools;

import org.dom4j.Element;

/**
 * DOM相关操作工具类
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DomUtils {
	public static String getElementText(Element element) {
		return null == element ? null : element.getText();
	}
}
