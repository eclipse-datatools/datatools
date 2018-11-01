/*******************************************************************************
 * Copyright (c) 2010, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;

public interface DBReverseProvider {

	public void reverseEngineer(Database database, int options,EObject[] objectsToRE,IProgressMonitor monitor);
	public void prepareLoader(Database database, int options,EObject[] objectsToRE);
	public void removeNeedlessObjects(EObject[] clonedObjects,int options);
}
