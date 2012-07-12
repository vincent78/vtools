/*
 *@author 		:vincent
 *@create time	:2012-5-29下午2:45:14
 *descript		:
 *
 *
 */

package com.vtools.core.views;

import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class CodeTemplateTreeContentProvider implements
		IStructuredContentProvider, ITreeContentProvider
{
	@Override
	public void dispose()
	{

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{

	}

	@Override
	public Object[] getChildren(Object parentElement)
	{
		ICodeTemplateTreeNode node = (ICodeTemplateTreeNode) parentElement;
		List<?> list = node.getChildren();
		if (list == null)
		{
			return new Object[0];
		}
		return list.toArray();
	}

	@Override
	public Object getParent(Object element)
	{
		return null;
	}

	@Override
	public boolean hasChildren(Object element)
	{
		ICodeTemplateTreeNode node = (ICodeTemplateTreeNode) element;
		List<?> list = node.getChildren();
		return !(list == null || list.isEmpty());
	}

	@Override
	public Object[] getElements(Object inputElement)
	{
		if (inputElement instanceof List)
		{
			List<?> input = (List<?>) inputElement;
			return input.toArray();
		}
		return new Object[0];
	}

}
