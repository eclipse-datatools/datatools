package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;

public class SybaseASABaseTableLoader extends BaseTableASABaseLoader {

	protected SybaseASABaseTable asaBaseTable;
	
	public SybaseASABaseTableLoader(SybaseASABaseTable catalogTable)
	{
		super(catalogTable);
		this.asaBaseTable = catalogTable;
	}
	
	protected void processTableInfoResultSet(ResultSet rs) throws SQLException {
			String dbspaceName = rs.getString(1);

			String remark = rs.getString(7);
			
			SybaseASABaseDBSpace dbspace = (SybaseASABaseDBSpace) SybaseASACatalogUtils
					.findElement(((SybaseASABaseDatabase) asaBaseTable
							.getSchema().getDatabase()).getDbSpaces(),
							dbspaceName);
			
			asaBaseTable.setDbSpace(dbspace);
			asaBaseTable.setDescription(remark);
	}

}
