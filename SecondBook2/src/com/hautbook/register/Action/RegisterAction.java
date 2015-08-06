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
 * @function : ��Աע��
 */
public class RegisterAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private @Getter @Setter String dept ;//ѧԺ
	private @Getter @Setter String name ;
	private @Getter @Setter String password ;
	private @Getter @Setter String repassword ;
	private @Getter @Setter String email ;
	private @Getter @Setter String captcha ;
	private Map<String , Object> session  ;
	private @Getter @Setter InputStream checkResult ;
	private boolean validateSuccess = true ; // ��ʶ�Ƿ�У��ɹ�
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
			// �ж����ݿ����Ƿ��Ѿ����ڸ��û�
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
	 * У����֤���Ƿ��session�е���ͬ
	 * @return
	 */
	private boolean validateCaptcha(){
		String catpchaString = session.get("captchaString").toString() ;
		session.remove("captchaString") ;// У�������Ƴ����sessionֵ��ʹ����֤��ʧЧ
		return this.captcha.trim().toLowerCase().equals(catpchaString) ;
	}
	/**
	 * У������
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
