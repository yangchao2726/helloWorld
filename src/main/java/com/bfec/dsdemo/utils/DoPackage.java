package com.bfec.dsdemo.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoPackage {

	String base = "E:\\release\\newbfeccnweb";//基本目录
	String lastDate = "2017-03-24 00:00:00"; //截止日期
	
	//huodong
//	String base = "E:\\workspace_eclipse\\code\\web\\target\\hd_web";//基本目录
//	String lastDate = "2016-09-27 06:00:00"; //截止日期
	
	
//	String base = "E:\\release\\cabbage";//基本目录
//	String lastDate = "2016-10-09 06:00:00"; //截止日期
	
	
	@Test
	public void delEmptyFolders(){
		File file = new File(base);
		delFolder(file);
	}
	@Test
	public void genCode(){
		System.err.print(RandomStringUtils.randomAlphanumeric(20));
	}

	
	private void delFolder(File file){
		if (!file.isDirectory()) {
			this.delfile(file);
		}
		if (file.getName().startsWith(".")) {
			file.delete();
		}
		
		File[] files = file.listFiles();
		
		if (files != null && files.length > 0) {
			for (File subfile : files) {
				delFolder(subfile);
			}
		}
		
		
		files = file.listFiles();
		if (files == null || files.length == 0 ) {
			if (file.isDirectory()) {
				System.out.println("delete: " + file.getAbsolutePath());
				file.delete();
			}
		}
	}
	
	private void delfile(File file){
		
		long lastModified = file.lastModified();
		Date fileDate = new Date(lastModified);
		if (fileDate.compareTo(this.getLastDate()) < 0 ) {
			file.delete();
		}
	}
	
	private Date getLastDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date =  sdf.parse(lastDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
