package org.eclipse.datatools.enablement.sybase.asa.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseForeignKeyLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.emf.common.util.EList;

public class SybaseASAForeignKeyLoader extends SybaseASABaseForeignKeyLoader {

	private SybaseASAForeignKey foreingKey;
	
	public SybaseASAForeignKeyLoader(SybaseASAForeignKey catalogFk)
	{
		super(catalogFk);
		this.foreingKey = catalogFk;
	}
	
	protected void processFKInfoResultSet(ResultSet rs, EList memberContainmentList, EList refMemContainmentList) throws SQLException {
		boolean isCheckOnCommit = rs.getString(10).equals("Y");
		boolean isNullable = ("Y").equals(rs.getString(11));
		foreingKey.setNullable(isNullable);
		foreingKey.setCheckOnCommit(isCheckOnCommit);
        super.processFKInfoResultSet(rs, memberContainmentList, refMemContainmentList);
	}
}
