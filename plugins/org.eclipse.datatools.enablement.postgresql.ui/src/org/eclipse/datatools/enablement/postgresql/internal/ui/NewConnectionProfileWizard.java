/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.internal.ui;

import org.eclipse.datatools.connectivity.db.generic.GenericDBPlugin;
import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @see Wizard
 */
public class NewConnectionProfileWizard
		extends
		ExtensibleNewConnectionProfileWizard {

	protected PostgreSQLProfileDetailsWizardPage mPropPage;

	public NewConnectionProfileWizard() {
		super(new PostgreSQLProfileDetailsWizardPage("detailsPage"));
		setWindowTitle(GenericDBPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizard.title")); //$NON-NLS-1$
	}
}