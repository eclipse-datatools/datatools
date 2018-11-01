/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCUserDefinedFunction;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUDFColumnLoader;

public class DerbyCatalogUserDefinedFunction extends JDBCUserDefinedFunction {
	private static final long serialVersionUID = 3905244528465163826L;

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCUserDefinedFunction#createParameterLoader()
	 */
	protected JDBCUDFColumnLoader createParameterLoader() {
		return new DerbyUserDefinedFunctionParameterLoader(this);
	}

}
