/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.emf.ecore.EObject;


/**
 * Convert the SQL model to the edit model used by accessble table viewer
 * 
 * @author Idull
 */
public class ModelConverter
{
    public ASATableEditorColumnsTableData getColumnsData(BaseTable table, Vector rows)
    {
        Database db = null;
        EObject obj = ContainmentServiceImpl.INSTANCE.getRootElement(table);
        if (obj instanceof Catalog)
        {
            Catalog catalog = (Catalog) obj;
            db = catalog.getDatabase();
        }
        else if (obj instanceof Database)
        {
            db = (Database) obj;
        }
        ASATableEditorColumnsTableData data = createTableData();

        if (db == null)
        {
            return data;
        }

        DatabaseDefinition dbdef = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(db);
        int i=0;
        Iterator iter = table.getColumns().iterator();
        while (iter.hasNext())
        {
            SybaseASABaseColumn column = (SybaseASABaseColumn) iter.next();

            ASATableEditorColumnRowData row = new ASATableEditorColumnRowData(data, RowData.STATE_ORIGINAL,
                    new Object[data.getColumnCount() + 1]);
            
            /**
             * Restore the information whether the row is a newly added one. If the model is a brand new one (cloned
             * after sync with the db), this "newly added" information will be cleared in ASAColumnsEditorPage.refresh()
             */
            if (rows != null && rows.size() > i)
            {
                if(rows.get(i) instanceof ASATableEditorColumnRowData)
                {
                    ASATableEditorColumnRowData r = (ASATableEditorColumnRowData)rows.get(i);
                    row.setIsNewlyAdded(r.isNewlyAdded());
                }
            }
            row.updateValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN, column.isPartOfPrimaryKey() ? "true"
                    : "false");
            row.updateValue(ASATableEditorColumnsTableData.NAME_COLUMN, column.getName());
            String dTypeString = "";
            boolean convert2LowerCase = false;
            if (column.getDataType() instanceof PredefinedDataType)
            {
                dTypeString = dbdef.getPredefinedDataTypeFormattedName((PredefinedDataType) column.getDataType());
                convert2LowerCase = true;
            }
            else
            {
                // In some very special case, the type can be null, should not happen
                if (column.getDataType() != null)
                {
                    dTypeString = column.getDataType().getName();
                }
                else
                {
                    /**
                     * The reason we copy the datatype string to the new row model is when the datatype the user inputs is
                     * an invalid one, it wont be stored in the table model (Table Column), thus we need to copy the original
                     * error string back and display it in the columns page.
                     */
                    if (rows != null && rows.size() > i)
                    {
                        if(rows.get(i) instanceof ASATableEditorColumnRowData)
                        {
                            ASATableEditorColumnRowData r = (ASATableEditorColumnRowData)rows.get(i);
                            dTypeString = r.getDataTypeString();
                        }
                    }
                }
            }
            row.setDataTypeString(dTypeString);
            

            row.updateValue(ASATableEditorColumnsTableData.TYPE_COLUMN,
                    dTypeString != null ? (convert2LowerCase ? dTypeString.toLowerCase() : dTypeString) : "");
            row.updateValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN, column.isNullable() ? "true" : "false");
            row.updateValue(ASATableEditorColumnsTableData.DEFAULT_COLUMN, column.getDefaultValue() == null ? ""
                    : column.getDefaultValue());
            convertAdditionalPart(row, column);
            row.setColumn(column);
            data.getRows().add(row);
            i++;
        }
        return data;
    }
    
    protected ASATableEditorColumnsTableData createTableData()
    {
        return new ASATableEditorColumnsTableData();
    }
    
    protected void convertAdditionalPart(ASATableEditorColumnRowData row, SybaseASABaseColumn column)
    {
        row.updateValue(ASATableEditorColumnsTableData.COMMENT_COLUMN, column.getDescription() == null ? ""
                : column.getDescription());
    }
}
