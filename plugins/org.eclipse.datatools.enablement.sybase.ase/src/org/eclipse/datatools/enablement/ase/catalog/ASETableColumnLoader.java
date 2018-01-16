package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class ASETableColumnLoader extends JDBCTableColumnLoader
{
	private static final String ATTR_COL_NAME                         = "COLUMN_NAME";
	
	String oldCatalog = null;
	Connection conn = null;
    PreparedStatement stmt = null;
	
	public ASETableColumnLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}
	
	/**
	 * Creates a result set to be used by the loading logic. The SQL statement is:
	 * <pre>
	 * select c.name from syscolumns c where id = ? order by c.colid
	 * </pre>
	 * 
	 */
	protected ResultSet createResultSet() throws SQLException {
        Table table = this.getTable();
		String query = null;
		String catalog = table.getSchema().getCatalog().getName();
		int tableId = ((ICatalogTable) table).getTableId();
		conn = ((ICatalogObject) table).getConnection();
		query = ASESQLs.QUERY_TABLE_COLUMNS;
		ResultSet rs = null;
		oldCatalog = conn.getCatalog();
		conn.setCatalog(catalog);
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, tableId);
        try{
		rs = stmt.executeQuery();
        }
        catch (SQLException e){
            JDBCASEPlugin.getDefault().log(e);
            throw e;
        }
		return rs;
	}
	
	protected void closeResultSet(ResultSet rs) {
		try
        {
            if (rs != null)
            {
                rs.close();
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            JDBCASEPlugin.getDefault().log(e);
        }
        try
        {
            if (oldCatalog != null)
            {
                conn.setCatalog(oldCatalog);
            }             
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
	}
    
    protected void initialize(Column column, ResultSet rs) throws SQLException{
        column.setName(rs.getString(COLUMN_COLUMN_NAME));
    }
    
    protected Column createColumn() {
        return new SybaseASECatalogColumn();
    }
	// load column name, type, constraints according DSE requirement
//	public void loadColumns(EList containmentList, Collection existingCols) 
//	{
//        Table table = this.getTable();
//
//        String query = null;
//        String catalog = table.getSchema().getCatalog().getName();
//        int tableId = ((ICatalogTable)table).getTableId();
//        Connection conn = ((ICatalogObject)table).getConnection();
//
//        query = ASESQLs.QUERY_TABLE_COLUMNS;
//
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        String oldCatalog = null;
//        try
//        {
//            oldCatalog = conn.getCatalog();
//            conn.setCatalog(catalog);
//            stmt = conn.prepareStatement(query);
//            stmt.setInt(1, tableId);
//            rs = stmt.executeQuery();
//            while (rs.next())
//            {
//                String columnName = rs.getString(ATTR_COL_NAME);
//                Column column;
//                Object element = ASEUtil.getSQLObject(existingCols, columnName);
//                if (element != null)
//                {
//                    column = (Column) element;
//                    containmentList.add(column);
//                    ((ICatalogObject) element).refresh();
//                }
//                else
//                {
//                    column = new SybaseASECatalogColumn();
//                    column.setName(columnName);
//                    containmentList.add(column);
//                }
//            }
//        }
//        catch (SQLException e)
//        {
//            JDBCASEPlugin.getDefault().log(e);
//        }
//        finally
//        {
//        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
//        }
//	}
}
