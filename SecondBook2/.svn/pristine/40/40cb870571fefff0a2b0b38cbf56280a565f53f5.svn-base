package com.hautbook.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author caiyao 
 *
 * @function 文件操作的一些常用功能
 */
public class FileUtils {
	/**
	 * 将文件对象写出到文件，如果目标文件或文件夹不存在则会自动创建
	 * @param file 源文件对象
	 * @param targetFilePath 目标文件路径
	 * @return 正确写入返回true， 否者返回false
	 * @throws IOException : 如果出现文件写入异常
	 */
	public static boolean writeToFile(File file , String targetFilePath) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		File tempFile = new File(targetFilePath);
		if(tempFile.exists()){
			// 如果已经存在同名临时文件，返回false
			inputStream.close() ; 
			return false ;
		}
		// 检查目标文件夹是否存在，如果不存在创建文件夹
		File folder = new File(targetFilePath.substring(0 , targetFilePath.lastIndexOf("/") + 1 ));
		if(!folder.exists()){
			folder.mkdirs() ;
		}
		if(!tempFile.createNewFile()){
			inputStream.close() ; 
			return false ;
		} 
		OutputStream outputStream = new FileOutputStream(tempFile);
		int length = 0 ;
		byte[] buffer = new byte[1024] ;
		while((length = inputStream.read(buffer)) != -1){
			outputStream.write(buffer , 0 , length );
		}
		inputStream.close() ;
		outputStream.close() ; 
		return true ;
	}
	/**
	 * 
	 * 删除文件（只删除文件，不删除文件夹）
	 * @param filePath 文件路径
	 * @return true 删除成功 ，  false文件不存在
	 * 
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()){
			// 文件不存在
			return false ;
		}
		file.delete() ;
		return true ;
	}
	/**
	 * 删除文件夹以及其下所有文件
	 * 如果传递进来的是一个文件，这会删除该文件并返回true
	 * @param folderPath 文件夹的绝对路径
	 * @return 删除成功返回true，文件夹不存在返回false
	 * 
	 */
	public static boolean deleteFolder(String folderPath){
		File folder = new File(folderPath);
		if(!folder.exists()){
			return false ;
		}
		// 当传递过来的是一个文件则删除文件并返回
		if(folder.isFile()){
			folder.delete() ;
			return true ;
		}
		File[] fileList = folder.listFiles() ;
	
		for(File temp : fileList){
			if(temp.isFile()){
				temp.delete() ;
			}else{
				if(temp.listFiles().length == 0){temp.delete() ; continue ;}
				deleteFolder( temp.getAbsolutePath() ) ;
			}
		}
		folder.delete() ;
		return true ;
	}
}
