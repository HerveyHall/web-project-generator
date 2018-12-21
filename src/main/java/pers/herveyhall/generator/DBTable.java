package pers.herveyhall.generator;

import java.util.ArrayList;
import java.util.List;

public class DBTable extends DBObject {

	// private static DBTable table;
	private String name;
	private String className;
	private String objectName;
	private List<String> nameElements;
	private String comment;
	private List<DBField> dbFields = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public List<String> getNameElements() {
		return nameElements;
	}

	public void setNameElements(List<String> nameElements) {
		this.nameElements = nameElements;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<DBField> getDbFields() {
		return dbFields;
	}

	public void setDbFields(List<DBField> dbFields) {
		this.dbFields = dbFields;
	}

	// public static DBTable getNewTable() {
	// if (null == table) {
	// synchronized (table.getClass()) {
	// table = new DBTable();
	// }
	// }
	// return table;
	// }
}
