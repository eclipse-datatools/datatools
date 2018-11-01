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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.TString;
import org.eclipse.jface.action.Action;


/**
 * Add a With table to the With Statement
 */
public class CreateWithTableAction extends Action {

    QuerySelectStatement withStatement;
    SQLDomainModel domainModel;

    public CreateWithTableAction(SQLDomainModel domainModel) {
        super(Messages._UI_ACTION_ADD_COMMON_TABLE);
        this.domainModel = domainModel;
    }

    public void setElement(Object obj) {
        if (obj instanceof QuerySelectStatement) {
            withStatement = (QuerySelectStatement) obj;
        }
        else if (obj instanceof QueryExpressionRoot) {
            withStatement = ((QueryExpressionRoot) obj).getSelectStatement();
        }
    }

    public void run() {
        createWithTable();
    }

    /**
     * Add the common table expression, including its sub-select
     */
    private void createWithTable() {
        // this method will generate a default name
        WithTableSpecification withTable = SelectHelper.createWithTableSpecification();
        QuerySelect qSelect = StatementHelper.createQuerySelect();
        withTable.setWithTableQueryExpr(qSelect);

        if (withStatement.getQueryExpr() == null) {
            StatementHelper.createQueryExpressionRoot(withStatement);
        }
        withTable.setName(getNewName());
        withStatement.getQueryExpr().getWithClause().add(withTable);
        SelectHelper.refresh(withStatement);
    }

    public String getNewName() {
        boolean done = false;
        String retVal = ""; //$NON-NLS-1$
        int suff = 1;

        while (!done) {
            String msg = Messages._UI_WITH_TABLE_NAME;
            retVal = TString.change(msg, "%1",Integer.toString(suff) );

            //retVal = "WithTable" + suff; //$NON-NLS-1$
            done = !isDupWithTableName(retVal);
            suff++;
        }
        return retVal;
    }

    private boolean isDupWithTableName(String name) {
        Iterator iterator = withStatement.getQueryExpr().getWithClause().iterator();
        while (iterator.hasNext()) {
            WithTableSpecification wt = (WithTableSpecification) iterator.next();
            String sname = wt.getName();
            if (sname != null && sname.trim().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
