/*
 *@author 		:vincent
 *@create time	:2012-5-29下午2:46:12
 *descript		:
 *
 *
 */

package com.vtools.core.views;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class CodeTemplateTreeLabelProvider extends LabelProvider implements ILabelProvider {
    public String getText(Object element) {
        ICodeTemplateTreeNode node = (ICodeTemplateTreeNode)element;
        return node.getName();
    }
    public Image getImage(Object element) {
    	ICodeTemplateTreeNode node = (ICodeTemplateTreeNode)element;
    	
    	if (node.getParent() == null)
    	{
    		return ResourceBundle.images[ResourceBundle.ciClosedFolder];
    	}
    	
    	if (node.getChildren() == null 
    			|| node.getChildren().size() == 0)
    	{
    		return ResourceBundle.images[ResourceBundle.ciTarget];
    	}
    	else
    	{
    		return ResourceBundle.images[ResourceBundle.ciClosedFolder];
    	}
    }
}
