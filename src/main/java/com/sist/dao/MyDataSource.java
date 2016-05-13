package com.sist.dao;
import java.sql.*;
/*
 *  class A
 *  {
 *     int age;
 *     public A()
 *     public void setAge(int age)
 *     {
 *        this,age=age;
 *     }
 *  }
 *  A a=new A();
 *  a.setAge(10)
 */
public class MyDataSource {
    private Connection conn;
    private String driver;
    private String url;
    private String username;
    private String password;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getConn() {
		return conn;
	}
	// 생성자  ==> setter ==> init => 사용자 활용 ==> destory DI(객체 생명주기)
	// 드라이버 등록 
	public void init()
	{
		try
		{
			Class.forName(driver);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	// 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(url,username,password);
					
		}catch(Exception ex){}
	}
	// 해제 
	public void disConnection()
	{
	   try
	   {
		   if(conn!=null) conn.close();
	   }catch(Exception ex){}
	}
	   
}





