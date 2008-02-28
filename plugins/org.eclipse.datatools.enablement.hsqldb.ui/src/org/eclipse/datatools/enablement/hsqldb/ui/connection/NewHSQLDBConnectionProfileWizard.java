/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.hsqldb.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;

public class NewHSQLDBConnectionProfileWizard extends
		ExtensibleNewConnectionProfileWizard {
	public NewHSQLDBConnectionProfileWizard() {
		super(
				new HSQLDBProfileDetailsWizardPage(
						"org.eclipse.datatools.enablement.hsqldb.ui.connection.HSQLDBProfileDetailsWizardPage")); //$NON-NLS-1$
	}
}
