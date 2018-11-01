/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.sqltools.common.core.tableviewer.AbstractTableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableDataChangeListener;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.emf.common.util.EList;

/**
 * 
 * @author Dafan Yang
 * @author Hui Cao
 * 
 */
public class ParametersData extends AbstractTableData
{
    public static HashMap                 INOUT_MAPPING                   = new HashMap();
    static
    {
        INOUT_MAPPING.put("", ParameterMode.IN_LITERAL);
        INOUT_MAPPING.put("IN", ParameterMode.IN_LITERAL);
        INOUT_MAPPING.put("OUT", ParameterMode.OUT_LITERAL);
        INOUT_MAPPING.put("INOUT", ParameterMode.INOUT_LITERAL);
    }

    public static HashMap                 TSQL_INOUT_MAPPING                   = new HashMap();
    static
    {
    	TSQL_INOUT_MAPPING.put("", ParameterMode.IN_LITERAL);
    	TSQL_INOUT_MAPPING.put("IN", ParameterMode.IN_LITERAL);
    	TSQL_INOUT_MAPPING.put("OUT", ParameterMode.INOUT_LITERAL);
    	TSQL_INOUT_MAPPING.put("OUTPUT", ParameterMode.INOUT_LITERAL);
    }
    
    private static final String[] COLUMN_NAMES   = new String[]
    {
            "",
            Messages.wizard_createSP_page2_parameter_label_name,
            Messages.wizard_createSP_page2_parameter_label_datatype,
            Messages.wizard_createSP_page2_parameter_label_inout,
            Messages.wizard_createSP_page2_parameter_label_default
    }
    ;
    public static final int[]     COLUMN_LENGTH  = new int[]
    {
        20, 200, 100, 60, 142
    }
    ;
    public static final int       DIRTY_COLUMN   = 0;
    public static final int       NAME_COLUMN    = 1;
    public static final int       TYPE_COLUMN    = 2;
    public static final int       INOUT_COLUMN   = 3;
    public static final int       DEFAULT_COLUMN = 4;
    
    private boolean _tsql = true;
    	
    public ParametersData()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public ParametersData(EList params, DatabaseDefinition dbdef)
    {
        init(params, dbdef, true);
    }

    public void init(EList params, DatabaseDefinition dbdef)
    {
    	init(params, dbdef, true);
    }
    
    public void init(EList params, DatabaseDefinition dbdef, boolean tsql)
    {
    	this._tsql = tsql;
        _rows.clear();
        /**
         * Whether the parameter default value has been processed
         */
    	boolean processed = false;
    	
		for (Iterator iter = params.iterator(); iter.hasNext();)
        {
            SybaseParameter param = (SybaseParameter) iter.next();
            int jdbcType = param.getJDBCParameterType().getValue();
            if (jdbcType == JDBCParameterType.RETURN || jdbcType == JDBCParameterType.RESULT || jdbcType == JDBCParameterType.UNKNOWN)
            {
                continue;
            }

            Routine routine = param.getRoutine();
            if (!processed )
            {
                if (routine instanceof SybaseRoutine)
                {
                    ((SybaseRoutine)routine).parseParameterDefaultValues();
                }
                processed = true;
            }
            Object[] data = new Object[getColumnCount()];
            data[DIRTY_COLUMN] = "";
            data[NAME_COLUMN] = param.getName();
            DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                    new DatabaseVendorDefinitionId(dbdef.getProduct(), dbdef.getVersion()))
                    .getSQLDataService().getDataTypeProvider();
            String typeName = provider.getDataTypeString(param.getDataType(), false);
            if(param.getDataType() instanceof PredefinedDataType)
            {
                typeName = typeName.toLowerCase();
            }
            data[TYPE_COLUMN] = typeName;
            data[INOUT_COLUMN] = getParameterModeValue(param.getMode());
            String dft = ((SybaseParameter)param).getDefaultValue();
            if(dft==null)
            {
                dft = "";
            }
            data[DEFAULT_COLUMN] = dft;
            
            ParametersRowData row = new ParametersRowData(this, RowData.STATE_ORIGINAL, data);
            row.setParameter(param);
            row.setValid(true);
            _rows.add(row);
        }
    }
    
    public boolean isReadonly()
    {
        return false;
    }

    public int getColumnCount()
    {
        return COLUMN_NAMES.length;
    }

    public String getColumnName(int col)
    {
        return COLUMN_NAMES[col];
    }

    public int getColumnType(int col)
    {
        return Types.CHAR;
    }

    public String getColumnHeader(int col)
    {
        return COLUMN_NAMES[col];
    }

    public Vector getRows()
    {
        return _rows;
    }

    public boolean save() throws Exception
    {
        return false;
    }

    public void dispose()
    {
        // ignore for now
    }

    public IRowData insertRow()
    {
        Object data[] = new Object[getColumnCount()];
        IRowData row = new ParametersRowData(this, RowData.STATE_INSERTED, data);
        _rows.add(row);

        Object[] listeners = _listenerList.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((ITableDataChangeListener) listeners[i]).rowAdded(row);
        }

        return row;
    }
    
    public void insertRow(IRowData rowData, int row)
    {
        if (rowData instanceof ParametersRowData)
        {
            ((ParametersRowData)rowData).setParameter(null);
        }
        super.insertRow(rowData, row);
    }
    
    /**
     * Returns the ParameterMode matching the customized mode string representation. Vendors that have particular representations
     * for the parameter modes can override this method. 
     * @param modeValue
     * @return
     */
    public ParameterMode getParameterMode(String modeValue)
    {
    	if (_tsql)
    	{
    		return (ParameterMode)TSQL_INOUT_MAPPING.get(modeValue);
    	}
    	else
    	{
    		return (ParameterMode)INOUT_MAPPING.get(modeValue);
    	}
    }

    /**
     * Returns the the customized mode string representation matching the given ParameterMode. Vendors that have particular representations
     * for the parameter modes can override this method. 
     * @param mode
     * @return
     */
    public String getParameterModeValue(ParameterMode mode)
    {
    	if (_tsql && mode.getValue() == ParameterMode.INOUT)
    	{
    		return "OUT";//there're 2 representations for INOUT in TSQL, the abbreviated form is preferred
    	}
    	HashMap mapping = _tsql? TSQL_INOUT_MAPPING: INOUT_MAPPING;
    	for (Iterator it = mapping.keySet().iterator(); it.hasNext();) {
    		String modeValue = (String) it.next();
			ParameterMode mapMode = (ParameterMode)mapping.get(modeValue); 
			if (mapMode.equals(mode))
			{
				return modeValue;
			}
		}
		return mode.getLiteral();
    }
}
