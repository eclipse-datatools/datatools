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

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EStructuralFeature;


public class DerbyCatalogCheckConstraint extends CheckConstraintImpl implements ICatalogObject {
	private static final long serialVersionUID = 3257282535142011699L;

	public void refresh() {
		this.loaded = false;
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
		return this.getBaseTable().getSchema().getCatalog().getDatabase();		
	}
	
	public SearchCondition getSearchCondition() {
		if(!this.loaded) this.load();
		return this.searchCondition;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION) {
			this.getSearchCondition();
		}
		
		return super.eIsSet(eFeature);
	}

	private synchronized void load() {
		if(this.loaded) return;
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			
			String query = "SELECT CHECKDEFINITION"+ //$NON-NLS-1$
						" FROM SYS.SYSCHECKS A,SYS.SYSCONSTRAINTS B"+ //$NON-NLS-1$
						" WHERE A.CONSTRAINTID=B.CONSTRAINTID" + //$NON-NLS-1$
						" AND B.CONSTRAINTNAME='"+ this.getName()+"'"; //$NON-NLS-1$ //$NON-NLS-2$
			
			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String text = r.getString("CHECKDEFINITION"); //$NON-NLS-1$
				DataModelElementFactory factory = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()).getDataModelElementFactory();
				SearchCondition searchCondition = (SearchCondition) factory.create(SQLExpressionsPackage.eINSTANCE.getSearchConditionDefault());
				searchCondition.setSQL(text);
				this.setSearchCondition(searchCondition);
			}
			this.loaded = true;
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		this.eSetDeliver(deliver);			
	}
	
	private boolean loaded = false;	
}
