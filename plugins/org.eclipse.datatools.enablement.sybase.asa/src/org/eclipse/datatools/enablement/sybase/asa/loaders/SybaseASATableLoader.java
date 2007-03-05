package org.eclipse.datatools.enablement.sybase.asa.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseTableLoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;

public class SybaseASATableLoader extends SybaseASABaseTableLoader {

	protected SybaseASATable asaTable;
	
	public SybaseASATableLoader(SybaseASATable catalogTable)
	{
		super(catalogTable);
		this.asaTable = catalogTable;
	}
	
	protected void processTableInfoResultSet(ResultSet rs) throws SQLException {
		super.processTableInfoResultSet(rs);
		int pctfree = rs.getInt(6);
		if(rs.wasNull())
		{
			pctfree = -1;
		}
		
		asaTable.setPctfree(pctfree);
	}

	protected Index createCatalogIndex() {
		return new SybaseASACatalogIndex();
	}
	
	protected ForeignKey createCatalogForeignKey() {
		return new SybaseASACatalogForeignKey();
	}
}
