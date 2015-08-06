package com.hautbook.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;

import com.hautbook.DB.DBpool.DBconnManager;
import com.hautbook.JavaBean.SimplePostBean;

/**
 * 
 * @author caiyao
 *
 * @function 执行对帖子数据表（SellPost，BuyPost）的操作
 * 
 */
public class PostDAO {
	private Connection conn ;
	private CallableStatement call = null ;
	private ResultSet rs = null;
	/**作为查询后的返回值*/
	public ArrayList<SimplePostBean> posts = new ArrayList<SimplePostBean>() ; 
	
	public PostDAO(){
		conn = DBconnManager.getUserConnection() ;
	}
	/**
	 * 查询多帖子
	 * @param type : 查询类型 ：buy(买)　、　sell(卖)
	 * @param bookName : 书名关键字
	 * @return ： ArrayList<SimplePostBean>类型的查询结果
	 * @throws NameNotFoundException 
	 * @throws SQLException
	 */
	public ArrayList<SimplePostBean> selectPost(String type , String bookName) throws SQLException{
		String sql = "" ;
		if(type.equals("buy")){
			sql = "{call SelectBuyByName(?)}" ;
		}else if(type.equals("sell")){
			sql = "{call SelectSellByName(?)}" ;
		}else{
			// 如果传递过来的参数不是buy和sell中的一个。
			return null ;
		}
		try{
			call = conn.prepareCall(sql) ;
			call.setString(1, bookName) ;
			rs = call.executeQuery() ;
			ArrayList<SimplePostBean> result = new ArrayList<SimplePostBean>();
			result =  rsToList(rs) ;
			return result ;
		    }finally{
		    	/**确保数据库连接关闭*/
		    		if(rs != null){
		    			rs.close() ;
		    		}
		    		if(call != null){
		    			rs.close() ;
		    		}
		    		if(conn != null){
		    			rs.close() ;
		    		}
		    }
	}
	/**
	 * 将多帖子查询结果对象转化成ArrayList<SimplePostBean>对象
	 * @param rs
	 * @return : 
	 */
	public ArrayList<SimplePostBean> rsToList(ResultSet rs){
		ArrayList<SimplePostBean> posts = new ArrayList<SimplePostBean>() ;
		try {
			while(rs.next()){
				SimplePostBean post = new SimplePostBean() ;
				post.setName(rs.getString("bookName")) ;
				post.setDesc(rs.getString("descr")) ;
				post.setPrice(rs.getInt("price")) ;
				posts.add(post) ;
			}
			return posts ;
		} catch (SQLException e) {
			e.printStackTrace() ;
			return null ;
		}
	}
}
