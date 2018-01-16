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
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.swt.widgets.Composite;

public class NewRepositoryWizard extends NewCPWizard {

	public NewRepositoryWizard() {
		super(new NewCPWizardCategoryFilter(
				IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID),
				null);
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
		setWindowTitle(ConnectivityUIPlugin.getDefault().getResourceString("NewRepositoryWizard.title")); //$NON-NLS-1$
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getShell(), HelpUtil.getContextId(
				IHelpConstants.CONTEXT_ID_NEW_REPOSITORY_WIZARD,
				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
	}

}
