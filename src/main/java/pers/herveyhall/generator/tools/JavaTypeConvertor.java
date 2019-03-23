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

package pers.herveyhall.generator.tools;

import java.util.HashMap;

/**
 * 数据类型转换器
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class JavaTypeConvertor {

	private static final String DEFAULT_TYPE = "java.lang.String";

	private static java.util.Map<String, String> typeMap = new HashMap<>();
	static {
		typeMap.put("int", "java.lang.Integer");
		typeMap.put("date", "java.util.Date");
		typeMap.put("datetime", "java.util.Date");
		typeMap.put("decimal", "java.math.BigDecimal");
		typeMap.put("double", "java.lang.Double");
		typeMap.put("varchar", "java.lang.String");
		typeMap.put("longtext", "java.lang.String");
		typeMap.put("text", "java.lang.String");
		typeMap.put("char", "java.lang.String");
		typeMap.put("tinyint", "java.lang.Boolean");
	}

	public static Class<?> convert(String dbType) {
		try {
			return Class.forName(typeMap.getOrDefault(dbType, DEFAULT_TYPE), false, ClassLoader.getSystemClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
