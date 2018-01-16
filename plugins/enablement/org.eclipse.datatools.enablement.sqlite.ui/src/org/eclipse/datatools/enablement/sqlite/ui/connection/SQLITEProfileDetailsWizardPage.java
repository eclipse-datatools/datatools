/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Brian Fitzpatrick - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sqlite.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.sqlite.ISQLITEConnectionProfileConstants;

public class SQLITEProfileDetailsWizardPage extends
		ExtensibleProfileDetailsWizardPage {

	public SQLITEProfileDetailsWizardPage(String pageName) {
		super(pageName, ISQLITEConnectionProfileConstants.SQLITE_CATEGORY_ID);
	}
}
