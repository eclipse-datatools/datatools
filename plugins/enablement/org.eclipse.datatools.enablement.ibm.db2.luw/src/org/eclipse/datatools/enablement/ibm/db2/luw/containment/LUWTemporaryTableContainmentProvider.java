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

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.TableContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.containment.DB2GroupID;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author bkadambi
 *
 */
public class LUWTemporaryTableContainmentProvider extends TableContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		return children;
	}
	
	public String getGroupId(EObject obj) {
		return DB2GroupID.TEMPTABLE;
	}
}
