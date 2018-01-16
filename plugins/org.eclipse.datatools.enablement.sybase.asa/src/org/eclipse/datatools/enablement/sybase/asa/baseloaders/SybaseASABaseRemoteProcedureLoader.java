package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure;

public class SybaseASABaseRemoteProcedureLoader extends RoutineInfoASALoader {

	private SybaseASABaseRemoteProcedure remoteProc;
	
	public SybaseASABaseRemoteProcedureLoader(SybaseASABaseRemoteProcedure remoteProc)
	{
		super(remoteProc);
		this.remoteProc = remoteProc;
	}
	
	protected void processResultSet(ResultSet rs) throws SQLException {
        String remoteSvr = rs.getString(1);
        remoteProc.setLocation(remoteSvr);
		super.processResultSet(rs);
	}
}
