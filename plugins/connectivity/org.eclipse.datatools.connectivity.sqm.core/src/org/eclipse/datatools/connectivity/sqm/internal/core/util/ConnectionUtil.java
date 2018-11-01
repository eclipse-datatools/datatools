/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.util;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;

public class ConnectionUtil
{
	public static final String CONNECTION_TYPE = "org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo"; //$NON-NLS-1$
	
	private static final ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
	private static final String CONNECTION_URI = "ConnectionURI"; //$NON-NLS-1$
	private static final String CONNECTION_URI_KEY = "ConnectionKey"; //$NON-NLS-1$

	private static String getConnectionEAnnotationUri()
	{
		return CONNECTION_URI;
	}

	private static String getConnectionEAnnotationKey()
	{
		return CONNECTION_URI_KEY;
	}
	
	public static ConnectionInfo getConnectionForEObject (EObject sqlObject)
	{
		while (sqlObject != null && !SQLSchemaPackage.eINSTANCE.getDatabase().isSuperTypeOf(sqlObject.eClass()))
		{
			sqlObject = containmentService.getContainer(sqlObject);
		}
		return sqlObject != null ? DatabaseConnectionRegistry.getConnectionForDatabase ((Database)sqlObject) : null;
	}

	public static void setConnectionProfile(SQLObject obj, String infoName)
	{
		if (infoName == null) throw new NullPointerException();
		EAnnotation annotation = obj.getEAnnotation(getConnectionEAnnotationUri());
		if (annotation == null)
		{
			annotation = obj.addEAnnotation(getConnectionEAnnotationUri());
		}
		obj.addEAnnotationDetail(annotation, getConnectionEAnnotationKey(), infoName);
	}

	public static void setConnectionProfile(SQLObject obj, IConnectionProfile info)
	{
		setConnectionProfile(obj, info.getName());
	}

	public static String getConnectionProfileName(SQLObject obj)
	{
		EAnnotation annotation = obj.getEAnnotation(getConnectionEAnnotationUri());
		if (annotation == null) return null;
		return obj.getEAnnotationDetail(annotation, getConnectionEAnnotationKey());
	}

}
