/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;

/**
 * 
 * @author Hui Cao
 *
 */
public class ParametersRowData extends RowData
{

    private SybaseParameter parameter = null;
    private boolean valid = false;
    
    public ParametersRowData()
    {
        super();
    }

    public ParametersRowData(ITableData table, int type, Object[] data)
    {
        super(table, type, data);
    }

    /**
     * update the value of given column, but don't notify the listeners
     * @param col
     * @param value
     */
    public void updateValueWithoutNotification(int col, Object value)
    {
        if (_state == STATE_ORIGINAL)
        {
            _state = STATE_UPDATED;
        }
        _newData[col] = value;
    }

    public SybaseParameter getParameter()
    {
        return parameter;
    }

    public void setParameter(SybaseParameter parameter)
    {
        this.parameter = parameter;
    }

    public boolean isValid()
    {
        return valid;
    }

    public void setValid(boolean valid)
    {
        this.valid = valid;
    }
    
    
}
