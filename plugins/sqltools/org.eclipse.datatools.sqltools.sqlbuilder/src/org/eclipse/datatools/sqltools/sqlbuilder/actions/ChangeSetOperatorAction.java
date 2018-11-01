/*******************************************************************************
 * Copyright © 2000, 2009 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.ChangeSetOperatorDialog;
import org.eclipse.jface.window.Window;

/**
 * Action for adding the SQL Statement query combined operator, 
 * e.g. from  UNION/INTERSECT/EXCEPT.
 * 
 * @author drigby
 * 
 */
public class ChangeSetOperatorAction extends SQLBuilderAction {
    QueryCombined fQueryCombined;

	/**
	 * Constructs an instance of this class. This is the default constructor.
	 */
	public ChangeSetOperatorAction() {
		super(Messages._UI_ACTION_CHANGE_SET_OPERATOR);
	}

	/**
	 * Sets the "query combined" (a query with UNION, INTERSECT, etc. operator)
	 * that this action should operate on.
	 * 
	 * @param queryCombined the query combined object to use
	 */
    public void setElement(QueryCombined queryCombined) {
        fQueryCombined = queryCombined;
    }
    
    /**
     * Executes the action defined by this class.
     */
	public void run() {
		if (fQueryCombined != null) {

		    QueryCombinedOperator combinedOper = fQueryCombined.getCombinedOperator();
		    int combinedOperVal = combinedOper.getValue();
			ChangeSetOperatorDialog dialog = new ChangeSetOperatorDialog(
					getShell(), combinedOperVal);
			dialog.create();
			dialog.setBlockOnOpen(true);
			if (dialog.open() == Window.CANCEL) {
				return;
			} else { // Change the set operator
			    int setOperType = dialog.getOperatorType();
			    combinedOper = QueryCombinedOperator.get(setOperType);
				fQueryCombined.setCombinedOperator(combinedOper);
			}
		}
	}
}
