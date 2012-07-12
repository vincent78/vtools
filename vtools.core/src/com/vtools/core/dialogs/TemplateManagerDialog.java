/*
 *@author 		:vincent
 *@create time	:2012-1-31下午4:47:40
 *descript		:
 *
 *
 */

package com.vtools.core.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class TemplateManagerDialog extends Dialog
{
	private int width;
	private int height;
	
	public TemplateManagerDialog(Shell parentShell)
	{
		super(parentShell);
		width = 600;
		height = 400;
	}
	

	@Override
	protected Control createContents(Composite parent)
	{
		((Shell)parent).setText("代码模板管理...");
		return super.createContents(parent);
	}


	@Override
	protected Point getInitialLocation(Point initialSize)
	{
		return super.getInitialLocation(initialSize);
	}

	@Override
	protected Point getInitialSize()
	{
		Point p = super.getInitialSize();
		p.x = this.width;
		p.y = this.height;
		return p;
	}
	
	
}
