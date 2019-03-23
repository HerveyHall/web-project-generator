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

package pers.herveyhall.generator.db.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DBTable extends DBObject {

	private String name;
	private String className;
	private String objectName;
	private List<String> nameElements;
	private String comment;
	private List<DBField> dbFields = new ArrayList<>();

	/**
	 * 获取表名
	 * 
	 * @return 表名
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取表对应的POJO类名
	 * 
	 * @return 表对应的POJO类名
	 */
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 获取表对应的POJO对象名
	 * 
	 * @return 表对应的POJO对象名
	 */
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * 获取表名中单词列表
	 * 
	 * @return 表名中单词列表
	 */
	public List<String> getNameElements() {
		return nameElements;
	}

	public void setNameElements(List<String> nameElements) {
		this.nameElements = nameElements;
	}

	/**
	 * 获取表注释
	 * 
	 * @return 表注释
	 */
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 获取表中的column对象列表
	 * 
	 * @return 表中的column对象列表
	 */
	public List<DBField> getDbFields() {
		return dbFields;
	}

	public void setDbFields(List<DBField> dbFields) {
		this.dbFields = dbFields;
	}
}
