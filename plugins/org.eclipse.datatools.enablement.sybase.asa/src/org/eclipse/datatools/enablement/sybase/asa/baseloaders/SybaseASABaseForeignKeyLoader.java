package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class SybaseASABaseForeignKeyLoader {

	protected SybaseASABaseForeignKey fk;
	protected Connection conn;
	protected ICatalogObject catalogObj;
	
	public SybaseASABaseForeignKeyLoader(SybaseASABaseForeignKey catalogFK)
	{
		this.fk = catalogFK;
		this.catalogObj = (ICatalogObject)catalogFK;
		this.conn = catalogObj.getConnection();
	}
	
	final public void loadFKInfo(EList memberContainmentList, EList refMemContainmentList) {
		boolean deliver = fk.eDeliver();
		fk.eSetDeliver(false);

		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Table table  = fk.getBaseTable();
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryForeignKeyInfo(db));
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, fk.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				processFKInfoResultSet(rs, memberContainmentList, refMemContainmentList);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		fk.eSetDeliver(deliver);
	}
	
	protected void processFKInfoResultSet(ResultSet rs, EList memberContainmentList, EList refMemContainmentList) throws SQLException
	{
		String primaryTableName = rs.getString(5);
        String primaryTableOwner = rs.getString(6);
        String foreignColListStr = rs.getString(2);
        String primaryColListStr = rs.getString(7);

        char updateActinChar = SybaseASACatalogUtils.getCharValue(rs.getString(8));
        char deleteActinChar = SybaseASACatalogUtils.getCharValue(rs.getString(9));
        boolean isClustered = rs.getString(3).equals("Y");
        String remarks = rs.getString(4);
        
        Schema primarySchema = (Schema) SybaseASACatalogUtils
				.findElement(catalogObj.getCatalogDatabase().getSchemas(),
						primaryTableOwner);
        Table primaryTable = (Table) SybaseASACatalogUtils
				.findElement(primarySchema.getTables(),
						primaryTableName);
        
        List primaryColumnList = SybaseASACatalogUtils.getSpecifiedColumns(primaryColListStr, primaryTable.getColumns());
        List foreignColumnList = SybaseASACatalogUtils.getSpecifiedColumns(foreignColListStr, fk.getBaseTable().getColumns());
        
        memberContainmentList.clear();
        memberContainmentList.addAll(foreignColumnList);
        refMemContainmentList.clear();
        refMemContainmentList.addAll(primaryColumnList);
        fk.setOnUpdate(getReferentialActionType(updateActinChar));
        fk.setOnDelete(getReferentialActionType(deleteActinChar));
        fk.setClustered(isClustered);
        fk.setDescription(remarks);
        fk.setUniqueConstraint(findUniqueConstraint(primaryColumnList, primaryTable));
        fk.setRoleName(fk.getName());
        fk.setReferencedTable((BaseTable)primaryTable);
	}
	
	protected ReferentialActionType getReferentialActionType(char typeChar)
	{
		ReferentialActionType result = null;
		switch(typeChar)
        {
        case 0: // '\0'
        	//default value
        	result = ReferentialActionType.RESTRICT_LITERAL;
            break;
        case 67: // 'C'
        	result = ReferentialActionType.CASCADE_LITERAL;
            break;
        case 78: // 'N'
        	result = ReferentialActionType.SET_NULL_LITERAL;
            break;

        case 68: // 'D'
            result = ReferentialActionType.SET_DEFAULT_LITERAL;
            break;
        }
        return result;
	}
	
	protected UniqueConstraint findUniqueConstraint(List primaryColumnList, Table primaryTable)
	{
		UniqueConstraint result = null;
		EList constraints = ((BaseTable)primaryTable).getConstraints();
		for(int i = 0; i<constraints.size(); i++)
		{
			TableConstraint constraint = (TableConstraint)constraints.get(i);
			if(constraint instanceof PrimaryKey || constraint instanceof UniqueConstraint)
			{
				UniqueConstraint uc = (UniqueConstraint)constraint;
				EList members = uc.getMembers();
				if(members.size() == primaryColumnList.size())
				{
					boolean found = true;
					for(int j = 0; j<primaryColumnList.size(); j++)
					{
						if(!members.contains(primaryColumnList.get(j)))
						{
							found = false;
							break;
						}
					}
					if(found)
					{
						result = uc;
						break;
					}
				}
			}
		}
		return result;
	}
}
