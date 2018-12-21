package pers.herveyhall.generator;

import static pers.herveyhall.generator.Utils.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.herveyhall.generator.JavaTypeConvertor;

public class ProjectGenerator {

	private static Logger logger = LoggerFactory.getLogger(ProjectGenerator.class);

	public static void main(String[] args) {
		ConfigInfo configInfo = ConfigInfo.getConfig();
		configInfo.driverClass = "com.mysql.jdbc.Driver";
		configInfo.url = "jdbc:mysql://127.0.0.1:3306/db_product_repertory";
		configInfo.username = "root";
		configInfo.password = "root";
		configInfo.packageName = "com.lvcheng.mall";
		configInfo.targetFileUrl = "D:/src/main/java/";
		configInfo.templatePath = "template";
		configInfo.dolayoutLog = true;
		 configInfo.tablePrefix = "tb_";
		generate(configInfo);
	}

	public static void generate(ConfigInfo configInfo) {
		try {
			Class.forName(configInfo.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(configInfo.url, configInfo.username, configInfo.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> tables = new ArrayList<>();
		try {
			ResultSet resultSet = statement.executeQuery("show tables");
			while (resultSet.next()) {
				tables.add(resultSet.getString(1));
			}
			List<DBTable> dbtables = new ArrayList<>();
			for (String table : tables) {
				// if (!table.matches("^customer[\\S]*")) {
				// continue;
				// }
				DBTable dbTable = new DBTable();
				dbTable.setName(table);
				dbTable.setObjectName(
						dbObjectNameToSimpleObjectName(trimFix(table, configInfo.tablePrefix, configInfo.tableSuffix)));
				dbTable.setClassName((char) (dbTable.getObjectName().charAt(0) - 32)
						+ dbTable.getObjectName().substring(1, dbTable.getObjectName().length()));
				dbTable.setNameElements(getWords(trimFix(table, configInfo.tablePrefix, configInfo.tableSuffix)));
				ResultSet resultSet2 = statement.executeQuery("show full columns from " + table);
				// System.out.println("=================================");
				// System.out.println(table);
				// System.out.println();
				JexlContext jexlContext = new MapContext();
				jexlContext.set("configInfo", configInfo);
				jexlContext.set("table", dbTable);
				while (resultSet2.next()) {
					DBField dbField = new DBField();
					dbField.setColumn(resultSet2.getString("Field"));
					dbField.setComment(resultSet2.getString("Comment"));
					dbField.setLength(resultSet2.getString("Type").matches("^\\S+\\([0-9]+(,[0-9]+)?\\)$")
							? resultSet2.getString("Type").substring(resultSet2.getString("Type").indexOf('(') + 1,
									resultSet2.getString("Type").length() - 1)
							: "");
					dbField.setType(resultSet2.getString("Type").replaceAll("\\([0-9]+(,[0-9]+)?\\)", ""));// 澶勭悊decimal鍜宨nt绛夌被鍨�
					dbField.setJavaType(JavaTypeConvertor.convert(dbField.getType()));
					dbField.setProperty(dbObjectNameToSimpleObjectName(trimFix(dbField.getColumn(),
							null == configInfo.fieldPrefixExpression ? null
									: new JexlBuilder().create().createExpression(configInfo.fieldPrefixExpression)
											.evaluate(jexlContext).toString(),
							null == configInfo.fieldSuffixExpression ? null
									: new JexlBuilder().create().createExpression(configInfo.fieldSuffixExpression)
											.evaluate(jexlContext).toString())));
					dbTable.getDbFields().add(dbField);
					// System.out.println(resultSet2.getString("Field") + " // " +
					// resultSet2.getString("Comment"));
					// System.out.println(dbField.getType());
					// System.out.println(dbField.getLength());
				}
				dbtables.add(dbTable);
			}
			Generator.generate(dbtables, configInfo.packageName, configInfo.targetFileUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (configInfo.dolayoutLog) {
			logger.info("SUCCESS");
		}
	}
}