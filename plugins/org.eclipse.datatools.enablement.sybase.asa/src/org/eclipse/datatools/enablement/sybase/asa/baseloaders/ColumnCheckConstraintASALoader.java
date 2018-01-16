package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class ColumnCheckConstraintASALoader extends CheckConstraintASALoader {

	protected SybaseASABaseColumnCheckConstraint columnCheckConstraint;
	public ColumnCheckConstraintASALoader(SybaseASABaseColumnCheckConstraint catalogCheckConstraint) {
		super(catalogCheckConstraint);
		columnCheckConstraint = catalogCheckConstraint;
	}

	protected void precessResultSet(ResultSet rs) throws SQLException
	{
		super.precessResultSet(rs);
		
//		String columnName = rs.getString(1);
//		SybaseASABaseColumn column = (SybaseASABaseColumn) SybaseASACatalogUtils
//				.findElement(columnCheckConstraint.getBaseTable().getColumns(), columnName);
//		column.setColumnConstraint(columnCheckConstraint);
	}
	
	protected Table getTable()
	{
		return columnCheckConstraint.getColumn().getTable();
	}
}
