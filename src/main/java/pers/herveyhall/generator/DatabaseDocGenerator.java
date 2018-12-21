package pers.herveyhall.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class DatabaseDocGenerator {
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
