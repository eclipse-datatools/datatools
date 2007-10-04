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
package org.eclipse.datatools.enablement.ibm.db2.luw.internal.ui;

import org.eclipse.datatools.connectivity.db.generic.ui.GenericDBProfilePropertyPage;
import org.eclipse.datatools.enablement.ibm.db2.internal.luw.ILUWConnectionProfileConstants;

public class LUWDBProfilePropertyPage extends GenericDBProfilePropertyPage {

	public LUWDBProfilePropertyPage() {
		super();
		noDefaultAndApplyButton();
		setDriverCategory(ILUWConnectionProfileConstants.DB2_LUW_CATEGORY_ID);	
	}

}
