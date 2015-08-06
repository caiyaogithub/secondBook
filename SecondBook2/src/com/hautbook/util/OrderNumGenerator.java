package com.hautbook.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author caiyao 
 *
 * @function ������������
 */
public class OrderNumGenerator {
	/**
	 * ��ȡ������
	 * ��ʽΪ CyyyyMMddxxxx 13λ
	 * @return ������String
	 * 
	 */
	public static String getOrderNum(String type ){
		Map<String , Object> application = ServletActionContext.getContext().getApplication() ;
		int orderedNum = Integer.parseInt(application.get("orderedNum").toString()) ;
		return type + (new SimpleDateFormat("yyyyMMdd").format((new Date()))) + (orderedNum + 1 );
	}
}
