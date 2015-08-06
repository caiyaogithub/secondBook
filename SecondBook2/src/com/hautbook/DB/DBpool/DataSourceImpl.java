package com.hautbook.DB.DBpool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DataSourceImpl implements DataSource{
	private ConnectionParam connParam ;
	private static ArrayList<_Connection> conns = new ArrayList<_Connection>();
	public DataSourceImpl(ConnectionParam connParam){
		this.connParam = connParam ;
	}
	/**
	 * 获取真实的数据库连接
	 * @return 如果正确获取则返回Connection对象，否者返回null
	 */
	private Connection getRealConnection(){
		Connection conn3 = null ;
		try {
			Class.forName(connParam.getDriver()) ;
			conn3 = DriverManager.getConnection(connParam.getUrl(), connParam.getUser(), connParam.getPassword()) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn3 ;
	}
	public void initConnection(){
		// 最小连接数
		for(int i = 0 ; i < connParam.getMinConnection()  ; i ++){
			_Connection conn = new _Connection(getRealConnection(),false) ;
			conns.add(conn) ;
		}
	}
	public void stop(){
		/**
		 * TODO : 停用是什么意思？
		 */
	}
	public void close() throws SQLException{
		 Iterator iter = conns.iterator(); 
         while(iter.hasNext()){ 
             _Connection _conn = (_Connection)iter.next(); 
             	_conn.close() ;
         }
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return null;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// 首先从连接池中找出空闲的对象
        Connection conn = getFreeConnection(0); 
        if(conn == null){ 
            // 判断是否超过最大连接数 , 如果超过最大连接数
            // 则等待一定时间查看是否有空闲连接 , 否则抛出异常告诉用户无可用连接
            if(getConnectionCount() >= connParam.getMaxConnection()) 
                conn = getFreeConnection(connParam.getWaitTime()); 
            else{// 没有超过连接数，重新获取一个数据库的连接
                connParam.setUser(username); 
                connParam.setPassword(password); 
                try {
					Class.forName(connParam.getDriver()) ;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
                Connection conn2 = DriverManager.getConnection(connParam.getUrl(),
                		username, password); 
                // 代理将要返回的连接对象
                _Connection _conn = new _Connection(conn2,true); 
                synchronized(conns){ 
                    conns.add(_conn); 
                } 
                conn = _conn.getConnection(); 
            } 
        } 
        return conn; 
	}
	/**
	 * 获取连接池正在连接的连接数
	 * @return
	 */
	public int getConnectionCount(){
		int connCount = 0;
		 Iterator iter = conns.iterator(); 
         while(iter.hasNext()){ 
             _Connection _conn = (_Connection)iter.next(); 
             if(_conn.isInUse()){ 
                   connCount ++ ;        
             } 
         }
         return connCount ;
	}
	 /** 
     * 从连接池中取一个空闲的连接
     * @param nTimeout     如果该参数值为 0 则没有连接时只是返回一个 null
     * 否则的话等待 nTimeout 毫秒看是否还有空闲连接，如果没有抛出异常
     * @return Connection 
     * @throws SQLException 
     */ 
     protected synchronized Connection getFreeConnection(long nTimeout)
         throws SQLException 
     { 
         Connection conn = null; 
         Iterator iter = conns.iterator(); 
         while(iter.hasNext()){ 
             _Connection _conn = (_Connection)iter.next(); 
             if(!_conn.isInUse()){ 
                 conn = _conn.getConnection(); 
                 _conn.setInUse(true);    
                 System.out.println("获取连接！");
                 return conn ; 
             }
         } 
         if(conn == null && nTimeout > 0){ 
             // 等待 nTimeout 毫秒以便看是否有空闲连接
             try{ 
                 Thread.sleep(nTimeout); 
             }catch(Exception e){
            	 System.out.println("数据库连接线程等待出现错误！");
             } 
             conn = getFreeConnection(0); 
             if(conn == null) 
                 throw new SQLException("没有可用的数据库连接"); 
         } 
         return conn; 
     }

}
