package com.hautbook.JavaBean;

import lombok.Data;

/**
 * 
 * @author caiyao 
 *
 * @function ��װ�û���Ϣ
 */
@Data
public class UserBean {
	 private String dept ;
	 private String name ;
	 private String password ;
	 private String email ;
	public UserBean(String dept, String name, String password, String email) {
		super();
		this.dept = dept;
		this.name = name;
		this.password = password;
		this.email = email;
	}
}
