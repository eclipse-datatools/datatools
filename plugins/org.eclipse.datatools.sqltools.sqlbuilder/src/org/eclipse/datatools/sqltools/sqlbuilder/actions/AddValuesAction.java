/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.jface.action.Action;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

public class AddValuesAction extends Action {

    SQLDomainModel domainModel;
    QueryCombined queryCombined;

    public AddValuesAction(SQLDomainModel domainModel) {
        super(Messages._UI_ACTION_ADD_VALUES);
        this.domainModel = domainModel;
    }

    public void setElement(Object obj) {
        queryCombined = (QueryCombined) obj;
    }

    public void run() {
        QueryValues values = SQLQueryModelFactoryImpl.eINSTANCE.createQueryValues();
        SelectHelper.setChildForQueryCombined(queryCombined, values);

    }
}
