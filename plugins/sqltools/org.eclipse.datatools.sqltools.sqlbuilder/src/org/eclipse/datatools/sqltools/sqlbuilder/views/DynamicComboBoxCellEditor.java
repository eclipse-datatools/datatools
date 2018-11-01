/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSearchTable;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSimpleTable;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function.ParamTable;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionElement;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionsTable;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.insert.InsertGridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectGridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateTreeViewer;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;


public class DynamicComboBoxCellEditor extends EditComboBoxCellEditor {

    TableViewer tableViewer = null;
    TableTreeViewer tableTreeViewer = null;
    private ITextProvider fTextProvider;


    public DynamicComboBoxCellEditor(Composite parent, LabelValuePair[] items, Object viewer) {
        super(parent, items, true);
        if (viewer instanceof TableViewer) {
            tableViewer = (TableViewer) viewer;
        }
        else if (viewer instanceof UpdateTreeViewer) {
            tableTreeViewer = (UpdateTreeViewer) viewer;
        }
    }

    public void addItemsToStart(LabelValuePair[] newItemsToAdd) {
        LabelValuePair[] fItemsNew = new LabelValuePair[newItemsToAdd.length + fItems.length];
        int i = 0;
        for (i = 0; i < newItemsToAdd.length; i++) {
            fItemsNew[i] = newItemsToAdd[i];
        }
        for (int j = 0; j < fItems.length; j++) {
            fItemsNew[i] = fItems[j];
            i++;
        }
        fItems = new LabelValuePair[fItemsNew.length];
        fItems = fItemsNew;
    }

    /**
     * Gets the current text provider.
     * 
     * @return the text provider or null if none was set
     */
    public ITextProvider getTextProvider() {
        return fTextProvider;
    }

    /**
     * Sets the text provider to the given object.
     * 
     * @param textProvider the text provider to set
     */
    public void setTextProvider(ITextProvider textProvider) {
        fTextProvider = textProvider;
    }

	/* Handle a set focus by setting the combo value to the current
	 * cell value.  If we can't reasonably determine the cell value,
	 * set it to blank. */
	protected void doSetFocus() {
		String valStr = "";
	    Object valObj = getValue();
	    if (valObj instanceof CriteriaElement) {
            /* If this combo box cell editor is being used in the Criteria (WHERE clause) grid, 
             * then get the combo text from the appropriate part of the current value using the 
             * text provider. */
            ITextProvider textProvider = getTextProvider();
            if (textProvider != null) {
                String text = textProvider.getText(valObj);
                if (text != null) {
                    valStr = text; 
                }
            }
	    }
	    if (valObj instanceof ExpressionElement) {
	    	/* Get the SQL model expression object from the ExpressionElement.
	    	 * In the Expression Builder context the DynamicCombBoxCellEditor
	    	 * is used only for the "Expression by Operators" dialog and the
	    	 * Function Builder dialog, and it appears to be safe to simply
	    	 * get the expression value from the ExpressionElement. */
	    	ExpressionElement exprElem = (ExpressionElement) valObj;
	    	QueryValueExpression expr = exprElem.getExpression();
	    	if (expr != null) {
	    		valStr = expr.getSQL();
	    	}
	    }
	    else if (valObj instanceof SelectTableElement) {
	    	/* Get the column text of the first column, which is the 
	    	 * qualified column name.  The DynamicComboBoxCellEditor class
	    	 * appears to be only used for the first column of the Select
	    	 * grid, so it looks like it's safe to assume that we get our  
	    	 * data from the first column of the SelectTableElement. */
	    	SelectTableElement tableElem = (SelectTableElement) valObj;
	    	valStr = tableElem.getColumnText(0);
	    }
	    else if (valObj instanceof String) {
	    	valStr = valObj.toString();
	    }
        combo.setText(valStr);
        combo.setFocus();
    }

    protected LabelValuePair createComboBoxItem(String newValue) {
        return new LabelValuePair(newValue, ExpressionHelper.createExpression(newValue));
    }

    protected void refreshComboItems() {
        int row = -1;
        if (tableViewer != null) {
            if (tableViewer instanceof CriteriaGridViewer) {
                CriteriaGridViewer cgv = (CriteriaGridViewer) tableViewer;
                row = cgv.getTable().getSelectionIndex();
                if (row >= 0) {
                    cgv.refreshCellEditor(row);
                }
            }
            else if (tableViewer instanceof SelectGridViewer) {
                SelectGridViewer sgv = (SelectGridViewer) tableViewer;
                row = sgv.getTable().getSelectionIndex();
                if (row >= 0) {
                    sgv.refreshCellEditor(row);
                }
            }
            else if (tableViewer instanceof ParamTable) {
                ParamTable ptv = (ParamTable) tableViewer;
                row = ptv.getTable().getSelectionIndex();
                if (row >= 0) {
                    ptv.refreshCellEditor(row);
                }
            }
            else if (tableViewer instanceof ExpressionsTable) {
                ExpressionsTable expTable = (ExpressionsTable) tableViewer;
                row = expTable.getTable().getSelectionIndex();
                if (row >= 0) {
                    expTable.refreshCellEditor(row);
                }
            }
            else if (tableViewer instanceof CaseSearchTable) {
                CaseSearchTable caseSearchTable = (CaseSearchTable) tableViewer;
                row = caseSearchTable.getTable().getSelectionIndex();
                if (row >= 0) {
                    caseSearchTable.refreshCellEditor(row);
                }
            }
            else if (tableViewer instanceof CaseSimpleTable) {
                CaseSimpleTable caseSimpleTable = (CaseSimpleTable) tableViewer;
                row = caseSimpleTable.getTable().getSelectionIndex();
                if (row >= 0) {
                    caseSimpleTable.refreshCellEditor(row);
                }
            }

            else if (tableViewer instanceof InsertGridViewer) {
                InsertGridViewer igv = (InsertGridViewer) tableViewer;
                row = igv.getTable().getSelectionIndex();
                if (row >= 0) {
                    igv.refreshCellEditor(row);
                }
            }
        }
        else if (tableTreeViewer != null) {
            if (tableTreeViewer instanceof UpdateTreeViewer) {
                UpdateTreeViewer utv = (UpdateTreeViewer) tableTreeViewer;
                if (row >= 0) {
                    utv.refresh();
                }
            }
        }
    }
}
