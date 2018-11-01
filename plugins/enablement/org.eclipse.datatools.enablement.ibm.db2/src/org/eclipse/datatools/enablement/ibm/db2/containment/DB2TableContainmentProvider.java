/*******************************************************************************
 * Copyright (c) 2012, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.TableContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.emf.ecore.EObject;

public class DB2TableContainmentProvider extends TableContainmentProvider {
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		DB2Table table = (DB2Table) obj;
		children.addAll(table.getPermissions());
		children.addAll(table.getMasks());
		return children;
	}	
}
