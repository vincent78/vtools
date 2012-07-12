/*
 *@author 		:vincent
 *@create time	:2012-5-25下午2:02:09
 *descript		:
 *
 *
 */

package com.vtools.core.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class TemplatePerspective implements IPerspectiveFactory
{

	@Override
	public void createInitialLayout(IPageLayout layout)
	{
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		//layout.addView("com.vtools.core.views.CodeTemplateView", IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.vtools.core.views.CodeTemplateView", IPageLayout.LEFT, 0.2f, editorArea);
		layout.getViewLayout("com.vtools.core.views.CodeTemplateView").setCloseable(false);
	}

}
