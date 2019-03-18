package pers.herveyhall.generator.supports;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * XML结构解析帮助类
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DomHelper {
	private static Map<String, Document> nativeCache = new HashMap<>();

	public static Document getDocument(String fileName) {
		if (nativeCache.containsKey(fileName)) {
			return nativeCache.get(fileName);
		}
		Document document = null;
		try {
			document = new SAXReader().read(DomHelper.class.getClassLoader().getResourceAsStream(fileName));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if (null == document) {
			return null;
		}
		nativeCache.put(fileName, document);
		return document;
	}
}
