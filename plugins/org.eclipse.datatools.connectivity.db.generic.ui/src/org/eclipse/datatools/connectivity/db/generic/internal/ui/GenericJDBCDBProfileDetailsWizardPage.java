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
package org.eclipse.datatools.connectivity.db.generic.internal.ui;

import org.eclipse.datatools.connectivity.db.internal.generic.IGenericJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;

public class GenericJDBCDBProfileDetailsWizardPage extends
		ExtensibleProfileDetailsWizardPage {

	public GenericJDBCDBProfileDetailsWizardPage(String pageName) {
		super(pageName, IGenericJDBCConnectionProfileConstants.GENERIC_JDBC_CATEGORY_ID);
	}
}
