/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECheckConstraintImpl;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsFactoryImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASECatalogCheckConstraint extends SybaseASECheckConstraintImpl implements ICatalogObject, IAdaptable {
	private static final long serialVersionUID = 3257282535142011699L;

	public void refresh() {
		synchronized (sourceLoaded) {
			if(sourceLoaded.booleanValue())
				sourceLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		SybaseASECatalogSchema schema = (SybaseASECatalogSchema)this.getBaseTable().getSchema();
		return schema.getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();		
	}
	
	public SearchCondition getSearchCondition() {
		synchronized (sourceLoaded) {
			if(!sourceLoaded.booleanValue())
				loadSource();
		}
		return this.searchCondition;
	}
	
	private void loadSource() {
		boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try{
        	oldCatalog = conn.getCatalog();
        	conn.setCatalog(this.getBaseTable().getSchema().getCatalog().getName());
        	stmt = conn.prepareStatement(ASESQLs.QUERY_CHECK_CONSTRAINTS_SOURCE);
        	stmt.setString(1, this.getName());
        	stmt.setString(2, this.getCreator().getName());
        	rs = stmt.executeQuery();
        	SearchConditionDefault sc = null;
        	while(rs.next())
        	{
        		String statement = rs.getString(1);
                 sc = (SearchConditionDefault) SQLExpressionsFactoryImpl
						.init().create(
								SQLExpressionsPackage.eINSTANCE
										.getSearchConditionDefault());
                sc.setSQL(SQLUtil.parseSearchStatement(statement));
        	}
        	super.setSearchCondition(sc);
        }
        catch(SQLException e)
        {
        	JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        
        this.eSetDeliver(deliver);
        this.sourceLoaded = Boolean.TRUE;
	}
    
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION) {
			this.getSearchCondition();
		}
		if(id == SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR)
		{
			this.getCreator();
		}
		
		return super.eIsSet(eFeature);
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	public Schema getCreator() {
	   synchronized (creatorLoaded) {
		 if(!creatorLoaded.booleanValue())
				loadCreatorInfo();
	   }
	   return super.creator;
	}
	 
	private void loadCreatorInfo()
	{
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getBaseTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_CHECK_CONSTRAINTS_CREATOR);
			stmt.setString(1, this.getName());
			stmt.setInt(2, ((ICatalogTable)this.getBaseTable()).getTableId());
			stmt.setInt(3, ((ICatalogTable)this.getBaseTable()).getTableId());
			rs = stmt.executeQuery();
			Schema creator = null;
			while(rs.next())
			{
				String creatorName = rs.getString(1);
                SybaseASECatalog catalog = (SybaseASECatalog) this.getBaseTable().getSchema().getCatalog();
                creator = (Schema) ASEUtil.getSQLObject(catalog.getSchemas(), creatorName);
			}
			super.setCreator(creator);
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}
		
		this.eSetDeliver(deliver);
		creatorLoaded = Boolean.TRUE;
	}
	private Boolean sourceLoaded = Boolean.FALSE;
	private Boolean creatorLoaded = Boolean.FALSE;
}
