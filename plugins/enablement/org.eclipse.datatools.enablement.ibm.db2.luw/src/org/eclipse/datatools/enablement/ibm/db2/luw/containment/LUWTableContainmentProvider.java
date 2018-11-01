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

import org.eclipse.datatools.enablement.ibm.db2.containment.DB2TableContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.emf.ecore.EObject;

public class LUWTableContainmentProvider extends DB2TableContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		LUWTable table = (LUWTable) obj;
		LUWSecurityPolicy policy = table.getSecurityPolicy();
		if (policy != null) {
			children.add(policy);
		}
		return children;
	}
}
