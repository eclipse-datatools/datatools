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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogStructuredUserDefinedType extends StructuredUserDefinedTypeImpl implements ICatalogObject,IDatabaseObject {
	public void refresh() {
		if (this.attributesLoaded) {
			this.attributes.clear();
			this.attributesLoaded = false;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();		
	}
	
	public void refresh(int refreshType){
		if ((IDatabaseObject.IMPACTS & refreshType)  == IDatabaseObject.IMPACTS) {
			this.impacts.clear();
			this.impactsLoaded = false;
		}
	}

	public EList getAttributes() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getAttributes();
		} else {
			if(!this.attributesLoaded) this.loadAttributes(); 
			return this.attributes;
		}
	}

	public StructuredUserDefinedType getSuper() {
		if (!this.superLoaded) this.loadSuper();
		return this.super_;
	}
	
	public ICatalogObject[] getImpacted(){
		if (!this.impactsLoaded) {
			this.impacts = this.getImpactedObjects();
			this.impactsLoaded = true;
		}
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
	}

	public Collection getStatistics(){
		return new ArrayList();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES) {
			this.getAttributes();
		}
		else if(id == SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER) {
			this.getSuper();
		}
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadAttributes() {
		if(this.attributesLoaded) return;
		EList attributeList = super.getAttributes();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT ATTR_NAME, ATTR_TYPESCHEMA, ATTR_TYPENAME" + //$NON-NLS-1$
					", LENGTH, SCALE, CODEPAGE, TARGET_TYPESCHEMA,TARGET_TYPENAME,LOGGED,COMPACT"  //$NON-NLS-1$
					+ " FROM SYSCAT.ATTRIBUTES"  //$NON-NLS-1$
					+ " WHERE TYPENAME = SOURCE_TYPENAME" //$NON-NLS-1$
					+ " AND TYPESCHEMA = SOURCE_TYPESCHEMA" //$NON-NLS-1$
					+ " AND TYPESCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
					+ " AND TYPENAME='" + LUWUtil.getIdentifier(this.getName())  //$NON-NLS-1$ //$NON-NLS-2$
					+ "' ORDER BY ORDINAL "); //$NON-NLS-1$
			while(r.next()) {
				LUWCatalogAttribute attribute = new LUWCatalogAttribute();
				
				final String attrName = r.getString(1);
				attribute.setName(attrName);
				
				final DatabaseDefinition databaseDefinition = this.getDatabaseDefinition();
				String attrTypeName = r.getString(3);
				if (attrTypeName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
					int length =r.getInt(4);
					if (length ==4) attrTypeName="REAL"; //$NON-NLS-1$
					else attrTypeName="DOUBLE"; //$NON-NLS-1$
				}
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(attrTypeName);
				if(typeDefinition != null) {
					
					if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
						final int codePage = r.getInt(6);
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					else if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
						final int codePage = r.getInt(6);
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					
					PredefinedDataType type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						final int length = r.getInt(4);
						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					else if(typeDefinition.isPrecisionSupported()) {
						if (attrTypeName.equals("TIMESTAMP")) {
							int length = r.getInt(4);
							EStructuralFeature feature = type.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
						else {
							int length = r.getInt(4);
							if (attrTypeName.equals("DECFLOAT")) {
								if (length == 8) length = 16;
								else length = 34;
							}
							EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
					}
					
					if(typeDefinition.isScaleSupported()) {
						final int scale = r.getInt(5);
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(scale));
					}
					
					attribute.setContainedType(type);
				}
				else {
					if (attrTypeName.equals("REFERENCE")){ //$NON-NLS-1$
						final String typeSchemaName = r.getString("TARGET_TYPESCHEMA").trim(); //$NON-NLS-1$
						final String udtName = r.getString("TARGET_TYPENAME"); //$NON-NLS-1$
						attribute.setReferencedType(LUWCatalogStructuredUserDefinedType.getUserDefinedType(this,typeSchemaName,udtName));
					} else {
						final String attrTypeSchemaName = r.getString(2).trim();
						Schema attrTypeSchema = LUWCatalogStructuredUserDefinedType.getSchema(this,attrTypeSchemaName);
						attribute.setReferencedType(LUWCatalogStructuredUserDefinedType.getUserDefinedType(this,attrTypeSchemaName,attrTypeName));
					}
				}
				
				final String logged = r.getString("LOGGED");
				if (logged.equals("Y")) {
					attribute.setLOBLogged(true);
				} else {
					attribute.setLOBLogged(false);
				}

				final String compact = r.getString("COMPACT");
				if (compact.equals("Y")) {
					attribute.setLOBCompacted(true);
				} else {
					attribute.setLOBCompacted(false);
				}
				
				
				attributeList.add(attribute);
			}
			this.attributesLoaded = true;
			r.close();
			s.close();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadSuper() {
		if(this.superLoaded) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			String query = "SELECT SOURCE_TYPESCHEMA,SOURCE_TYPENAME"  //$NON-NLS-1$
				+ " FROM SYSCAT.ATTRIBUTES"  //$NON-NLS-1$
				+ " WHERE TYPESCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
				+ " AND TYPENAME='" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
				+ " AND TYPENAME <> SOURCE_TYPENAME" //$NON-NLS-1$
				+ " ORDER BY ORDINAL DESC"; //$NON-NLS-1$
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				LUWCatalogAttribute attribute = new LUWCatalogAttribute();
				
				final String sourceSchemaName = r.getString("SOURCE_TYPESCHEMA").trim(); //$NON-NLS-1$
				final String sourceTypeName = r.getString("SOURCE_TYPENAME"); //$NON-NLS-1$

				this.setSuper(LUWCatalogStructuredUserDefinedType.getStructuredUserDefinedType(this,sourceSchemaName,sourceTypeName));

				break;
			}

			this.superLoaded = true;
			r.close();
			s.close();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	
	private DatabaseDefinition getDatabaseDefinition() {
		return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());
	}
	
	protected static Schema getSchema(UserDefinedType type, String schemaName) {
		Schema s = type.getSchema();
		if(s.getName().equals(schemaName)) return s;
		
		Database db = s.getDatabase();
		if (db instanceof LUWCatalogDatabase){
			s = ((LUWCatalogDatabase)db).getSchema(schemaName);
			if (s != null) return s;
		} 
		Iterator it = db.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
		
		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(db);

		if (db instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)db).cacheSchema(schema);
		}
		
		return schema;
	}
	private static UserDefinedType getUserDefinedType(UserDefinedType type,String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogStructuredUserDefinedType.getSchema(type,schemaName);
		Iterator it = schema.getUserDefinedTypes().iterator();
		while(it.hasNext()) {
			UserDefinedType userDefinedType = (UserDefinedType) it.next();
			if(userDefinedType.getName().equals(userDefinedTypeName)) return userDefinedType;			
		}

		UserDefinedType userDefinedType = new LUWCatalogDistinctUserDefinedType();
		userDefinedType.setName(userDefinedTypeName);
		userDefinedType.setSchema(schema);
		
		return userDefinedType;		
	}

	private static StructuredUserDefinedType getStructuredUserDefinedType(UserDefinedType type ,String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogStructuredUserDefinedType.getSchema(type,schemaName);
		Iterator it = schema.getUserDefinedTypes().iterator();
		while(it.hasNext()) {
			UserDefinedType userDefinedType = (UserDefinedType) it.next();
			if(userDefinedType instanceof StructuredUserDefinedType 
				&& userDefinedType.getName().equals(userDefinedTypeName)) {
				return (StructuredUserDefinedType)userDefinedType;			
			}
		}

		StructuredUserDefinedType userDefinedType = new LUWCatalogStructuredUserDefinedType();
		userDefinedType.setName(userDefinedTypeName);
		userDefinedType.setSchema(schema);
		
		return userDefinedType;		
	}
	
	private static Table getTable(UserDefinedType type,String schemaName, String tableName) {
		Schema schema = LUWCatalogStructuredUserDefinedType.getSchema(type,schemaName);
		if (schema instanceof LUWCatalogSchema) {
			return  ((LUWCatalogSchema)schema).getTable(schema.getName(),tableName);
		} 
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}
		return null;		
	}
		
	public static Routine getRountine(UserDefinedType type,String schemaName, String specificName) {
		Schema schema = LUWCatalogStructuredUserDefinedType.getSchema(type,schemaName);
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine routine = (Routine) it.next();
			if(specificName.equals(routine.getSpecificName())) return routine;			
		}
		return null;
	}

	public static TableConstraint getTableConstraint(UserDefinedType type, String schemaName,String  tableName, String constName) {
		Table t = LUWCatalogStructuredUserDefinedType.getTable(type, schemaName,tableName);
		if (!(t instanceof BaseTable)) return null;
		Iterator it = ((BaseTable)t).getConstraints().iterator();
		while(it.hasNext()) {
			TableConstraint constraint = (TableConstraint) it.next();
			if(constraint.getName().equals(constName)) return constraint;			
		}

		return null;
	}

	public static Trigger getTrigger(UserDefinedType type, String schemaName, String tabName, String triggerName) {
		Table table = LUWCatalogStructuredUserDefinedType.getTable(type, schemaName,tabName);
		if (table != null) {
			Iterator it = table.getTriggers().iterator();
			while(it.hasNext()) {
				Trigger r = (Trigger) it.next();
				if(r.getName().equals(triggerName)) return r;			
			}
		}
		return null;
	}

	public static DB2Package getDb2Package(UserDefinedType type,String schemaName, String pkgName, String pkgUniqueID) {
		Schema schema = LUWCatalogStructuredUserDefinedType.getSchema(type,schemaName);
		if (schema instanceof LUWCatalogSchema) {
			return  ((LUWCatalogSchema)schema).getDB2Package(pkgName, pkgUniqueID);
		} 
		return null;
	}
	
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();

		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedConstraints(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedPackages(connection, this));

		return impacts;
	}

	protected static Collection getImpactedTables(Connection connection, UserDefinedType type) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT DISTINCT TABNAME, TABSCHEMA" +
					" FROM SYSCAT.TABDEP" +
					" WHERE BTYPE='R'" +
					" AND BNAME ='" + LUWUtil.getIdentifier(type.getName()) + "'" +
					" AND BSCHEMA = '" + LUWUtil.getIdentifier(type.getSchema().getName()) + "'" +
					" UNION ALL" +
					" SELECT DISTINCT TABNAME,TABSCHEMA" +
					" FROM SYSCAT.COLUMNS" +
					" WHERE TYPESCHEMA = '" + LUWUtil.getIdentifier(type.getSchema().getName()) + "'" +
					" AND TYPENAME ='" + LUWUtil.getIdentifier(type.getName()) + "'" 
