package com.vtools.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcUtil
{
	private String url;
	private String driver;
	private String userName;
	private String passwd;
	
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getDriver()
	{
		return driver;
	}
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPasswd()
	{
		return passwd;
	}
	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}

	public JdbcUtil()
	{
		driver	= "oracle.jdbc.driver.OracleDriver";
		url 	= "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		userName= "wellwin";
		passwd	= "wellwin";
	}
	
	public JdbcUtil(String driver,String url,String userName,String passwd)
	{
		this.driver		= driver;
		this.url		= url;
		this.userName	= userName;
		this.passwd		= passwd;
	}
	
	public boolean connect()
	{
		boolean retVal = false;
		try
		{
			if (!checkConnect())
			{
				Class.forName(driver);
				conn = DriverManager.getConnection(url, userName, passwd);
				//System.out.println("connect db is successful!");
			}
			retVal = true;
		}
		catch (Exception e) 
		{
			System.out.println("connect db is failed!");
			System.out.println("the message is :"+e.getMessage());
			System.out.println("system message:");
			e.printStackTrace();
		}
		return retVal;
	}
	
	public void disConnect()
	{
		
		try
		{
			if (rs != null && !rs.isClosed())
			{
				rs.close();
				rs = null;
			}
			if(stat != null && !stat.isClosed())
			{
				stat.close();
				stat = null;
			}
			if (conn != null && !conn.isClosed())
			{
				conn.close();
				conn = null;
			}
		}
		catch (Exception e) 
		{
			System.out.println("disconnect db is failed!");
			System.out.println("the message is :"+e.getMessage());
			System.out.println("system message:");
			e.printStackTrace();
		}
	}
	
	public boolean checkConnect() throws SQLException 
	{
		boolean retVal = false;
		if (conn != null && !conn.isClosed())
			retVal = true;
		return retVal;
	}
	
	public List<HashMap<String,Object>> getList(String sqlstr,HashMap<String, Object> params) throws SQLException
	{
		
		return null;
	}
	
	public List<HashMap<String,Object>> getList(String sqlstr) throws SQLException
	{
		ArrayList<HashMap<String,Object>> retList = new ArrayList<HashMap<String,Object>>();
		rs = null;
		stat = conn.createStatement();
		rs = stat.executeQuery(sqlstr);
		ResultSetMetaData rsmd = rs.getMetaData();
		int colNum = rsmd.getColumnCount();
		while(rs.next())
		{
			HashMap<String, Object> rowObj = new HashMap<String, Object>();
			for(int i=0;i<colNum;i++ )
			{
				rowObj.put(rsmd.getColumnName(i+1).toUpperCase(), rs.getString(i+1));
			}
			retList.add(rowObj);
		}
		return retList;
	}
	
	public int execute(String sqlstr) throws SQLException
	{
		int retVal = -1;
		stat = conn.createStatement();
		retVal = stat.executeUpdate(sqlstr);
		return retVal;
	}
	
	public static void main(String[] arg0)
	{
		System.out.println("BEGIN!");
	}
}
