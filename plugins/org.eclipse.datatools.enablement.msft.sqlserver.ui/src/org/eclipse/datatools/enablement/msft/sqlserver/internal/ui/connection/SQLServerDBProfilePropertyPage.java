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
package org.eclipse.datatools.enablement.msft.sqlserver.internal.ui.connection;

import org.eclipse.datatools.connectivity.db.generic.ui.GenericDBProfilePropertyPage;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.connection.ISQLServerConnectionProfileConstants;

public class SQLServerDBProfilePropertyPage extends GenericDBProfilePropertyPage {

	public SQLServerDBProfilePropertyPage() {
		super();
		noDefaultAndApplyButton();
		setDriverCategory(ISQLServerConnectionProfileConstants.SQLSERVER_CATEGORY_ID);	
	}

}
