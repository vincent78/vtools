/*
 * author 		:vincent
 * DateTime	:2012-1-17  下午8:21:11
 * Description:
 * 
 */

package com.vtools.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ObjectUtil
{
	/**
	 * 打印出对象的信息
	 * 
	 * @param obj
	 */
	public static void printObj(Object obj)
	{
		Class<?> targetClass = obj.getClass();
		System.out.println(targetClass.getName());
		Field[] fields = targetClass.getDeclaredFields();
		try
		{
			for (int i = 0; i < fields.length; i++)
			{
				String fieldName = fields[i].getName();
				System.out.println(fieldName
						+ " : "
						+ StringUtil.nullValue(targetClass
								.getDeclaredField(fieldName)));
			}
		} catch (Exception e)
		{
			System.out.println("ObjectUtil printObj is error");
		}
	}

	/**
	 * 将Map 转为对象
	 * 
	 * @param <T>
	 * @param paramMap
	 * @param clazz
	 * @return
	 * 
	 *         User newUser = mapToBean(um, User.class);
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
		//String propertyNameTmp = "";
		for(int i=0;i<entity.getClass().getDeclaredFields().length;i++)
		{
			if (entity.getClass().getDeclaredFields()[i].getName().equalsIgnoreCase(propertyName))
			{
				propertyName = entity.getClass().getDeclaredFields()[i].getName();
			}
		}
		PropertyDescriptor pd = new PropertyDescriptor(propertyName,
		entity.getClass());
		Method methodSet = pd.getWriteMethod();
		methodSet.invoke(entity, value);
	}
}
