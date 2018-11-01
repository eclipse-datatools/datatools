/*******************************************************************************
 * Copyright (c) 2004-2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 * 			IBM Corporation - defect fix #213266
 ******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.internal.ui;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;

/**
 * This class gathers the properties for the generic DB connection profile.
 * 
 * @author brianf
 */
public class PostgreSQLProfileDetailsWizardPage 
	extends ExtensibleProfileDetailsWizardPage {

	/**
	 * Constructor
	 * 
	 * @param pageName
	 */
	public PostgreSQLProfileDetailsWizardPage(String pageName) {
		super(pageName, "org.eclipse.datatools.enablement.postgresql.driverCategory");
	}

}
