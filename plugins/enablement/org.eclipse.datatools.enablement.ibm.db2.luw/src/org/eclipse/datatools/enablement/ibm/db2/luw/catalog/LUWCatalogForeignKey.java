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
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;

public class LUWCatalogForeignKey extends ForeignKeyImpl implements ICatalogObject {
	public void refresh() {
		if(this.memberLoaded) {
			this.members.clear();
			this.memberLoaded = false;
		}
		
		this.uniqueConstraintLoaded = false;
		
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

	public UniqueConstraint getUniqueConstraint() {
		if(!this.uniqueConstraintLoaded) this.loadUniqueConstraint();
		return this.uniqueConstraint;
	}

	public ReferentialActionType getOnDelete() {
		if(!this.uniqueConstraintLoaded) this.loadUniqueConstraint();
		return this.onDelete;
	}
	
	public ReferentialActionType getOnUpdate() {
		if(!this.uniqueConstraintLoaded) this.loadUniqueConstraint();
		return this.onUpdate;
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
		if(id == SQLConstraintsPackage.FOREIGN_KEY__MEMBERS) {
			this.getMembers();
		}
		else if(id == SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE) {
			this.getOnDelete();
		}
		else if(id == SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE) {
			this.getOnUpdate();
		}
		else if(id == SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT) {
			this.getUniqueConstraint();
		}
		else if(id == SQLConstraintsPackage.FOREIGN_KEY__DEPENDENCIES) {
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
			ResultSet r = s.executeQuery("SELECT COLNAME FROM SYSCAT.KEYCOLUSE" + this.getWhereClause() + "  ORDER BY COLSEQ"); //$NON-NLS-1$ //$NON-NLS-2$
			while(r.next()) {
				final String colName = r.getString(1);
				memberList.add(this.getColumn(colName));
			}
			
			r.close();
			s.close();
			setAsIdentifyingRelatinship(this, this.isIdentifyingRelationship(memberList));
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}
	public static void setAsIdentifyingRelatinship(ForeignKey fk,boolean identifying){
		EAnnotation eAnnotation = fk.addEAnnotation(RDBCorePlugin.FK_MODELING_RELATIONSHIP);
		fk.addEAnnotationDetail(eAnnotation,RDBCorePlugin.FK_IS_IDENTIFYING_RELATIONSHIP,new Boolean(identifying).toString());

		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_MULTIPLICITY, RDBCorePlugin.MANY);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_ROLE_NAME, new String ());
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_MULTIPLICITY, (fk.getMembers().size() > 0) ? RDBCorePlugin.ZERO_TO_ONE : RDBCorePlugin.ONE);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_ROLE_NAME, new String ());
	}
	
	private synchronized void loadUniqueConstraint() {
		if(this.uniqueConstraintLoaded) return;
		this.uniqueConstraintLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			if(connection == null) return;

			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT REFKEYNAME, REFTABSCHEMA, REFTABNAME, DELETERULE, UPDATERULE FROM SYSCAT.REFERENCES" + this.getWhereClause()); //$NON-NLS-1$
			while (r.next()) {
				final String refKeyName   = r.getString(1);
				final String refTabSchema = r.getString(2).trim();
				final String refTabName   = r.getString(3);
				this.setParentConstraint(refTabSchema, refTabName, refKeyName);
				final String onDeleteStr = r.getString(4);
				final String onUpdateStr = r.getString(5);
				if(onDeleteStr.equals("A")) //$NON-NLS-1$
				{
					this.onDelete = ReferentialActionType.NO_ACTION_LITERAL;
				}
				else if(onDeleteStr.equals("C")) //$NON-NLS-1$
				{
					this.onDelete = ReferentialActionType.CASCADE_LITERAL;
				}
				else if(onDeleteStr.equals("N")) //$NON-NLS-1$
				{
					this.onDelete = ReferentialActionType.SET_NULL_LITERAL;
				}
				else if(onDeleteStr.equals("R")) //$NON-NLS-1$
				{
					this.onDelete = ReferentialActionType.RESTRICT_LITERAL;
				}
				if(onUpdateStr.equals("A")) //$NON-NLS-1$
				{
					this.onUpdate = ReferentialActionType.NO_ACTION_LITERAL;
				}
				else if(onUpdateStr.equals("R")) //$NON-NLS-1$
				{
					this.onUpdate = ReferentialActionType.RESTRICT_LITERAL;
				}
			}
			r.close();
			s.close();
			loadMembers();
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
		return " WHERE CONSTNAME='" + LUWUtil.getIdentifier(this.getName()) + "' AND TABSCHEMA='" + LUWUtil.getIdentifier(this.getBaseTable().getSchema().getName())  //$NON-NLS-1$ //$NON-NLS-2$
				+ "' AND TABNAME='" + LUWUtil.getIdentifier(this.getBaseTable().getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
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
	
	private Schema getSchema(String schemaName) {
		Schema s = this.getBaseTable().getSchema();
		if(s.getName().equals(schemaName)) return s;
		Database d = s.getDatabase();

		if (d instanceof LUWCatalogDatabase){
			s = ((LUWCatalogDatabase)d).getSchema(schemaName);
			if (s != null) return s;
		} 
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(d);

		if (d instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)d).cacheSchema(schema);
		}		
		return schema;
	}
	
	private Table getTable(String schemaName, String tableName) {
		Schema schema = this.getSchema(schemaName);

		if(schema instanceof LUWCatalogSchema){
			Table t = ((LUWCatalogSchema)schema).getTable(schemaName,tableName);
			if (t != null) return t;
		} 
		
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}

		Table table = new LUWCatalogTable();
		table.setName(tableName);
		table.setSchema(schema);

		return table;
	}
	
	private void setParentConstraint(String schemaName, String tableName, String constraintName) {
		BaseTable table = (BaseTable) this.getTable(schemaName, tableName);
		this.setReferencedTable(table);
		Iterator it = table.getConstraints().iterator();
		while(it.hasNext()) {
			Constraint constraint = (Constraint) it.next();
			if(constraint.getName().equals(constraintName)) {
				this.setUniqueConstraint((UniqueConstraint) constraint);
				return;
			}
		}
		
		Iterator iter = table.getIndex().iterator();
		while(iter.hasNext()) {
			Index index = (Index) iter.next();
			if(index.getName().equals(constraintName)) {
				this.setUniqueIndex((Index) index);
				return;
			}
		}

		UniqueConstraint uniqueConstrain = new LUWCatalogUniqueConstraint();
		uniqueConstrain.setName(constraintName);
		uniqueConstrain.setBaseTable(table);
		this.setUniqueConstraint(uniqueConstrain);

	}
		
	private boolean isIdentifyingRelationship(EList memberList){
		boolean isIdentifying= true;
		Iterator it = memberList.iterator();
		while(it.hasNext()) {
			Column column = (Column) it.next();
			if (!column.isPartOfPrimaryKey()) {
				isIdentifying = false;
			}
		}
		return isIdentifying;
	}
	
	private boolean memberLoaded = false;
	private boolean uniqueConstraintLoaded = false;
	private boolean dependencyLoaded = false;
		
}
