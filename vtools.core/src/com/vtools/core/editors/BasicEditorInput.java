/*
 *@author 		:vincent
 *@create time	:2012-8-21下午4:09:28
 *descript		:
 *
 *
 */

package com.vtools.core.editors;

import java.io.File;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class BasicEditorInput implements IEditorInput
{
	private String path;
	
	public BasicEditorInput(String path)
	{
		this.path = path;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter)
	{
		return null;
	}

	@Override
	public boolean exists()
	{
		return new File(path).exists();
	}

	@Override
	public ImageDescriptor getImageDescriptor()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return path;
	}

	@Override
	public IPersistableElement getPersistable()
	{
		return null;
	}

	@Override
	public String getToolTipText()
	{
		return path;
	}
	
	public boolean equals(Object obj){
		if(super.equals(obj)){
			return true;
		}if(obj instanceof BasicEditorInput){
			return path.equals(((BasicEditorInput)obj).getName());
		}
		return false;
	}
	public int hashCode(){
		return path.hashCode();
	}

}
