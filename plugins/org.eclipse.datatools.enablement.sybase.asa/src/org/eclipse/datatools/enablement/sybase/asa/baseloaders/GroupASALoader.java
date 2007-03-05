package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;

public class GroupASALoader extends AuthorizationIdentifierASALoader {

	protected Group group;
	
	public GroupASALoader(Group catalogGroup)
	{
		super(catalogGroup);
		this.group = catalogGroup;
	}
	
	final public void loadGroupUsers(List userContainmentList)
	{
		boolean deliver = group.eDeliver();
		group.eSetDeliver(false);

		userContainmentList.clear();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ASASQLs.QUERY_GROUP_MEMBERS);
			stmt.setString(1, group.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String memberName = rs.getString(1);
				AuthorizationIdentifier member = (AuthorizationIdentifier) SybaseASACatalogUtils
						.findElement(super.catalogObj.getCatalogDatabase().getAuthorizationIds(),
								memberName);
				userContainmentList.add(member);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		} finally {
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		group.eSetDeliver(deliver);
	}
}
