/*
 *@author 		:vincent
 *@create time	:2012-9-14上午10:18:13
 *descript		:
 *
 *
 */

package com.vtools.core.commands.codetemplate;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.handlers.HandlerUtil;

import com.vtools.core.beans.CodeTemplateTreeNode;
import com.vtools.core.views.CodeTemplateView;

public class GenerateHandle implements IHandler
{

	@Override
	public void addHandlerListener(IHandlerListener handlerListener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		CodeTemplateView view = (CodeTemplateView)HandlerUtil.getActivePartChecked(event);
		Object[] nodes =  view.getTreeView().getCheckedElements();
		
		for(int i=0;i<nodes.length;i++)
		{
			CodeTemplateTreeNode node = (CodeTemplateTreeNode)nodes[i];
			if (node.getChildren() == null)
			{
				System.out.println(node.getPath());
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener)
	{
		// TODO Auto-generated method stub

	}

}
