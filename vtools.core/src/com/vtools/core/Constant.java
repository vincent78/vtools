package com.vtools.core;

import vtools.core.Activator;

import com.vtools.utils.JdbcUtil;
import com.vtools.utils.RcpUtil;

public class Constant
{
	public static JdbcUtil util ;
	
	static 
	{
		util = new JdbcUtil();
		String path = RcpUtil.getPathOfPlugin(Activator.getDefault().getBundle().getSymbolicName());
		util.setUrl("jdbc:hsqldb:file:"+path+"\\db\\CONFDB;ifexists=true");
		
		util.setDriver("org.hsqldb.jdbc.JDBCDriver");
		util.setUserName("vtools");
		util.setPasswd("vtools");
	}
	
	public static final int ADD = 1;
	public static final int MOD = 2;
	public static final int DEL = 3;
	
	
	
}
