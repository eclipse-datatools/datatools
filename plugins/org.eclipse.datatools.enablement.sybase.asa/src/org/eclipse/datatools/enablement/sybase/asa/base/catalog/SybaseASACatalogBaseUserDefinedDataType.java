package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseUserDefinedDataType extends SybaseASABaseUserDefinedTypeImpl implements ICatalogObject
{
	private static final long serialVersionUID = 1757200934996274093L;

	protected Boolean UDTInfoLoaded = Boolean.FALSE;
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
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

	protected void loadUDTInfo() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = createUDTInfoResultSet(conn);
			stmt = rs.getStatement();
			while(rs.next())
			{
                processUDTInfoResultSet(rs);
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
	
	protected ResultSet createUDTInfoResultSet(Connection conn) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(ASASQLs.QUERY_UDT_INFO);
		stmt.setString(1, this.getName());
		return stmt.executeQuery();
	}
	
	protected void processUDTInfoResultSet(ResultSet rs) throws SQLException
	{
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
        	
        
        super.setPredefinedRepresentation(pdt);
        super.setNullable(nulltype);
        super.setDefaultValue(defaultValue);
        super.setDefaultType(td);
        super.getConstraint().clear();
        CheckConstraint checkContr = SQLConstraintsFactory.eINSTANCE.createCheckConstraint();
        SearchCondition sc = SQLExpressionsFactory.eINSTANCE.createSearchConditionDefault();
        sc.setSQL(check);
        checkContr.setSearchCondition(sc);
        super.getConstraint().add(checkContr);
	}
}
