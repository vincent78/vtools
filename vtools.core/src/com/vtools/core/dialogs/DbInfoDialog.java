package com.vtools.core.dialogs;

import java.util.HashMap;
import java.util.List;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;

import com.vtools.core.services.DbInfoService;
import com.vtools.core.services.DbTypeService;

public class DbInfoDialog extends Dialog
{
	public DbInfoDialog(Shell parentShell)
	{
		super(parentShell);
		width = 600;
		height = 400;
	}
	
	private TableViewer dbListTV;
	private Text nameText;
	private Text seqText;
	private Combo typeCom;
	private Text urlText;
	private Text userNameText;
	private Text passwdText;
	private Text remarkText;
	private Button submitBtn;
	private Button deleteBtn;
	private int width;
	private int height;
	
	@Override
	protected Control createContents(Composite parent)
	{
		//MessageDialog.openInformation(this.getShell(), "测试", "这是测试用的");
		((Shell)parent).setText("数据库设置...");
		initTable(parent);
		initEditArea(parent);
		return parent;
	}
	
	private void initTable(Composite parent)
	{
		parent.setLayout(new FormLayout());
		dbListTV = new TableViewer(parent,SWT.BORDER|SWT.SINGLE|SWT.FULL_SELECTION);
		FormData formData1 = new FormData();
		formData1.left = new FormAttachment(0);
		formData1.top = new FormAttachment(0);
		formData1.bottom = new FormAttachment(100);
		formData1.right = new FormAttachment(69);
		dbListTV.getTable().setLayoutData(formData1);
		
		TableViewerColumn column00 = new TableViewerColumn(dbListTV, SWT.NONE);
		column00.getColumn().setText("序号");
		column00.getColumn().setAlignment(SWT.CENTER);
		column00.getColumn().setWidth(35);
		column00.getColumn().setResizable(false);
		column00.getColumn().setMoveable(false);
		
		TableViewerColumn column01 = new TableViewerColumn(dbListTV, SWT.NONE);
		column01.getColumn().setText("名称");
		column01.getColumn().setAlignment(SWT.CENTER);
		column01.getColumn().setWidth(100);
		column01.getColumn().setResizable(true);
		column01.getColumn().setMoveable(true);
		
		TableViewerColumn column02 = new TableViewerColumn(dbListTV, SWT.NONE);
		column02.getColumn().setText("备注");
		column02.getColumn().setAlignment(SWT.CENTER);
		column02.getColumn().setWidth(270);
		column02.getColumn().setResizable(true);
		column02.getColumn().setMoveable(true);
		
		dbListTV.getTable().setHeaderVisible(true);
		dbListTV.getTable().setLinesVisible(true);
		
		//加载内容
		dbListTV.setContentProvider(new ContentProvider());
		dbListTV.setLabelProvider(new TableLabelProvider());
		dbListTV.setInput(DbInfoService.getDbInfoList());
		
		//初始化右键菜单
		initDBTableContextMenu();
		
		//注册双击事件
		dbListDoubleClick();
	}
	
