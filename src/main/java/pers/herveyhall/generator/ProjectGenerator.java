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

package pers.herveyhall.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import pers.herveyhall.generator.core.GeneratorStarter;

/**
 * 项目生成器主类
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class ProjectGenerator {

	private static final String DEFAULT_CONFIG_FILENAME = "config.properties";
	private static final String DEFAULT_CONFIG_OBJECT = "configInfo";

	/**
	 * 以默认配置运行生成器
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		startGenerate();
	}

	/**
	 * 以默认配置调用生成器
	 */
	public static void startGenerate() {
		try {
			startGenerate(true, DEFAULT_CONFIG_FILENAME, DEFAULT_CONFIG_OBJECT);
		} catch (FileNotFoundException e) {
			new IllegalArgumentException("默认的配置文件丢失", e).printStackTrace();
		}
	}

	/**
	 * 以自定义配置调用生成器
	 * 
	 * @param isRelative 配置文件是否以相对路径引用
	 * @param uri        配置文件路径
	 * @param configObj  配置信息对象名
	 * @throws 未找到配置文件
	 */
	public static void startGenerate(boolean isRelative, String uri, String configObj) throws FileNotFoundException {
		if (isRelative) {
			GeneratorStarter.start(ProjectGenerator.class.getClassLoader().getResourceAsStream(uri), configObj);
		}
		GeneratorStarter.start(new FileInputStream(new File(uri)), configObj);
	}
}
