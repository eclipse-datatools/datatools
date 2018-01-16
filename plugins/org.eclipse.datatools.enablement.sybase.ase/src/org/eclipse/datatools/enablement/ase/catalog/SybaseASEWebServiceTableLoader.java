package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.ase.ISybaseASECatalogWebServiceTable;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;

public class SybaseASEWebServiceTableLoader extends SybaseASEProxyTableLoader
{

    public SybaseASEWebServiceTableLoader(SybaseASEBaseTable masterObj)
    {
        super(masterObj);
    }


    protected void loadTableInfo()
    {
        // TODO Auto-generated method stub
        super.loadTableInfo();
        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);
        String sql = "{ call sp_webservices 'list' }";
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String tableName = rs.getString("Proxy Table Name");
                String ownerName = rs.getString("Owner");
                String method = rs.getString("WebMethod");
                String uri = rs.getString("WSDL URI");
                if (_masterObj.getName().equals(tableName) && _masterObj.getSchema().getName().equals(ownerName))
                {
                    ((SybaseASEWebServiceTable)_masterObj).setWSDLURI(uri);
                    ((SybaseASEWebServiceTable)_masterObj).setMethod(method);
                    return;
                }
            }
        }
        catch(SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        _masterObj.eSetDeliver(deliver);
      
    }

    public String getMethod()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
            return ((ISybaseASECatalogWebServiceTable)_masterObj).getMethodSuper();
        }
    }

    public String getWSDLURI()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
            return ((ISybaseASECatalogWebServiceTable)_masterObj).getWSDLURISuper();
        }
    }
    
    
    
    

}
