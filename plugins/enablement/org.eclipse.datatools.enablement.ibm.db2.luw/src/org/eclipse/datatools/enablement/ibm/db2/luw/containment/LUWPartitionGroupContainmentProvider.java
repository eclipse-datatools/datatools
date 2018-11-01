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
package org.eclipse.datatools.enablement.ibm.db2.luw.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWPartitionGroupContainmentProvider extends AbstractContainmentProvider  
{
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		LUWPartitionGroup pg = (LUWPartitionGroup) obj;
		children.addAll(pg.getPartitions());
		return children;
	}

	public EObject getContainer(EObject obj) {
		return ((LUWPartitionGroup) obj).getDatabase();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return LUWPackage.eINSTANCE.getLUWDatabase_Groups();
	}

	public String getGroupId(EObject obj) {
		return LUWGroupID.PARTITION_GROUP;
	}
}
