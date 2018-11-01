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
package org.eclipse.datatools.enablement.ibm.db2.zseries.internal.ui;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.ibm.db2.internal.zseries.IZSeriesConnectionProfileConstants;

public class ZSeriesDBProfileDetailsWizardPage 
	extends ExtensibleProfileDetailsWizardPage{

	public ZSeriesDBProfileDetailsWizardPage(String pageName) {
		super(pageName, IZSeriesConnectionProfileConstants.DB2_ZSERIES_CATEGORY_ID);
	}
}
