package com.hautbook.util;

import java.security.NoSuchAlgorithmException;

public class Password {
	
	/**
	 * @param password : 明文 密码
	 * @return ： 十六进制表示的MD5摘要字符串
	 * @throws NoSuchAlgorithmException
	 */
	 public static String getMD5(String password) throws NoSuchAlgorithmException{
	    	// 获取摘要算法
	    	java.security.MessageDigest md = null ;
				md = java.security.MessageDigest.getInstance("MD5");
	    	// 为摘要算法提供数据
	    	md.update(password.getBytes()) ;
	    	// 计算摘要，获取的是128位长整形，赋值给byte 就是16个byte
	    	byte temp[] = md.digest() ;
	    	char[] hex = {'0' , '1' , '2' , '3' , '4' ,'5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f'} ;
	    	// 定义char数组用于存放转换后的十六进制字符，一个byte8位，用16进制表示需要两个16进制字符。所以定义char数组的长度为16*2 
	    	char[] hexString = new char[16*2];
	    	int k = 0 ;
	    	for(int i = 0 ; i < temp.length ; i ++){
	    		// 取出来第i个字节
	    		byte byteo = temp[i] ;
	    		// 取出高四位，因为要将高四位转换为16进制字符
	    		int top4 = byteo>>>4 & 0xf;
	    		// 将高四位十六进制表示转换成char并赋值
	    		hexString[k++] = hex[top4] ;
	    		// 取出低四位
	    		int low4 = byteo & 0xf ;
	    		hexString[k++] = hex[low4] ;
	    	}
	    	return new String(hexString) ;
	    }
}
