package pers.herveyhall.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Deprecated
public class ServiceGenerator {
	public static void generate(List<DBTable> dbTables, String groupId, String outPath, String author) {
		for (DBTable dbTable : dbTables) {
			List<DBField> dbFields = dbTable.getDbFields();

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("package ");
			stringBuilder.append(groupId);
			stringBuilder.append(".service.");
			stringBuilder.append(dbTable.getNameElements().get(0));
			stringBuilder.append(";\n");
			stringBuilder.append("\n");
			stringBuilder.append("import java.util.List;\n");
			stringBuilder.append("\n");
			stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;\n");
			stringBuilder.append("import org.springframework.stereotype.Service;\n");
			stringBuilder.append("import org.springframework.transaction.annotation.Transactional;\n");
			stringBuilder.append("\n");
			stringBuilder.append("import ");
			stringBuilder.append(groupId);
			stringBuilder.append(".dao.");
			stringBuilder.append(dbTable.getNameElements().get(0));
			stringBuilder.append(".");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append("Dao;\n");
			stringBuilder.append("import ");
			stringBuilder.append(groupId);
			stringBuilder.append(".entity.");
			stringBuilder.append(dbTable.getNameElements().get(0));
			stringBuilder.append(".");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append(";\n");
			stringBuilder.append("\n/**\n * ");
			stringBuilder.append((dbFields.get(0).getComment().endsWith("id")
					? dbFields.get(0).getComment().substring(0, dbFields.get(0).getComment().lastIndexOf("id"))
					: ""));
			stringBuilder.append("模块业务逻辑层\n * @author ");
			stringBuilder.append(author);
			stringBuilder.append("\n */\n");
			stringBuilder.append("@Service\n@Transactional\n");
			stringBuilder.append("public class ");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append("Service {\n");
			stringBuilder.append("	@Autowired\n");
			stringBuilder.append("	private ");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append("Dao ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao;\n");
			stringBuilder.append("\n");
			stringBuilder.append("	public ");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append(" insert(");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append(" ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(") {\n");
			stringBuilder.append("		if (0 < ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao.insert(");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(")) {\n");
			stringBuilder.append("			return ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(";\n");
			stringBuilder.append("		}\n");
			stringBuilder.append("		return null;\n");
			stringBuilder.append("	}\n");
			stringBuilder.append("\n");
			stringBuilder.append("	public int delete(");
			stringBuilder.append(dbTable.getDbFields().get(0).getJavaType().getSimpleName());
			stringBuilder.append(" ");
			stringBuilder.append(dbTable.getDbFields().get(0).getProperty());
			stringBuilder.append(") {\n");
			stringBuilder.append("		return ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao.delete(");
			stringBuilder.append(dbTable.getDbFields().get(0).getProperty());
			stringBuilder.append(");\n");
			stringBuilder.append("	}\n");
			stringBuilder.append("\n");
			stringBuilder.append("	public int update(");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append(" ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(") {\n");
			stringBuilder.append("		return ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao.update(");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(");\n");
			stringBuilder.append("	}\n");
			stringBuilder.append("\n");
			stringBuilder.append("	public ");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append(" find(");
			stringBuilder.append(dbTable.getDbFields().get(0).getJavaType().getSimpleName());
			stringBuilder.append(" ");
			stringBuilder.append(dbTable.getDbFields().get(0).getProperty());
			stringBuilder.append(") {\n");
			stringBuilder.append("		return ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao.find(");
			stringBuilder.append(dbTable.getDbFields().get(0).getProperty());
			stringBuilder.append(");\n");
			stringBuilder.append("	}\n");
			stringBuilder.append("\n");
			stringBuilder.append("	public List<");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append("> queryList(");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append(" ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(") {\n");
			stringBuilder.append("		return ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao.qureyList(");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append(");\n");
			stringBuilder.append("	}\n");
			stringBuilder.append("\n");
			stringBuilder.append("	public List<");
			stringBuilder.append(dbTable.getClassName());
			stringBuilder.append("> queryListBy");
			stringBuilder.append((char) (dbFields.get(0).getProperty().charAt(0) - 32));
			stringBuilder.append(dbFields.get(0).getProperty().substring(1, dbFields.get(0).getProperty().length()));
			stringBuilder.append("s(List<");
			stringBuilder.append(dbTable.getDbFields().get(0).getJavaType().getSimpleName());
			stringBuilder.append("> ");
			stringBuilder.append(dbTable.getDbFields().get(0).getProperty());
			stringBuilder.append("List) {\n");
			stringBuilder.append("		return ");
			stringBuilder.append(dbTable.getObjectName());
			stringBuilder.append("Dao.queryListBy");
			stringBuilder.append((char) (dbFields.get(0).getProperty().charAt(0) - 32));
			stringBuilder.append(dbFields.get(0).getProperty().substring(1, dbFields.get(0).getProperty().length()));
			stringBuilder.append("s(");
			stringBuilder.append(dbTable.getDbFields().get(0).getProperty());
			stringBuilder.append("List);\n");
			stringBuilder.append("	}\n");
			stringBuilder.append("}\n");

			FileWriter fileWriter = null;
			try {
				File file = new File(outPath + "/" + groupId.replaceAll(".", "/") + "/service/"
						+ dbTable.getNameElements().get(0) + "/");
				file.mkdirs();
				fileWriter = new FileWriter(file.getAbsolutePath() + "/" + dbTable.getClassName() + "Service.java");
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
