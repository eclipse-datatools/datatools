/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;

/**
 * 
 * @author Hui Cao
 *
 */
public class ResultsRowData extends RowData
{

    private Column _column = null;
    private boolean _valid = false;
    
    public ResultsRowData()
    {
        super();
    }

    public ResultsRowData(ITableData table, int type, Object[] data)
    {
        super(table, type, data);
    }

    public Column getColumn()
    {
        return _column;
    }
    
    public void setColumn(Column column)
    {
        this._column = column;
    }

    public boolean isValid()
    {
        return _valid;
    }

    public void setValid(boolean valid)
    {
        this._valid = valid;
    }

}
