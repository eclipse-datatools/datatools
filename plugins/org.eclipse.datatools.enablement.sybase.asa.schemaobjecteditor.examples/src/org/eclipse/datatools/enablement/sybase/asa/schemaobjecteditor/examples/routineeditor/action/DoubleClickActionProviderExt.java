/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.DoubleClickActionProvider;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.ICommonActionConstants;


/**
 * 
 * @author Hui Cao
 * 
 */
public class DoubleClickActionProviderExt extends DoubleClickActionProvider
{
	protected IStructuredSelection     _selection;
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    public void fillActionBars(IActionBars actionBars) 
    {
        super.fillActionBars(actionBars);
        
        boolean hasSelection = _selection != null && !_selection.isEmpty();
        
        if (hasSelection)
        {
            Object firstElement = null;
            if (_selection instanceof IStructuredSelection)
            {
                firstElement = ((IStructuredSelection)_selection).getFirstElement();
            }
            
            if (firstElement != null && firstElement instanceof SQLObject)
            {
                DatabaseDefinition dbdef = ModelUtil.getDatabaseDefinition((SQLObject)firstElement);
                if (dbdef != null && dbdef.getProduct() != null && dbdef.getProduct().startsWith("Sybase"))
                {
                    _doubleClickAction = new EditRoutineInFormAction(firstElement)
                    {
                        public void run() 
                        {
                            IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
                            Object selectedResource = null;
                            if (selection.size() == 1) 
                            {
                                selectedResource = (Object) selection.getFirstElement();
                                if (selectedResource instanceof Parameter)
                                {
                                    if (((Parameter) selectedResource).getRoutine() instanceof Function
                                            && ((Parameter) selectedResource).getMode().getName().equals(
                                                    ParameterMode.OUT_LITERAL.getName()))
                                    {
                                        return;
                                    }
                                    selectedResource = ((Parameter) selectedResource).getRoutine();
                                }
                                //If you don't want the node expanded, comment the following line
                               ((TreeViewer) _site.getStructuredViewer()).setExpandedState(selectedResource,true);
                            }
                            init();
                            initSQLObject(this, selectedResource);
                            initConnectionProfile();
                            super.run();
                        }
                    };
                }
            }
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, _doubleClickAction);            
        }
    }
    
    public void setContext(ActionContext context)
    {
        super.setContext(context);
        if (context != null && (context.getSelection() instanceof IStructuredSelection))
        {
            _selection = ((IStructuredSelection) context.getSelection());
        }
    }
    

}
