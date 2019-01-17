package pers.herveyhall.generator.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;

/**
 * 日志操作帮助类
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
public class LogHelper {
	public static Logger getLogger() {
		return LoggerFactory.getLogger(Util.getCallingClass());
	}
}
