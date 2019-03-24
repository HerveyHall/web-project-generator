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

import java.lang.reflect.Field;

/**
 * 数据库的结构
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DBObject {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		stringBuffer.append("[");
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				stringBuffer.append(null != field.getName() ? field.getName() : null).append(" = ");
				stringBuffer.append(field.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			field.setAccessible(false);
			stringBuffer.append(", ");
		}
		stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length()).append("]").append("(")
				.append(super.toString()).append(")");
		return stringBuffer.toString();
	}
}
