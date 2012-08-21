/*
 *@author 		:vincent
 *@create time	:2012-5-29下午3:26:18
 *descript		:
 *
 *
 */

package com.vtools.core.beans;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hsqldb.lib.StringUtil;

import com.vtools.core.views.ICodeTemplateTreeNode;

public class CodeTemplateTreeNode implements ICodeTemplateTreeNode
{
	private String code;
	private String name;
	private String path;
	private String open_Flag;
	private List<CodeTemplateTreeNode> children;
	private ICodeTemplateTreeNode parent;
	private String remark;

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getOpen_Flag()
	{
		return open_Flag;
	}

	public void setOpen_Flag(String open_Flag)
	{
		this.open_Flag = open_Flag;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getCode()
	{
		return this.code;
	}

	@Override
	public void setCode(String code)
	{
		this.code = code;
	}

	@Override
	public void setChildren(List<?> Children)
	{
		
	}

	@Override
	public List<CodeTemplateTreeNode> getChildren()
	{
		this.children =getChildren(this);
		return this.children;
	}
	
	protected List<CodeTemplateTreeNode> getChildren(CodeTemplateTreeNode node)
	{
		if (node == null || StringUtil.isEmpty(path))
		{
			return null;
		}
		
		File direct = new File(path);
		if (!direct.isDirectory())
		{
			return null;
		}
		File[]  listFile = direct.listFiles();
		ArrayList<CodeTemplateTreeNode> nodeList = new ArrayList<CodeTemplateTreeNode>();
		for(int i=0;i<listFile.length;i++)
		{
			File tmp = listFile[i];
			if (tmp.isDirectory() || tmp.getName().toLowerCase().contains(".ftl"))
			{
				CodeTemplateTreeNode nodeChildren = new CodeTemplateTreeNode();
				nodeChildren.code = tmp.getAbsolutePath();
				nodeChildren.name = tmp.getName().toLowerCase();
				nodeChildren.path = tmp.getAbsolutePath();
				nodeChildren.parent = node;
				if (tmp.isDirectory())
				{
					
					nodeChildren.children = nodeChildren.getChildren();
				}
				else
				{
					nodeChildren.children = null;
					//System.out.println(nodeChildren.getCode());
				}
				nodeList.add(nodeChildren);
				
			}
		}
		return nodeList;
	}

	@Override
	public ICodeTemplateTreeNode getParent()
	{
		return parent;
	}
	
	public static void main(String[] arg0)
	{
		CodeTemplateTreeNode node = new CodeTemplateTreeNode();
		node.path="D:\\TEMP\\T1";
		node.getChildren();
	}

	@Override
	public boolean isLeaf()
	{
		if (children == null || children.size()==0)
			return true;
		else
			return false;
	}
	

}
