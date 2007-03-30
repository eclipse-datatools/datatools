/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;

public class AddRepositoryViewAction extends AddProfileViewAction {

	public void run() {
		setCategory(IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID);
		super.run();
	}

}
