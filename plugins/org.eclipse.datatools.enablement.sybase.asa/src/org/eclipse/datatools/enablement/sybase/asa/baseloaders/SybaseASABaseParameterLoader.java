package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCProcedureColumnLoader;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.modelbase.sql.datatypes.Domain;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

public class SybaseASABaseParameterLoader {

	protected Routine routine;
	public SybaseASABaseParameterLoader(Routine routine)
	{
		this.routine = routine;
	}
	
	final public void loadParameterInfo(EList paramContaintmentList)
	{
		boolean deliver = routine.eDeliver();
		routine.eSetDeliver(false);

		paramContaintmentList.clear();
		
		Connection conn = ((ICatalogObject)routine).getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Schema schema = routine.getSchema();
			Database database = schema.getDatabase();
			stmt = conn.prepareStatement(ASASQLs.QUERY_PARAMETER_INFO);
			stmt.setString(1, schema.getName());
			stmt.setString(2, routine.getName());
			stmt.setString(3, "%");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String parmName = rs.getString(2);
				String domainName = rs.getString(3);
				int width = rs.getInt(4);
				int scale = rs.getInt(5);
				String typeName = rs.getString(6);
				int parmtype = rs.getInt(7);
				boolean parmModeIn = rs.getString(8).equals("Y");
				boolean parmModeOut = rs.getString(9).equals("Y");
				
				SybaseASABaseParameter param = SybaseasabasesqlmodelFactory.eINSTANCE.createSybaseASABaseParameter();
				param.setName(parmName);
				if(typeName == null || typeName.equals(""))
				{
					DatabaseDefinition dbDefn = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
					PredefinedDataType datatype = SybaseASACatalogUtils.getASAPredefinedType(width, scale, domainName, dbDefn);
					param.setDataType(datatype);
				}
				else
				{
					Domain domain = SybaseASACatalogUtils
							.getSpecifiedUserDefinedDatatype(database, typeName);
					param.setDataType(domain);
				}
				param.setParmType(getParameterType(parmtype));
				ParameterMode mode = null;
				if(parmModeIn && parmModeOut)
				{
					mode = ParameterMode.INOUT_LITERAL;
				}
				else if(parmModeIn && !parmModeOut)
				{
					mode = ParameterMode.IN_LITERAL;
				}
				else if(!parmModeIn && parmModeOut)
				{
					mode = ParameterMode.OUT_LITERAL;
				}
				param.setMode(mode);
				//nullable not supported
//                param.setNullable(rs.getShort(JDBCProcedureColumnLoader.COLUMN_NULLABLE) != DatabaseMetaData.procedureNoNulls);
//                JDBCParameterType pType = JDBCParameterType.get(rs.getInt(JDBCProcedureColumnLoader.COLUMN_COLUMN_TYPE));
//                param.setJDBCParameterType(pType);

				paramContaintmentList.add(param);
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
	
	private ParameterType getParameterType(int iType)
	{
		switch(iType)
		{
		case 0:
			return ParameterType.VARIABLE_LITERAL;
		case 1:
			return ParameterType.RESULT_LITERAL;
		case 2:
			return ParameterType.SQLSTATE_LITERAL;
		case 3:
			return ParameterType.SQLCODE_LITERAL;
		default:
			return ParameterType.RETURN_LITERAL;
		}
	}
}
