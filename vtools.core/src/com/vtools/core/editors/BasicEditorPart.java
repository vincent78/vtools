/*
 *@author 		:vincent
 *@create time	:2012-8-21下午4:12:00
 *descript		:
 *
 *
 */

package com.vtools.core.editors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;


public class BasicEditorPart extends EditorPart implements ISaveablePart2
{
	public static final String ID = "com.vtools.core.editors.basiceditorpart";

	private BasicEditorInput input;
	private Text text;
	private boolean dirty;

	public BasicEditorPart() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		SaveToLocal(input.getPath());
		dirty=false;
		firePropertyChange(ISaveablePart2.PROP_DIRTY);

	}

	@Override
	public void doSaveAs() {
		FileDialog dialog=new FileDialog(getEditorSite().getShell(),SWT.SAVE);
		String path=dialog.open();
		if(path!=null){
			SaveToLocal(path);
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		//setPartName(input.getName());
		setPartName(input.getName());
		this.input=(BasicEditorInput)input;

	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void createPartControl(Composite parent) {
		text =new Text(parent,SWT.BORDER|SWT.WRAP|SWT.H_SCROLL|SWT.CANCEL|SWT.MULTI);
		loadText();
		
		text.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				dirty=true;
				firePropertyChange(ISaveablePart2.PROP_DIRTY);
			}
			
		});
	}

	@Override
	public void setFocus() {
		text.setFocus();

	}

	@Override
	public int promptToSaveOnClose() {
		if(dirty){
			if(MessageDialog.openConfirm(getEditorSite().getShell(), "提示", "没保存，继续？")){
				return ISaveablePart2.NO;
			}else{
				return ISaveablePart2.CANCEL;
			}
		}
		return YES;
	}
	private void loadText(){
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(input.getPath()),"utf-8"));
			StringBuffer sb=new StringBuffer();
			String line=reader.readLine();
			while(line!=null){
				sb.append(line);
				sb.append("\n");
				line=reader.readLine();
			}
			reader.close();
			text.setText(sb.toString());
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private void SaveToLocal(String path){
		try{
			OutputStream out=new FileOutputStream(path);
			OutputStreamWriter writer =new OutputStreamWriter(out,"utf-8");
			writer.write(text.getText());
			writer.close();
			out.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
