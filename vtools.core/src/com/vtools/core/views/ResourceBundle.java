/*
 *@author 		:vincent
 *@create time	:2012-5-29下午4:40:11
 *descript		:
 *
 *
 */

package com.vtools.core.views;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class ResourceBundle
{
	static final int ciClosedFolder = 0, ciOpenFolder = 1,ciTarget = 2;
	static final String[] imageLocations =
	{ "closedFolder.gif", //$NON-NLS-1$
	  "openFolder.gif",	
			"target.png" }; //$NON-NLS-1$
	static final int[] imageTypes =
	{ SWT.ICON, SWT.ICON, SWT.ICON };
	static Image images[];
	
	

	static void initResources()
	{
		@SuppressWarnings("rawtypes")
		final Class clazz = CodeTemplateView.class;
		try
		{
			if (images == null)
			{
				images = new Image[imageLocations.length];

				for (int i = 0; i < imageLocations.length; ++i)
				{
					InputStream sourceStream = clazz
							.getResourceAsStream(imageLocations[i]);
					ImageData source = new ImageData(sourceStream);
					if (imageTypes[i] == SWT.ICON)
					{
						ImageData mask = source.getTransparencyMask();
						images[i] = new Image(null, source, mask);
					}
					else
					{
						images[i] = new Image(null, source);
					}
					try
					{
						sourceStream.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			return;
		}
		catch (Throwable t)
		{
		}
		
		freeResources();
	}

	static void freeResources()
	{
		if (images != null)
		{
			for (int i = 0; i < images.length; ++i)
			{
				final Image image = images[i];
				if (image != null)
					image.dispose();
			}
			images = null;
		}
	}
	
	public void dispose()
	{
		freeResources();
	}

}
