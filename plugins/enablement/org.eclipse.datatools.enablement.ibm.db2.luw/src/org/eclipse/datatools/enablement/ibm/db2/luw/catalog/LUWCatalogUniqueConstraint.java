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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;

public class LUWCatalogUniqueConstraint extends UniqueConstraintImpl implements ICatalogObject {
	public synchronized void refresh() {
		if(this.memberLoaded) {
			this.members.clear();
			this.memberLoaded = false;
		}
		
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public EList getMembers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getMembers();
		} else {
			if(!this.memberLoaded) this.loadMembers();
			return this.members;
		}
	}

	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
		}
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS) {
			this.getMembers();
		}
		else if(id == SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES) {
			this.getDependencies();
		}
		return super.eIsSet(eFeature);
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();		
	}

	private synchronized void loadMembers() {
		if(this.memberLoaded) return;
		this.memberLoaded = true;

		EList memberList = super.getMembers();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			if(connection == null) return;

			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT COLNAME FROM SYSCAT.KEYCOLUSE" + this.getWhereClause()); //$NON-NLS-1$
			while(r.next()) {
				final String colName = r.getString(1);
				memberList.add(this.getColumn(colName));
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogPrimaryKey.loadDependencies(this.getConnection(), super.getDependencies(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);		
	}
	
	private String getWhereClause() {
		Table table = this.getBaseTable();
		return " WHERE CONSTNAME='" + this.getName() + "' AND TABSCHEMA='" + LUWUtil.getIdentifier(this.getBaseTable().getSchema().getName())  //$NON-NLS-1$ //$NON-NLS-2$
				+ "' AND TABNAME='" + LUWUtil.getIdentifier(this.getBaseTable().getName()) + "' ORDER BY COLSEQ"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private Column getColumn(String columnName) {
		BaseTable table = this.getBaseTable();
		if (table instanceof LUWCatalogTable) {
			Column c = ((LUWCatalogTable)table).getColumn(columnName);
			if (c!= null) return c;
		} 
		
		Iterator it = table.getColumns().iterator();
		while(it.hasNext()) {
			Column c = (Column) it.next();
			if(c.getName().equals(columnName)) return c;
		}

		Column column = new LUWCatalogColumn();
		column.setName(columnName);
		column.setTable(table);
		Database database = table.getSchema().getDatabase();
		final DatabaseDefinition def = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		CharacterStringDataType dataType = (CharacterStringDataType) def.getPredefinedDataType("Char"); //$NON-NLS-1$
		dataType.setLength(5);
		column.setContainedType(dataType);

		return column;
	}
		
	private boolean memberLoaded = false;
	private boolean dependencyLoaded = false;
}
