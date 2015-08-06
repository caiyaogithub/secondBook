package com.hautbook.register.Action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * 
 * @author caiyao
 *
 * @function 用户下线 
 *
 */
public class UserLoginOutAction implements SessionAware , ApplicationAware{
	private  Map<String, Object> sessions ;
	private  Map<String, Object> application ;
	private @Getter @Setter InputStream inputStream ;

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessions = session ;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application ;
	}
	public String execute(){
		if(!application.containsKey(sessions.get("userName"))){
			inputStream = new ByteArrayInputStream("notlogin".getBytes()) ;
		}else{
			sessions.remove("userName") ;
			inputStream = new ByteArrayInputStream("success".getBytes()) ;
		}
		return "result" ;
	}
	
}
