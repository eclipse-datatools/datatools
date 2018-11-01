/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sample.wizards;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class SelectExistingConnectionWizardPage extends WizardPage {

	SelectExistingConnectionProfileDialogPage dialogPage;
	
	protected SelectExistingConnectionWizardPage(String pageName) {
		super(pageName);
	}

	public void createControl(Composite parent) {
        setTitle("Sample Page"); //$NON-NLS-1$
        setMessage("Select a connection.  This sample wizard will only display JDBC connections \nto demonstrate the filter capabilities of the dialog page.");
        
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

        dialogPage = new SelectExistingConnectionProfileDialogPage (this, true, true);
        dialogPage.createControl(container); 
        setControl(container);
	}

	public void handleEvent(Event event) {
		this.getContainer().updateButtons();
	}
	
	public IConnectionProfile getSelectedConnection(){
		IConnectionProfile connection =null;
		if(dialogPage != null){
			connection = dialogPage.getSelectedConnection();
		}
		return connection;
	}
}
