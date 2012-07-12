/*
 *@author 		:vincent
 *@create time	:2012-1-31下午4:50:47
 *descript		:
 *
 *
 */

package com.vtools.core.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import com.vtools.core.dialogs.TemplateManagerDialog;

public class TemplateManagerCommand extends AbstractHandler
{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		TemplateManagerDialog dialog = new TemplateManagerDialog(shell);
		dialog.open();
		return null;
	}

}
