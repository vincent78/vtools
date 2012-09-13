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
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.part.ViewPart;
import com.vtools.core.beans.CodeTemplateTreeNode;
import com.vtools.core.services.CodeTemplateService;
import com.vtools.utils.RcpUtil;

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
	
	/**
	 * 初始化右键菜单
	 */
	public void initMenu()
	{
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
		
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
					if (node.isLeaf())
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
				IStructuredSelection selection = (IStructuredSelection) treeView.getSelection();
				CodeTemplateTreeNode node = (CodeTemplateTreeNode)selection.getFirstElement();
				System.out.println(node.getPath());
				if (!node.isLeaf())
				{
					List<CodeTemplateTreeNode> nodes = node.getChildren();
					for(int i=0;i<node.getChildren().size();i++)
					{
						
						CodeTemplateTreeNode cnode = nodes.get(i);
						if (!cnode.isDirectory())
							CodeTemplateService.openTheFile(cnode.getPath());
					}
				}
				else
				{
					if (!node.isDirectory())
						CodeTemplateService.openTheFile(node.getPath());
				}
				
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
				RcpUtil.ShowMsg("生成代码", "提示");
				
			}

		});
	}
	
//    private void initializeToolBar()
//    {
//    	IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();//取得此View的toolBarManager
//    	toolBarManager.add(new RefreshAction());
//    }
}
