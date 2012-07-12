/*
 *@author 		:vincent
 *@create time	:2012-5-25下午2:55:54
 *descript		:
 *
 *
 */

package com.vtools.core.views;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.part.ViewPart;

import com.vtools.core.actions.RefreshAction;
import com.vtools.core.beans.CodeTemplateTreeNode;
import com.vtools.core.services.CodeTemplateService;

public class CodeTemplateView extends ViewPart
{
	private ContainerCheckedTreeViewer  treeView;

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
		initContextMenu();
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
	
	/**
	 * 初始化右键菜单
	 */
	public void initContextMenu()
	{
		MenuManager menuManager = new MenuManager();
		menuManager.add(new Action("新建模板")
		{

			@Override
			public void run()
			{
				System.out.println("Begin print the node path!");
				Object[] nodes =  treeView.getCheckedElements();
				for(int i=0;i<nodes.length;i++)
				{
					CodeTemplateTreeNode node = (CodeTemplateTreeNode)nodes[i];
					if (node.getChildren() == null)
					{
						System.out.println(node.getPath());
					}
				}
			}

		});
		menuManager.add(new Action("查看模板")
		{

			@Override
			public void run()
			{
				
			}

		});
		menuManager.add(new Action("修改模板")
		{

			@Override
			public void run()
			{
				
			}

		});
		menuManager.add(new Action("参数设置")
		{

			@Override
			public void run()
			{
				
			}

		});
		
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		
		menuManager.add(new Action("生成代码")
		{

			@Override
			public void run()
			{
				
			}

		});
		Menu menu = menuManager.createContextMenu(treeView.getControl());
		treeView.getControl().setMenu(menu);
	}
	
    private void initializeToolBar()
    {
    	IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();//取得此View的toolBarManager
    	toolBarManager.add(new RefreshAction());
    }
}
