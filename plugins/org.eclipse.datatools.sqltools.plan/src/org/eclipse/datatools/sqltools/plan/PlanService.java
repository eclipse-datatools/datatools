/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan;

import org.eclipse.datatools.sqltools.plan.treeplan.TreePlanDrawer;

/**
 * 
 * @author Hui Cao
 * 
 */
public class PlanService implements IPlanService {

	public PlanSupportRunnable createPlanSupportRunnable(PlanRequest request,
			String profileName, String dbName) {
		return null;
	}

	public IPlanDrawer getPlanDrawer() {
		return new TreePlanDrawer();
	}

	public IPlanOption getPlanOption() {
		return new PlanOption();
	}

	public IPlanParser getPlanParser() {
		return null;
	}

}
