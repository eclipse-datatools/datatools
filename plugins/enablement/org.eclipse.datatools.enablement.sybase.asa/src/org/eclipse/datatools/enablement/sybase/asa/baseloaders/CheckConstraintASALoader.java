package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsFactory;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class CheckConstraintASALoader {

	public static final String TABLE_CHECK_CONSTRAINT_TYPE = "T";
	public static final String COLUMN_CHECK_CONSTRAINT_TYPE = "C";
	
	protected CheckConstraint check;
	protected Connection conn;
	protected ICatalogObject catalogObj;
	
	/**
	 * 
	 * @param catalogCheckConstraint 
	 */
	public CheckConstraintASALoader(CheckConstraint catalogCheckConstraint)
	{
		this.check = catalogCheckConstraint;
		this.catalogObj = (ICatalogObject)catalogCheckConstraint;
		this.conn = catalogObj.getConnection();
	}
	
	/**
	 * 
	 * @param type "T" means tablecheckconstraint and type "C" means columncheckconstraint
	 */
	final public void loadCCInfo(String type)
	{
		boolean deliver = check.eDeliver();
		check.eSetDeliver(false);
		
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase(); 
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Table table  = getTable();
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryCheckConstraintInfo(db));
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, check.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				precessResultSet(rs);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		check.eSetDeliver(deliver);
	}
	
	protected void precessResultSet(ResultSet rs) throws SQLException
	{
		String conditions = rs.getString(2);
		SearchCondition sc = SQLExpressionsFactory.eINSTANCE.createSearchConditionDefault();
		sc.setSQL(SQLUtil.parseSearchStatement(conditions));
		check.setSearchCondition(sc);
	}
    
	protected Table getTable()
	{
		return check.getBaseTable();
	}
}
