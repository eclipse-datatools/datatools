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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLFileUtil {

	public static ISQLEditorConnectionInfo getConnectionInfo(IFile file) {
		String encodedConnection = null;
		encodedConnection = getEncodedConnectionInfo(file);
		ISQLEditorConnectionInfo connectionInfo;
		if ((encodedConnection != null)
				&& (!encodedConnection.trim().equals(""))) {
			connectionInfo = SQLEditorConnectionInfo.decode(encodedConnection);
		} else {
			connectionInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
		}
		return connectionInfo;
	}

	public static String getEncodedConnectionInfo(IFile file) {
		String encodedConnection = null;
		if (file != null) {
			try {
				encodedConnection = file
						.getPersistentProperty(new QualifiedName(
								SqlscrapbookPlugin.PLUGIN_ID,
								"encodedConnection"));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return encodedConnection;
	}

	public static void setEncodedConnectionInfo(IFile fileResource, String encodedConnection)
	{
		try {
			// Save PersistentProperty encodedConnection for not *.sqlpage
			if (fileResource.exists()) {
				fileResource.setPersistentProperty(new QualifiedName(
						SqlscrapbookPlugin.PLUGIN_ID, "encodedConnection"),
						encodedConnection);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
