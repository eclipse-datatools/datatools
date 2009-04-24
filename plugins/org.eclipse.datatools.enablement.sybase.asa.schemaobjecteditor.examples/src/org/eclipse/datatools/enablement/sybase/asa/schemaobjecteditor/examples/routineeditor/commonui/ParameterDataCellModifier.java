/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import java.util.regex.Matcher;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseParameterValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataCellModifier;
import org.eclipse.swt.widgets.TableItem;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ParameterDataCellModifier extends TableDataCellModifier
{
    private boolean _enforcePrefix = false;
    private boolean _supportsExp = false;
    public ParameterDataCellModifier(AccessibleTableViewer viewer, boolean enforcePrefix, boolean supportsExp )
    {
        super(viewer);
        _enforcePrefix = enforcePrefix;
        _supportsExp = supportsExp;
    }
    
    public void modify(Object element, String property, Object value)
    {
        int column = getColumnIndex(property);
        if (column == ParametersData.NAME_COLUMN)
        {
            if (_enforcePrefix)
            {
                if (value != null && !((String)value).trim().equals("") && !((String)value).startsWith("@"))
                {
                    value = "@" + value.toString();
                }
            }
        }
        else if (column == ParametersData.DEFAULT_COLUMN && value != null && element instanceof TableItem)
        {
            Object obj = ((TableItem)element).getData();
            if (obj != null && obj instanceof ParametersRowData)
            {
                SybaseParameter p = ((ParametersRowData)obj).getParameter();
                if (p != null )
                {
                    DataType type = p.getDataType();
                    if(isStringDataType(type))
                    {
                        String unquote = SQLUtil.unquote((String)value);
                        //if there're separators in the value, it must be quoted
                        Matcher m = SybaseParameterValidator.NON_WORD.matcher((String)value);
                        if (m.find())
                        {
                            if (value.equals(unquote) && !_supportsExp)
                            {
                                value = SQLUtil.quote((String)value, '\'');
                            }
                        }
                    }
                }
            }
        }
        super.modify(element, property, value);
    }
    
    private boolean isStringDataType(DataType type)
    {
        if(type instanceof PredefinedDataType)
        {
            return ( type instanceof CharacterStringDataType || type instanceof DateDataType || type instanceof TimeDataType); 
        }
        else if (type instanceof DistinctUserDefinedType)
        {
            return isStringDataType(((DistinctUserDefinedType)type).getPredefinedRepresentation());
        }
        return false;
    }
}
