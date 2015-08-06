package com.hautbook.DB.DBpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.Throwable;

/** 
 * 数据连接的自封装，屏蔽了 close 方法
 * @author Liudong 
 */ 
 class _Connection implements InvocationHandler 
 { 
     private final static String CLOSE_METHOD_NAME = "close"; 
     private Connection conn = null; 
     // 数据库的空闲状态
     private boolean inUse = false; 
     // 用户最后一次访问该连接方法的时间
     private long lastAccessTime = System.currentTimeMillis(); 
    
     _Connection(Connection conn, boolean inUse){ 
         this.conn = conn; 
         this.inUse = inUse; 
     } 
     /** 
     * Returns the conn. 
     * @return Connection 
     */ 
     public Connection getConnection() { 
         // 返回数据库连接 conn 的接管类，以便截住 close 方法
         Connection conn2 = (Connection)Proxy.newProxyInstance( 
             conn.getClass().getClassLoader(), 
             new Class[]{Connection.class},this); 
         return conn2; 
     } 
     /** 
     * 该方法真正的关闭了数据库的连接
     * @throws SQLException 
     */ 
     void close() throws SQLException{ 
        // 由于类属性 conn 是没有被接管的连接，因此一旦调用 close 方法后就直接关闭连接
        conn.close(); 
     } 
     /** 
     * Returns the inUse. 
     * @return boolean 
     */ 
     public boolean isInUse() { 
         return inUse; 
     } 
     /** 
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, 
     * java.lang.reflect.Method, java.lang.Object) 
     */ 
     public Object invoke(Object proxy, Method m, Object[] args) 
         //throws java.lang.Throwable 
     { 
         Object obj = null; 
         // 判断是否调用了 close 的方法，如果调用 close 方法则把连接置为可用状态
         if(CLOSE_METHOD_NAME.equals(m.getName())) {
        	 System.out.println("关闭连接！");
        	  setInUse(false);
         } 
		else
			try {
				obj = m.invoke(conn, args);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}     
         // 设置最后一次访问时间，以便及时清除超时的连接
         lastAccessTime = System.currentTimeMillis(); 
         return obj; 
     } 
        
     /** 
     * Returns the lastAccessTime. 
     * @return long 
     */ 
     public long getLastAccessTime() { 
         return lastAccessTime; 
     } 
     /** 
     * Sets the inUse. 
     * @param inUse The inUse to set 
     */ 
     public void setInUse(boolean inUse) { 
         this.inUse = inUse; 
     } 
}