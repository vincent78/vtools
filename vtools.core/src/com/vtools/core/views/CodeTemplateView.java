/*
 *@author 		:vincent
 *@create time	:2012-5-25下午2:55:54
 *descript		:
 *
 *
 */

package com.vtools.core.views;
import java.util.List;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.part.ViewPart;
import com.vtools.core.beans.CodeTemplateTreeNode;
import com.vtools.core.services.CodeTemplateService;

public class CodeTemplateView extends ViewPart
{
	private ContainerCheckedTreeViewer  treeView;

	public ContainerCheckedTreeViewer getTreeView()
	{
		return treeView;
	}

	@Override
	public void dispose()
	{
		super.dispose();
		ResourceBundle.freeResources();
	}

	public CodeTemplateView()
	{
		ResourceBundle.initResources();
	}

	@Override
	public void createPartControl(Composite parent)
	{
		int style = SWT.SINGLE | SWT.CHECK | SWT.BORDER;
		treeView = new ContainerCheckedTreeViewer(parent, style);
		treeView.setLabelProvider(new CodeTemplateTreeLabelProvider());
		treeView.setContentProvider(new CodeTemplateTreeContentProvider());
		treeView.setInput(getData(null));
		treeView.addDoubleClickListener(new IDoubleClickListener()
		{
			
			@Override
			public void doubleClick(DoubleClickEvent event)
			{
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				CodeTemplateTreeNode node = (CodeTemplateTreeNode)selection.getFirstElement();
				if (node.isLeaf() && !node.isDirectory())
				{
					CodeTemplateService.openTheFile(node.getPath());
				}
			}
		});
		//initMenu();
	}
	
	/**
	 * 获取节点数据
	 * @param parentNode
	 * @return
	 */
	public List<?> getData(CodeTemplateTreeNode parentNode)
	{
		if (parentNode == null)
			return CodeTemplateService.getTemplateByObj();
		else
			return null;
	}

	@Override
	public void setFocus()
	{

	}
}
