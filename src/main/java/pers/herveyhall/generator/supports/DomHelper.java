/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
