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
package org.eclipse.datatools.enablement.internal.oracle;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnection;

public class JDBCOracleJDBCConnection extends JDBCConnection {

	public JDBCOracleJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}
}
