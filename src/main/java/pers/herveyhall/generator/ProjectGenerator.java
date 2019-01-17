package pers.herveyhall.generator;

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

import pers.herveyhall.generator.db.model.DBField;
import pers.herveyhall.generator.db.model.DBTable;
import pers.herveyhall.generator.tools.JavaTypeConvertor;

import static pers.herveyhall.generator.Utils.*;

/**
 * 项目生成器主类
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class ProjectGenerator {

	private static Logger logger = LoggerFactory.getLogger(ProjectGenerator.class);

	/**
	 * 生成器启动类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigInfo configInfo = ConfigInfo.getConfig();
		configInfo.driverClass = "com.mysql.jdbc.Driver";
		configInfo.url = "jdbc:mysql://127.0.0.1:3306/test";
		configInfo.username = "root";
		configInfo.password = "root";
		configInfo.packageName = "demo.herveyhall";
		configInfo.targetFileUrl = "D:/src/main/java/";
		configInfo.templatePath = "template";
		configInfo.dolayoutLog = true;
		// configInfo.tablePrefix = "tb_";
		generate(getSchemas(configInfo), configInfo);
	}

	/**
	 * 获取表结构对象列表
	 */
	public static List<DBTable> getSchemas(ConfigInfo configInfo) {
		try {
			Class.forName(configInfo.driverClass);
		} catch (ClassNotFoundException e) {
			if (configInfo.dolayoutLog) {
				logger.error("无法加载数据库驱动类" + configInfo.driverClass);
			}
			throw new IllegalArgumentException("无法加载数据库驱动类" + configInfo.driverClass, e);
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
		List<DBTable> dbtables = new ArrayList<>();
		DBTable dbTable = null;
		try {
			ResultSet resultSet = statement.executeQuery("show tables");
			while (resultSet.next()) {
				tables.add(resultSet.getString(1));
			}
			for (String table : tables) {
				dbTable = new DBTable();
				dbTable.setName(table);
				dbTable.setObjectName(
						dbObjectNameToSimpleObjectName(trimFix(table, configInfo.tablePrefix, configInfo.tableSuffix)));
				dbTable.setClassName((char) (dbTable.getObjectName().charAt(0) - 32)
						+ dbTable.getObjectName().substring(1, dbTable.getObjectName().length()));
				dbTable.setNameElements(getWords(trimFix(table, configInfo.tablePrefix, configInfo.tableSuffix)));
				ResultSet resultSet2 = statement.executeQuery("show full columns from " + table);
				JexlContext jexlContext = new MapContext();
				jexlContext.set("configInfo", configInfo);
				jexlContext.set("table", dbTable);
				DBField dbField = null;
				while (resultSet2.next()) {
					dbField = new DBField();
					dbField.setColumn(resultSet2.getString("Field"));
					dbField.setComment(resultSet2.getString("Comment"));
					dbField.setLength(resultSet2.getString("Type").matches("^\\S+\\([0-9]+(,[0-9]+)?\\)$")
							? resultSet2.getString("Type").substring(resultSet2.getString("Type").indexOf('(') + 1,
									resultSet2.getString("Type").length() - 1)
							: "");
					dbField.setType(resultSet2.getString("Type").replaceAll("\\([0-9]+(,[0-9]+)?\\)", ""));// 处理decimal和int等类型
					dbField.setJavaType(JavaTypeConvertor.convert(dbField.getType()));
					dbField.setProperty(dbObjectNameToSimpleObjectName(trimFix(dbField.getColumn(),
							null == configInfo.fieldPrefixExpression ? null
									: new JexlBuilder().create().createExpression(configInfo.fieldPrefixExpression)
											.evaluate(jexlContext).toString(),
							null == configInfo.fieldSuffixExpression ? null
									: new JexlBuilder().create().createExpression(configInfo.fieldSuffixExpression)
											.evaluate(jexlContext).toString())));
					dbTable.getDbFields().add(dbField);
					dbField = null;
				}
				dbtables.add(dbTable);
				dbTable = null;
			}
		} catch (SQLException e) {
			if (configInfo.dolayoutLog) {
				logger.error("发现不支持的查询操作", e);
			}
			throw new UnsupportedOperationException("发现不支持的查询操作", e);
		}
		return dbtables;
	}

	/**
	 * 执行生成操作
	 * 
	 * @param dbtables 表结构对象列表
	 * @param configInfo 生成器配置
	 */
	public static void generate(List<DBTable> dbtables, ConfigInfo configInfo) {
		// DatabaseDocGenerator.generate(dbtables, "dbdoc.xlsx", "D:\\数据库文档1.xlsx");
		Generator.generate(dbtables, configInfo.packageName, configInfo.targetFileUrl);
		if (configInfo.dolayoutLog) {
			logger.info("SUCCESS");
		}
	}
}