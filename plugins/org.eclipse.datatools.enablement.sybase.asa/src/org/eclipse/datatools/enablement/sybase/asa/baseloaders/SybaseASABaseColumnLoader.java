package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsFactory;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class SybaseASABaseColumnLoader {

	protected Connection conn;
	protected SybaseASABaseColumn column;
	protected ICatalogObject catalogObj;
	
	public SybaseASABaseColumnLoader(SybaseASABaseColumn catalogColumn)
	{
		this.conn = ((ICatalogObject)catalogColumn).getConnection();
		this.column = catalogColumn;
		this.catalogObj = (ICatalogObject)catalogColumn;
	}
	
	 public void loadColumnInfo() {
		boolean deliver = column.eDeliver();
		column.eSetDeliver(false);
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Table table  = column.getTable();
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(ASASQLs.QUERY_COLUMN_INFO);
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, column.getName());
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
		column.eSetDeliver(deliver);
	}
	
	protected void processResultSet(ResultSet rs) throws SQLException
	{
		DatabaseDefinition dbDefn = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(catalogObj.getCatalogDatabase());
		
		String domainName = rs.getString(3);
		System.out.println();
		int width = rs.getInt(4);
		int scale = rs.getInt(5);
		String typeName = rs.getString(6);
		String typeOwner = rs.getString(7);
		boolean isNullable = rs.getString(8).equals("Y"); //$NON-NLS-1$
		boolean isUnique = rs.getString(9).equals("Y"); //$NON-NLS-1$
		char colType = SybaseASACatalogUtils.getCharValue(rs.getString(10));
		String defaultValue = rs.getString(11);
		String remarks = rs.getString(12);
		
		DataType type = null;
		if(typeName == null || typeName.equals("")) //$NON-NLS-1$
		{
			type = SybaseASACatalogUtils.getASAPredefinedType(width, scale, domainName, dbDefn);
		}
		else
		{
			Schema typeSchema = (Schema)SybaseASACatalogUtils.findElement(catalogObj.getCatalogDatabase().getSchemas(), typeOwner);
			type = (DataType)SybaseASACatalogUtils.findElement(typeSchema.getUserDefinedTypes(), typeName);
		}
		column.setDataType(type);
		column.setNullable(isNullable);
		column.setDescription(remarks);
		column.setUnique(isUnique);
		if(colType == 'C')
		{
			//computed column
			ValueExpression vExpr = SQLExpressionsFactory.eINSTANCE.createValueExpressionDefault();
			vExpr.setSQL(defaultValue);
			column.setGenerateExpression(vExpr);
			column.setTypeOfDefault(TypeOfDefault.COMPUTED_VALUE_LITERAL);
		}
		else
		{
			column.setDefaultValue(defaultValue);
			if(defaultValue != null)
			{
				column
						.setTypeOfDefault(SybaseASACatalogUtils.isSystemDefault(defaultValue) ? TypeOfDefault.SYSTEM_DEFINED_LITERAL
								: TypeOfDefault.USER_DEFINED_LITERAL);
			}
			else
			{
				column.setTypeOfDefault(null);
			}
		}
	}
	
	public void loadColumnCheckConstraint(SybaseASABaseColumnCheckConstraint oldColCheck)
	{
		boolean deliver = column.eDeliver();
		column.eSetDeliver(false);
		
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase(); 
			
		SybaseASABaseColumnCheckConstraint check = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Table table  = column.getTable();
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryColumnConstraintsScript(db));
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, column.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String checkName = rs.getString(1);
				if(oldColCheck != null && oldColCheck.getName().equals(checkName))
				{
					check = oldColCheck;
					((ICatalogObject)check).refresh();
				}
				else
				{
					check = new SybaseASACatalogBaseColumnCheckConstraint();
					check.setName(checkName);
				}
			}
			column.setColumnConstraint(check);
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		column.eSetDeliver(deliver);
	}
	
}
