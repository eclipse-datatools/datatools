/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Hui Cao
 * 
 */
public class DisplayHelpAction extends Action implements IIntroHyperAction
{
    String _contextHelpId = null;
    String _pluginId      = null;

    public DisplayHelpAction()
    {

    }

    public DisplayHelpAction(String text)
    {
        super(text);

    }

    public DisplayHelpAction(String text, String contextHelpId)
    {
        super(text);
        _contextHelpId = contextHelpId;
    }

    private void displayHelp()
    {
        String contextId = null;
        if (_contextHelpId != null && _pluginId != null)
        {
            contextId = HelpUtil.getContextId(_contextHelpId, _pluginId);
        }

        if ((contextId != null) && (contextId.length() > 0))
        {
            IContext context = HelpSystem.getContext(contextId);

            if (context == null || (context.getRelatedTopics().length == 0)
                    || (context.getRelatedTopics()[0].getHref() == null))
            {
                PlatformUI.getWorkbench().getHelpSystem().displayHelp();
            }
            else if (context.getRelatedTopics().length > 1)
            {
                PlatformUI.getWorkbench().getHelpSystem().displayHelp(contextId);
            }
            else
            {
                PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(context.getRelatedTopics()[0].getHref());
            }
        }
        else
        {
            PlatformUI.getWorkbench().getHelpSystem().displayHelp();
        }

    }

    public void run()
    {
        displayHelp();
    }

    public void setPage(ISchemaObjectEditorPage page)
    {
        if (_contextHelpId == null && page instanceof IntroductionPage)
        {
            _contextHelpId = ((IntroductionPage) page).getContextHelpId();
            _pluginId = page.getEditorDescriptor().getPluginId();
        }
    }
}
