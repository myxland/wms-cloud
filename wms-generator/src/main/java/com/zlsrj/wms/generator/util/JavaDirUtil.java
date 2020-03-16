package com.zlsrj.wms.generator.util;

import java.io.File;

public class JavaDirUtil {

	public final static String SEPARATOR = "/"; // Windows/Linux都兼容的文件夹分隔符
	public final static String WINDOWS_SEPARATOR = "\\"; // Windows文件夹分隔符
	public final static String LINUX_SEPARATOR = "/"; // Linux文件夹分隔符

	public static String createDirByPath(String path) {
		File dir = new File(path);
		if (dir.exists() == false) {
			dir.mkdirs();
		}

		return dir.getAbsolutePath();
	}

	public static String createDirByPath(File parentDir, String... subDirs) {
		String parentDirString = parentDir.getAbsolutePath();
		return createDirByPath(parentDirString, subDirs);
	}

	public static String createDirByPath(String parentDir, String... subDirs) {
		String fullDir = parentDir;
		if (parentDir.endsWith(JavaDirUtil.WINDOWS_SEPARATOR)) {
			fullDir = parentDir.substring(0, parentDir.length() - 1);
		}

		if (parentDir.endsWith(JavaDirUtil.LINUX_SEPARATOR)) {
			fullDir = parentDir.substring(0, parentDir.length() - 1);
		}

		for (String subDir : subDirs) {
			fullDir = fullDir + JavaDirUtil.SEPARATOR + subDir;
		}

		return createDirByPath(fullDir);

	}
}
