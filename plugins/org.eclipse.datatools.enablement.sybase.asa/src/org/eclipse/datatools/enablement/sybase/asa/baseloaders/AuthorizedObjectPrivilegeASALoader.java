package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASAUtil;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class AuthorizedObjectPrivilegeASALoader {

	protected SQLObject object;
	protected Connection conn;
	protected ICatalogObject catalogObj;
	
	public AuthorizedObjectPrivilegeASALoader(SQLObject catalogAuthId)
	{
		this.object = catalogAuthId;
		this.catalogObj = (ICatalogObject)catalogAuthId;
		this.conn = catalogObj.getConnection();
	}
	
	final public void loadPrivilegs(EList privileges)
	{
		boolean deliver = object.eDeliver();
		object.eSetDeliver(false);
        
        privileges.clear();
		
        if (object instanceof Table)
        {
            loadTablePrivilegs(privileges);
        }
        else if (object instanceof Routine)
        {
            loadRoutinePrivileges(privileges);
        }
        
		object.eSetDeliver(deliver);
	}
    
	public HashMap loadTableColumnPrivilege() {
        HashMap privileges = new HashMap();

        Table table = ((Column)object).getTable();
        Schema schema = table.getSchema();
        
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try
        {
            stmt = conn.prepareStatement(ASASQLs.QUERY_COLUMN_PERMISSIONS_BY_OBJ);
            stmt.setString(1, schema.getName());
            stmt.setString(2, table.getName());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                int privilegeType = rs.getInt(1);
                boolean isGrantable = rs.getString(2).equals("Y");
                String grantorName = rs.getString(3);
                String granteeName = rs.getString(4);
                String columnName = rs.getString(5);

                SybasePrivilege p = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                AuthorizationIdentifier grantor = (AuthorizationIdentifier) SybaseASACatalogUtils.findElement(
                        catalogObj.getCatalogDatabase().getAuthorizationIds(), grantorName.trim());
                AuthorizationIdentifier grantee = (AuthorizationIdentifier) SybaseASACatalogUtils.findElement(
                        catalogObj.getCatalogDatabase().getAuthorizationIds(), granteeName.trim());
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
//                p.setObject(col);
                p.setGrantor(grantor);
                p.setGrantee(grantee);
                ArrayList privilegeList = (ArrayList)privileges.get(columnName);
                if (privilegeList == null)
                {
                    privilegeList = new ArrayList();
                    privileges.put(columnName, privilegeList);
                }
                privilegeList.add(p);
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
		return privileges;
	}

	protected void loadRoutinePrivileges(List privileges) {
		
	    Routine r = (Routine)object;
        Schema schema = r.getSchema();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.prepareStatement(ASASQLs.QUERY_ROUTINE_PERMISSIONS_BY_OBJ);
			stmt.setString(1, schema.getName());
			stmt.setString(2, r.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				boolean isExcutable = rs.getString(1).equals("Y");
				String granteeName = rs.getString(2);
				if(isExcutable)
				{

                    SybasePrivilege p = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                    AuthorizationIdentifier grantee = (AuthorizationIdentifier) SybaseASACatalogUtils.findElement(
                            catalogObj.getCatalogDatabase().getAuthorizationIds(), granteeName.trim());
                    //don't setObject here to avoid deadloop
					//p.setObject(r);
                    p.setAction(ASAUtil.PERMISSION_EXECUTE_ACTION);
                    p.setGrantee(grantee);
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
        Table table = (Table)object;
        Schema schema = table.getSchema();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.prepareStatement(ASASQLs.QUERY_TABLE_PERMISSIONS_BY_OBJ);
			stmt.setString(1, schema.getName());
			stmt.setString(2, table.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				char selectAuth = SybaseASACatalogUtils.getCharValue(rs.getString(1));
				char insertAuth = SybaseASACatalogUtils.getCharValue(rs.getString(2));
				char deleteAuth = SybaseASACatalogUtils.getCharValue(rs.getString(3));
				char updateAuth = SybaseASACatalogUtils.getCharValue(rs.getString(4));
				char alterAuth = SybaseASACatalogUtils.getCharValue(rs.getString(5));
				char referenceAuth = SybaseASACatalogUtils.getCharValue(rs.getString(6));
				String grantorName = rs.getString(7);
				char updateColAuth = SybaseASACatalogUtils.getCharValue(rs.getString(8));
                String granteeName = rs.getString(10);

                AuthorizationIdentifier grantor = (AuthorizationIdentifier) SybaseASACatalogUtils.findElement(
                        catalogObj.getCatalogDatabase().getAuthorizationIds(), grantorName.trim());
                AuthorizationIdentifier grantee = (AuthorizationIdentifier) SybaseASACatalogUtils.findElement(
                        catalogObj.getCatalogDatabase().getAuthorizationIds(), granteeName.trim());
				
				SQLObject obj = table; 
				
				if(selectAuth == 'Y' || selectAuth == 'G')
				{
					Privilege p = createTablePrivilege(selectAuth, ASAUtil.PERMISSION_SELECT_ACTION, grantor, grantee, obj);
					privileges.add(p);
				}
				
				if(insertAuth == 'Y' || insertAuth == 'G')
				{
					Privilege p = createTablePrivilege(insertAuth, ASAUtil.PERMISSION_INSERT_ACTION, grantor, grantee, obj);
					privileges.add(p);
				}
				
				if(deleteAuth == 'Y' || deleteAuth == 'G')
				{
					Privilege p = createTablePrivilege(deleteAuth, ASAUtil.PERMISSION_DELETE_ACTION, grantor, grantee, obj);
					privileges.add(p);
				}
				
				if(updateAuth == 'Y' || updateAuth == 'G')
				{
					Privilege p = createTablePrivilege(updateAuth, ASAUtil.PERMISSION_UPDATE_ACTION, grantor, grantee, obj);
					privileges.add(p);
				}
				
				if(alterAuth == 'Y' || alterAuth == 'G')
				{
					Privilege p = createTablePrivilege(alterAuth, ASAUtil.PERMISSION_ALTER_ACTION, grantor, grantee, obj);
					privileges.add(p);
				}
				
				if(referenceAuth == 'Y' || referenceAuth == 'G')
				{
					Privilege p = createTablePrivilege(referenceAuth, ASAUtil.PERMISSION_REFERENCE_ACTION, grantor, grantee, obj);
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
	}
	
	private Privilege createTablePrivilege(char authChar, String action, AuthorizationIdentifier grantor, AuthorizationIdentifier grantee, SQLObject obj)
	{
        SybasePrivilege result = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();

		result.setGrantable(authChar == 'G');
		result.setAction(action);
		result.setGrantor(grantor);
		result.setGrantee(grantee);
//		result.setObject(obj);
		return result;
	}
	
}
