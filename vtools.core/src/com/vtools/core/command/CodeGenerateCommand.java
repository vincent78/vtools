/*
 *@author 		:vincent
 *@create time	:2012-2-2上午11:03:26
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
import com.vtools.core.dialogs.CodeGenerateDialog;

public class CodeGenerateCommand extends AbstractHandler
{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		CodeGenerateDialog dialog = new CodeGenerateDialog(shell);
		dialog.open();
		return null;
	}

}
