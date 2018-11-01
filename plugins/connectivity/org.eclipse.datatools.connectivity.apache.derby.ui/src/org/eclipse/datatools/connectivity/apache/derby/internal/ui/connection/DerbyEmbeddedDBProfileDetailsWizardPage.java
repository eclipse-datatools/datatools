/*******************************************************************************
 * Copyright (c) 2004-2006, 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 *     IBM Corporation - migrated to new wizard framework
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.connection;

import org.eclipse.datatools.connectivity.apache.derby.internal.ui.DerbyUIPlugin;
import org.eclipse.datatools.connectivity.apache.internal.derby.connection.IDerbyConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * This class gathers the properties for the derby embedded DB connection
 * profile.
 * 
 * @author brianf
 */
public class DerbyEmbeddedDBProfileDetailsWizardPage extends
		ExtensibleProfileDetailsWizardPage implements IContextProvider {

	public DerbyEmbeddedDBProfileDetailsWizardPage(String pageName) {
		super(pageName, IDerbyConnectionProfileConstants.DERBY_CATEGORY_ID);
	}

	private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(
			DerbyUIPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getControl(), HelpUtil.getContextId(
				IHelpContextsDerbyProfile.DERBY_PROFILE_WIZARD_PAGE,
				DerbyUIPlugin.getDefault().getBundle().getSymbolicName()));
	}
}
