/**
 * @Description:按照SVN--changeLog打包
 * @author:YC
 * @time:2017年3月30日 下午3:29:58
 */
package com.bfec.dsdemo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description:按照SVN--changeLog打包
 * @author:YC
 * @time:2017年3月30日 下午3:29:58
 */
public class PackageUtil {

	public static String patchFile = "C:/Users/admin/Desktop/changeLog.txt";// 补丁文件,由eclipse svn生成

	public static String projectPath = "D:/eclipseWorkplace/dpt";// 项目文件夹路径

	public static String webContent = "WebRoot";// web应用文件夹名

	public static String classPath = "D:/eclipseWorkplace/dpt/WebRoot/WEB-INF/classes";// class存放路径

	public static String desPath = "C:/Users/admin/Desktop/update_pkg";// 补丁文件包存放路径

	public static String version = "20140711";// 补丁版本

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		copyFiles(getPatchFileList());
	}

	public static Set<String> getPatchFileList() {
		Set<String> fileList = new HashSet<String>();
		FileInputStream f = null;
		BufferedReader dr = null;
		String reg="^\\d+$";
		String line;
		try {
			f = new FileInputStream(patchFile);
			dr = new BufferedReader(new InputStreamReader(f, "utf-8"));
			while ((line = dr.readLine()) != null) {
				if (line.indexOf("	A") != -1 || line.indexOf("	M") != -1) {
					line = line.replaceAll("	M ", "").replaceAll("	A ", "");
					line = line.substring(line.indexOf(":") + 1, line.length());
					fileList.add(line);
				}else if(line.matches(reg)) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(dr != null) {
					dr.close();
				}
				if(f != null) {
					f.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileList;
	}

	public static void copyFiles(Set<String> set) {
		for (String fullFileName : set) {
			if (fullFileName.indexOf("src/") != -1) {// 对源文件目录下的文件处理
//				String fileName = fullFileName.replace("src", "");
				String fileName = fullFileName.substring(fullFileName.indexOf("src")+3);
				fullFileName = classPath + fileName;
				if (fileName.endsWith(".java")) {
					fileName = fileName.replace(".java", ".class");
					fullFileName = fullFileName.replace(".java", ".class");
				}
				String tempDesPath = fileName.substring(0,
						fileName.lastIndexOf("/"));
				String desFilePathStr = desPath + "/" + version
						+ "/WEB-INF/classes" + tempDesPath;
				String desFileNameStr = desPath + "/" + version
						+ "/WEB-INF/classes" + fileName;
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, desFileNameStr);
				System.out.println(fullFileName + "复制完成");
			} else {// 对普通目录的处理
				String desFileName = fullFileName.replaceAll(webContent, "");
				fullFileName = projectPath + "/" + fullFileName;// 将要复制的文件全路径
				String fullDesFileNameStr = desPath + "/" + version
						+ desFileName;
				String desFilePathStr = fullDesFileNameStr.substring(0,
						fullDesFileNameStr.lastIndexOf("/"));
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, fullDesFileNameStr);
				System.out.println(fullDesFileNameStr + "复制完成");
			}

		}

	}

	private static void copyFile(String sourceFileNameStr, String desFileNameStr) {
		File srcFile = new File(sourceFileNameStr);
		File desFile = new File(desFileNameStr);
		try {
			copyFile(srcFile, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

}
