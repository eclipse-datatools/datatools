/*******************************************************************************
 * Copyright (c) 2008 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Wolfgang Auer - initial implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sap.maxdb.ui.internal.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;


public class NewMaxDBConnectionProfileWizard extends
		ExtensibleNewConnectionProfileWizard {

	public NewMaxDBConnectionProfileWizard() {
		super(
				new MaxDBProfileDetailsWizardPage(
						"org.eclipse.datatools.enablement.sap.maxdb.internal.ui.connection.MaxDBProfileDetailsWizardPage"));
	}
}