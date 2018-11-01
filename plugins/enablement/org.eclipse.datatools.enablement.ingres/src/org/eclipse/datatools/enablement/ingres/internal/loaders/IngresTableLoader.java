/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogTable;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogView;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * Class for loading a list of tables from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresTableLoader extends JDBCTableLoader {

	/**
	 * Constructor registers new factories for tables and views.
	 */
	public IngresTableLoader() {
		super(null);
		registerTableFactory(TYPE_TABLE, new IngresTableFactory());
		registerTableFactory(TYPE_VIEW, new IngresViewFactory());
	}

	public static class IngresTableFactory extends TableFactory {

		protected Table newTable() {
			return new IngresCatalogTable();
		}

	}

	public static class IngresViewFactory extends ViewFactory {

		protected Table newTable() {
			return new IngresCatalogView();
		}

	}

}
