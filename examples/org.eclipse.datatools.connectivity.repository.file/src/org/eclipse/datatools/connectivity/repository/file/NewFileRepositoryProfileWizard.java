/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.repository.file;

import java.util.Properties;

import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Composite;

public class NewFileRepositoryProfileWizard extends NewConnectionProfileWizard
		implements IWizard {

	protected FileRepositoryDetailsWizardPage mPropPage;

	public NewFileRepositoryProfileWizard() {
		setWindowTitle("New Connection Profile File Repository");
	}

	public void addCustomPages() {
		mPropPage = new FileRepositoryDetailsWizardPage("detailsPage"); //$NON-NLS-1$
		addPage(mPropPage);
	}

	public Properties getProfileProperties() {
		Properties props = new Properties();
		props.setProperty(FileRepositoryPlugin.FILE_NAME_PROP_ID, mPropPage
				.getFileName());
		props.setProperty(FileRepositoryPlugin.ENCRYPT_PROP_ID, Boolean
				.valueOf(mPropPage.getEncrypt()).toString());
		if (mPropPage.getEncrypt()) {
			props.setProperty(FileRepositoryPlugin.PASSWORD_PROP_ID, mPropPage
					.getPassword());
		}
		return props;
	}

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(FileRepositoryPlugin.getDefault().getBundle().getSymbolicName());

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
				IHelpContextsRepositoryFile.FILE_REPOSITORY_PROFILE_WIZARD,
				FileRepositoryPlugin.getDefault().getBundle().getSymbolicName()));
	}
}
