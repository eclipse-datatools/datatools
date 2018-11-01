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
package org.eclipse.datatools.enablement.mysql.internal.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsPropertyPage;
import org.eclipse.datatools.enablement.internal.mysql.connection.IMySQLConnectionProfileConstants;

public class MySQLDBProfilePropertyPage extends
		ExtensibleProfileDetailsPropertyPage {

	public MySQLDBProfilePropertyPage() {
		super(IMySQLConnectionProfileConstants.MYSQL_CATEGORY_ID);
	}
}
