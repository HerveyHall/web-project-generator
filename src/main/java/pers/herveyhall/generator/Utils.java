package pers.herveyhall.generator;

import java.util.ArrayList;
import java.util.List;

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
