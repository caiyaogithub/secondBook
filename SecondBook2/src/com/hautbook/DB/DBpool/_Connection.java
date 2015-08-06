package com.hautbook.DB.DBpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.Throwable;

/** 
 * �������ӵ��Է�װ�������� close ����
 * @author Liudong 
 */ 
 class _Connection implements InvocationHandler 
 { 
     private final static String CLOSE_METHOD_NAME = "close"; 
     private Connection conn = null; 
     // ���ݿ�Ŀ���״̬
     private boolean inUse = false; 
     // �û����һ�η��ʸ����ӷ�����ʱ��
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
         // �������ݿ����� conn �Ľӹ��࣬�Ա��ס close ����
         Connection conn2 = (Connection)Proxy.newProxyInstance( 
             conn.getClass().getClassLoader(), 
             new Class[]{Connection.class},this); 
         return conn2; 
     } 
     /** 
     * �÷��������Ĺر������ݿ������
     * @throws SQLException 
     */ 
     void close() throws SQLException{ 
        // ���������� conn ��û�б��ӹܵ����ӣ����һ������ close �������ֱ�ӹر�����
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
         // �ж��Ƿ������ close �ķ������������ close �������������Ϊ����״̬
         if(CLOSE_METHOD_NAME.equals(m.getName())) {
        	 System.out.println("�ر����ӣ�");
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
         // �������һ�η���ʱ�䣬�Ա㼰ʱ�����ʱ������
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