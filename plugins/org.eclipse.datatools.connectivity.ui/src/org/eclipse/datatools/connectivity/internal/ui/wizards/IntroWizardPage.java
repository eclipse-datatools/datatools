/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: danielva - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * @author danielva
 * 
 * Introduction Wizard Page
 */

public class IntroWizardPage extends BaseWizardPage {

	private Text _txtIntroduction;
	private static final String PAGE_NAME = "IntroductionWizardPage"; //$NON-NLS-1$

	/**
	 * Constructor
	 */
	public IntroWizardPage() {
		super(PAGE_NAME);

		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				PAGE_NAME + ".title")); //$NON-NLS-1$
		setDescription(""); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();

		gl.numColumns = 1;
		composite.setLayout(gl);

		gd = new GridData();
		gd.heightHint = 100;
		gd.widthHint = 404;
		_txtIntroduction = new Text(composite, SWT.MULTI | SWT.WRAP);
		_txtIntroduction.setLayoutData(gd);
		_txtIntroduction.setEditable(false);
		_txtIntroduction.setText(ConnectivityUIPlugin.getDefault()
				.getResourceString(PAGE_NAME + ".intro")); //$NON-NLS-1$

		setControl(composite);
		setPageComplete(true);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				IHelpConstants.CONTEXT_ID_INTRO_WIZARD_PAGE);

	}
}
