package pers.herveyhall.generator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Generator {

	private static String templatePath = ConfigInfo.getConfig().templatePath;

	private static boolean dolayoutLog = ConfigInfo.getConfig().dolayoutLog;

	private static Logger logger = LoggerFactory.getLogger(Generator.class);

	public static void generate(List<DBTable> dbTables, String groupId, String targetPath) {
		File[] files = new File(Generator.class.getClassLoader().getResource(templatePath).getPath()).listFiles();
		String[] fileNames = new String[files.length];
		Map<String, Object> map = new HashMap<>();
		map.put("groupId", groupId);
		for (int i = 0; i < files.length; ++i) {
			fileNames[i] = files[i].getName();
		}
		for (String fileName : fileNames) {
			GeneratorTemplate generatorTemplate = TemplateFactory
					.getGeneratorTemplate(templatePath + File.separator + fileName);
			if (dolayoutLog) {
				logger.info("正在处理模板文件" + templatePath + File.separator + fileName + "\r\n...");
			}
			for (DBTable table : dbTables) {
				map.put("table", table);
				Template outPathSource = null;
				Template outNameSource = null;
				Template codeSource = null;
				try {
					outPathSource = new Template(null, generatorTemplate.getOutFilePath(), null);
					outNameSource = new Template(null, generatorTemplate.getOutFileName(), null);
					codeSource = new Template(null, generatorTemplate.getSource(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ByteArrayOutputStream outPathStream = new ByteArrayOutputStream();
				ByteArrayOutputStream outNameStream = new ByteArrayOutputStream();
				try {
					outPathSource.process(map, new OutputStreamWriter(outPathStream, "utf8"));
					outNameSource.process(map, new OutputStreamWriter(outNameStream, "utf8"));
				} catch (TemplateException | IOException e) {
					e.printStackTrace();
				}
				String outPath = null;
				String outName = null;
				try {
					outPath = new String(outPathStream.toByteArray(), "utf8");
					outName = new String(outNameStream.toByteArray(), "utf8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (dolayoutLog) {
					logger.info("正在以模板文件" + templatePath + File.separator + fileName + "生成目标文件" + targetPath
							+ File.separator + outPath + File.separator + outName.trim());
				}
				File file = new File(targetPath + File.separator + outPath + File.separator);
				if (!file.exists()) {
					file.mkdirs();
				}
				file = new File(targetPath + File.separator + outPath + File.separator + outName.trim());
				try {
					codeSource.process(map, new FileWriter(file));
				} catch (TemplateException | IOException e) {
					e.printStackTrace();
				}
				if (dolayoutLog) {
					logger.info("成功生成目标文件" + targetPath + File.separator + outPath + File.separator + outName.trim());
				}
			}
		}
	}
}
