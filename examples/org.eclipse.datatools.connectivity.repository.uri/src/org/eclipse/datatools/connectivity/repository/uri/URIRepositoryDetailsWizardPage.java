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

import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class URIRepositoryDetailsWizardPage extends
		ConnectionProfileDetailsPage {

	private Text mPathText;

	public URIRepositoryDetailsWizardPage(String name) {
		super(name);
		setTitle("URI Repository Details");
		setDescription("Please specify a URI location.");
	}

	public String getFileName() {
		return mPathText.getText();
	}

	public void createCustomControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		content.setLayout(layout);

		Label label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText("Directory Path:");

		mPathText = new Text(content, SWT.BORDER);
		mPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mPathText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				handlePathModify();
			}
		});

		Button button = new Button(content, SWT.PUSH);
		button.setText("Browse...");
		button.setLayoutData(new GridData());
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});

	}

	private void handleBrowse() {
		FileDialog dialog = new FileDialog(getShell());
		String path = dialog.open();
		if (path != null) {
			mPathText.setText(path);
		}
	}

	private void handlePathModify() {
		validate();
	}

	private void validate() {
		String errorMessage;
		String path = mPathText.getText();
		if (path == null) {
			errorMessage = "Please specify repository location.";
		}
		else {
			errorMessage = null;
		}
		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
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

	public void createControl(Composite parent) {
		super.createControl(parent);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpContextsRepositoryURI.URI_REPOSITORY_PROFILE_WIZARD_PAGE, URIRepositoryPlugin.getDefault().getBundle().getSymbolicName()));
	}
}
