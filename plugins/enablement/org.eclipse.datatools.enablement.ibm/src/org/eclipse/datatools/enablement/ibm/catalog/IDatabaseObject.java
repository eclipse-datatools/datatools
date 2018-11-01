/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.catalog;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;

public interface IDatabaseObject {
	public final int IMPACTS = 0;
	public final int STATISTICS = 1;
	
	public ICatalogObject[] getImpacted();
	public Collection getStatistics();
	public void refresh(int refreshType);
}
