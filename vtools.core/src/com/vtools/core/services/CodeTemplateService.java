/*
 *@author 		:vincent
 *@create time	:2012-5-29下午3:54:09
 *descript		:
 *
 *
 */

package com.vtools.core.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.vtools.core.Constant;
import com.vtools.core.beans.CodeTemplateTreeNode;
import com.vtools.core.editors.BasicEditorInput;
import com.vtools.core.editors.BasicEditorPart;
import com.vtools.utils.ObjectUtil;

public class CodeTemplateService
{
	public static List<HashMap<String, Object>> getTemplateByList()
	{	
		List<HashMap<String, Object>> retList = null;
		Constant.util.connect();
		try
		{
			retList = Constant.util.getList("select * from template order by name");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		Constant.util.disConnect();
		
		return retList;
	}
	
	public static List<CodeTemplateTreeNode> getTemplateByObj()
	{
		List<HashMap<String, Object>> retList = getTemplateByList();
		List<CodeTemplateTreeNode> nodeList = new ArrayList<CodeTemplateTreeNode>();
		if (retList==null)
		{
			return null;
		}
		for(int i=0;i<retList.size();i++)
		{
			HashMap<String, Object> tmp = retList.get(i);
			nodeList.add(ObjectUtil.mapToBean(tmp, CodeTemplateTreeNode.class));
		}
		return nodeList;
	}
	
	public static void openTheFile(String path)
	{
		IWorkbenchPage page=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		BasicEditorInput input = new BasicEditorInput(path);
		try
		{
			page.openEditor(input, BasicEditorPart.ID, true);
		}
		catch (PartInitException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
