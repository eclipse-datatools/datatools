package org.eclipse.datatools.enablement.sybase.asa.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseTempTableLoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;

public class SybaseASATempTableLoader extends SybaseASABaseTempTableLoader {

	protected SybaseASATempTable asaTempTable;
	
	public SybaseASATempTableLoader(SybaseASATempTable catalogTable)
	{
		super(catalogTable);
		this.asaTempTable = catalogTable;
	}
	
	protected void processTableInfoResultSet(ResultSet rs) throws SQLException {
		super.processTableInfoResultSet(rs);
		int pctfree = rs.getInt(6);
		if(rs.wasNull())
		{
			pctfree = -1;
		}
		
		asaTempTable.setPctfree(pctfree);
	}

	protected Index createCatalogIndex() {
		return new SybaseASACatalogIndex();
	}
	
	protected ForeignKey createCatalogForeignKey() {
		return new SybaseASACatalogForeignKey();
	}
}
