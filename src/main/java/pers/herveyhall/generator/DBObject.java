package pers.herveyhall.generator;

import java.lang.reflect.Field;

public class DBObject {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		stringBuilder.append("[");
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				stringBuilder.append(null != field.getName() ? field.getName() : null).append(" = ");
				stringBuilder.append(field.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			field.setAccessible(false);
			stringBuilder.append(", ");
		}
		stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("]").append("(")
				.append(super.toString()).append(")");
		return stringBuilder.toString();
	}
}
