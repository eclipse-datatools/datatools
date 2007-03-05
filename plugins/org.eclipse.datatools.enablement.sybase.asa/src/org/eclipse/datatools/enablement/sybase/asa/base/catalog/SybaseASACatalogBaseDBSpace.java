package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDBSpaceImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseDBSpace extends SybaseASABaseDBSpaceImpl implements ICatalogObject
{
	private static final long serialVersionUID = 7542251482941333022L;
	
	protected Boolean fileLoaded = Boolean.FALSE;

	public Database getCatalogDatabase() {
		return getDatabase();
	}

	public Connection getConnection() 
	{
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (fileLoaded) {
			if(fileLoaded.booleanValue())
			{
				fileLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DB_SPACE__FILE_NAME:
			this.getFileName();
			break;
		}
		return super.eIsSet(eFeature);
	}	
	
	public String getFileName() {
		synchronized (fileLoaded) {
			if(!fileLoaded.booleanValue())
			{
				loadFileName();
				fileLoaded = Boolean.TRUE;
			}
		}
		return super.getFileName();
	}
	
	private void loadFileName() {
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ASASQLs.QUERY_DBSPACE_FILENAME);
			stmt.setString(1, this.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
                String fileName = rs.getString(1);
                super.setFileName(fileName);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
	}
	
}
