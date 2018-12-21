package pers.herveyhall.generator;

import org.dom4j.Element;

public class DomUtils {
	public static String getElementText(Element element) {
		return null == element ? null : element.getText();
	}
}
