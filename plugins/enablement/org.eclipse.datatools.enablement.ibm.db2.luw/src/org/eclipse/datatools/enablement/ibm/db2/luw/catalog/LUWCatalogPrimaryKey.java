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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.LUWCatalogIndex.IndexUniqueRule;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.PrimaryKeyImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogPrimaryKey extends PrimaryKeyImpl implements ICatalogObject {
	public void refresh() {
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
		if(id == SQLConstraintsPackage.PRIMARY_KEY__MEMBERS) {
			this.getMembers();
		}
		else if(id == SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES) {
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

		LUWIndex index = this.getImplementIndex();
		if (index != null) {
			for (Iterator iterator=index.getMembers().iterator(); iterator.hasNext(); ){
				IndexMember indexMemeber = (IndexMember)iterator.next();
				memberList.add(indexMemeber.getColumn());
			}
		} else {
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
		return " WHERE CONSTNAME='" + LUWUtil.getIdentifier(this.getName()) + "' AND TABSCHEMA='" + LUWUtil.getIdentifier(this.getBaseTable().getSchema().getName())  //$NON-NLS-1$ //$NON-NLS-2$
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

	public static void loadDependencies(Connection connection, EList dependencyList, Constraint constraint) throws SQLException {
		final Table table = (Table) constraint.eContainer();
		final Database database = table.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery("SELECT BSCHEMA, BNAME, BTYPE FROM SYSCAT.CONSTDEP WHERE CONSTNAME='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(constraint.getName()) + "' AND TABSCHEMA='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(table.getSchema().getName()) + "' AND TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			while(r.next()) {
				final String bschema = r.getString(1).trim();
				final String bname   = r.getString(2);
				final String btype   = r.getString(3);
				SQLObject obj = null;
				if(btype.equals("F")) {	//function //$NON-NLS-1$
					obj = LUWCatalogView.getRountine(table, bschema, bname);					
				}
				if(btype.equals("I")) {	//index //$NON-NLS-1$
					obj = LUWCatalogView.getIndex(table, bschema, bname);										
				}
				if(btype.equals("R")) {	//structured type //$NON-NLS-1$
					obj = LUWCatalogView.getUserDefinedType(table, bschema, bname);										
				}
				else {
					continue;
				}
				
				if(obj == null) continue;
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(obj);
				dependencyList.add(dep);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		r.close();
		s.close();
	}


	private LUWIndex getImplementIndex(){
		for (Iterator iter = this.getBaseTable().getIndex().iterator(); iter.hasNext();) {
			LUWIndex index = (LUWIndex) iter.next();
			if (index instanceof LUWCatalogIndex
				&& ((LUWCatalogIndex)index).getIndexUniqueRule() == IndexUniqueRule.PRIMARYKEY) {
				return index;
			}
		}
		return null;
	}
	private boolean memberLoaded = false;
	private boolean dependencyLoaded = false;
		
}
