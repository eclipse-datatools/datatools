/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.ase.ISybaseASECatalogProxyTable;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;

/**
 * 
 * @author Hao wang
 */
public class SybaseASEProxyTableLoader extends SybaseASEBaseTableLoader
{
    public SybaseASEProxyTableLoader(SybaseASEBaseTable masterObj)
    {
        super(masterObj);
    }
    
    protected void loadTableInfo()
    {
        super.loadTableInfo();
        Connection conn = this.getConnection();

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.REMOTE_OBJECT_OF_PROXY_TABLE_QUERY);
            stmt.setString(1, _masterObj.getName());
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String externalType = rs.getString(1);
                String pathName = rs.getString(2);
                String delimiter = rs.getString(3);
                ((SybaseASEProxyTable)_masterObj).setExternalType(ProxyTableExternalType.get(externalType));
                ((SybaseASEProxyTable)_masterObj).setExternalPath(pathName);
                if (externalType.equals(ProxyTableExternalType.FILE_LITERAL.getLiteral()))
                {
                   // TODO: fail to retrieve delimiter from system table
                    ((SybaseASEProxyTable)_masterObj).setColumnDelimiter(delimiter);
                }
             }
              // TODO: fail to retrieve existing attribute
            ((SybaseASEProxyTable)_masterObj).setExisting((getStatus() & 2048) == 2048);
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        _masterObj.eSetDeliver(deliver);
    }
    
    public ProxyTableExternalType getExternalType(){
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
            return ((ISybaseASECatalogProxyTable)_masterObj).getExternalTypeSuper();
        }
    }
    
    public boolean isExisting(){
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
            return ((ISybaseASECatalogProxyTable)_masterObj).isExistingSuper();
        }
    }
    
    public String getFileDelimiter(){
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
            return ((ISybaseASECatalogProxyTable)_masterObj).getFileDelimiterSuper();
        }
    }
    
    public String getExternalPath(){
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
            return ((ISybaseASECatalogProxyTable)_masterObj).getExternalPathSuper();
        }
    }

}
