package pers.herveyhall.generator.db.model;

/**
 * 数据库表中的列
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DBField extends DBObject {
	private String column;
	private String property;
	private String type;
	private Class<?> javaType;
	private String length;
	private String comment;

	/**
	 * 获取列名
	 * 
	 * @return 列名
	 */
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * 获取列对应的属性名
	 * 
	 * @return 列对应的属性名
	 */
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * 获取列在数据库中的类型
	 * @return 列在数据库中的类型
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取列对应属性的类型
	 * 
	 * @return 列对应属性的类型
	 */
	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	/**
	 * 获取列在数据库中定义的长度
	 * 
	 * @return 列在数据库中定义的长度
	 */
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * 获取列的注释
	 * 
	 * @return 列的注释
	 */
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
