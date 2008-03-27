package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsFactory;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseUserDefinedDataType extends SybaseASABaseUserDefinedTypeImpl implements ICatalogObject,IAdaptable
{
    public static final int BATCH_LOAD_THRESHHOLD = 10;
	private static final long serialVersionUID = 1757200934996274093L;

	protected Boolean UDTInfoLoaded = Boolean.FALSE;
	
	public Database getCatalogDatabase() {
		return getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (UDTInfoLoaded) {
			if(UDTInfoLoaded.booleanValue())
			{
				UDTInfoLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION:
			getPredefinedRepresentation();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE:
			getDefaultType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_VALUE:
			getDefaultValue();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE:
			getNullable();
			break;
		}
		return super.eIsSet(eFeature);
	}

	public PredefinedDataType getPredefinedRepresentation() {
		synchronized (UDTInfoLoaded) {
			if(!UDTInfoLoaded.booleanValue())
			{
				loadUDTInfo();
				UDTInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getPredefinedRepresentation();
	}
	
	public TypeOfDefault getDefaultType() {
		synchronized (UDTInfoLoaded) {
			if(!UDTInfoLoaded.booleanValue())
			{
				loadUDTInfo();
				UDTInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDefaultType();
	}

	public AllowNullType getNullable() {
		synchronized (UDTInfoLoaded) {
			if(!UDTInfoLoaded.booleanValue())
			{
				loadUDTInfo();
				UDTInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getNullable();
	}

	public String getDefaultValue() {
		synchronized (UDTInfoLoaded) {
			if(!UDTInfoLoaded.booleanValue())
			{
				loadUDTInfo();
				UDTInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDefaultValue();
	}

	public EList getConstraint() {
		synchronized (UDTInfoLoaded) {
			if(!UDTInfoLoaded.booleanValue())
			{
				loadUDTInfo();
				UDTInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getConstraint();
	}
	
	public EList getSuperConstraint()
	{
	    return super.getConstraint();
	}

	private List getAllUDTs()
	{
        List allUDTs = new ArrayList();
        for (Iterator it = this.getSchema().getDatabase().getSchemas().iterator(); it.hasNext();)
        {
            Schema s = (Schema) it.next();
            allUDTs.addAll(s.getUserDefinedTypes());
        }
        return allUDTs;
	}
	
	private boolean needBatchLoad(List allUDTs)
	{
	    int numOfUDTNotLoaded = 0;
        for (Iterator it = allUDTs.iterator(); it.hasNext();)
        {
            SybaseASACatalogBaseUserDefinedDataType udt = (SybaseASACatalogBaseUserDefinedDataType) it.next();
            if (!udt.UDTInfoLoaded.booleanValue())
            {
                numOfUDTNotLoaded ++;
                if (numOfUDTNotLoaded >= BATCH_LOAD_THRESHHOLD)
                {
                    return true;
                }
            }
        }
        return false;
	}
	
	protected void loadUDTInfo() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		List allUDTs = getAllUDTs();
		boolean needBatchLoadStrategy = needBatchLoad(allUDTs);
		
		Connection conn = this.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = createUDTInfoResultSet(conn, needBatchLoadStrategy);
			stmt = rs.getStatement();
			while(rs.next())
			{
                processUDTInfoResultSet(rs, allUDTs);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		this.eSetDeliver(deliver);
	}

	protected AllowNullType getNullType(char charNull)
	{
		switch (charNull) {
		case 'Y':
			return AllowNullType.NULLABLE_LITERAL;
		case 'N':
			return AllowNullType.NOT_NULLABLE_LITERAL;
		default:
			return AllowNullType.DATABASE_DEFAULT_LITERAL;
		}
	}
	
	protected ResultSet createUDTInfoResultSet(Connection conn, boolean needBatchLoadStrategy) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(needBatchLoadStrategy ? ASASQLs.QUERY_ALL_UDT_INFO
                : ASASQLs.QUERY_UDT_INFO);
		if(!needBatchLoadStrategy)
		{
		    stmt.setString(1, this.getName());
		}
		return stmt.executeQuery();
	}
	
    public static SQLObject getSQLObject(List sqlObjs, String objName)
    {
        Iterator it = sqlObjs.iterator();
        while (it.hasNext())
        {
            SQLObject obj = (SQLObject) it.next();
            if (obj.getName().equals(objName))
            {
                return obj;
            }
        }
        return null;
    }
	
	protected void processUDTInfoResultSet(ResultSet rs, List allUDTs) throws SQLException
	{
	    String typeName = rs.getString(1);
	    
	    // The UDT to be populated
	    SybaseASACatalogBaseUserDefinedDataType udt = (SybaseASACatalogBaseUserDefinedDataType)getSQLObject(allUDTs, typeName);
	    if(udt == null || udt.UDTInfoLoaded.booleanValue())
	    {
	    	return;
	    }
		String domainName = rs.getString(3);
        int width = rs.getInt(4);
        int scale = rs.getInt(5);
        char nulls = SybaseASACatalogUtils.getCharValue(rs.getString(6));
        String defaultValue = rs.getString(7);
        String check = rs.getString(8);
        
        DatabaseDefinition dbDefn = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(getCatalogDatabase());
        PredefinedDataType pdt = SybaseASACatalogUtils.getASAPredefinedType(width, scale, domainName, dbDefn);
        AllowNullType nulltype = getNullType(nulls);
        TypeOfDefault td = null;
        if(defaultValue != null)
        {
        	td = SybaseASACatalogUtils
					.isSystemDefault(defaultValue) ? TypeOfDefault.SYSTEM_DEFINED_LITERAL
					: TypeOfDefault.USER_DEFINED_LITERAL;
        }
        	
        
        udt.setPredefinedRepresentation(pdt);
        udt.setNullable(nulltype);
        udt.setDefaultValue(defaultValue);
        udt.setDefaultType(td);
        udt.getSuperConstraint().clear();
        if(check != null && !check.equals(""))
        {
            CheckConstraint checkContr = SQLConstraintsFactory.eINSTANCE.createCheckConstraint();
            SearchCondition sc = SQLExpressionsFactory.eINSTANCE.createSearchConditionDefault();
            sc.setSQL(check);
            checkContr.setSearchCondition(sc);
            udt.getSuperConstraint().add(checkContr);
        }
        udt.UDTInfoLoaded = Boolean.TRUE;
	}
	
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}	
}
