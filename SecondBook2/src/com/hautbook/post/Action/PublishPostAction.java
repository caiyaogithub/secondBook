package com.hautbook.post.Action;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.apache.struts2.interceptor.SessionAware;

import com.hautbook.JavaBean.PostBean;
import com.hautbook.exception.ValidateException;
import com.hautbook.util.OrderNumGenerator;

/**
 * 
 * @author caiyao
 *
 * @function : 会员的功能，用于发布信息。
 * 
 */
@Data
public class PublishPostAction implements SessionAware{
	 private String type ;
	 private String bookName ;
	 private String author ;
	 private String poster ;
	 private String isbn ;
	 private String category ;
	 private String desc ;
	 private float price ;
	 private String linkmanName ;
	 private String linkmanTel ;
	 private InputStream result ;
	 
	 private Map<String , Object> session ;
	 // 用户名（学院+昵称）从session中获取
	public void validate(){
		// 从session中获取userName
		String userName = session.get("userName").toString() ;
		System.out.println("session ： " + userName) ;
		// 自动生成id
		String id = OrderNumGenerator.getOrderNum(type) ;
		System.out.println("获取的id为：  " + id );
		PostBean post = new PostBean( userName.trim(),  id.trim(),  type.trim(),  bookName.trim(),
							      author.trim(),  poster.trim(),  isbn.trim(),  category.trim(),
								  desc.trim(),  price,  linkmanName.trim(),  
								  linkmanTel.trim(),"Y") ;
		List<ValidateException> validateExcept = validatePost(post) ; 
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session ;
	}
	
}
