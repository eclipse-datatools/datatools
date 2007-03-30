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
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;

public class NewRepositoryWizard extends NewCPWizard {

	public NewRepositoryWizard() {
		super(new NewCPWizardCategoryFilter(
				IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID),
				null);
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
		setWindowTitle("New Repository Wizard Window"); //$NON-NLS-1$
	}

}
