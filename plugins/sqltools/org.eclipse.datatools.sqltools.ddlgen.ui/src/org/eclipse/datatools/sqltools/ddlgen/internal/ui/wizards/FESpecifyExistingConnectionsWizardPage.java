/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.util.ResourceLoader;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class FESpecifyExistingConnectionsWizardPage extends
        WizardPage {
	FESpecifyExistingConnectionsDialogPage dialogPage;
	
	protected FESpecifyExistingConnectionsWizardPage(String pageName) {
		super(pageName);
	}

	public void createControl(Composite parent) {
        setTitle(ResourceLoader.INSTANCE
        	    .queryString("FEWizard.SpecifyExistingConnectionPage.pageHeaderTitle")); //$NON-NLS-1$
        setMessage(ResourceLoader.INSTANCE
        	    .queryString("FEWizard.SpecifyExistingConnectionPage.pageHeaderMessage")); //$NON-NLS-1$
        
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        container.setLayout(gridLayout);
        
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        container.setLayoutData(gridData);

        dialogPage = new FESpecifyExistingConnectionsDialogPage (this, true, true);
        dialogPage.createControl(container); 
        setControl(container);
        
        setPageComplete(false);
	}

	public void handleEvent(Event event) {
		if (getSelectedConnection() != null){
			setPageComplete(true);
		} else {
			setPageComplete(false);
		}
		this.getContainer().updateButtons();
	}
	
	public IConnectionProfile getSelectedConnection(){
		IConnectionProfile connection =null;
		if(dialogPage != null){
			connection = dialogPage.getSelectedConnection();
		}
		return connection;
	}
	
	/**
	 * @param allowedProduct
	 *            The allowedProduct to set.
	 */
	public void setAllowedVendor(String allowedVendor) {
		if (dialogPage != null){
			dialogPage.setAllowedVendor(allowedVendor);
		}
	}

	/**
	 * @param allowedVersion
	 *            The allowedVersion to set.
	 */
	public void setAllowedVersion(String allowedVersion) {
		if (dialogPage != null){
			dialogPage.setAllowedVersion(allowedVersion);
		}
	}
}
