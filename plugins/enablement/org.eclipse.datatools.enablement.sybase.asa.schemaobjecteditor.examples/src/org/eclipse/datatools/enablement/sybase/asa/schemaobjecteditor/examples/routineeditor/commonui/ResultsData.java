/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableDataChangeListener;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;

/**
 * 
 * @author Hui Cao
 * 
 */
public class ResultsData extends VariablesData
{
    private RoutineResultTable _result;
    public ResultsData()
    {
        // TODO Auto-generated constructor stub
    }

    public ResultsData(RoutineResultTable params, DatabaseDefinition dbdef)
    {
        init(params, dbdef);
    }

    public void init(RoutineResultTable result, DatabaseDefinition dbdef)
    {
        _result = result;
        _rows.clear();
        
        if (result != null)
        {
            for (Iterator iter = result.getColumns().iterator(); iter.hasNext();)
            {
                Column column = (Column) iter.next();
                
                Object[] data = new Object[getColumnCount()];
                data[DIRTY_COLUMN] = "";
                data[NAME_COLUMN] = column.getName();
                DataType datatype = column.getDataType();
                
                DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                        new DatabaseVendorDefinitionId(dbdef.getProduct(), dbdef.getVersion()))
                        .getSQLDataService().getDataTypeProvider();
                String typeName = provider.getDataTypeString(datatype, false);
                if(datatype instanceof PredefinedDataType)
                {
                    typeName = typeName.toLowerCase();
                }

                if (typeName == null)
                {
                    if (datatype != null)
                    {
                        typeName = datatype.toString();
                    }
                    else if (column.getDescription() != null)
                    {
                        //if the datatype is invalid, the datatype is null and the description is the invalid datatype name.
                        typeName = column.getDescription();
                    }
                }
                data[TYPE_COLUMN] = typeName;
                
                ResultsRowData row = new ResultsRowData(this, RowData.STATE_ORIGINAL, data);
                row.setColumn(column);
                _rows.add(row);
            }
        }
    }
    

    public IRowData insertRow()
    {
        Object data[] = new Object[getColumnCount()];
        IRowData row = new ResultsRowData(this, RowData.STATE_INSERTED, data);
        _rows.add(row);

        Object[] listeners = _listenerList.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((ITableDataChangeListener) listeners[i]).rowAdded(row);
        }

        return row;
    }
    
}
