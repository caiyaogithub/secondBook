package com.hautbook.JavaBean;

import lombok.Data;

/**
 * 
 * @author caiyao 
 *
 * @function 封装用户信息
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
