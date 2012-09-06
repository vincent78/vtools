/*
 *@author 		:vincent
 *@create time	:2012-9-5下午2:00:50
 *descript		:
 *
 *
 */

package com.vtools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexUtil
{
	//get the standard property , can split to more line
	private static final String propertyStr = "(?<=<#--property:)[\\s\\S]*?(?=-->)";
	//get the properties name array
	@SuppressWarnings("unused")
	private static final String propertiesNameStr = "\\s\\w+\\s*(?==)";
	
	//get the properties value array
	private static final String propertiesValueStr = "(?<=(=(['\"])))[\\s\\S].*?(?=\\2)";
	
	//format the properties Str  (drop the spaces which arround with =)
	private static final String propertiesFormatStr = "\\s*?=\\s*[^'\"]";
	
	
	public static void main(String[] args)
	{
		//getPropertyStr();
		StringBuilder source = new StringBuilder();
		source.append("  proper1=\"value1\"  proper2 = \'va\"l\"ue2\' \n proper3=\"value3\"");
		
		getAllProperties(source.toString(),propertiesValueStr);
	}
	
	protected  static  void getPropertyStr()
	{
		//get the standard property , can split to more line
		//String partternStr = "(?<=<#--property:)[\\s\\S]*?(?=-->)";
			
		StringBuilder source = new StringBuilder();
		source.append("<#--  adf adf2sfsdf -->");
		source.append("sdf<#--property:  112adf adf2sfsdf -->");
		source.append("\n<#--property:  112adf \n adf2sfsdff2234 -->sdfad2");
		source.append("I'm singing while you're dancing.");
		
		
		Pattern patten = Pattern.compile(propertyStr);
		Matcher matcher = patten.matcher(source);
		
		System.out.println("begin");
		int i=0;
		while(matcher.find())
		{
			System.out.println("line"+String.valueOf(i+1)+":");
			System.out.println(matcher.group());
			i++;
		}
		System.out.println("end");
	
	}

	protected static void getAllProperties(String sourceStr,String patternStr)
	{
		System.out.println("sourceStr:"+sourceStr);
		sourceStr = replace(sourceStr,propertiesFormatStr,"=");
		Pattern patten = Pattern.compile(patternStr);
		Matcher matcher = patten.matcher(sourceStr);
		int i=0;
		while(matcher.find())
		{
			System.out.println("result"+String.valueOf(i+1)+":"+matcher.group());
			i++;
		}
	}
	
	/**
	 * replace the source 
	 * @param sourceStr
	 * @param patternStr
	 * @param replaceStr
	 * @return
	 */
	protected static String replace(String sourceStr,String patternStr,String replaceStr)
	{
		Pattern patten = Pattern.compile(patternStr);
		Matcher matcher = patten.matcher(sourceStr);
		return matcher.replaceAll(replaceStr);
	}

	
}
