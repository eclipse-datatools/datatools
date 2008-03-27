package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	protected Table table;
	protected ICatalogObject catalogObj;
	
	public SybaseASABaseColumnLoader(SybaseASABaseColumn catalogColumn)
	{
		this.conn = ((ICatalogObject)catalogColumn).getConnection();
		this.table = catalogColumn.getTable();
		this.catalogObj = (ICatalogObject)catalogColumn;
	}
	
	 public void loadColumnInfo() {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(ASASQLs.QUERY_COLUMN_INFO);
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			//stmt.setString(3, "%");
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
	}
	
	protected void processResultSet(ResultSet rs) throws SQLException
	{

		DatabaseDefinition dbDefn = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(catalogObj.getCatalogDatabase());
		
		String column_name = rs.getString(2);
		String domainName = rs.getString(3);
		int width = rs.getInt(4);
		int scale = rs.getInt(5);
		String typeName = rs.getString(6);
		String typeOwner = rs.getString(7);
		boolean isNullable = rs.getString(8).equals("Y");
		boolean isUnique = rs.getString(9).equals("Y");
		char colType = SybaseASACatalogUtils.getCharValue(rs.getString(10));
		String defaultValue = rs.getString(11);
		String remarks = rs.getString(12);

		SybaseASABaseColumn column = (SybaseASABaseColumn)SybaseASACatalogUtils.findElement(table.getColumns(), column_name);
        if (column == null)
        {
            return;
        }
		
		DataType type = null;
		if(typeName == null || typeName.equals(""))
		{
			type = SybaseASACatalogUtils.getASAPredefinedType(width, scale, domainName, dbDefn);
		}
		else
		{
			Schema typeSchema = (Schema)SybaseASACatalogUtils.findElement(catalogObj.getCatalogDatabase().getSchemas(), typeOwner);
			type = (DataType)SybaseASACatalogUtils.findElement(typeSchema.getUserDefinedTypes(), typeName);
		}

        boolean deliver = column.eDeliver();
        column.eSetDeliver(false);

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
            column.setIsComputedColumn(true);
            column.setDefaultValue(defaultValue);
		}
		else
		{
			column.setGenerateExpression(null);
            column.setIsComputedColumn(false);
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
	      column.eSetDeliver(deliver);

	}
	
	public HashMap loadColumnCheckConstraint(HashMap existingConstraints)
	{
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase(); 
		
		HashMap constraints = new HashMap();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryColumnConstraintsScript(db));
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String checkName = rs.getString(1);
				String columnName = rs.getString(2);
				if (columnName == null)
				{
				    continue;
				}
                SybaseASABaseColumnCheckConstraint check = null;
                List existingConstraintsList = (List)existingConstraints.get(columnName);
                if (existingConstraintsList != null)
                {
                    check = (SybaseASABaseColumnCheckConstraint)SybaseASACatalogUtils.findElement(existingConstraintsList, checkName);
                }
                
                ArrayList constraintList = (ArrayList)constraints.get(columnName);
                if (constraintList == null)
                {
                    constraintList = new ArrayList();
                    constraints.put(columnName, constraintList);
                }
				if(check != null)
				{
                    constraintList.add(check);
					((ICatalogObject)check).refresh();
				}
				else
				{
					check = new SybaseASACatalogBaseColumnCheckConstraint();
					check.setName(checkName);
                    constraintList.add(check);
				}
			}
//			column.setColumnConstraint(check);
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		return constraints;
	}
	
}
