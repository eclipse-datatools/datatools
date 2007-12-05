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
package org.eclipse.datatools.enablement.oracle.internal.ui;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.internal.oracle.IOracleConnectionProfileConstants;

public class OracleDBProfileDetailsWizardPage 
	extends ExtensibleProfileDetailsWizardPage{

	public OracleDBProfileDetailsWizardPage(String pageName) {
		super(pageName, IOracleConnectionProfileConstants.ORACLE_CATEGORY_ID);	
	}
}
