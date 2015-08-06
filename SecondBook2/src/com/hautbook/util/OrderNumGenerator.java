package com.hautbook.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author caiyao 
 *
 * @function 订单号生成器
 */
public class OrderNumGenerator {
	/**
	 * 获取订单号
	 * 格式为 CyyyyMMddxxxx 13位
	 * @return 订单号String
	 * 
	 */
	public static String getOrderNum(String type ){
		Map<String , Object> application = ServletActionContext.getContext().getApplication() ;
		int orderedNum = Integer.parseInt(application.get("orderedNum").toString()) ;
		return type + (new SimpleDateFormat("yyyyMMdd").format((new Date()))) + (orderedNum + 1 );
	}
}
