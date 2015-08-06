package com.hautbook.register.Action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import com.hautbook.DB.UserDAO;
import com.hautbook.util.Password;
import com.hautbook.util.Validate;

/**
 * 
 * @author caiyao
 *
 * @function ¿ØÖÆÓÃ»§µÇÂ½ 
 *
 */
public class UserLoginAction implements SessionAware , ApplicationAware{
	private Map<String , Object> session ;
	private Map<String , Object> application ;
	private @Getter @Setter String dept ;
	private @Getter @Setter String name ;
	private @Getter @Setter String password ;
	private @Getter @Setter InputStream inputStream ;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session ;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application ;
	}
	public void validate(){
		if(!Validate.validateDept(dept) || !Validate.validateName(name) || !Validate.validatePassword(password)){
			inputStream = new ByteArrayInputStream("invalid".getBytes()) ;
		}
	}
	public String execute(){
		try {
			if(application.containsKey( dept + "_" +  name )){
				inputStream = new ByteArrayInputStream("logined".getBytes());
				return "result" ;
			}
			UserDAO userdao = new UserDAO();
			if(userdao.hasOne(dept, name, Password.getMD5(password))){
				session.put("userName",  dept + "_" + name ) ;
				inputStream = new ByteArrayInputStream("success".getBytes());
			}else{
				inputStream = new ByteArrayInputStream("notexist".getBytes());
			}
			return "result" ;
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace(); 
			return "error" ;
		} 
	}
}
