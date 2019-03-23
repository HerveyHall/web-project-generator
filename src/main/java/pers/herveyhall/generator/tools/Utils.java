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

import java.util.ArrayList;
import java.util.List;

/**
 * 常用工具类
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class Utils {
	public static String trimFix(String string, String prefix, String suffix) {
		if (null != prefix) {
			string = string.replaceFirst(
					"^(?:" + prefix.replaceAll("([\\\\\\$\\(\\)\\*\\+\\.\\[\\]\\?\\^\\{\\}\\|])", "\\\\$1") + ")", "");
		}
		if (null != suffix) {
			string = string.replaceFirst(
					"(?:" + suffix.replaceAll("([\\\\\\$\\(\\)\\*\\+\\.\\[\\]\\?\\^\\{\\}\\|])$", "\\\\$1") + ")", "");
		}
		return string;
	}

	public static String dbObjectNameToSimpleObjectName(String tableName) {
		tableName = tableName.toLowerCase();
		StringBuilder tableNameSB = new StringBuilder();
		boolean isNextInitial = false;
		for (int j = 0; j < tableName.length(); ++j) {
			if (tableName.charAt(j) > 96 && tableName.charAt(j) < 123) {
				tableNameSB.append(isNextInitial ? (char) (tableName.charAt(j) - 32) : tableName.charAt(j));
				isNextInitial = false;
			} else {
				isNextInitial = true;
			}
		}
		return tableNameSB.toString();
	}

	public static List<String> getWords(String sentence) {
		List<String> result = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		char[] characters = sentence.toCharArray();
		for (Character ch : characters) {
			if (Character.isLetterOrDigit(ch)) {
				stringBuilder.append(ch);
			} else if (0 < stringBuilder.length()) {
				result.add(stringBuilder.toString());
				stringBuilder.delete(0, stringBuilder.length() - 1);
			} else {
				;
			}
		}
		result.add(stringBuilder.toString());
		return result;
	}

	public static String implode(Object[] arg1, String arg2, String arg3) {
		if (0 == arg1.length) {
			return new String();
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (Object item : arg1) {
			if (null == item) {
				continue;
			}
			if (null != arg3) {
				stringBuilder.append(arg3);
			}
			stringBuilder.append(item).append(arg2);
		}
		return stringBuilder.substring(0, stringBuilder.length() - arg2.length());
	}
}
