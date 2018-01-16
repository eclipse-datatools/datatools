package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASAUtil;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlFactory;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class AuthorizationIdentifierASALoader {

	protected AuthorizationIdentifier authId;
	protected Connection conn;
	protected ICatalogObject catalogObj;
	
	public AuthorizationIdentifierASALoader(AuthorizationIdentifier catalogAuthId)
	{
		this.authId = catalogAuthId;
		this.catalogObj = (ICatalogObject)catalogAuthId;
		this.conn = catalogObj.getConnection();
	}
	
	final public void loadPrivilegs(EList privileges)
	{
		boolean deliver = authId.eDeliver();
		authId.eSetDeliver(false);
        
        privileges.clear();
		
		loadTablePrivilegs(privileges);
        loadTableColumnPrivilege(privileges);
        
		loadRoutinePrivileges(privileges);
		
		authId.eSetDeliver(deliver);
	}
    
    //load remark
    final public void loadAuthInfo()
    {
        boolean deliver = authId.eDeliver();
        authId.eSetDeliver(false);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(ASASQLs.QUERY_AUTHID_INFO);
            stmt.setString(1, authId.getName());
            rs = stmt.executeQuery();
            while(rs.next())
            {
                String remarks = rs.getString(1);
                authId.setDescription(remarks);
            }
        }
        catch (SQLException e) {
            JDBCASAPlugin.getDefault().log(e);
        } finally {
            SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
        }
        authId.eSetDeliver(deliver);
    }
	
	protected void loadTableColumnPrivilege(List privileges) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try
        {
            stmt = conn.prepareStatement(ASASQLs.QUERY_COLUMN_PERMISSIONS);
            stmt.setString(1, authId.getName());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String tableName = rs.getString(1);
                String ownerName = rs.getString(2);
                String columnName = rs.getString(3);
                int privilegeType = rs.getInt(4);
                boolean isGrantable = rs.getString(5).equals("Y");
                String grantorName = rs.getString(6);

                Schema schema = (Schema) SybaseASACatalogUtils.findElement(
                        catalogObj.getCatalogDatabase().getSchemas(), ownerName);
                Table table = (Table) SybaseASACatalogUtils.findElement(schema.getTables(), tableName);
                Column col = (Column) SybaseASACatalogUtils.findElement(table.getColumns(), columnName);
                SybasePrivilege p = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                AuthorizationIdentifier grantor = (AuthorizationIdentifier) SybaseASACatalogUtils.findElement(
                        catalogObj.getCatalogDatabase().getAuthorizationIds(), grantorName);
                String action = null;
                switch (privilegeType)
                {
                    case 1:
                        action = ASAUtil.PERMISSION_SELECT_ACTION;
                        break;
                    case 8:
                        action = ASAUtil.PERMISSION_UPDATE_ACTION;
                        break;
                    case 16:
                        action = ASAUtil.PERMISSION_REFERENCE_ACTION;
                        break;
                }
                p.setAction(action);
                p.setGrantable(isGrantable);
                p.setObject(col);
                p.setGrantor(grantor);
                p.setGrantee(authId);
                privileges.add(p);
            }
        }
        catch (SQLException e)
        {
            JDBCASAPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
        }
		
	}

	protected void loadRoutinePrivileges(List privileges) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.prepareStatement(ASASQLs.QUERY_ROUTINE_PERMISSIONS);
			stmt.setString(1, authId.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String routineName = rs.getString(1);
				String ownerName = rs.getString(2);
				boolean isExcutable = rs.getString(4).equals("Y");
				if(isExcutable)
				{
					Privilege p = SQLAccessControlFactory.eINSTANCE.createPrivilege();
					Schema schema = (Schema)SybaseASACatalogUtils.findElement(catalogObj.getCatalogDatabase().getSchemas(), ownerName);
					Routine r = (Routine)SybaseASACatalogUtils.findElement(schema.getRoutines(), routineName);
					p.setObject(r);
                    p.setAction(ASAUtil.PERMISSION_EXECUTE_ACTION);
					privileges.add(p);
				}
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
	}

	protected void loadTablePrivilegs(List privileges) 
    {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.prepareStatement(ASASQLs.QUERY_TABLE_PERMISSIONS);
			stmt.setString(1, authId.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String tableName = rs.getString(1);
				String tableOwner = rs.getString(2);
				char selectAuth = SybaseASACatalogUtils.getCharValue(rs.getString(3));
				char insertAuth = SybaseASACatalogUtils.getCharValue(rs.getString(4));
				char deleteAuth = SybaseASACatalogUtils.getCharValue(rs.getString(5));
				char updateAuth = SybaseASACatalogUtils.getCharValue(rs.getString(6));
				char alterAuth = SybaseASACatalogUtils.getCharValue(rs.getString(7));
				char referenceAuth = SybaseASACatalogUtils.getCharValue(rs.getString(8));
				String grantorName = rs.getString(9);
				char updateColAuth = SybaseASACatalogUtils.getCharValue(rs.getString(10));
//				int tableId = rs.getInt(11);
				
				SQLObject obj = null; 
				
				if(selectAuth == 'Y' || selectAuth == 'G')
				{
					Privilege p = createTablePrivilege(selectAuth, ASAUtil.PERMISSION_SELECT_ACTION, grantorName, tableOwner, tableName, obj);
					privileges.add(p);
				}
				
				if(insertAuth == 'Y' || insertAuth == 'G')
				{
					Privilege p = createTablePrivilege(insertAuth, ASAUtil.PERMISSION_INSERT_ACTION, grantorName, tableOwner, tableName, obj);
					privileges.add(p);
				}
				
				if(deleteAuth == 'Y' || deleteAuth == 'G')
				{
					Privilege p = createTablePrivilege(deleteAuth, ASAUtil.PERMISSION_DELETE_ACTION, grantorName, tableOwner, tableName, obj);
					privileges.add(p);
				}
				
				if(updateAuth == 'Y' || updateAuth == 'G')
				{
					Privilege p = createTablePrivilege(updateAuth, ASAUtil.PERMISSION_UPDATE_ACTION, grantorName, tableOwner, tableName, obj);
					privileges.add(p);
				}
				
				if(alterAuth == 'Y' || alterAuth == 'G')
				{
					Privilege p = createTablePrivilege(alterAuth, ASAUtil.PERMISSION_ALTER_ACTION, grantorName, tableOwner, tableName, obj);
					privileges.add(p);
				}
				
				if(referenceAuth == 'Y' || referenceAuth == 'G')
				{
					Privilege p = createTablePrivilege(referenceAuth, ASAUtil.PERMISSION_REFERENCE_ACTION, grantorName, tableOwner, tableName, obj);
					privileges.add(p);
				}
				
//				if(updateColAuth == 'Y' || updateColAuth == 'G')
//				{
//					results.add(new Integer(tableId));
//				}
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
//		return results;
	}
	
	private Privilege createTablePrivilege(char authChar, String action, String grantorName, String tableOwner, String tableName, SQLObject obj)
	{
        SybasePrivilege result = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
		result.setGrantable(authChar == 'G');
		result.setAction(action);
		AuthorizationIdentifier grantor = (AuthorizationIdentifier) SybaseASACatalogUtils
				.findElement(catalogObj.getCatalogDatabase().getAuthorizationIds(),
						grantorName);
		result.setGrantor(grantor);
        result.setGrantee(authId);
		if(obj == null)
			obj = findSQLObject(tableOwner, tableName, true);
		result.setObject(obj);
		return result;
	}
	
	private SQLObject findSQLObject(String tableOwner, String tableName, boolean isTable)
	{
		Schema schema = (Schema)SybaseASACatalogUtils.findElement(catalogObj.getCatalogDatabase().getSchemas(), tableOwner);
		SQLObject result = null;
		if(isTable)
			result = (SQLObject)SybaseASACatalogUtils.findElement(schema.getTables(), tableName);
		else
			result = (SQLObject)SybaseASACatalogUtils.findElement(schema.getRoutines(), tableName);
		
		return result;
	}
}
