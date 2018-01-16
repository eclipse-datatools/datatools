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
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class RoutineInfoASALoader {

	private Routine routine;
	private Connection conn;
	private ICatalogObject catalogObj;
	
	public RoutineInfoASALoader(Routine catalogRoutine)
	{
		this.routine = catalogRoutine;
		this.catalogObj = (ICatalogObject)catalogRoutine;
		this.conn = catalogObj.getConnection();
	}
	
	final public void loadRoutineInfo()
	{
		boolean deliver = routine.eDeliver();
		routine.eSetDeliver(false);
		
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Schema schema = routine.getSchema();
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryRoutineInfo(db));
			stmt.setString(1, schema.getName());
			stmt.setString(2, routine.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				processResultSet(rs);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		routine.eSetDeliver(deliver);
	}
	
	protected void processResultSet(ResultSet rs) throws SQLException
	{
        String remark = rs.getString(2);
        String source = rs.getString(3);
        String def = rs.getString(4);
        
        routine.setDescription(remark);
        Source src = SQLRoutinesFactory.eINSTANCE.createSource();
        src.setBody(source != null ? source : def);
        routine.setSource(src);
	}
}
