/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.repository.uri;

import java.util.Properties;

import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Composite;

public class NewURIRepositoryProfileWizard extends NewConnectionProfileWizard
		implements IWizard {

	protected URIRepositoryDetailsWizardPage mPropPage;

	public NewURIRepositoryProfileWizard() {
		setWindowTitle("New Connection Profile URI Repository");
	}

	public void addCustomPages() {
		mPropPage = new URIRepositoryDetailsWizardPage("detailsPage"); //$NON-NLS-1$
		addPage(mPropPage);
	}

	public Properties getProfileProperties() {
		Properties props = new Properties();
		props.setProperty(URIRepositoryPlugin.URI_PATH_PROP_ID, mPropPage
				.getFileName());
		return props;
	}

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(URIRepositoryPlugin.getDefault().getBundle().getSymbolicName());

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
				IHelpContextsRepositoryURI.URI_REPOSITORY_PROFILE_WIZARD,
				URIRepositoryPlugin.getDefault().getBundle().getSymbolicName()));
	}
}
