package pers.herveyhall.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import pers.herveyhall.generator.db.model.DBTable;

/**
 * 数据库文档生成器（运行此生成器需在ProjectGenerator调用DatabaseDocGenerator.generate()方法）
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class DatabaseDocGenerator {
	
	/**
	 * 生成数据库文档
	 * 
	 * @param dbTables 数据库表结构对象列表
	 * @param template Excel模板路径
	 * @param outPath 文档输出路径
	 */
	public static void generate(List<DBTable> dbTables, String template, String outPath) {
		Context context = new Context();
		context.putVar("dbTables", dbTables);
		try {
			JxlsHelper.getInstance().processTemplate(
					DatabaseDocGenerator.class.getClassLoader().getResourceAsStream(template),
					new FileOutputStream(outPath), context);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
