package pers.herveyhall.generator;

public class ConfigInfo {

	String driverClass;
	String url;
	String username;
	String password;
	String tablePrefix;
	String tableSuffix;
	String fieldPrefixExpression;
	String fieldSuffixExpression;
	String packageName;
	String targetFileUrl;
	String templatePath;
	boolean dolayoutLog;

	private static ConfigInfo configInfo;

	private ConfigInfo() {
	}

	public static ConfigInfo getConfig() {
		if (null == configInfo) {
			synchronized (ConfigInfo.class) {
				configInfo = new ConfigInfo();
			}
		}
		return configInfo;
	}
}
