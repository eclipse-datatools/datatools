/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.schema.Comment;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class CommentContainmentProvider extends AbstractContainmentProvider
{
	public EStructuralFeature getContainmentFeature(EObject obj)
	{
		return SQLSchemaPackage.eINSTANCE.getSQLObject_Comments();
	}

	public EObject getContainer(EObject obj)
	{
		EObject eObject = ((Comment) obj).getSQLObject();
		return eObject;
	}

	public String getGroupId(EObject obj)
	{
		return GroupID.COMMENT;
	}
}
