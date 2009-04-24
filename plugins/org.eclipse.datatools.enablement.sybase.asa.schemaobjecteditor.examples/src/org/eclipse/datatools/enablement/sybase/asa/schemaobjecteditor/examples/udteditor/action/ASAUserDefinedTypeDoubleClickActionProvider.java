/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.action;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.DoubleClickActionProvider;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.ICommonActionConstants;

/**
 * @author renj
 */
public class ASAUserDefinedTypeDoubleClickActionProvider extends DoubleClickActionProvider
{

    public void fillActionBars(IActionBars actionBars)
    {
        super.fillActionBars(actionBars);

        ActionContext context = getContext();

        boolean hasSelection = context != null && !context.getSelection().isEmpty();

        if (hasSelection)
        {
            Object firstElement = null;
            if (context.getSelection() instanceof IStructuredSelection)
            {
                firstElement = ((IStructuredSelection) context.getSelection()).getFirstElement();
            }

            if (firstElement != null && firstElement instanceof SybaseASABaseUserDefinedType)
            {
                DatabaseDefinition dbdef = ModelUtil.getDatabaseDefinition((SQLObject) firstElement);
                if (dbdef != null && dbdef.getProduct() != null && dbdef.getProduct().startsWith("Sybase"))
                {
                    _doubleClickAction = new ASAUserDefinedTypeEditAction((SybaseASABaseUserDefinedType) firstElement);
                }
            }
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, _doubleClickAction);
        }
    }

}
