/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.plan;

import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.PlanService;
import org.eclipse.datatools.sqltools.plan.PlanSupportRunnable;

/**
 * @author enrico.schenk@ingres.com
 */
public class IngresPlanService extends PlanService {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.plan.PlanService#createPlanSupportRunnable(org.eclipse.datatools.sqltools.plan.PlanRequest, java.lang.String, java.lang.String)
	 */
	public PlanSupportRunnable createPlanSupportRunnable(
			final PlanRequest request, final String profileName,
			final String dbName) {
		return new IngresPlanSupportRunnable(request, profileName, dbName);
	}

}
