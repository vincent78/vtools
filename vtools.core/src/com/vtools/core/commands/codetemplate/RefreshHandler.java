/*
 *@author 		:vincent
 *@create time	:2012-9-10下午2:59:59
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

import com.vtools.core.views.CodeTemplateView;

public class RefreshHandler implements IHandler
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
		view.getTreeView().setInput(view.getData(null));
		//MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "测试", "测试command");
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
