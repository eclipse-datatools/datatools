/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: danielva - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author danielva
 * 
 * Introduction Wizard Page
 */

public class IntroWizardPage extends BaseWizardPage implements IContextProvider {

	private Text _txtIntroduction;
	private static final String PAGE_NAME = "IntroductionWizardPage"; //$NON-NLS-1$

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

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
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
//				IHelpConstants.CONTEXT_ID_INTRO_WIZARD_PAGE);

        getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_INTRO_WIZARD_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

	}

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}
}
