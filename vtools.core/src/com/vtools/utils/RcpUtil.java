package com.vtools.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.Bundle;

public class RcpUtil
{
	/**
	 * 获取程序的安装路径
	 * @return
	 */
	public static String getPathOfProject()
	{
		String path = "";
		Location location = Platform.getInstallLocation();
		if (location != null)
		{
			URL url = location.getURL();
			path = url.getPath();
		}
		return path;
	}
	
	public static String getPathOfPlugin(String pluginId)
	{
		String path = "";
		
		if (pluginId == null) {   
            throw new IllegalArgumentException();   
        } 
		Bundle bundle = Platform.getBundle(pluginId);
		if (bundle !=null && (bundle.getState() & (Bundle.RESOLVED | Bundle.STARTING   
                | Bundle.ACTIVE | Bundle.STOPPING)) != 0)
        {
        	try
        	{
        		URL url = FileLocator.find(bundle, new Path(File.separator), null);
        		path = FileLocator.resolve(url).getPath();
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
		
		return path;
	}
	

	
	/**
	 * 将Map 转为对象
	 * @param <T>
	 * @param paramMap
	 * @param clazz
	 * @return
	 * 
	 * User newUser = mapToBean(um, User.class);
	 */
	public static <T> T mapToBean(Map<String, Object> paramMap, Class<T> clazz)
	{
		T beanObj = null;
		try
		{
			beanObj = clazz.newInstance();
			String propertyName = null;
			Object propertyValue = null;
			for (Map.Entry<String, Object> entity : paramMap.entrySet())
			{
				propertyName = entity.getKey();
				propertyValue = entity.getValue();
				setProperties(beanObj, propertyName, propertyValue);
			}
		} catch (IllegalArgumentException e)
		{
			throw new RuntimeException("不合法或不正确的参数", e);
		} catch (IllegalAccessException e)
		{
			throw new RuntimeException("实例化JavaBean失败", e);
		} catch (Exception e)
		{
			throw new RuntimeException("Map转换为Java Bean对象失败", e);
		}
		return beanObj;
	}

	protected static <T> void setProperties(T entity, String propertyName,
			Object value) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException
	{
		PropertyDescriptor pd = new PropertyDescriptor(propertyName,
				entity.getClass());
		Method methodSet = pd.getWriteMethod();
		methodSet.invoke(entity, value);
	}
	
}
