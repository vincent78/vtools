/*
 *@author 		:vincent
 *@create time	:2012-3-9下午4:27:28
 *descript		:
 *
 *
 */

package com.vtools.core.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.vtools.core.Constant;

public class DbInfoService
{
	/**
	 * 获取所有的数据库连接信息
	 * @return
	 */
	public static List<HashMap<String, Object>> getDbInfoList()
	{	
		List<HashMap<String, Object>> retList = null;
		Constant.util.connect();
		try
		{
			retList = Constant.util.getList("select * from dbinfo order by seq,name");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
		
		return retList;
	}
	
	/**
	 * 按名称获取数据库的连接信息 
	 * @param name
	 * @return
	 */
	public static HashMap<String, Object> getDbInfoByName(String name)
	{	
		List<HashMap<String, Object>> retList = null;
		Constant.util.connect();
		try
		{
			retList = Constant.util.getList("select * from dbinfo where name = '"+name+"'");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
		if (retList == null || retList.size() == 0)
			return null;
		else
			return retList.get(0);
	}
	
	
	public static void insert(String name,int seq,int type,String url,String userName,String passwd,String remark)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into dbinfo(name,seq,db_type,url,username,passwd,remark) values ");
		sb.append("('").append(name).append("',").append(seq).append(",").append(type);
		sb.append(",'").append(url).append("','").append(userName).append("','").append(passwd);
		sb.append("','").append(remark).append("')");
		Constant.util.connect();
		try
		{
			Constant.util.execute(sb.toString());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
	}
	
	public static void update(String name,int seq,int type,String url,String userName,String passwd,String remark)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("update dbinfo set ");
		sb.append(" seq = ").append(seq);
		sb.append(" ,db_type = ").append(type);
		sb.append(" ,userName = '").append(userName).append("'");
		sb.append(" ,passwd = '").append(passwd).append("'");
		sb.append(" ,remark = '").append(remark).append("'");
		sb.append(" where name = '").append(name).append("'");
		Constant.util.connect();
		try
		{
			Constant.util.execute(sb.toString());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
	}
	
	public static void delete(String name)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("delete from  dbinfo ");
		sb.append(" where name = '").append(name).append("'");
		Constant.util.connect();
		try
		{
			Constant.util.execute(sb.toString());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
	}
}
