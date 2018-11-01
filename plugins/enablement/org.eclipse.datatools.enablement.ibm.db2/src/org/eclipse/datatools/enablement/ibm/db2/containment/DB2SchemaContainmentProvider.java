/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
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

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.SchemaContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.emf.ecore.EObject;

public class DB2SchemaContainmentProvider extends SchemaContainmentProvider {
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		DB2Schema schema = (DB2Schema) obj;
		children.addAll(schema.getOlapObjects());
		children.addAll(schema.getJars());		
		children.addAll(schema.getXsrObjects());
		children.addAll(schema.getPackages());
		children.addAll(schema.getModules());
		children.addAll(schema.getGlobalVariables());
		return children;
	}
}
