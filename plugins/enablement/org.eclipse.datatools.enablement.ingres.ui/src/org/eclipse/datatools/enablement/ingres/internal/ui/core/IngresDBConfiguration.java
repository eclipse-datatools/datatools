/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import java.util.HashMap;

import org.eclipse.datatools.enablement.ingres.internal.ui.plan.IngresExplainSQLActionDelegate;
import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.core.services.ExecutionService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.core.services.SQLService;

/**
 * An Ingres related database configuration implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresDBConfiguration extends SQLDevToolsConfiguration {

	private static final String[] PRODUCTS = { "Ingres", "II" };
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration#getConnectionService()
	 */
	public ConnectionService getConnectionService() {
		return new IngresConnectionService();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration#getDBHelper()
	 */
	public DBHelper getDBHelper() {
		return new IngresDBHelper();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration#getSQLService()
	 */
	public SQLService getSQLService() {
		return new IngresSQLService();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration#getExecutionService()
	 */
	public ExecutionService getExecutionService() {
		return new IngresExcecutionService();
	}

	private String format(String in) {
		return in.trim().toLowerCase();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration#recognize(java.lang.String, java.lang.String)
	 */
	public boolean recognize(String product, String version) {
		// TODO extract version from supplied string
		// example product="INGRES" and version="0.1.0.w32/115)"

		// BTF - per bug 347164, the code comparison being done on "II" was conflicting with another
		// enablement project (at JBoss) called Teiid - so there needs to be a better way to handle that
		if (product != null) {
			DatabaseVendorDefinitionId targetid = new DatabaseVendorDefinitionId(
					product, version);
			for (int i = 0; i < PRODUCTS.length; i++) {
				DatabaseVendorDefinitionId id = new DatabaseVendorDefinitionId(
						PRODUCTS[i], getDatabaseVendorDefinitionId()
								.getVersion());
				if (id.equals(targetid)) {
					return true;
				}
			}
			return false;
//			String formattedProduct = format(product);
//			for (int i = 0; i < PRODUCTS.length; i++) {
//				if (formattedProduct.indexOf(format(PRODUCTS[i])) > -1) {
//					return true;
//				}
//			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration#getAssociatedConnectionProfileType()
	 */
	public String[] getAssociatedConnectionProfileType() {
		return new String[] { "org.eclipse.datatools.enablement.ingres.profile.connectionProfile" };
	}

}
