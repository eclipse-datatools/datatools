/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditor;

/**
 * 
 * @author Hui Cao
 * 
 */
public class StartEditAction extends IntroHyperAction implements IIntroHyperAction
{
    public StartEditAction()
    {

    }

    public void run()
    {
        if (_introPage != null && _introPage.getEditor() != null)
        {
            SchemaObjectEditor editor = (SchemaObjectEditor) _introPage.getEditor();
            int currentInd = editor.getCurrentPageIndex();
            if (currentInd == 0)
            {
                editor.setActivePage(1);
            }
            else
            {
                editor.setActivePage(0);
            }
        }
        super.run();
    }
}
