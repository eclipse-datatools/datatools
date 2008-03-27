package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBasePrimaryKey;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.emf.common.util.EList;

abstract public class BaseTableASABaseLoader extends TableASABaseLoader{

	protected BaseTable baseTable;
	
	final public static String PRIMARY_KEY_TYPE = "P";
	final public static String UNIQUE_CONSTRAINT_TYPE = "U";
	final public static String FOREIGN_KEY_TYPE = "F";
	final public static String TABLE_CHECK_CONSTRAINT_TYPE = "T";
	final public static String COLUMN_CHECK_CONSTRAINT_TYPE = "C";
	
	public BaseTableASABaseLoader(BaseTable catalogTable) {
		super(catalogTable);
		baseTable = catalogTable;
	}

	final public void loadConstraints(EList constraintContainmentList)
	{
		try {
			boolean deliver = baseTable.eDeliver();
			baseTable.eSetDeliver(false);

			List existingConstraintList = new ArrayList(constraintContainmentList.size());
			existingConstraintList.addAll(constraintContainmentList);
			constraintContainmentList.clear();
			
			Object loadedPK = loadPrimaryKey(existingConstraintList);
			if(loadedPK != null)
				constraintContainmentList.add(loadedPK);
			constraintContainmentList.addAll(loadConstraints(existingConstraintList, UNIQUE_CONSTRAINT_TYPE));//add unique constraints
			constraintContainmentList.addAll(loadConstraints(existingConstraintList, FOREIGN_KEY_TYPE));
			constraintContainmentList.addAll(loadConstraints(existingConstraintList, TABLE_CHECK_CONSTRAINT_TYPE));
			constraintContainmentList.addAll(loadColumnCheckConstraints());
			
			for(int i = 0; i<constraintContainmentList.size(); i++)
			{
				ICatalogObject constraint = (ICatalogObject)constraintContainmentList.get(i);
				constraint.refresh();
			}
			
			baseTable.eSetDeliver(deliver);
		} catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	protected List loadColumnCheckConstraints() {
		List results = new ArrayList();
		EList columns = baseTable.getColumns();
		for(int i = 0; i<columns.size(); i++)
		{
			SybaseASABaseColumn column = (SybaseASABaseColumn)columns.get(i);
//			SybaseASABaseColumnCheckConstraint columnCheck = column.getColumnConstraint();
            List checks = column.getColumnConstraint();
//			if(columnCheck != null)
//				results.add(columnCheck);
            results.addAll(checks);
		}
		return results;
	}
	
	protected PrimaryKey loadPrimaryKey(List existingConstraintList) {
		PrimaryKey result = null;
		Connection conn = super.catalogObj.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String schemaName = baseTable.getSchema().getName();
			String tableName = baseTable.getName();
			stmt = conn.prepareStatement(SQLScriptsProvider.getQueryConstraintsScript(super.catalogObj.getCatalogDatabase()));
			stmt.setString(1, PRIMARY_KEY_TYPE);
			stmt.setString(2, tableName);
			stmt.setString(3, schemaName);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String pkName = rs.getString(1);
				PrimaryKey pk = (PrimaryKey) SybaseASACatalogUtils.findElement(
						existingConstraintList, pkName,
						SQLConstraintsPackage.eINSTANCE.getPrimaryKey());
				if(pk == null || !pk.getName().equals(pkName))
				{
					result = createCatalogPrimaryKey();				
					result.setName(pkName);
				}
				else
				{
					result = pk;
				}
			}
			
			//we remove the obsolete PK from existingConstraintList [cr476913]
			for (int i = 0; i < existingConstraintList.size(); i++)
            {
                Object constraint = existingConstraintList.get(i);
                if(constraint instanceof PrimaryKey)
                {
                    existingConstraintList.remove(constraint);
                    break;
                }
            }
		}
		catch(SQLException e)
		{
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		return result;
	}
	
	protected PrimaryKey createCatalogPrimaryKey()
	{
		return new SybaseASACatalogBasePrimaryKey(); 
	}

	protected List loadConstraints(List existingConstrs, String constrType)
	{
		List result = new ArrayList();
		Connection conn = super.catalogObj.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String schemaName = baseTable.getSchema().getName();
			String tableName = baseTable.getName();
			stmt = conn
			.prepareStatement(SQLScriptsProvider
					.getQueryConstraintsScript((SybaseASABaseDatabase) super.catalogObj
							.getCatalogDatabase()));
			stmt.setString(1, constrType);
			stmt.setString(2, tableName);
			stmt.setString(3, schemaName);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String constrName = rs.getString(1);
				SQLObject constraint = null;
				if (constrType.equals(TABLE_CHECK_CONSTRAINT_TYPE)) {
					constraint = (SQLObject)SybaseASACatalogUtils.findElement(
							existingConstrs, constrName,
							SQLConstraintsPackage.eINSTANCE
									.getCheckConstraint());
				} else if(constrType.equals(UNIQUE_CONSTRAINT_TYPE)){
					constraint = (SQLObject)SybaseASACatalogUtils.findElement(
							existingConstrs, constrName,
							SQLConstraintsPackage.eINSTANCE.getUniqueConstraint());
				} else{
					constraint = (SQLObject)SybaseASACatalogUtils.findElement(
							existingConstrs, constrName,
							SQLConstraintsPackage.eINSTANCE.getForeignKey());
				}
				
				if(constraint == null)
				{
					if(constrType.equals(UNIQUE_CONSTRAINT_TYPE))
					{
						constraint = createCatalogUniqueConstraint();
					}
					else if(constrType.equals(FOREIGN_KEY_TYPE))
					{
						constraint = createCatalogForeignKey();
					}
					else if(constrType.equals(TABLE_CHECK_CONSTRAINT_TYPE))
					{
						constraint = createCatalogTableCheckConstraint();
					}
					else if(constrType.equals(COLUMN_CHECK_CONSTRAINT_TYPE))
					{
						constraint = createCatalogColumnCheckConstraint();
					}
					constraint.setName(constrName);
				}
				result.add(constraint);
			}
		}
		catch(SQLException e)
		{
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		return result;
	}
	
	protected UniqueConstraint createCatalogUniqueConstraint()
	{
		return new SybaseASACatalogBaseUniqueConstraint();
	}
	
	protected ForeignKey createCatalogForeignKey()
	{
		return new SybaseASACatalogBaseForeignKey();
	}
	
	// create table check constraint
	protected CheckConstraint createCatalogTableCheckConstraint() {
		return new SybaseASACatalogBaseCheckConstraint();
	}

	// create column check constraint
	protected CheckConstraint createCatalogColumnCheckConstraint() {
		return new SybaseASACatalogBaseColumnCheckConstraint();
	}

}
