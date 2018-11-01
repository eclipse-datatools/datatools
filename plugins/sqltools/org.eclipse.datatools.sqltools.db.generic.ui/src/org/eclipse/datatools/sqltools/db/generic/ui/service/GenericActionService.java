/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.generic.ui.service;

import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;

/**
 * 
 * @author Hui Cao
 * 
 */
public class GenericActionService extends ActionService {

	public boolean supportsAction(String actionId) {
		return actionId != null
				&& (actionId == ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID
                        || actionId == ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID
                        || actionId == ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID
                        || actionId == ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID
                        || actionId == ISQLEditorActionConstants.RUN_ACTION_ID || actionId == ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID);
	}

}
