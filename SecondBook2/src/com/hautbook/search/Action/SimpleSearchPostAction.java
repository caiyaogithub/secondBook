package com.hautbook.search.Action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.hautbook.DB.PostDAO;
import com.hautbook.JavaBean.SimplePostBean;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author caiyao
 *
 * @function 查询条件为分类、书名关键字的搜索
 */
public class SimpleSearchPostAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String type ;
	private String bookName ;
	private HttpServletRequest request ;
	private String message = ERROR ;
	private ArrayList<SimplePostBean> posts = new ArrayList<SimplePostBean>() ;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request ;
	}
	public void validate(){
		/**
		 * TODO : 暂时不对数据进行校验
		 */
	}
	public String execute(){
			PostDAO postdao = new PostDAO() ;
				try {
					posts = postdao.selectPost(type, bookName) ;
				} catch (SQLException e) {
					message = ERROR ;
				}
			message = type ;
		/**将查询后的结果放进request对象中*/
		request.setAttribute("posts", posts) ;
		return message ;
	}
	
}
