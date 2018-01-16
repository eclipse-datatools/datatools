package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsFactory;

public class SybaseASABaseViewTableLoader extends TableASABaseLoader{

	protected SybaseASABaseViewTable viewTable;
	
	public SybaseASABaseViewTableLoader(SybaseASABaseViewTable catalogTable)
	{
		super(catalogTable);
		viewTable = catalogTable;
	}
	
	//load view statement, query expression
	final public void loadViewInfo()
	{
		boolean deliver = viewTable.eDeliver();
		viewTable.eSetDeliver(false);

		Connection conn = super.catalogObj.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(ASASQLs.QUERY_VIEW_DEFN);
			stmt.setString(1, viewTable.getSchema().getName());
			stmt.setString(2, viewTable.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String source = rs.getString(1);
				String defn = rs.getString(2);
                String remarks = rs.getString(3);
				
				String viewText = source != null ? source : defn; 
				SQLStatement statement = SQLStatementsFactory.eINSTANCE.createSQLStatementDefault();
				statement.setSQL(viewText);
				viewTable.setStatement(statement);
                viewTable.setDescription(remarks);
				
				SybaseASACatalogUtils.parseView(viewTable, viewText);
			}
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		viewTable.eSetDeliver(deliver);
	}

	protected void processTableInfoResultSet(ResultSet rs) {
		//do nothing for view
	}
}
