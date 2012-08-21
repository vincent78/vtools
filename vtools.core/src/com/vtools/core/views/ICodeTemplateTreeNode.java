/*
 *@author 		:vincent
 *@create time	:2012-5-29下午2:47:37
 *descript		:
 *
 *
 */

package com.vtools.core.views;

import java.util.List;

public interface ICodeTemplateTreeNode
{
	public String getName();
	public void setName(String name);
	public String getCode();
	public void setCode(String code);
    public void setChildren(List<?> Children);
    public List<?> getChildren();
    public ICodeTemplateTreeNode getParent();
    public boolean isLeaf();
}
