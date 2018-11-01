/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.containment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class DB2AliasContainmentProvider extends AbstractContainmentProvider  {

	public EObject getContainer(EObject obj) {
		return ((DB2Alias) obj).getSchema();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getSchema_Tables();
	}

	public String getGroupId(EObject obj) {
		return DB2GroupID.ALIAS;
	}
	
	public Collection getContainedElements(EObject obj) {
		  List myList = new ArrayList();
		  return (Collection)myList;
	}

}
