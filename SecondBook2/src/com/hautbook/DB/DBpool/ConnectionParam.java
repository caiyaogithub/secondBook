package com.hautbook.DB.DBpool;

import java.io.Serializable;

public class ConnectionParam implements Serializable 
{ 
	 private String driver; 				 // 数据库驱动程序
	 private String url; 					 // 数据连接的 URL 
	 private String user; 					 // 数据库用户名
	 private String password; 				 // 数据库密码
	 private int minConnection = 0; 		 // 初始化连接数
	 private int maxConnection = 50; 		 // 最大连接数
	 private long timeoutValue = 600000;// 连接的最大空闲时间
	 private long waitTime = 30000; 		 // 取连接的时候如果没有可用连接最大的等待时间
	 public ConnectionParam(String driver , String url , String user , String password){
		 this.driver = driver ;
		 this.url = url ;
		 this.user = user ;
		 this.password = password ;
	 }
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMinConnection() {
		return minConnection;
	}
	public void setMinConnection(int minConnection) {
		this.minConnection = minConnection;
	}
	public int getMaxConnection() {
		return maxConnection;
	}
	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}
	public long getTimeoutValue() {
		return timeoutValue;
	}
	public void setTimeoutValue(long timeoutValue) {
		this.timeoutValue = timeoutValue;
	}
	public long getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}
	 
}