/*******************************************************************************
 * Copyright (c) 2004-2005, 2007 Sybase, Inc.
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
import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

/**
 * @see Wizard
 */
public class NewDerbyEmbeddedConnectionProfileWizard extends
ExtensibleNewConnectionProfileWizard {

	public NewDerbyEmbeddedConnectionProfileWizard() {
		super(new DerbyEmbeddedDBProfileDetailsWizardPage("detailsPage")); //$NON-NLS-1$
		setWindowTitle(Messages.getString("NewDerbyEmbeddedConnectionProfileWizard.DerbyEmbeddedDatabaseWizardTitle")); //$NON-NLS-1$
	}

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(DerbyUIPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getShell(), HelpUtil.getContextId(
				IHelpContextsDerbyProfile.DERBY_PROFILE_WIZARD,
				DerbyUIPlugin.getDefault().getBundle().getSymbolicName()));
	}
}