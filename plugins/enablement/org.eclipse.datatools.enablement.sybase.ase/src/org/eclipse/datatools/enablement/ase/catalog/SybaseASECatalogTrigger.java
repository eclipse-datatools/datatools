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
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETriggerImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsFactoryImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogTrigger extends SybaseASETriggerImpl implements ICatalogObject, IAdaptable {
	private static final long serialVersionUID = 3976740254881363508L;

	public void refresh() {
		synchronized (sourceLoaded) {
			if (this.sourceLoaded.booleanValue()) {
				this.sourceLoaded = Boolean.FALSE;
				super.getActionStatement().clear();
			}			
		}
		synchronized (triggerInfoLoaded) {
			if(triggerInfoLoaded.booleanValue())
				triggerInfoLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		ICatalogObject database = (ICatalogObject)this.getCatalogDatabase();
		return database.getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getSubjectTable().getSchema().getCatalog().getDatabase();
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SQLTablesPackage.TRIGGER__ACTION_STATEMENT:
			this.getActionStatement();
			break;
		case SQLTablesPackage.TRIGGER__DELETE_TYPE:
			this.isDeleteType();
			break;
		case SQLTablesPackage.TRIGGER__UPDATE_TYPE:
			this.isUpdateType();
			break;
		case SQLTablesPackage.TRIGGER__INSERT_TYPE:
			this.isInsertType();
			break;
		case SQLTablesPackage.TRIGGER__SCHEMA:
			this.getSchema();
			break;
		}
		
		return super.eIsSet(eFeature);
	}

	public EList getActionStatement() {
		synchronized (sourceLoaded) {
			if (!this.sourceLoaded.booleanValue()) this.loadCode();	
		}
		
		return super.getActionStatement();
	}

	protected void loadCode() {
		if(this.sourceLoaded.booleanValue()) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		EList actions = super.getActionStatement();

		actions.clear();
        String body = SybaseASECatalogUtils.getCompiledObjectText(this, this.getConnection(), this.getSubjectTable().getSchema().getCatalog().getName());
		SQLStatement sql = (SQLStatement)SQLStatementsFactoryImpl.eINSTANCE.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
		sql.setSQL(body);
		actions.add(sql);

		this.sourceLoaded = Boolean.TRUE;
		this.eSetDeliver(deliver);
	}
	
	public boolean isDeleteType() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
				loadTriggerInfo();
		}
		return super.isDeleteType();
	}
	
	public boolean isUpdateType() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
				loadTriggerInfo();
		}
		return super.isUpdateType();
	}
	
	public boolean isInsertType() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
				loadTriggerInfo();
		}
		return super.isInsertType();
	}
	
	private void loadTriggerInfo() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getSubjectTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_TRIGGER_INFO);
			stmt.setString(1, this.getName());
			stmt.setInt(2, ((ICatalogTable)this.getSubjectTable()).getTableId());
			rs = stmt.executeQuery();
			boolean delTrigger = false;
            boolean updTrigger = false;
            boolean insTrigger = false;
            long triggerId = 0;
			while(rs.next())
			{
//				int status = rs.getInt(1);
				delTrigger = rs.getInt(2) == 1;
                updTrigger = rs.getInt(3) == 1;
                insTrigger = rs.getInt(4) == 1;
                triggerId  = rs.getLong(5);
			}
            
            stmt = conn.prepareStatement(ASESQLs.QUERY_TRIGGER_ENABLE);
            stmt.setLong(1, triggerId);
            rs = stmt.executeQuery();
            boolean enableTrigger = true;
            while(rs.next())
            {
                enableTrigger = !(rs.getInt(1) == 1);
            }

            super.setDeleteType(delTrigger);
            super.setUpdateType(updTrigger);
            super.setInsertType(insTrigger);
            super.setEnabled(enableTrigger);
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
		triggerInfoLoaded = Boolean.TRUE;
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
	protected Boolean sourceLoaded = Boolean.FALSE;
	protected Boolean triggerInfoLoaded = Boolean.FALSE;
}
