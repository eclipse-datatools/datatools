/*******************************************************************************
 * Copyright 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.delete.DeleteViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.FullSelectViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.ValuesRowViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.ValuesViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.insert.InsertViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.with.WithStatementViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.with.WithTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;

/**
 * Design Pane 
 *  SplitWindow is used for insert/update/delete/select statements
 */
public class DesignViewer extends PageBook {
    
    Control fullSelect, valuesRowPage, valuesClause, withStatement, withTable, split;
    SelectViewer selectViewer;
    InsertViewer insertViewer;
    UpdateViewer updateViewer;
    DeleteViewer deleteViewer;
    WithStatementViewer withStatementViewer;
    WithTableViewer withTableViewer;
    FullSelectViewer fullSelectViewer;
    ValuesRowViewer valuesRowViewer;
    ValuesViewer valuesViewer;
    SQLDomainModel sqlDomainModel;    
    
    private Object previousObject; // variable for refresh solution

    public DesignViewer(SQLDomainModel domainModel, Composite parent) {
        super(parent, SWT.NULL);
        this.sqlDomainModel = domainModel;
        createControl(this);
    }

    public SQLDomainModel getDomainModel() {
        return sqlDomainModel;
    }

    /**
     * Returns SWT Widget that will be made the client of the view pane.
     */
    void createControl(Composite parent) {
        // select Viewer
        selectViewer = new SelectViewer(sqlDomainModel);
        selectViewer.createControl(parent);

        // insert Viewer
        insertViewer = new InsertViewer(sqlDomainModel);
        insertViewer.createControl(parent);
        insertViewer.setDesignViewer(this);

        // update Viewer
        updateViewer = new UpdateViewer(sqlDomainModel);
        updateViewer.createControl(parent);

        // delete Viewer
        deleteViewer = new DeleteViewer(sqlDomainModel);
        deleteViewer.createControl(parent);

        //
        // Add the With Statement viewer
        // 
        withStatementViewer = new WithStatementViewer(sqlDomainModel);
        withStatement = withStatementViewer.createControl(parent);

        //
        // Add the With Table viewer
        //
        withTableViewer = new WithTableViewer(sqlDomainModel);
        withTable = withTableViewer.createControl(parent);

        //
        // Add the FullSelect viewers
        //
        fullSelectViewer = new FullSelectViewer(sqlDomainModel);
        fullSelect = fullSelectViewer.createControl(parent);

        valuesRowViewer = new ValuesRowViewer(sqlDomainModel);
        valuesRowPage = valuesRowViewer.createControl(parent);

        valuesViewer = new ValuesViewer(sqlDomainModel);
        valuesClause = valuesViewer.createControl(parent);

    }

    public void inputChanged(Object input) {        
        previousObject = input;
        if (input instanceof QueryInsertStatement) {
            showPage(insertViewer.getControl());            
            insertViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_INSERT_VIEW);
        }
        else if (input instanceof QueryUpdateStatement) {
            showPage(updateViewer.getControl());            
            updateViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_UPDATE_VIEW);
        }
        else if (input instanceof QueryDeleteStatement) {
            showPage(deleteViewer.getControl());            
            deleteViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_DELETE_VIEW);
        }
        else if (input instanceof QuerySelectStatement) {
            QueryExpressionBody queryBody = SelectHelper.getQueryExpressionBody((QuerySelectStatement) input);
            if (queryBody instanceof QuerySelect) {
                selectViewer.setInput(input);
                showPage(selectViewer.getControl());                
                // WITH statements use this viewer as well, set the help accordingly
                QuerySelectStatement sqlSelectStatement = (QuerySelectStatement)input;
                List withClause = sqlSelectStatement.getQueryExpr().getWithClause();
                if(withClause.isEmpty())
                {
                	PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_SELECT_VIEW);
                }
                else
                {
                	PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_WITH_STATEMENT_VIEW);
                }
            }
            else if (queryBody instanceof QueryCombined) {
                showPage(fullSelect);                
                fullSelectViewer.setInput(queryBody);
                PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_FULL_SELECT_VIEW);
            }
            else if (queryBody instanceof QueryValues) {
                showPage(valuesRowPage);                
                valuesRowViewer.setInput(queryBody);
                PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_VALUES_CLAUSE_VIEW);
            }
            else {
                selectViewer.setInput(input);
                showPage(selectViewer.getControl());                
                PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_SELECT_VIEW);
            }

        }
        else if (input instanceof WithTableSpecification) {
            showPage(withTable);            
            withTableViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_WITH_TABLE_VIEW);
        }
        else if (input instanceof QuerySelect) {
            selectViewer.setInput(input);
            showPage(selectViewer.getControl());            
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_SELECT_VIEW);
        }
        else if (input instanceof QueryCombined) {
            showPage(fullSelect);            
            fullSelectViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_FULL_SELECT_VIEW);
        }
        else if (input instanceof QueryValues) {
            showPage(valuesClause);            
            valuesViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_VALUES_CLAUSE_VIEW);
        }
        else if (input instanceof ValuesRow) {
            showPage(valuesRowPage);             
            valuesRowViewer.setInput(input);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_VALUES_ROW_VIEW);
        }
    }
    
    /**
     * Forces the design viewer to changes the input so that the GUI will show the latest
     * model values.  This is a work around solution for the model notification issues
     */
    public void forceRefresh() {    	
        inputChanged(previousObject);
    }

    public void setEnabled(boolean enable) {
        selectViewer.setEnabled(enable);
        insertViewer.setEnabled(enable);
        updateViewer.setEnabled(enable);
        deleteViewer.setEnabled(enable);
        withStatementViewer.setEnabled(enable);
        fullSelectViewer.setEnabled(enable);

        super.setEnabled(enable);
    }
}
