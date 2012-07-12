/*
 *@author 		:vincent
 *@create time	:2012-2-2上午11:04:04
 *descript		:
 *
 *
 */

package com.vtools.core.dialogs;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchActionConstants;

public class CodeGenerateDialog extends Dialog
{
	private int width;
	private int height;
	private CTabFolder tabFolder;
	private CTabItem tabItem1;
	private CTabItem tabItem2;
	private TableViewer templateGroupTV;
	private CheckboxTableViewer ctemplateGroupTV;

	public CodeGenerateDialog(Shell parentShell)
	{
		super(parentShell);
		width = 600;
		height = 400;
	}

	@Override
	protected Control createContents(Composite parent)
	{
		((Shell) parent).setText("代码自动生成...");
		System.out.println("createContents.....");
		init(parent);
		// return super.createContents(parent);
		return parent;
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		System.out.println("createDialogArea....");
		return super.createDialogArea(parent);
	}

	@Override
	protected Point getInitialLocation(Point initialSize)
	{
		return super.getInitialLocation(initialSize);
	}

	protected void init(Composite composite)
	{
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		tabFolder = new CTabFolder(composite, SWT.NONE);
		tabFolder.setLayout(layout);
		tabFolder.setBorderVisible(false);
		tabFolder.setTabHeight(25);
		tabFolder.setSimple(false);
		// 设置TABFOLDER的选中后的背景色

		tabItem1 = new CTabItem(tabFolder, SWT.NONE);
		tabItem1.setText(" 模板 ");
		Composite tabItem1Composite = new Composite(tabFolder, SWT.NONE);
		tabItem1Composite.setLayout(layout);
		templateGroupTV = new TableViewer(tabItem1Composite, SWT.CHECK
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		ctemplateGroupTV = new CheckboxTableViewer(templateGroupTV.getTable());
		tabItem1.setControl(tabItem1Composite);

		TableViewerColumn col01 = new TableViewerColumn(templateGroupTV,
				SWT.NONE);
		col01.getColumn().setAlignment(SWT.CENTER);
		col01.getColumn().setWidth(20);
		col01.getColumn().setResizable(true);
		col01.getColumn().setMoveable(false);

		TableViewerColumn col02 = new TableViewerColumn(templateGroupTV,
				SWT.NONE);
		col02.getColumn().setText("模板文件");
		col02.getColumn().setAlignment(SWT.CENTER);
		col02.getColumn().setWidth(150);
		col02.getColumn().setResizable(true);
		col02.getColumn().setMoveable(false);

		TableViewerColumn col03 = new TableViewerColumn(templateGroupTV,
				SWT.NONE);
		col03.getColumn().setText("备注");
		col03.getColumn().setAlignment(SWT.CENTER);
		col03.getColumn().setWidth(400);
		col03.getColumn().setResizable(true);
		col03.getColumn().setMoveable(false);

		templateGroupTV.getTable().setHeaderVisible(true);
		templateGroupTV.getTable().setLinesVisible(true);

		List<String[]> itemList = new ArrayList<String[]>();
		for (int i = 0; i < 10; i++)
		{
			String[] item = new String[3];
			item[0] = "";
			item[1] = "测试模板" + String.valueOf(i);
			item[2] = "备注" + String.valueOf(i);
			itemList.add(item);
		}
		templateGroupTV.setLabelProvider(new TableLabelProvider());
		templateGroupTV.setContentProvider(new ContentProvider());
		templateGroupTV.setInput(itemList);
		
		initTemplateGroupTVContextMenu();

		tabItem2 = new CTabItem(tabFolder, SWT.NONE);
		tabItem2.setText(" 参数 ");
		
		tabFolder.addSelectionListener(new TabItemChange());


	}
	
	class  TabItemChange extends SelectionAdapter
	{

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			MessageBox box = new MessageBox(Display.getCurrent()
					.getActiveShell());
			
			
			if (tabFolder.getSelectionIndex() == 1)
			{
				box.setMessage(tabFolder.getSelection().getText());
			}
			box.open();
			super.widgetSelected(e);
		}
		
	}

	class TableLabelProvider extends LabelProvider implements
			ITableLabelProvider
	{

		public String getColumnText(Object element, int columnIndex)
		{
			String[] objAttr = (String[])element;
			if (columnIndex == 1)
				return objAttr[1];
			else if (columnIndex == 2)
				return objAttr[2];
			else return objAttr[0];
		}

		public Image getColumnImage(Object element, int columnIndex)
		{

			return null;

		}
	}

	class ContentProvider implements IStructuredContentProvider
	{

		public Object[] getElements(Object inputElement)
		{

			if (inputElement instanceof List<?>)
			{

				return ((List<?>) inputElement).toArray();

			}

			return new Object[0];

		}

		public void dispose()
		{

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{

		}

	}

	/**
	 * 初始化模板表格中的右键菜单
	 */
	protected void initTemplateGroupTVContextMenu()
	{
		MenuManager menuManager = new MenuManager();
		menuManager.add(new Action("全选")
		{

			@Override
			public void run()
			{
				if (ctemplateGroupTV != null)
					ctemplateGroupTV.setAllChecked(true);
			}

		});
		menuManager.add(new Action("反选")
		{

			@Override
			public void run()
			{
				if (ctemplateGroupTV != null)
					ctemplateGroupTV.setAllChecked(false);
			}

		});
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		menuManager.add(new Action("查看")
		{

			@Override
			public void run()
			{
				String selectedFiles = "";
				String alertStr = "无选中模板！";
				if (ctemplateGroupTV != null)
				{
					Object[] elements=ctemplateGroupTV.getCheckedElements();
					for(int i=0;i<elements.length;i++)
					{
						Object[] element = (Object[])elements[i];
						selectedFiles += "," + element[1];
					}
				}
				if (selectedFiles.length() >0)
				{
					selectedFiles = selectedFiles.substring(1);
					alertStr = "当前选中的模板："+selectedFiles;
				}
				
				MessageBox box = new MessageBox(Display.getCurrent()
						.getActiveShell());
				box.setMessage(alertStr);
				box.open();
			}

		});
		Menu menu = menuManager.createContextMenu(templateGroupTV.getTable());
		templateGroupTV.getTable().setMenu(menu);
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
