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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import pers.herveyhall.generator.db.model.DBTable;

/**
 * 数据库文档生成器（运行此生成器需在ProjectGenerator调用DatabaseDocGenerator.generate()方法）
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DatabaseDocGenerator {
	
	/**
	 * 生成数据库文档
	 * 
	 * @param dbTables 数据库表结构对象列表
	 * @param template Excel模板路径
	 * @param outPath 文档输出路径
	 */
	public static void generate(List<DBTable> dbTables, String template, String outPath) {
		Context context = new Context();
		context.putVar("dbTables", dbTables);
		try {
			JxlsHelper.getInstance().processTemplate(
					DatabaseDocGenerator.class.getClassLoader().getResourceAsStream(template),
					new FileOutputStream(outPath), context);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
