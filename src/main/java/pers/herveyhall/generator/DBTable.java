package pers.herveyhall.generator;

import java.util.ArrayList;
import java.util.List;

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
