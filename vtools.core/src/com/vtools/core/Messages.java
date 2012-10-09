/*
 *@author 		:vincent
 *@create time	:2012-10-9下午2:05:34
 *descript		:
 *
 *
 */

package com.vtools.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.vtools.core.messages";
	
	static 
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String MessageDialog_Title_Warn;
	public static String MessageDialog_Title_Error;
	public static String MessageDialog_Title_Prompt;
	
	public static String Vtools_Core_Template_Create;
}