	protected void  dbListDoubleClick()
	{
		final Table table = dbListTV.getTable();
		table.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseUp(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseDown(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
				int rowIndex = table.getSelectionIndex();
				String name = table.getItem(rowIndex).getText(1);
				//MessageDialog.openInformation(table.getShell(), "测试", tempText);
				HashMap<String, Object> dbinfo = DbInfoService.getDbInfoByName(name);
				//EditArea区域
				nameText.setText(dbinfo.get("NAME").toString());
				seqText.setText(dbinfo.get("SEQ").toString());
				String[] items = typeCom.getItems();
				for(int i=0;i<items.length;i++)
				{
					if (typeCom.getData(items[i]).equals(dbinfo.get("DB_TYPE").toString()))
					{
						typeCom.select(i);
						break;
					}
				}
				
				urlText.setText(dbinfo.get("URL").toString());
				userNameText.setText(dbinfo.get("USERNAME").toString());
				passwdText.setText(dbinfo.get("PASSWD").toString());
				remarkText.setText(dbinfo.get("REMARK").toString());
			}
		});
	}
	
	public class ContentProvider implements IStructuredContentProvider {
        @SuppressWarnings("rawtypes")
		public Object[] getElements(Object inputElement) {
            if(inputElement instanceof List){
                return ((List)inputElement).toArray();
            }else{
                return new Object[0];
            }
        }
        public void dispose() {
        }
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }
	
	public class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof HashMap){
            	@SuppressWarnings("unchecked")
				HashMap<String, Object>  p = (HashMap<String, Object> )element;
                if(columnIndex == 0){
                    return p.get("SEQ").toString();
                }else if(columnIndex == 1){
                    return p.get("NAME").toString();
                }else if (columnIndex ==2){
                    return p.get("REMARK").toString();
                
                }
            }
            return null;
        }
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }
    }

	
	private void initEditArea(Composite parent)
	{
		Composite rightLayout = new Composite(parent,SWT.NONE);
		FormData formData2 = new FormData();
		formData2.left = new FormAttachment(71);
		formData2.top = new FormAttachment(0);
		formData2.bottom = new FormAttachment(100);
		formData2.right = new FormAttachment(100);
		rightLayout.setLayoutData(formData2);
		
		
		GridLayout shellLayout = new GridLayout();
	    shellLayout.marginWidth = 10;
	    shellLayout.horizontalSpacing = 0;
	    shellLayout.marginHeight = 10;
	    shellLayout.verticalSpacing = 10;
	    shellLayout.makeColumnsEqualWidth = true;
	    shellLayout.numColumns = 10;
	    
		
		rightLayout.setLayout(shellLayout);
		
		GridData gd = new GridData(768);
		gd.horizontalSpan = 3;
		Label nameLB = new Label(rightLayout,0);
		nameLB.setText("名称");
		nameLB.setLayoutData(gd);
		
		nameText = new Text(rightLayout,SWT.BORDER);
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		nameText.setLayoutData(gd);
		
		Label seqLB = new Label(rightLayout,0);
		seqLB.setText("顺序");
		gd = new GridData(768);
		gd.horizontalSpan = 3;
		seqLB.setLayoutData(gd);
		
		seqText = new Text(rightLayout,SWT.BORDER);
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		seqText.setLayoutData(gd);
		
		Label typeLB = new Label(rightLayout,0);
		typeLB.setText("类型");
		gd = new GridData(768);
		gd.horizontalSpan = 3;
		typeLB.setLayoutData(gd);
		
		//typeText = new Text(rightLayout,SWT.BORDER);
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		//typeText.setLayoutData(gd);
		typeCom = new Combo(rightLayout,SWT.READ_ONLY);
		List<HashMap<String, Object>> types = DbTypeService.getDbType();
		for(int i=0;types!=null&&i<types.size();i++)
		{
			HashMap<String, Object> type = types.get(i);
			typeCom.add(type.get("NAME").toString());
			typeCom.setData(type.get("NAME").toString(), type.get("ID"));
			
		}
		typeCom.setLayoutData(gd);
		typeCom.select(0);
		
		Label urlLB = new Label(rightLayout,0);
		urlLB.setText("URL");
		gd = new GridData(768);
		gd.horizontalSpan = 3;
		urlLB.setLayoutData(gd);
		
		urlText = new Text(rightLayout,SWT.BORDER);
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		urlText.setLayoutData(gd);
		
		Label userNameLB = new Label(rightLayout,0);
		userNameLB.setText("用户名");
		gd = new GridData(768);
		gd.horizontalSpan = 3;
		userNameLB.setLayoutData(gd);
		
		userNameText = new Text(rightLayout,SWT.BORDER);
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		userNameText.setLayoutData(gd);
		
		Label passwdLB = new Label(rightLayout,0);
		passwdLB.setText("密码");
		gd = new GridData(768);
		gd.horizontalSpan = 3;
		passwdLB.setLayoutData(gd);
		
		passwdText = new Text(rightLayout,SWT.BORDER);
		passwdText.setEchoChar('*');
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		passwdText.setLayoutData(gd);
		
		Label remarkLB = new Label(rightLayout,0);
		remarkLB.setText("备注");
		gd = new GridData(768);
		gd.horizontalSpan = 3;
		remarkLB.setLayoutData(gd);
		
		remarkText = new Text(rightLayout,SWT.BORDER | SWT.MULTI | SWT.WRAP);
		gd = new GridData(768);
		gd.horizontalSpan = 7;
		gd.verticalSpan = 4;	
		gd.horizontalAlignment 	= SWT.FILL;
		gd.verticalAlignment	= SWT.FILL;
		remarkText.setLayoutData(gd);
		
		
		submitBtn = new Button(rightLayout,0);
		submitBtn.setText("提交");
		//注册事件
		submitBtn.addListener(SWT.MouseDown, submitListener);
		gd = new GridData(768);
		//gd.horizontalAlignment = SWT.CENTER;
		//gd.horizontalIndent = 3;
		gd.horizontalSpan = 10;
		submitBtn.setLayoutData(gd);
		
		deleteBtn = new Button(rightLayout,0);
		deleteBtn.setText("删除");
		//注册事件
		deleteBtn.addListener(SWT.MouseDown, delListener);
		
		gd = new GridData(768);
		gd.horizontalSpan = 10;
		deleteBtn.setLayoutData(gd);
		
	}
	
	@Override
	protected Point getInitialSize()
	{
		Point p = super.getInitialSize();
		p.x = this.width;
		p.y = this.height;
		return p;
	}


	@Override
	protected Point getInitialLocation(Point initialSize)
	{
		return super.getInitialLocation(initialSize);
	}
	
	
	/**
	 * 删除按钮的事件
	 */
	Listener  delListener = new Listener()
	{
		public void handleEvent(Event event) 
		{
			if(event.widget == deleteBtn)
			{
				HashMap<String, Object> dbInfo = DbInfoService.getDbInfoByName(nameText.getText().trim());
				if (dbInfo == null ) 
				{
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "提示", "相关信息没有查到，请确认！");
				}
				else
				{
					DbInfoService.delete(nameText.getText().trim());
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "提示", "操作成功！");
					clearInputArea();
					dbListTV.setInput(DbInfoService.getDbInfoList());
				}
			}
		};
	};
	
	/**
	 *	确定按钮的事件
	 */
	Listener submitListener = new Listener()
	{
		public void handleEvent(Event event) 
		{
			if(event.widget == submitBtn)
			{
				try
				{
				
					String name = nameText.getText().trim();
					if ("".equals(name))
					{
						MessageBox box = new MessageBox(Display.getCurrent().getActiveShell());
						box.setMessage("名称不能为空！");
						box.open();
						nameText.setFocus();
					}
					else
					{
						int seq = 999;
						if(seqText.getText().trim().length() >0 )
						{
							seq = Integer.parseInt(seqText.getText());
						}
						HashMap<String, Object> dbInfo = DbInfoService.getDbInfoByName(name);
						if (dbInfo == null ) //没有此名称的记录  新增
						{
							DbInfoService.insert(name, seq
									, Integer.parseInt(typeCom.getData(typeCom.getText()).toString())
									, urlText.getText()
									, userNameText.getText()
									, passwdText.getText()
									, remarkText.getText());
						}
						else //有此名称的记录   提示后修改
						{
							if(MessageDialog.openConfirm(Display.getCurrent().getActiveShell()
								, "确定", "已有同名的数据，确定要修改码？"))
							{
								DbInfoService.update(name,seq
									, Integer.parseInt(typeCom.getData(typeCom.getText()).toString())
									, urlText.getText()
									, userNameText.getText()
									, passwdText.getText()
									, remarkText.getText());
							}
						}
					}
					dbListTV.setInput(DbInfoService.getDbInfoList());
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "提示", "操作成功！");
				}
				catch(Exception e)
				{
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "提示", "操作失败！");
				}
				
				
			}
		};
	};
	
	/**
	 * 清空输入区中所有控件的值 
	 */
	protected void clearInputArea()
	{
		nameText.setText("");
		seqText.setText("");
		typeCom.select(0);
		urlText.setText("");
		userNameText.setText("");
		passwdText.setText("");
		remarkText.setText("");
		
		nameText.setFocus();
	}
	
	/**
	 * 初始化表格中的右键菜单
	 */
	protected void initDBTableContextMenu()
	{
		MenuManager menuManager = new MenuManager();
		menuManager.add(new Action("清空")
		{

			@Override
			public void run()
			{
				clearInputArea();
			}

		});
		
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		menuManager.add(new Action("自动排序")
		{

			@Override
			public void run()
			{
			}

		});
		menuManager.add(new Action("上移")
		{

			@Override
			public void run()
			{
			}

		});
		menuManager.add(new Action("下移")
		{

			@Override
			public void run()
			{
			}

		});
		menuManager.add(new Action("移到最前")
		{

			@Override
			public void run()
			{
			}

		});
		menuManager.add(new Action("移到最后")
		{

			@Override
			public void run()
			{
			}

		});
		Menu menu = menuManager.createContextMenu(dbListTV.getTable());
		dbListTV.getTable().setMenu(menu);
	}
	
	
}
