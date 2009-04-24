/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.action;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
/**
 * Action provider for ASA table editing
 * 
 * @author Idull
 */
public class ASATableEditorActionProvider extends CommonActionProvider
{

    protected IStructuredSelection _selection;
    protected EditASATableAction   _editASATableAction;
    public ASATableEditorActionProvider()
    {

    }

    protected BaseTable getTable(Object obj)
    {
        BaseTable table = null;
        if(obj instanceof Table)
        {
            table = (BaseTable)obj;
        }
        return table;
    }
    
    private void calculateAction()
    {
        Object obj = _selection.getFirstElement();

        BaseTable table = getTable(obj);
        
        Database db = table.getSchema().getDatabase();
    	DatabaseIdentifier di = new DatabaseIdentifier(ModelUtil.getConnectionProfile(db).getName(), table
    			.getSchema().getCatalog().getName());

    	_editASATableAction = new EditASATableAction(db.getVendor(), db.getVersion(),
				null, null, di, table); //$NON-NLS-1$
    	
    	_editASATableAction.setText(Messages.ASATableEditorActionProvider_edit_table);
    }
    
    public void fillActionBars(IActionBars actionBars)
    {
        super.fillActionBars(actionBars);
        Object obj = _selection.getFirstElement();
        if(obj == null)
        {
            return;
        }
        
        calculateAction();
        actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, _editASATableAction);
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
