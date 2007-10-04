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
package org.eclipse.datatools.enablement.ibm.db2.iseries.internal.ui;

import org.eclipse.datatools.connectivity.db.generic.ui.GenericDBProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.ibm.db2.internal.iseries.IISeriesConnectionProfileConstants;

public class ISeriesDBProfileDetailsWizardPage 
	extends GenericDBProfileDetailsWizardPage{

	public ISeriesDBProfileDetailsWizardPage(String pageName) {
		super(pageName);
		setDriverCategory(IISeriesConnectionProfileConstants.DB2_ISERIES_CATEGORY_ID);	
	}
}
