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

import java.util.List;

import org.eclipse.jface.action.Action;

import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class AddValueRowAction extends Action {

    SQLDomainModel domainModel;
    QueryValues queryValues;

    public AddValueRowAction(SQLDomainModel domainModel) {
        super(Messages._UI_ACTION_ADD_VALUE_ROW);
        this.domainModel = domainModel;
    }

    public void setElement(Object obj) {
        queryValues = (QueryValues) obj;
    }

    public void run() {
        if (queryValues != null) {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
            ValuesRow valuesRow = factory.createValuesRow();
            List rowList = queryValues.getValuesRowList();
            if (rowList != null) {
                rowList.add(valuesRow);
            }
        }
    }
}
