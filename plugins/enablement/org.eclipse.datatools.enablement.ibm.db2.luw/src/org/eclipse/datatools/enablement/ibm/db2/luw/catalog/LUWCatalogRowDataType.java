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

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRowDataTypeImpl;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.Field;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogRowDataType extends LUWRowDataTypeImpl implements ICatalogObject {
	public void refresh() {
		this.fieldsLoaded = false;
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
	
	public EList getFields() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getFields();
		} else {
			if(!this.fieldsLoaded) this.loadFileds(); 
			return this.fields;
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_ROW_DATA_TYPE__FIELDS) {
			this.getFields();
		}
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadFileds() {
		if(this.fieldsLoaded) return;
		this.fieldsLoaded = true;
		
		EList fieldList = super.getFields();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			
			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

			Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT FIELDNAME, FIELDTYPESCHEMA, FIELDTYPEMODULENAME, FIELDTYPENAME" + //$NON-NLS-1$
					", LENGTH, SCALE, CODEPAGE"  //$NON-NLS-1$
					+ " FROM SYSCAT.ROWFIELDS"  //$NON-NLS-1$
					+ " WHERE TYPENAME = '" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$
					+ " AND TYPESCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
					+ " AND TYPEMODULENAME IS NULL"
					+ " ORDER BY ORDINAL "); //$NON-NLS-1$
			while(r.next()) {
				Field field = (Field) factory.create(SQLDataTypesPackage.eINSTANCE.getField());

				final String fieldName = r.getString("FIELDNAME");
				field.setName(fieldName);
				
				String typeName = r.getString("FIELDTYPENAME");
				if (typeName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
					int length =r.getInt(4);
					if (length ==4) typeName="REAL"; //$NON-NLS-1$
					else typeName="DOUBLE"; //$NON-NLS-1$
				}
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
						final int codePage = r.getInt("CODEPAGE");
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					else if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
						final int codePage = r.getInt("CODEPAGE");
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					
					PredefinedDataType type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						final int length = r.getInt("LENGTH");
						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					else if(typeDefinition.isPrecisionSupported()) {
						if (typeName.equals("TIMESTAMP")) {
							int length = r.getInt("LENGTH");
							EStructuralFeature feature = type.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
						else {
							int length = r.getInt("LENGTH");
							if (typeName.equals("DECFLOAT")) {
								if (length == 8) length = 16;
								else length = 34;
							}
							EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
					}
					
					if(typeDefinition.isScaleSupported()) {
						final int scale = r.getInt("SCALE");
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(scale));
					}
					
					field.setContainedType(type);
				}
				else {
					final String typeSchemaName = r.getString("FIELDTYPESCHEMA").trim();
					final String typeModuleName = r.getString("FIELDTYPEMODULENAME");
					field.setReferencedType(LUWCatalogRowDataType.getUserDefinedType(this,typeSchemaName,typeName));
				}
				
				
				fieldList.add(field);
			}

			r.close();
			s.close();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		this.eSetDeliver(deliver);
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

	private boolean fieldsLoaded = false;
}
	

