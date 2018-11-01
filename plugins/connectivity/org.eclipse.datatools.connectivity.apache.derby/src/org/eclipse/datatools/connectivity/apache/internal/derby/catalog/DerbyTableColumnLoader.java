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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.modelbase.sql.tables.Column;

public class DerbyTableColumnLoader extends JDBCTableColumnLoader {

	public DerbyTableColumnLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader#createColumn()
	 */
	protected Column createColumn() {
		return new DerbyCatalogColumn();
	}

}
