package com.vtools.core.beans;

import java.util.Date;


public class DbType
{
	private int id;
	private String name;
	private String desc;
	private String driver;
	private String sys_Flag;
	private int create_User;
	private Date create_Time;
	private int update_User;
	private Date update_Time;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getDriver()
	{
		return driver;
	}
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	public String getSys_Flag()
	{
		return sys_Flag;
	}
	public void setSys_Flag(String sys_Flag)
	{
		this.sys_Flag = sys_Flag;
	}
	public int getCreate_User()
	{
		return create_User;
	}
	public void setCreate_User(int create_User)
	{
		this.create_User = create_User;
	}
	public Date getCreate_Time()
	{
		return create_Time;
	}
	public void setCreate_Time(Date create_Time)
	{
		this.create_Time = create_Time;
	}
	public int getUpdate_User()
	{
		return update_User;
	}
	public void setUpdate_User(int update_User)
	{
		this.update_User = update_User;
	}
	public Date getUpdate_Time()
	{
		return update_Time;
	}
	public void setUpdate_Time(Date update_Time)
	{
		this.update_Time = update_Time;
	}
	
	
	
}
