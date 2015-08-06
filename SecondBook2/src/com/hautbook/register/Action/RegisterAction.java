package com.hautbook.register.Action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.hautbook.DB.UserDAO;
import com.hautbook.JavaBean.UserBean;
import com.hautbook.util.Password;
import com.hautbook.util.Validate;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 
 * @author caiyao
 *
 * @function : 会员注册
 */
public class RegisterAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private @Getter @Setter String dept ;//学院
	private @Getter @Setter String name ;
	private @Getter @Setter String password ;
	private @Getter @Setter String repassword ;
	private @Getter @Setter String email ;
	private @Getter @Setter String captcha ;
	private Map<String , Object> session  ;
	private @Getter @Setter InputStream checkResult ;
	private boolean validateSuccess = true ; // 标识是否校验成功
	public void validate(){
			checkResult = new ByteArrayInputStream("success".getBytes());
			if(!Validate.validateDept(dept) 
			|| !Validate.validateName(name)
			|| !validatePassword() 
			|| !Validate.validateEmail(email) 
			|| !validateCaptcha()){
				checkResult = new ByteArrayInputStream("invalid".getBytes()) ;
				validateSuccess = false ;
				return ;
			}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session ;
	}
	public String execute() {
		try{
			// 判断数据库中是否已经存在该用户
				if(!validateSuccess)return "result" ;
				
				UserDAO userdao = new UserDAO() ;
				if(userdao.hasOne(dept, name)){
					checkResult = new ByteArrayInputStream("exist".getBytes("UTF-8")) ;
					return "result" ;
				}
				
				if(userdao.addUser(new UserBean(dept , name , Password.getMD5(password) , email))){
					return "tologin" ;
				}else{
				    throw new SQLException();
				}
		}catch(SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e ){
					checkResult = new ByteArrayInputStream("error".getBytes()) ;
					return "result" ;
		}
	}
	/**
	 * 校验验证码是否和session中的相同
	 * @return
	 */
	private boolean validateCaptcha(){
		String catpchaString = session.get("captchaString").toString() ;
		session.remove("captchaString") ;// 校验完后就移除这个session值，使该验证码失效
		return this.captcha.trim().toLowerCase().equals(catpchaString) ;
	}
	/**
	 * 校验密码
	 * 
	 * @return
	 */
	private boolean validatePassword() {
		password = password.trim() ;
		repassword = repassword.trim() ;
		if (Validate.validatePassword(password) && password.equals(repassword)) {
			return true;
		}
		return false;
	}
}
