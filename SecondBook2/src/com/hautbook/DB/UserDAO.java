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
 * @function ִ���й�user��Ĳ���
 */
public class UserDAO {
	private Connection conn ;
	private CallableStatement call = null ;
	private ResultSet rs = null ;
	public UserDAO() {
		
	}
	/**
	 * ����û�
	 * @param dept ѧԺ
	 * @param name �ǳ�
	 * @param password ����
	 * @param email ��������
	 * @return ���û�г������ݿ�����򷵻�true�����߷���false
	 * @throws SQLException ����������ݿ����
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
	 * ����ѧԺ���ǳƺ������ѯ�������Ƿ���ڸ��û������ڵ�½
	 * @param dept ѧԺ
	 * @param name �ǳ�
	 * @param password ����
	 * @return ���ڷ���true �� ���򷵻�false
	 * @throws SQLException ����������ݿ����
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
	 * ����ѧԺ���ǳƺ������ѯ�������Ƿ���ڸ��û������ڵ�½
	 * @param dept ѧԺ
	 * @param name �ǳ�
	 * @param password ����
	 * @return ���ڷ���true �� ���򷵻�false
	 * @throws SQLException ����������ݿ����
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
