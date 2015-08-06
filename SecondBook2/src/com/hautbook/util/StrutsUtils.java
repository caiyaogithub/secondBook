package com.hautbook.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author caiyao 
 *
 * @function : 关于struts框架的一些常用功能
 */

public class StrutsUtils {
	/**
	 * 获取session对象
	 * @return HttpSession
	 */
	public static HttpSession getSession(){
		return ((HttpServletRequest)(ActionContext.getContext().getValueStack().
				getContext().get(StrutsStatics.HTTP_REQUEST))).getSession() ;
	}
	/**
	 * 获取request对象
	 * @return ServletRequest
	 */
	public static ServletRequest getRequest(){
		return (ServletRequest)(ActionContext.getContext().getValueStack().
				getContext().get(StrutsStatics.HTTP_REQUEST)) ;
	}
	/**
	 * 获取response对象
	 * @return ServletResponse
	 */
	public static ServletResponse getResponse(){
		return (ServletResponse)ActionContext.getContext().getValueStack().
				getContext().get(StrutsStatics.HTTP_RESPONSE) ;
	}
}
