package com.hautbook.DB.DBpool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

/**
 * 
 * @author caiyao
 *
 * @function 执行数据库连接池的初始化和获取数据库连接对象Connection
 */
public class DBconnManager {
	private static String name ;
	private static String driver ;
	private static String url ;
	static {
		name = "pool";
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/secondbook";
		ConnectionParam param = new ConnectionParam(driver, url, null, null);
		/**设置最小连接数*/
		param.setMinConnection(1);
		/**设置最大连接数*/
		param.setMaxConnection(10);
		/**当缓存池中没有空闲连接的时候，定义最长等待时间*/
		param.setTimeoutValue(20000);
		/**设置用户名*/
		param.setUser("secondbook_user") ;
		/**密码*/
		param.setPassword("user") ;
		try {
			ConnectionFactory.bind(name, param);
		} catch (NameAlreadyBoundException e) {
			e.printStackTrace() ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace() ;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getUserConnection(){
		try{
			DataSourceImpl ds = (DataSourceImpl) ConnectionFactory.lookup("pool");
			Connection conn = ds.getConnection("secondbook_user","user");
			return conn ;
		}catch(SQLException e){
			e.printStackTrace() ;
			return null ;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null ;
		}
		
		
	}
	public static Connection getAdminConnection(){
		/**
		 * TODO : 获取管理员数据库连接
		 */
		return null ;
	}
}
