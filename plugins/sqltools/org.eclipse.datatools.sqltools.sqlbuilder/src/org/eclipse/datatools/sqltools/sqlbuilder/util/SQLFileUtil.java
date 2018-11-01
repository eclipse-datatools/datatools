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
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLFileUtil {

	private static final String ENCODED_CONNECTION_PROPERTYNAME = "encodedConnection";
	private static final String ENCODED_OMIT_SCHEMA_PROPERTYNAME = "encodedOmitSchemaInfo";
	
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
								"org.eclipse.datatools.sqltools.sqlscrapbook",
								ENCODED_CONNECTION_PROPERTYNAME));
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
						"org.eclipse.datatools.sqltools.sqlscrapbook", ENCODED_CONNECTION_PROPERTYNAME),
						encodedConnection);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	public static IOmitSchemaInfo getOmitSchemaInfo(IFile file) {
		String encodedOmitSchemaInfo = null;
		encodedOmitSchemaInfo = getEncodedOmitSchemaInfo(file);
		IOmitSchemaInfo omitSchemaInfo;
		if ((encodedOmitSchemaInfo != null)
				&& (!encodedOmitSchemaInfo.trim().equals(""))) {
			omitSchemaInfo = OmitSchemaInfo.decode(encodedOmitSchemaInfo);
		} else {
			omitSchemaInfo = new OmitSchemaInfo();
			omitSchemaInfo.initFromPreferences();
		}
		return omitSchemaInfo;
	}

	public static String getEncodedOmitSchemaInfo(IFile file) {
		String encodedOmitSchemaInfo = null;
		if (file != null) {
			try {
				encodedOmitSchemaInfo = file
						.getPersistentProperty(new QualifiedName(
								SQLBuilderPlugin.getPlugin().getBundle().getSymbolicName(),
								ENCODED_OMIT_SCHEMA_PROPERTYNAME));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return encodedOmitSchemaInfo;
	}

	public static void setEncodedOmitSchemaInfo(IFile fileResource, String encodedOmitSchemaInfo)
	{
		try {
			// Save PersistentProperty encodedOmitSchemaInfo
			if (fileResource.exists()) {
				fileResource.setPersistentProperty(new QualifiedName(
						SQLBuilderPlugin.getPlugin().getBundle().getSymbolicName(),
						ENCODED_OMIT_SCHEMA_PROPERTYNAME),
						encodedOmitSchemaInfo);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	
}
