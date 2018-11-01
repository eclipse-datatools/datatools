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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DatabaseHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DeleteHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class TableAliasAction extends Action {

    SQLDomainModel domainModel;

    public TableAliasAction(SQLDomainModel domainModel) {
        super(Messages._UI_ACTION_UPDATE_ALIAS);
        this.domainModel = domainModel;
    }

    SQLQueryObject statement;

    public void setStatement(SQLQueryObject statement) {
        this.statement = statement;
    }

    TableExpression tableExpr;

    public void setTable(TableExpression table) {
        this.tableExpr = table;
    }

    public void run() {
        String tableAliasName = TableHelper.getExposedTableName(tableExpr);
        if (tableAliasName == null || tableAliasName.equals(tableExpr.getName()))
            tableAliasName = ""; //$NON-NLS-1$

        AliasValidator aliasValidator = new AliasValidator(tableAliasName);

        Shell aliasShell = Display.getCurrent().getActiveShell();
        PlatformUI.getWorkbench().getHelpSystem().setHelp(aliasShell, SQLBuilderContextIds.SQLG_UPDATE_ALIAS_TEXT);

        UpdateTableAliasDialog updateAliasDialog = new UpdateTableAliasDialog(aliasShell, Messages._UI_DIALOG_CHANGE_ALIAS_TITLE,
                Messages._UI_DIALOG_CHANGE_ALIAS_TEXT, tableAliasName, aliasValidator); 

        updateAliasDialog.setBlockOnOpen(true);
        int value = updateAliasDialog.open();

        if (value == Window.CANCEL) {
            return;
        } // end of if ()

        String newName = null;

        newName = updateAliasDialog.getNewAlias();
        TableHelper.setTableAliasInTableExpression(tableExpr, newName);

        if (statement instanceof QuerySelectStatement) {
            SelectHelper.refresh((QuerySelectStatement) statement);
        }
        else if (statement instanceof QueryUpdateStatement) {
            UpdateHelper.refresh((QueryUpdateStatement) statement);
        }
        else if (statement instanceof QueryDeleteStatement) {
            DeleteHelper.refresh((QueryDeleteStatement) statement);
        }
    }

    class UpdateTableAliasDialog extends InputDialog {

        Object object;
        String newAlias = null;
        String initialAlias = null;

        public UpdateTableAliasDialog(Shell parentShell, String title, String message, String initialAlias, AliasValidator aliasValidator) {
            super(parentShell, title, message, initialAlias, aliasValidator);
            this.initialAlias = initialAlias;
        }

        public String getNewAlias() {
            return getValue();
        }
    }

    class AliasValidator implements IInputValidator {

        String initialAlias;

        public AliasValidator(String initialAlias) {
            super();
            this.initialAlias = initialAlias;
        }

        public String getInitialAlias() {
            return initialAlias;
        }

        public String isValid(String newText) {
            newText = newText.toUpperCase().trim();
            if (statement instanceof QuerySelectStatement) {
                boolean aliasIsUnique = true;
                boolean tableUsed = true;
                if (newText.equals("") && tableUsed) { //$NON-NLS-1$
                    return Messages._ERROR_ENTER_ALIAS;
                }
                else if (!aliasIsUnique && !newText.equals(initialAlias.toUpperCase())) {
                    return Messages._ERROR_ALIAS_NOT_UNIQUE_TEXT;
                }
            }

            if (equalsDatabaseTableName(newText)) {
                return Messages._ERROR_ALIAS_TABLE_USED;
            }
            // remove check for alpha-numeric characters to allow DB characters
            // RATLC01113833  
            //else if (!SQLStringHelper.isAlphanumericOrUnderscore(newText)) {
            //    return Messages._ERROR_ALIAS_NOT_ALPHANUMERIC;
            //}
            // end RATLC01113833 
            return null;
        }

        /**
         * Look at all database tables - return true if name of any table is same as aliasString
         */
        private boolean equalsDatabaseTableName(String aliasString) {
            Iterator dbTableIterator = null;
            Database database = domainModel.getDatabase();
            dbTableIterator = DatabaseHelper.getTableList(database).iterator();
            while (dbTableIterator.hasNext()) {
                Table table = (Table) dbTableIterator.next();
                if (table != null && table.getName().toUpperCase().equals(aliasString)) {
                    return true;
                }
            }
            return false;
        }
    }
}// TableAliasAction
