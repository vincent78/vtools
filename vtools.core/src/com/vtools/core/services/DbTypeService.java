package com.vtools.core.services;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import com.vtools.core.Constant;
public class DbTypeService
{
	public static List<HashMap<String, Object>> getDbType()
	{	
		List<HashMap<String, Object>> retList = null;
		Constant.util.connect();
		try
		{
			retList = Constant.util.getList("select * from dbtype order by id");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
		
		return retList;
	}
}
