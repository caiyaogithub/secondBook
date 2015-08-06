package com.hautbook.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hautbook.DB.DBpool.DBconnManager;
import com.hautbook.JavaBean.UserBean;

/**
 * 
 * @author caiyao
 *
 * @function 执行有关user表的操作
 */
public class UserDAO {
	private Connection conn ;
	private CallableStatement call = null ;
	private ResultSet rs = null ;
	public UserDAO() {
		
	}
	/**
	 * 添加用户
	 * @param dept 学院
	 * @param name 昵称
	 * @param password 密码
	 * @param email 电子邮箱
	 * @return 如果没有出现数据库错误则返回true，否者返回false
	 * @throws SQLException 如果发生数据库错误
	 */
	public boolean addUser(UserBean user) throws SQLException{
		conn = DBconnManager.getUserConnection() ;
		String sql = "call InsertUser(?,?,?,?)" ;
		try{
			call = conn.prepareCall(sql) ;
			call.setString(1,user.getDept());
			call.setString(2,user.getName());
			call.setString(3,user.getPassword());
			call.setString(4,user.getEmail());
			return (call.executeUpdate() == 1) ? true : false ;
		}finally{
				if(call != null)call.close() ;
				if(conn != null)conn.close() ;
		}
	}
	/**
	 * 根据学院、昵称和密码查询数据中是否存在该用户，用于登陆
	 * @param dept 学院
	 * @param name 昵称
	 * @param password 密码
	 * @return 存在返回true ， 否则返回false
	 * @throws SQLException 如果出现数据库错误
	 */
	public boolean hasOne(String dept , String name , String password) throws SQLException{
		conn = DBconnManager.getUserConnection() ;
		String sql = "call SelectUserByNameAndPassword(?,?,?)" ;
		try{
			call = conn.prepareCall(sql) ;
			call.setString(1, dept) ;
			call.setString(2, name) ;
			call.setString(3, password) ;
			rs = call.executeQuery() ;
			return rs.next() ;
		}finally{
			if(rs != null)rs.close() ;
			if(call != null)call.close() ;
			if(conn != null)conn.close() ;
		}
	}
	
	/**
	 * 根据学院、昵称和密码查询数据中是否存在该用户，用于登陆
	 * @param dept 学院
	 * @param name 昵称
	 * @param password 密码
	 * @return 存在返回true ， 否则返回false
	 * @throws SQLException 如果出现数据库错误
	 */
	public boolean hasOne(String dept , String name ) throws SQLException{
		conn = DBconnManager.getUserConnection() ;
		String sql = "call SelectUserByName(?,?)" ;
		try{
			call = conn.prepareCall(sql) ;
			call.setString(1, dept) ;
			call.setString(2, name) ;
			rs = call.executeQuery() ;
			return rs.next() ;
		}finally{
			if(rs != null)rs.close() ;
			if(call != null)call.close() ;
			if(conn != null)conn.close() ;
		}
	}
}
