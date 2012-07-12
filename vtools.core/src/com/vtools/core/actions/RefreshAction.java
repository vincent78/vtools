/*
 *@author 		:vincent
 *@create time	:2012-6-8下午2:13:00
 *descript		:
 *
 *
 */

package com.vtools.core.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class RefreshAction extends Action implements IWorkbenchWindowActionDelegate
{

	@Override
	public void run(IAction action)
	{
		System.out.println("Tesssssssssssssst");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection)
	{

	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void init(IWorkbenchWindow window)
	{

	}
	

}
