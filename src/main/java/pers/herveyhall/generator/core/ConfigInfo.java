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

package pers.herveyhall.generator.core;

import pers.herveyhall.generator.tools.NotNull;

/**
 * 生成器配置信息
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class ConfigInfo {

	/**
	 * 数据库驱动类
	 */
	@NotNull
	String driverClass;

	/**
	 * 数据库连接地址
	 */
	@NotNull
	String url;

	/**
	 * 数据库用户名
	 */
	@NotNull
	String username;

	/**
	 * 数据库密码
	 */
	@NotNull
	String password;

	/**
	 * 表名前缀
	 */
	String tablePrefix;

	/**
	 * 表名后缀
	 */
	String tableSuffix;

	/**
	 * 列名前缀表达式
	 */
	String fieldPrefixExpression;

	/**
	 * 列名后缀表达式
	 */
	String fieldSuffixExpression;

	/**
	 * 输出包名
	 */
	@NotNull
	String packageName;

	/**
	 * 输出文件路径
	 */
	@NotNull
	String targetFileUrl;

	/**
	 * 模板所在路径
	 */
	@NotNull
	String templatePath;

	/**
	 * 日志开关
	 */
	boolean dolayoutLog = true;

	private static ConfigInfo configInfo;

	private ConfigInfo() {
	}

	public static ConfigInfo getConfig() {
		if (null == configInfo) {
			synchronized (ConfigInfo.class) {
				configInfo = new ConfigInfo();
			}
		}
		return configInfo;
	}
}
