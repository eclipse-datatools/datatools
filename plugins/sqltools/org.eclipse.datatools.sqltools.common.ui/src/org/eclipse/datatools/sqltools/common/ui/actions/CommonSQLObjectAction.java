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
package org.eclipse.datatools.sqltools.common.ui.actions;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class CommonSQLObjectAction extends Action implements IObjectActionDelegate {

	protected SQLObject _sqlObject = null;

	protected Database _database = null;

	protected IConnectionProfile _connectionProfile = null;
	
	protected IWorkbenchPart _targetPart = null;

	/**
	 * 
	 */
	public CommonSQLObjectAction() {
	}

	/**
	 * Constructs a CommonSQLObjectAction from the selected resource. This
	 * happens when this action is instantiated by the common action provider.
	 */
	public CommonSQLObjectAction(Object selectedResource) {
		initSQLObject(this, selectedResource);
		initConnectionProfile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_connectionProfile = null;
		_sqlObject = null;
		_database = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				Object selectedResource = structuredSelection.getFirstElement();
				initSQLObject(action, selectedResource);
			}
		}
		initConnectionProfile();

	}

	/**
	 * Inits the connection profile field based on Database
	 *
	 */
	protected void initConnectionProfile() {
		_connectionProfile = getConnectionProfile(_database);
		
	}

	public static IConnectionProfile getConnectionProfile(Database database) {
		if (database != null) {
			ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance()
					.getConnectionForDatabase(database);
			if (connInfo instanceof ConnectionInfoImpl) {
				return ((ConnectionInfoImpl) connInfo)
						.getConnectionProfile();
			}
		}
		return null;
	}

	/**
	 * Subclass should override this method to initialize the _database object based on the selected sql object.
	 * @param action
	 * @param selectedResource
	 */
	protected void initSQLObject(IAction action, Object selectedResource) {
		if (selectedResource instanceof SQLObject) {
			_sqlObject = (SQLObject) selectedResource;
			action.setEnabled(true);
		}
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
	}
	
	
}