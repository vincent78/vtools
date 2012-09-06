package com.vtools.utils;

import java.io.File;
import java.net.URL;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
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
	
	
	public static void ShowMsg(String msg,String title)
	{
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(), title, msg);
	}

	

	
}
