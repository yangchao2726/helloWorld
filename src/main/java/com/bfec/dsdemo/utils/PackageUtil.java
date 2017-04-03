/**
 * @Description:����SVN--changeLog���
 * @author:YC
 * @time:2017��3��30�� ����3:29:58
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:����SVN--changeLog���
 * @author:YC
 * @time:2017��3��30�� ����3:29:58
 */
public class PackageUtil {

	/** 
	* @Description: PATCH_FILE : �����ļ�,��eclipse svn����
	*/
	public static final String PATCH_FILE = "C:\\Users\\Administrator\\Desktop\\changeLog.txt";

	/** 
	* @Description: PROJECT_PATH : ��Ŀ�ļ���·��
	*/
	public static final String PROJECT_PATH = "F:\\WorkSpace_for_package\\dpt";

	/** 
	* @Description: WEB_ROOT : webӦ���ļ�����
	*/
	public static final String WEB_ROOT = "/WebRoot";
	
	public static final String CONFIG = "config";

	/** 
	* @Description: CLASS_PATH : class���·��
	*/
	public static final String CLASS_PATH = "F:\\WorkSpace_for_package\\dpt\\WebRoot\\WEB-INF\\classes";

	/** 
	* @Description: DES_PATH : �����ļ������·��
	*/
	public static final String DES_PATH = "C:\\Users\\Administrator\\Desktop\\update_pkg\\";
	
	/** 
	* @Description: versionList : ���δ��������������svn�汾
	*/
	public static List<Integer> versionList = new ArrayList<Integer>();
	
	/** 
	* @Description: packageCNT : ����ļ�����
	*/
	public static int packageCNT = 0;
	
	public static int unKnownFileCNT = 0;
	
	/** 
	 * @Description: VERSION : �����汾
	 */
	public static String version = "";

	static {
		version = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		copyFiles(getPatchFileList());
		System.out.println("������ļ���"+packageCNT+"����");
		System.out.println("δ�����ļ���"+unKnownFileCNT+"����");
	}

	public static Set<String> getPatchFileList() {
		Set<String> fileSet = new HashSet<String>();
		FileInputStream f = null;
		BufferedReader dr = null;
		Pattern p = Pattern.compile("^r\\d+");
		Matcher matcher = null;
		String line;
		try {
			f = new FileInputStream(PATCH_FILE);
			dr = new BufferedReader(new InputStreamReader(f, "utf-8"));
			while ((line = dr.readLine()) != null) {
				if (line.indexOf("	A") != -1 || line.indexOf("	M") != -1) {
					line = line.replaceAll("	M ", "").replaceAll("	A ", "");
					line = line.substring(line.indexOf(":") + 1, line.length());
					fileSet.add(line);
				}else {
					matcher = p.matcher(line);
					if(matcher.find()) {
						versionList.add(Integer.parseInt(matcher.group(0).replace("r", "")));
					}
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
		Collections.sort(versionList);
		return fileSet;
	}

	public static void copyFiles(Set<String> set) throws IOException {
		for (String fullFileName : set) {
			// �������ļ�����
			if (fullFileName.indexOf("src/") != -1) {
				String fileName = fullFileName.substring(fullFileName.indexOf("src")+3);
				fullFileName = CLASS_PATH + fileName;
				String srcParentPath = "";
				String realFileName = "";
				boolean classFileFlag = false;
				if (fileName.endsWith(".java")) {
					fileName = fileName.replace(".java", ".class");
					fullFileName = fullFileName.replace(".java", ".class");
					classFileFlag = true;
				}
				if(isNotFile(fullFileName)) continue;
				String tempDesPath = fileName.substring(0,fileName.lastIndexOf("/"));
				String desFilePathStr = getDesFilePath(tempDesPath);
				String desFileNameStr = getDesFilePath(fileName);
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, desFileNameStr);
				System.out.println(fullFileName + "�������");
				if(classFileFlag) {
					srcParentPath = fullFileName.substring(0, fullFileName.lastIndexOf("/"));
					realFileName = fullFileName.substring(fullFileName.lastIndexOf("/")+1, fullFileName.length()-6);
					File parentPath = new File(srcParentPath);
					File[] filesInSameFolder = parentPath.listFiles();
					for(File tempFile : filesInSameFolder) {
						if(tempFile.getName().indexOf(realFileName+"$") != -1) {
							copyFile(tempFile.getAbsolutePath(), desFilePathStr + "/" + tempFile.getName());
							System.out.println(tempFile.getAbsolutePath() + "�������");
						}
					}
				}
			}else if (fullFileName.indexOf(CONFIG+"/") != -1) {
				// �����ļ�����
				String fileName = fullFileName.substring(fullFileName.indexOf(CONFIG)+CONFIG.length());
				fullFileName = CLASS_PATH + fileName;
				if(isNotFile(fullFileName)) continue;
				String tempDesPath = fileName.substring(0,
						fileName.lastIndexOf("/"));
				String desFilePathStr = getDesFilePath(tempDesPath);
				String desFileNameStr = getDesFilePath(fileName);
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, desFileNameStr);
				System.out.println(fullFileName + "�������");
			}else if (fullFileName.indexOf(WEB_ROOT) != -1) {
				// ��̬�ļ�����
				String desFileName = fullFileName.substring(fullFileName.indexOf(WEB_ROOT));
				fullFileName = PROJECT_PATH + desFileName;// ��Ҫ���Ƶ��ļ�ȫ·��
				String fullDesFileNameStr = DES_PATH + version
						+ desFileName;
				String desFilePathStr = fullDesFileNameStr.substring(0,
						fullDesFileNameStr.lastIndexOf("/"));
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				if(isNotFile(fullFileName)) continue;
				copyFile(fullFileName, fullDesFileNameStr);
				System.out.println(fullFileName + "�������");
			}else {
				unKnownFileCNT++;
				System.out.println("δʶ���ļ�:"+fullFileName);
			}
		}
	}
	
	private static String getDesFilePath(String pathName) {
		StringBuffer path = new StringBuffer(DES_PATH);
		path.append(version).append(WEB_ROOT).append("/WEB-INF/classes").append(pathName);
		return path.toString();
	}
	
	private static boolean isNotFile(String fileName) {
		File file = null;
		file = new File(fileName);
		if(file.isFile()) return false;
		return true;
	}

	private static void copyFile(String sourceFileNameStr, String desFileNameStr) throws IOException {
		File srcFile = new File(sourceFileNameStr);
		File desFile = new File(desFileNameStr);
		copyFile(srcFile, desFile);
	}

	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// �½��ļ����������������л���
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// �½��ļ���������������л���
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// ��������
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// ˢ�´˻���������
			outBuff.flush();
			packageCNT++;
		} finally {
			// �ر���
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

}
