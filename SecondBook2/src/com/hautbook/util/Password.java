package com.hautbook.util;

import java.security.NoSuchAlgorithmException;

public class Password {
	
	/**
	 * @param password : ���� ����
	 * @return �� ʮ�����Ʊ�ʾ��MD5ժҪ�ַ���
	 * @throws NoSuchAlgorithmException
	 */
	 public static String getMD5(String password) throws NoSuchAlgorithmException{
	    	// ��ȡժҪ�㷨
	    	java.security.MessageDigest md = null ;
				md = java.security.MessageDigest.getInstance("MD5");
	    	// ΪժҪ�㷨�ṩ����
	    	md.update(password.getBytes()) ;
	    	// ����ժҪ����ȡ����128λ�����Σ���ֵ��byte ����16��byte
	    	byte temp[] = md.digest() ;
	    	char[] hex = {'0' , '1' , '2' , '3' , '4' ,'5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f'} ;
	    	// ����char�������ڴ��ת�����ʮ�������ַ���һ��byte8λ����16���Ʊ�ʾ��Ҫ����16�����ַ������Զ���char����ĳ���Ϊ16*2 
	    	char[] hexString = new char[16*2];
	    	int k = 0 ;
	    	for(int i = 0 ; i < temp.length ; i ++){
	    		// ȡ������i���ֽ�
	    		byte byteo = temp[i] ;
	    		// ȡ������λ����ΪҪ������λת��Ϊ16�����ַ�
	    		int top4 = byteo>>>4 & 0xf;
	    		// ������λʮ�����Ʊ�ʾת����char����ֵ
	    		hexString[k++] = hex[top4] ;
	    		// ȡ������λ
	    		int low4 = byteo & 0xf ;
	    		hexString[k++] = hex[low4] ;
	    	}
	    	return new String(hexString) ;
	    }
}