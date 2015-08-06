package com.hautbook.DB.DBpool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

/**
 * 
 * @author caiyao
 *
 * @function ִ�����ݿ����ӳصĳ�ʼ���ͻ�ȡ���ݿ����Ӷ���Connection
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
		/**������С������*/
		param.setMinConnection(1);
		/**�������������*/
		param.setMaxConnection(10);
		/**���������û�п������ӵ�ʱ�򣬶�����ȴ�ʱ��*/
		param.setTimeoutValue(20000);
		/**�����û���*/
		param.setUser("secondbook_user") ;
		/**����*/
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
		 * TODO : ��ȡ����Ա���ݿ�����
		 */
		return null ;
	}
}
