package pers.herveyhall.generator;

import java.util.HashMap;

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
