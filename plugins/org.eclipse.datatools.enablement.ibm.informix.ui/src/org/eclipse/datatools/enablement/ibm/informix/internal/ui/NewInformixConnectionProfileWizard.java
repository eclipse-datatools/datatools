/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.informix.internal.ui;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;

public class NewInformixConnectionProfileWizard extends ExtensibleNewConnectionProfileWizard {

	protected InformixDBProfileDetailsWizardPage mPropPage;

	public NewInformixConnectionProfileWizard() {
		super(new InformixDBProfileDetailsWizardPage("org.eclipse.datatools.enablement.ibm.informix.internal.ui.InformixDBProfileDetailsWizardPage")); //$NON-NLS-1$
	}
}