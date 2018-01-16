package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsFactory;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class IndexInfoASALoader {

	private SybaseASABaseIndex index;
	private Connection conn;
	private ICatalogObject catalogObj;
	
	public IndexInfoASALoader(SybaseASABaseIndex catalogIndex)
	{
		this.index = catalogIndex;
		this.catalogObj = (ICatalogObject)catalogIndex;
		this.conn = catalogObj.getConnection();
	}
	
	final public void loadIndexInfo(List memberList)
	{
		boolean deliver = index.eDeliver();
		index.eSetDeliver(false);
		
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Table table  = index.getTable();
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryIndexInfo(db));
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, index.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				
                String dbspaceName = rs.getString(2);
                String colListStr = rs.getString(3);
                
                boolean isUnique = false;
                if(db.isBaseOnASA10())
                {
                	int intUnique = rs.getInt(4);
                	isUnique = intUnique != 4;
                }
                else
                {
                	String strUnique = rs.getString(4);
                	isUnique = strUnique.equals("Y") || strUnique.equals("U");
                }
                boolean isClustered = rs.getString(5).equals("Y");
                String remarks = rs.getString(6);
                String orderListStr = rs.getString(7);
                
                SybaseASABaseDBSpace dbspace = (SybaseASABaseDBSpace) SybaseASACatalogUtils
						.findElement(((SybaseASABaseDatabase) catalogObj
								.getCatalogDatabase()).getDbSpaces(),
								dbspaceName);
                index.setDbSpace(dbspace);
                
                List columnNameList = parseColumnList(colListStr);
                List orderList = parseOrderList(orderListStr);
                memberList.clear();
                for(int i = 0; i<columnNameList.size(); i++)
                {
                	String colName = (String)columnNameList.get(i);
                	Column column = (Column)SybaseASACatalogUtils.findElement(table.getColumns(), colName);
                	if(column != null)
                	{
                		IndexMember member = SQLConstraintsFactory.eINSTANCE.createIndexMember();
                		String order = ((String)orderList.get(i)).trim();
                		member.setColumn(column);
                		member.setIncrementType(order.equals("A")?IncrementType.ASC_LITERAL:IncrementType.DESC_LITERAL);
                		memberList.add(member);
                	}
                }
                
                index.setUnique(isUnique);
                index.setClustered(isClustered);
                index.setDescription(remarks);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		index.eSetDeliver(deliver);
	}
	
	private List parseColumnList(String columnListStr)	
	{
		List results = new ArrayList();
		
		int index = -1;
		while((index = columnListStr.indexOf(ASASQLs.COLUMN_NAME_DELIMITER)) != -1) 
		{
			String columnName = columnListStr.substring(0, index);
			results.add(columnName);
			columnListStr = columnListStr.substring(index + ASASQLs.COLUMN_NAME_DELIMITER.length());
		}
		
		results.add(columnListStr);
		
		return results;
	}
	
	private List parseOrderList(String orderListStr)
	{
		List results = new ArrayList();
		
		int index = -1;
		while((index = orderListStr.indexOf(ASASQLs.COLUMN_ORDER_DELIMITER)) != -1) 
		{
			String columnName = orderListStr.substring(0, index);
			results.add(columnName);
			orderListStr = orderListStr.substring(index + ASASQLs.COLUMN_ORDER_DELIMITER.length());
		}
		
		results.add(orderListStr);
		
		return results;
	}
}
