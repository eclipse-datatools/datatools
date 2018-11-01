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
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlParser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEViewTableImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASECatalogView extends SybaseASEViewTableImpl implements ICatalogObject, ICatalogTable, IAdaptable
{
    private static final long serialVersionUID = 3258125856181269553L;

    public void refresh()
    {
        if(isNeedRefresh())
        {
            synchronized (columnsLoaded) {
                if(columnsLoaded.booleanValue())
                    columnsLoaded = Boolean.FALSE;
            }
            synchronized (viewLoaded) {
                if(viewLoaded.booleanValue())
                    viewLoaded = Boolean.FALSE;
            }
            synchronized (idLoaded) {
                if(idLoaded.booleanValue())
                    idLoaded = Boolean.FALSE;
            }
            synchronized (privilegesLoaded) {
                if (privilegesLoaded.booleanValue()) {
                    privilegesLoaded = Boolean.FALSE;
                }
            }        
            RefreshManager.getInstance().referesh(this);
        }
    }

    public Connection getConnection()
    {
        SybaseASECatalogSchema schema = (SybaseASECatalogSchema) this.getSchema();
        return schema.getConnection();
    }

    public Database getCatalogDatabase()
    {
        return this.getSchema().getCatalog().getDatabase();
    }

    public EList getColumns()
    {
    	synchronized (columnsLoaded) {
    		if (!this.columnsLoaded.booleanValue())
                this.loadColumns();
		}
        
        return this.columns;
    }

    public QueryExpression getQueryExpression()
    {
    	synchronized (viewLoaded) {
            if (!this.viewLoaded.booleanValue())
                this.loadView();			
		}

        return this.queryExpression;
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
        int id = eDerivedStructuralFeatureID(eFeature);
        if (id == SQLTablesPackage.VIEW_TABLE__COLUMNS)
        {
            this.getColumns();
        }
        if (id == SQLTablesPackage.VIEW_TABLE__CHECK_TYPE)
        {
            this.getCheckType();
        }
        if (id == SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION)
        {
            this.getQueryExpression();
        }
        if (id == SybasesqlmodelPackage.SYBASE_BASE_TABLE__PRIVILEGES)
        {
            getPrivileges();
        }
        return super.eIsSet(eFeature);
    }

    /**
     * Returns all the privileges granted to this table and its columns
     */
    public EList getPrivileges() {
        synchronized (privilegesLoaded) {
            if (!privilegesLoaded.booleanValue())
            {
                loadPrivileges();
                privilegesLoaded = Boolean.TRUE;
            }
        }
        return super.getPrivileges();
    }
    
    protected void loadPrivileges() {
        super.getPrivileges().clear();
        SybaseASECatalog catalog = (SybaseASECatalog)getSchema().getCatalog();
        List privileges = SybaseASECatalogUtils.getPrivileges(this, catalog);
        super.getPrivileges().addAll(privileges);
    }
    

    private void loadColumns()
    {
    	if (this.columnsLoaded.booleanValue())
            return;
        EList columnList = super.getColumns();
        columnList.clear();
        Connection connection = this.getConnection();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        String oldCatalog = null;
        try
        {
        	oldCatalog = connection.getCatalog();
        	connection.setCatalog(this.getSchema().getCatalog().getName());
            DatabaseMetaData metaData = this.getConnection().getMetaData();
            String catalogName = null;
            if (metaData.supportsCatalogsInTableDefinitions())
            {
                catalogName = connection.getCatalog();
            }
            ResultSet r = metaData.getColumns(catalogName, this.getSchema().getName(), this.getName(), null);
            while (r.next())
            {
                Column column = new SybaseASECatalogColumn();

                final String columnName = r.getString(4);
                column.setName(columnName);
                columnList.add(column);
            }
            this.columnsLoaded = Boolean.TRUE;
            r.close();
        }
        catch (SQLException e)
        {
        	JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	try
        	{
        		if(oldCatalog != null)
        			connection.setCatalog(oldCatalog);
        	}
        	catch(SQLException e)
        	{
        		JDBCASEPlugin.getDefault().log(e);
        	}
        		
        }
       

        this.eSetDeliver(deliver);
    }

    private void loadView()
    {
        if (this.viewLoaded.booleanValue())
            return;
        Connection connection = this.getConnection();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        final String text = SybaseASECatalogUtils.getCompiledObjectText(this, connection, this.getSchema().getCatalog().getName());

        SybaseASEDdlParser ddlParser = new SybaseASEDdlParser(this.getDatabaseDefinition());
        ddlParser.parseView(this, text);

        this.viewLoaded = Boolean.TRUE;

        this.eSetDeliver(deliver);
    }

    private DatabaseDefinition getDatabaseDefinition()
    {
        Database d = this.getSchema().getCatalog().getDatabase();
        return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(d);
    }

    private Boolean columnsLoaded = Boolean.FALSE;
    private Boolean viewLoaded    = Boolean.FALSE;
    private Boolean idLoaded 	  = Boolean.FALSE; 
    private Boolean privilegesLoaded = Boolean.FALSE;
    
    private int viewId = -1;
    
	public int getTableId() {
		synchronized (idLoaded) {
            if (!this.idLoaded.booleanValue())
            {
                this.loadViewId();
                idLoaded = Boolean.TRUE;
            }
		}
		return viewId;
	}
	
	private void loadViewId()
	{
		if(this.idLoaded.booleanValue())
			return;
		
		PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = this.getConnection();
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_OBJECT_ID);
            stmt.setString(1, this.getSchema().getName());
            stmt.setString(2, this.getName());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                this.viewId = rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
	}

    public boolean isSystem()
    {
        return false;
    }
    
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}    
	
	public boolean isNeedRefresh()
	{
	    if(columnsLoaded.booleanValue()||viewLoaded.booleanValue()||
	            idLoaded.booleanValue()||privilegesLoaded.booleanValue())
	    {
	        return true;
	    }
	    else return false;
	}
}
