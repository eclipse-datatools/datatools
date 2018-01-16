/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.TableModelUtil;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataCellModifier;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;


/**
 * Cell modified for ASA columns table viewer
 * 
 * @author Idull
 */
public class ASATableEditorColumnsViewerCellModifier extends TableDataCellModifier
{
    public ASATableEditorColumnsViewerCellModifier(AccessibleTableViewer viewer, BaseTable table)
    {
        super(viewer);
    }

    public void setViewer(AccessibleTableViewer viewer)
    {
        _viewer = viewer;
    }

    /**
     * 1.If the current column is PRI_KEY_COLUMN, and current value is false, if it's the last column in PK, should
     * popup dialog to let user confirm; <br>
     * 2.<br>
     */
    public void modify(Object element, String property, Object value)
    {
        IRowData row = _viewer.getRow();

        boolean orginalPKState = false;

        // It can be a new column
        if (row != null && row.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN) != null)
        {
            orginalPKState = Boolean.parseBoolean((String) row.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN));
        }
        super.modify(element, property, value);
        row = _viewer.getRow();

        // Should remove unique constraint if the column is changed to nullable
        int column = getColumnIndex(property);

        boolean nullable = false;
        if (row.getValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN) != null)
        {
            nullable = Boolean.valueOf((String) row.getValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN))
                    .booleanValue();
        }
        boolean isPK = false;
        if (row.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN) != null)
        {
            isPK = Boolean.valueOf((String) row.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN)).booleanValue();
        }

        // Current edit column is PRI_KEY_COLUMN
        if (column == ASATableEditorColumnsTableData.PRI_KEY_COLUMN)
        {
            // Should uncheck "nullable" option if current column is select to be a column in PK
            if (nullable && isPK)
            {
                row.updateValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN, "false"); //$NON-NLS-1$
            }

            // Should remove pk if this is the last column in pk and it's unchecked
            if (!isPK && orginalPKState)
            {
                boolean needContinue = removeReferencingUniqueConstraints((ASATableEditorColumnRowData) row, true);
                if (!needContinue)
                {
                    row.updateValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN, "true"); //$NON-NLS-1$
                }
                else
                {
                    ((ASATableEditorColumnRowData) row).markDirty(true);
                    _viewer.refresh();
                }
            }
            else if (isPK && !orginalPKState)
            {
                ((ASATableEditorColumnRowData) row).markDirty(true);
                _viewer.refresh();
            }
        }

        // Current edit column is NULLABLE_COLUMN
        if (column == ASATableEditorColumnsTableData.NULLABLE_COLUMN)
        {
            // Should remove unique constraint/pk if this is the last column in members list
            if (nullable)
            {
                boolean needContinue = removeReferencingUniqueConstraints((ASATableEditorColumnRowData) row, false);
                if (needContinue)
                {
                    row.updateValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN, "false"); //$NON-NLS-1$
                }
            }
        }

        _viewer.refresh();
    }

    private boolean removeReferencingUniqueConstraints(ASATableEditorColumnRowData asaRow, boolean onlyRemovePK)
    {
        // If current is nullable and it's referenced by unique constraint/pk, should remove it from the constraint
        if (asaRow.getColumn() != null)
        {
            BaseTable table = (BaseTable) asaRow.getColumn().eContainer();
            List matches = TableModelUtil.getMatchedColumnUniqueConstraint(table, asaRow.getColumn());
            if(onlyRemovePK)
            {
            	Iterator iter = matches.iterator();
            	matches = new ArrayList();
            	while(iter.hasNext())
            	{
            		Object obj = iter.next();
            		if(obj instanceof PrimaryKey)
            		{
            			matches.add(obj);
            			break;
            		}
            	}
            }
            if (matches.size() > 0)
            {

                if (true)
                {
                    MessageDialog d = new MessageDialog(_viewer.getControl()
                            .getShell(), Messages.ASATableEditorColumnsViewerCellModifier_remove_constraints, null, 
                            Messages.ASATableEditorColumnsViewerCellModifier_constraints_remove_also
                            + TableModelUtil.constructConstraintNamesList(matches)
                            + Messages.ASATableEditorColumnsViewerCellModifier_continue, MessageDialog.QUESTION, new String[] { IDialogConstants.OK_LABEL,
                                    IDialogConstants.CANCEL_LABEL }, 0);
                    d.open();
                    int result = d.getReturnCode();
                    switch (result)
                    {
                        case MessageDialog.OK:
                        	Iterator iter = matches.iterator();
                            List uniquesTobeRemoved = new ArrayList();
                            // table.eSetDeliver(true);
                            while (iter.hasNext())
                            {
                                uniquesTobeRemoved.add(iter.next());
                            }
                            table.getConstraints().removeAll(uniquesTobeRemoved);
                            // table.eSetDeliver(false);
                            return true;
                        case MessageDialog.CANCEL:
                            asaRow.updateValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN, "false"); //$NON-NLS-1$
                            _viewer.refresh();
                            return false;
                        default:
                            break;
                    }
                }
                else
                {
                    Iterator iter = matches.iterator();
                    // table.eSetDeliver(true);
                    while (iter.hasNext())
                    {
                        table.getConstraints().remove(iter.next());
                    }
                    // table.eSetDeliver(false);
                    return true;
                }
            }
            boolean nullable = false;
            if (asaRow.getValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN) != null)
            {
                nullable = Boolean.valueOf((String) asaRow.getValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN))
                        .booleanValue();
            }
            
            // If current column is selected to be nullable, should remove it from unique constraints
            if (nullable)
            {
                TableModelUtil.removeColumnFromRefConstraints(table, asaRow.getColumn());
            }
            
            boolean isPK = false;
            if (asaRow.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN) != null)
            {
                isPK = Boolean.valueOf((String) asaRow.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN)).booleanValue();
            }
            // If current column is deleted from PK, remove it from PK
            if (table.getPrimaryKey() != null && !isPK)
            {
                table.getPrimaryKey().getMembers().remove(asaRow.getColumn());
            }
        }
        return true;
    }

    public boolean canChange(Object element, int index)
    {
        if (index == ASATableEditorColumnsTableData.UNIQUE_COLUMN)
        {
            return false;
        }
        if (index == ASATableEditorColumnsTableData.MARKER_COLUMN)
        {
            return false;
        }
        return true;
    }

}
