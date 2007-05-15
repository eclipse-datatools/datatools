/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core;

import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.editor.contentassist.ContentAssistQueryRequest;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;
import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 * 
 */
public class DerbyControlConnection extends AbstractControlConnection implements
		IControlConnection {

	/**
	 * @param sd
	 * @throws SQLException
	 * @throws NoSuchProfileException
	 */
	public DerbyControlConnection(IControlConnectionManager manager,
			DatabaseIdentifier databaseIdentifier) {
		super(manager, databaseIdentifier);
	}

	protected void aboutToDisconnect() {
	}

	protected IDatatype getUserDataType(String typeName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void profileRenamed(String profileName) {
		// TODO Auto-generated method stub

	}

	public void registerInternalConn(String connid) {
		// TODO Auto-generated method stub

	}

	public void unregisterInternalConn(String connid) {
		// TODO Auto-generated method stub

	}

}
