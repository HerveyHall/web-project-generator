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
