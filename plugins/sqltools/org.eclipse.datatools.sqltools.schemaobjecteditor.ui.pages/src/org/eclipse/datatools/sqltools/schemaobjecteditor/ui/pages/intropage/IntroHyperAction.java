/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.jface.action.Action;

/**
 * 
 * @author Idull
 */
public class IntroHyperAction extends Action implements IIntroHyperAction
{
    protected ISchemaObjectEditorPage _introPage;

    public void setPage(ISchemaObjectEditorPage page)
    {
        _introPage = page;
    }
}
