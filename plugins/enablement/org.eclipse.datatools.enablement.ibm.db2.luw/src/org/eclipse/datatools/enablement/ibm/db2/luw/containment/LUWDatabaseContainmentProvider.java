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
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.DatabaseContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.emf.ecore.EObject;

public class LUWDatabaseContainmentProvider extends DatabaseContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		LUWDatabase database = (LUWDatabase) obj;
		children.addAll(database.getGroups());
		children.addAll(database.getBufferpools());
		children.addAll(database.getWrappers());
		children.addAll(database.getStorageGroups());
		// Servers are now contained under LUWWrapper
		// children.addAll(database.getServers());
//		children.addAll(database.getTablespaces());
		Iterator it = database.getGroups().iterator();
		while(it.hasNext()) {
			LUWPartitionGroup g = (LUWPartitionGroup) it.next();
			children.addAll(g.getTableSpaces());
		}
		Iterator tps = database.getTablespaces().iterator();
		while (tps.hasNext()) {
			LUWTableSpace tp = (LUWTableSpace) tps.next();
			if(!children.contains(tp)){
				children.add(tp);
			}
		}

		return children;
	}
}
