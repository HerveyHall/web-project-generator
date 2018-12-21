package pers.herveyhall.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Deprecated
public class DaoGenerator {
	public static void generate(List<DBTable> dbTables, String groupId, String outPath, String author) {
		for (DBTable dbTable : dbTables) {
			List<DBField> dbFields = dbTable.getDbFields();

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("package " + groupId + ".dao." + dbTable.getNameElements().get(0) + ";\n");
			stringBuilder.append("\n");
			stringBuilder.append("import java.util.List;\n");
			stringBuilder.append("\n");
			stringBuilder.append("import org.springframework.stereotype.Repository;\n");
			stringBuilder.append("\n");
			stringBuilder.append("import " + groupId + ".entity." + dbTable.getNameElements().get(0) + "."
					+ dbTable.getClassName() + ";\n");
			stringBuilder
					.append("\n/**\n * "
							+ (dbFields.get(0).getComment().endsWith("id") ? dbFields.get(0).getComment().substring(0,
									dbFields.get(0).getComment().lastIndexOf("id")) : "")
							+ "模块数据访问层\n * @author " + author + "\n */\n");
			stringBuilder.append("@Repository\n");
			stringBuilder.append("public interface " + dbTable.getClassName() + "Dao {\n");
			stringBuilder.append("\n");
			stringBuilder.append("	int insert(" + dbTable.getClassName() + " " + dbTable.getObjectName() + ");\n");
			stringBuilder.append("\n");
			stringBuilder.append("	int delete(" + dbFields.get(0).getJavaType().getSimpleName() + " "
					+ dbFields.get(0).getProperty() + ");\n");
			stringBuilder.append("\n");
			stringBuilder.append("	int update(" + dbTable.getClassName() + " " + dbTable.getObjectName() + ");\n");
			stringBuilder.append("\n");
			stringBuilder.append("	" + dbTable.getClassName() + " find("
					+ dbFields.get(0).getJavaType().getSimpleName() + " " + dbFields.get(0).getProperty() + ");\n");
			stringBuilder.append("\n");
			stringBuilder.append("	List<" + dbTable.getClassName() + "> queryList(" + dbTable.getClassName() + " "
					+ dbTable.getObjectName() + ");\n");
			stringBuilder.append("\n");
			stringBuilder.append("	List<" + dbTable.getClassName() + "> queryListBy"
					+ (char) (dbFields.get(0).getProperty().charAt(0) - 32)
					+ dbFields.get(0).getProperty().substring(1, dbFields.get(0).getProperty().length()) + "s(List<"
					+ dbTable.getDbFields().get(0).getJavaType().getSimpleName() + "> " + dbFields.get(0).getProperty()
					+ "List);\n");
			stringBuilder.append("}\n");

			FileWriter fileWriter = null;
			try {
				File file = new File(outPath + "/" + groupId.replaceAll(".", "/") + "/dao/"
						+ dbTable.getNameElements().get(0) + "/");
				file.mkdirs();
				fileWriter = new FileWriter(file.getAbsolutePath() + "/" + dbTable.getClassName() + "Dao.java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileWriter.write(stringBuilder.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
