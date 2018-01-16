package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;

public class SybaseASABaseProxyTableLoader extends SybaseASABaseTableLoader {

	protected SybaseASABaseProxyTable proxyTable;
	
	public SybaseASABaseProxyTableLoader(SybaseASABaseProxyTable catalogTable)
	{
		super(catalogTable);
		this.proxyTable = catalogTable;
	}
	
	protected void processTableInfoResultSet(ResultSet rs) throws SQLException {
		super.processTableInfoResultSet(rs);
		String remoteLocation = rs.getString(4);
		proxyTable.setRemoteObjectLocation(remoteLocation);
	}

}
