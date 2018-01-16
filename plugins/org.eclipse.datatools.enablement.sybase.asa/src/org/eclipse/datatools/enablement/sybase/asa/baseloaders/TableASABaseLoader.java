package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;

public abstract class TableASABaseLoader {

	protected ICatalogObject catalogObj;
	protected Table table;
	
	private SoftReference columnLoaderRef;
	
	public TableASABaseLoader(Table catalogTable)
	{
		this.table = catalogTable;
		this.catalogObj = (ICatalogObject) catalogTable;
	}
	
	final public void loadColumns(EList columnContainmentList) {
		try {
			boolean deliver = table.eDeliver();
			table.eSetDeliver(false);
			
			List existingColumns = new ArrayList(columnContainmentList.size());
			existingColumns.addAll(columnContainmentList);
			getColumnLoader().clearColumns(columnContainmentList);
			getColumnLoader().loadColumns(columnContainmentList, existingColumns);

			table.eSetDeliver(deliver);

		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	protected Column createCatalogColumn()
	{
		return new SybaseASACatalogBaseColumn();
	}
	
	protected JDBCTableColumnLoader createColumnLoader() {
		return new ASABaseColumnLoader(catalogObj);
	}
	
	private JDBCTableColumnLoader getColumnLoader() {
		if (columnLoaderRef == null || columnLoaderRef.get() == null) {
			columnLoaderRef = new SoftReference(createColumnLoader());
		}
		return (JDBCTableColumnLoader) columnLoaderRef.get();
	}
	
	
	static public class ASABaseColumnLoader extends JDBCTableColumnLoader {
		
		public ASABaseColumnLoader(ICatalogObject catalogObj)
		{
			super(catalogObj);
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Table table = getTable();
			Schema schema = table.getSchema();
			PreparedStatement stmt = this.getCatalogObject().getConnection()
					.prepareStatement(ASASQLs.QUERY_TABLE_COLLUMNS);
			stmt.setString(1, schema.getName());
			stmt.setString(2, table.getName());
			return stmt.executeQuery();
		}
        
        protected void closeResultSet(ResultSet rs)
        {
            try {
                Statement stmt = rs.getStatement();
                super.closeResultSet(rs);
                stmt.close();
            }
            catch (SQLException e) {
            }
        }
        
        protected Column createColumn() {
            return new SybaseASACatalogBaseColumn();
        }
        
        protected void initialize(Column column, ResultSet rs) throws SQLException 
        {
            column.setName(rs.getString(COLUMN_COLUMN_NAME));
        }
	}
	
	final public void loadIndices(EList indexConstainmentList)
	{
		boolean deliver = table.eDeliver();
		table.eSetDeliver(false);

		List existingIndices = new ArrayList(indexConstainmentList.size());
		existingIndices.addAll(indexConstainmentList);
		indexConstainmentList.clear();
		
		Connection conn = catalogObj.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
		
		try {
			stmt = conn.prepareStatement(getIndexQuerySQL(db));
			stmt.setString(1, table.getSchema().getName());
			stmt.setString(2, table.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String indexName = rs.getString(1);

				Index index = (Index)SybaseASACatalogUtils.findElement(existingIndices, indexName);
				if(index == null)
				{
					index = createCatalogIndex();
					initIndex(index, rs);
					indexConstainmentList.add(index);
					((SybaseASABaseSchema)table.getSchema()).getSuperIndices().add(index);
				}
				else
				{
					indexConstainmentList.add(index);
					((SybaseASABaseSchema)table.getSchema()).getSuperIndices().add(index);
					((ICatalogObject)index).refresh();
				}
			}
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		table.eSetDeliver(deliver);
	}

    protected String getIndexQuerySQL(SybaseASABaseDatabase db)
    {
        return SQLScriptsProvider.getQueryTableIndex(db);
    }
	
	protected void initIndex(Index index, ResultSet rs) throws SQLException
	{
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
		String idxName = rs.getString(1);
		index.setName(idxName);
		boolean isSysGen = false;
		if(db.isBaseOnASA10())
		{//for ASA10
			int indexUnique = rs.getInt(2);
			int indexCategory = rs.getInt(3);
			switch(indexCategory)
	        {
	        case 1: // '\001'
	        case 2: // '\002'
	            isSysGen = true;

	        case 3: // '\003'
	            isSysGen = indexUnique == 2;
	        }
		}
		else
		{//for ASA9
			String strUnique = rs.getString(2);
			isSysGen = strUnique.equals("U");
		}
		index.setSystemGenerated(isSysGen);
	}
	
	protected Index createCatalogIndex()
	{
		return new SybaseASACatalogBaseIndex();
	}
	
	final public void loadTriggers(EList triggerContainmentList)
	{
		boolean deliver = table.eDeliver();
		table.eSetDeliver(false);

		List existingTriggers = new ArrayList(triggerContainmentList.size());
		existingTriggers.addAll(triggerContainmentList);
		triggerContainmentList.clear();
		
		Connection conn = catalogObj.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(ASASQLs.QUERY_TABLE_TRIGGERS);
			stmt.setString(1, table.getSchema().getName());
			stmt.setString(2, table.getName());

			rs = stmt.executeQuery();
			while(rs.next())
			{
				String triggerName = rs.getString(1);
				SQLObject trigger = (SQLObject)SybaseASACatalogUtils.findElement(existingTriggers, triggerName);
				if(trigger == null)
				{
					trigger = new SybaseASACatalogBaseTrigger();
					trigger.setName(triggerName);
					triggerContainmentList.add(trigger);
					((SybaseASABaseSchema)table.getSchema()).getSuperTriggers().add(trigger);
				}
				else
				{
					triggerContainmentList.add(trigger);
					((SybaseASABaseSchema)table.getSchema()).getSuperTriggers().add(trigger);
					((ICatalogObject)trigger).refresh();
				}
			}
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		table.eSetDeliver(deliver);
	}
	
	protected Trigger createCatalogTrigger()
	{
		return new SybaseASACatalogBaseTrigger();
	}
	
	//load table info
	final public void loadTableInfo()
	{
		boolean deliver = table.eDeliver();
		table.eSetDeliver(false);

		Connection conn = catalogObj.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = createTableInfoResultSet(conn);
			stmt = rs.getStatement();
			while(rs.next())
			{
				processTableInfoResultSet(rs);
			}
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		table.eSetDeliver(deliver);
	}
	
	protected ResultSet createTableInfoResultSet(Connection conn) throws SQLException
	{
		SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
		
		PreparedStatement stmt = conn.prepareStatement(SQLScriptsProvider.getQueryTableInfo(db));
		stmt.setString(1, table.getSchema().getName());
		stmt.setString(2, table.getName());
		
		return stmt.executeQuery();
	}
	
	abstract protected void processTableInfoResultSet(ResultSet rs) throws SQLException;
	
	/**
     * 
     * @author linsong
     */
    public static interface IASABaseLoaderTable
    {
        Boolean isTriggerLoaded();
        Boolean isIndexLoaded();
        void setTriggerLoaded(Boolean loaded);
        void setIndexLoaded(Boolean loaded);
        EList getTriggerSuper();
        EList getIndexSupper();
    }
}