+					" FOR FETCH ONLY";
			
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Table t = LUWCatalogStructuredUserDefinedType.getTable(type, schemaName, tabName);
				if (t !=  null) {
					impacts.add(t);
				}
			}
			r.close();
			s.close();

		}catch(SQLException e) {
			 e.printStackTrace();
		}
		return impacts;
	}



	protected static Collection getImpactedConstraints(Connection connection, UserDefinedType type) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT CONSTNAME,TABNAME,TABSCHEMA" +
					" FROM SYSCAT.CONSTDEP" +
					" WHERE BTYPE='R'" +
					" AND BNAME='" + LUWUtil.getIdentifier(type.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(type.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String constName = r.getString("CONSTNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				TableConstraint constraint = LUWCatalogStructuredUserDefinedType.getTableConstraint(type, schemaName, tabName, constName);
				if (constraint !=  null) {
					impacts.add(constraint);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			 e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedTriggers(Connection connection, UserDefinedType type) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT DISTINCT A.TABSCHEMA, A.TABNAME, A.TRIGNAME" +
			" FROM SYSCAT.TRIGGERS A, SYSCAT.TRIGDEP B" +
			" WHERE (B.BNAME='" + LUWUtil.getIdentifier(type.getName()) + "'" +
			" AND B.BSCHEMA='" + LUWUtil.getIdentifier(type.getSchema().getName()) + "'" +
			" AND B.BTYPE='R'" +
			" AND A.TRIGNAME=B.TRIGNAME" +
			" AND A.TRIGSCHEMA=B.TRIGSCHEMA)" +
			" FOR FETCH ONLY";
			
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String trigName = r.getString("TRIGNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Trigger trigger = LUWCatalogStructuredUserDefinedType.getTrigger(type, schemaName, tabName, trigName);
				if (trigger !=  null) {
					impacts.add(trigger);
				}

			}
			r.close();
			s.close();
		}catch(SQLException e) {
			 e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedRoutines(Connection connection, UserDefinedType type) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT ROUTINENAME,ROUTINESCHEMA" +
					" FROM SYSCAT.ROUTINEDEP" +
					" WHERE BTYPE='R'" +
					" AND BNAME='" + LUWUtil.getIdentifier(type.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(type.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String routineName = r.getString("ROUTINENAME").trim();
				final String schemaName = r.getString("ROUTINESCHEMA").trim();
				Routine p = LUWCatalogStructuredUserDefinedType.getRountine(type, schemaName, routineName);
				if (p !=  null) {
					impacts.add(p);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			 e.printStackTrace();
		}
		return impacts;
	}
	
	protected static Collection getImpactedPackages(Connection connection, UserDefinedType type) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT PKGNAME, PKGSCHEMA, HEX(UNIQUE_ID) AS UID" +
					" FROM SYSCAT.PACKAGEDEP" +
					" WHERE BTYPE = 'R'" +
					" AND BNAME='" + LUWUtil.getIdentifier(type.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(type.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String pkgName = r.getString("PKGNAME").trim();
				final String schemaName = r.getString("PKGSCHEMA").trim();
				final String pkgUniqueID = r.getString("UID").trim();
				DB2Package p = LUWCatalogStructuredUserDefinedType.getDb2Package(type, schemaName, pkgName, pkgUniqueID);
				if (p !=  null) {
					impacts.add(p);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			 e.printStackTrace();
		}
		return impacts;
	}


	private boolean attributesLoaded = false;
	private boolean superLoaded = false;
	private boolean impactsLoaded = false;
	private Collection impacts = new ArrayList();
}
