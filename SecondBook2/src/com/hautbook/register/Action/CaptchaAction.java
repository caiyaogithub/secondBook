package com.hautbook.register.Action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.hautbook.util.CaptchaImageGenerator;
import com.hautbook.util.CaptchaStringGenerator;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author caiyao
 *
 * @function 获取验证码 
 *
 */
public class CaptchaAction extends ActionSupport implements SessionAware{
	
	private ByteArrayInputStream imageStream ;
	private Map<String , Object> session ;
	
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session ;
	}
	public String execute(){
		//获取验证码字符串
		String captchaString = CaptchaStringGenerator.getCaptchaString(4).toLowerCase() ;
		session.put("captchaString", captchaString) ;
		try {
			imageStream = CaptchaImageGenerator.getImage(captchaString) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success" ;
	}
}
