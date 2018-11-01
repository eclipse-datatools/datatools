/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.schema.Database;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl;

/**
 * @author cdchu
 */
public class LUWCatalogJar extends DB2JarImpl implements ICatalogObject {
	private boolean loaded = false;
	private boolean dependencyLoaded = false;

   /* (non-Javadoc)
    * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#refresh()
    */
   public void refresh() {
      this.loaded = false;
      
      if (this.dependencyLoaded){
         this.dependencies.clear();
         this.dependencyLoaded = false;
      }
      
      RefreshManager.getInstance().referesh(this);
   }

   /* (non-Javadoc)
    * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#getConnection()
    */
   public Connection getConnection() {
      Database database = this.getCatalogDatabase();
      return ((LUWCatalogDatabase) database).getConnection();
   }

   /* (non-Javadoc)
    * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#getCatalogDatabase()
    */
   public Database getCatalogDatabase() {
      return this.getSchema().getDatabase();		
   }

}
