package com.vtools.core.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import com.vtools.core.dialogs.DbInfoDialog;

public class DbInfoCommand extends AbstractHandler
{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		DbInfoDialog dialog = new DbInfoDialog(shell);
		dialog.open();
		
		return null;
	}
	
}
