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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DerbyCatalogTrigger extends TriggerImpl implements ICatalogObject {
	private static final long serialVersionUID = 3976740254881363508L;

	public void refresh() {
		if (this.columnLoaded) {
			this.triggerColumn.clear();
			this.columnLoaded = false;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public EList getTriggerColumn() {
		if(!this.columnLoaded) this.loadColumn();
		return this.triggerColumn;
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database db = this.getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public Database getCatalogDatabase() {
		
		if(this.getSchema().getDatabase() != null)
			return this.getSchema().getDatabase();
		else
			return this.getSchema().getCatalog().getDatabase();
	}
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLTablesPackage.TRIGGER__TRIGGER_COLUMN) {
			this.getTriggerColumn();
		}
		return super.eIsSet(eFeature);
	}
	private synchronized void loadColumn() {
		if(this.columnLoaded ||!this.isUpdateType()) return;
		EList columnList = super.getTriggerColumn();
		Connection connection = this.getConnection();
		if(connection == null) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			final String query = "SELECT REFERENCEDCOLUMNS" + //$NON-NLS-1$
			" FROM SYS.SYSTRIGGERS A, SYS.SYSTABLES B, SYS.SYSSCHEMAS C"+ //$NON-NLS-1$
			" WHERE A.TABLEID=B.TABLEID"+ //$NON-NLS-1$
			" AND B.TABLENAME='"+ this.getSubjectTable()+"'"+ //$NON-NLS-1$ //$NON-NLS-2$
			" AND A.SCHEMAID=C.SCHEMAID"; //$NON-NLS-1$
			
			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String colName = r.getString("REFERENCEDCOLUMNS"); //$NON-NLS-1$
				/* The columns might be in a string with comma as the separator */
				columnList.add(this.getColumn(colName));
			}
			this.columnLoaded = true;
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
		}
		catch (Exception e) {
		}
		
		this.eSetDeliver(deliver);		
	}
	
	private Column getColumn(String columnName) {
		Iterator it = this.getSubjectTable().getColumns().iterator();
		while(it.hasNext()) {
			Column c = (Column) it.next();
			if(c.getName().equals(columnName)) return c;
		}
		return null;
	}

	private boolean columnLoaded = false;
	
}
