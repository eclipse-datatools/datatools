/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ingres.containment.IngresGroupID;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.emf.ecore.EObject;

/**
 * A containment provider to support Ingres synonyms.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSynonymContainmentProvider extends
		AbstractContainmentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider#getContainer(org.eclipse.emf.ecore.EObject)
	 */
	public EObject getContainer(EObject obj) {
		return ((IngresSynonym) obj).getSchema();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentProvider#getGroupId(org.eclipse.emf.ecore.EObject)
	 */
	public String getGroupId(EObject obj) {
		return IngresGroupID.SYNONYM_GROUP_ID;
	}

}
