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

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl;
import org.eclipse.emf.ecore.EStructuralFeature;


public class DerbyCatalogColumn extends ColumnImpl implements ICatalogObject{
	private static final long serialVersionUID = 3257570602843189304L;
	
	public void refresh() {
		this.identityLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}	

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}
	
	public Database getCatalogDatabase() {
		Table table = (Table) this.eContainer;
		return table.getSchema().getCatalog().getDatabase();		
	}
	
	public IdentitySpecifier getIdentitySpecifier(){
		if(!this.identityLoaded) this.loadIdentity();
		return this.identitySpecifier;
	}

	
	public String getDefaultValue(){
		if(!this.identityLoaded) this.loadIdentity();
		return this.defaultValue;
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER) {
			this.getIdentitySpecifier();
		}
		else if(id == SQLTablesPackage.COLUMN__DEFAULT_VALUE) {
			this.getDefaultValue();
		}
		
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadIdentity(){
		if(this.identityLoaded) return;
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		DerbyCatalogColumn.this.loadIdentity(connection,this);
		
		this.eSetDeliver(deliver);			
	}
	
	
	public void loadIdentity(Connection connection, Column column){
		try {
			final Table table = (Table) column.eContainer();
			final Database database = table.getSchema().getCatalog().getDatabase();;
			final DatabaseDefinition databaseDefinition =RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			final String query = "SELECT COLUMNDEFAULT,AUTOINCREMENTVALUE,AUTOINCREMENTSTART" + //$NON-NLS-1$
								" FROM SYS.SYSCOLUMNS A, SYS.SYSTABLES B, SYS.SYSSCHEMAS C"+ //$NON-NLS-1$
								" WHERE A.REFERENCEID=B.TABLEID"+ //$NON-NLS-1$
								" AND B.TABLENAME='"+ column.getTable().getName()+"'"+  //$NON-NLS-1$//$NON-NLS-2$
								" AND B.SCHEMAID=C.SCHEMAID" + //$NON-NLS-1$
								" AND C.SCHEMANAME='"+ column.getTable().getSchema().getName()+"'" + //$NON-NLS-1$ //$NON-NLS-2$
								" AND A.COLUMNNAME='" + column.getName() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			ResultSet r = s.executeQuery(query); 
			while(r.next()) {
				Serializable defaultValue = (Serializable)r.getObject("COLUMNDEFAULT"); //$NON-NLS-1$
				if (defaultValue !=null ){
					column.setDefaultValue(defaultValue.toString());
				}

				long increment = r.getLong("AUTOINCREMENTVALUE"); //$NON-NLS-1$
				if (increment != 0) {
					IdentitySpecifier identitySpecifier = (IdentitySpecifier)factory.create(SQLSchemaPackage.eINSTANCE.getIdentitySpecifier());
					identitySpecifier.setStartValue(BigInteger.valueOf(r.getLong("AUTOINCREMENTSTART"))); //$NON-NLS-1$
					identitySpecifier.setIncrement(BigInteger.valueOf(increment));
					column.setIdentitySpecifier(identitySpecifier);
				}
				
			}
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	private boolean identityLoaded = false;
	
}
