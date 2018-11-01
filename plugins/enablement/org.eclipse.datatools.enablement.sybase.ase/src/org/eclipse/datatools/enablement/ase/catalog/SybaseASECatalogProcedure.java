/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesFactoryImpl;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogProcedure extends SybaseASEProcedureImpl implements ICatalogObject, IAdaptable {
//TODO SQLJ procedure
	private static final long serialVersionUID = 2969974132309352611L;
	static final String rsKeyword = "RESULT SETS"; //$NON-NLS-1$
    
	public void refresh() {
		if(isNeedRefresh())
		{
		    synchronized (parametersLoaded)
            {
                if (parametersLoaded.booleanValue())
                {
                    parametersLoaded = Boolean.FALSE;
                }
            }
            synchronized (privilegesLoaded)
            {
                if (privilegesLoaded.booleanValue())
                {
                    privilegesLoaded = Boolean.FALSE;
                }
            }
            synchronized (sourceLoaded)
            {
                if (sourceLoaded.booleanValue())
                {
                    sourceLoaded = Boolean.FALSE;
                    setSource(null);
                }
            }
            synchronized (isSystemProcLoaded)
            {
                if (isSystemProcLoaded.booleanValue())
                {
                    isSystemProcLoaded = Boolean.FALSE;
                }
            }
            synchronized (transactionModeLoaded)
            {
                if (transactionModeLoaded.booleanValue())
                {
                    transactionModeLoaded = Boolean.FALSE;
                }
            }

            RefreshManager.getInstance().referesh(this);
		}
	}

	public Source getSource() {
		synchronized (sourceLoaded) {
			if (!sourceLoaded.booleanValue())
				loadCode();
		}
		return super.getSource();
	}

	protected void loadCode() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Source source = SQLRoutinesFactoryImpl.eINSTANCE.createSource();
		source.setBody(SybaseASECatalogUtils.getCompiledObjectText(this, this
				.getConnection(), this.getSchema().getCatalog().getName()));
		setSource(source);
		this.eSetDeliver(deliver);
		sourceLoaded = Boolean.TRUE;
	}
	
	public boolean isSystemProcedure() {
		synchronized (isSystemProcLoaded) {
			if(!isSystemProcLoaded.booleanValue())
				loadIsSystemProc();
		}
		return super.isSystemProcedure();
	}

	private void loadIsSystemProc()
	{
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Catalog sysprocCatalog = (Catalog)ASEUtil.getSQLObject(this.getCatalogDatabase().getCatalogs(), "sybsystemprocs");
		Schema dboSchema = (Schema)ASEUtil.getSQLObject(sysprocCatalog.getSchemas(), "dbo");
		Object proc = ASEUtil.findSP(dboSchema.getProcedures(), this.getName(), this.getGroupNumber());
		super.setSystemProcedure(proc != null);
		this.eSetDeliver(deliver);
		isSystemProcLoaded = Boolean.TRUE;
	}
	
	public TransactionModeType getTransactionMode() {
		synchronized (transactionModeLoaded) {
			if(!transactionModeLoaded.booleanValue())
				loadTransactionMode();
		}
		return super.getTransactionMode();
	}
	
	private void loadTransactionMode() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_PROCEDURE_STATUS);
			stmt.setString(1, this.getSchema().getName());
			stmt.setString(2, this.getName());
			stmt.setInt(3, this.getGroupNumber());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				int status2 = rs.getInt(1);
				if ((status2 & TransactionModeType.CHAINED) == TransactionModeType.CHAINED) {
					super.setTransactionMode(TransactionModeType.CHAINED_LITERAL);
				} else if ((status2 & TransactionModeType.ANYMODE) == TransactionModeType.ANYMODE) {
					super.setTransactionMode(TransactionModeType.ANYMODE_LITERAL);
				} else {
					super.setTransactionMode(TransactionModeType.UNCHAINED_LITERAL);
				}
			}
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);

		}
		this.eSetDeliver(deliver);
		transactionModeLoaded = Boolean.TRUE;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLRoutinesPackage.PROCEDURE__SOURCE:
			getSource();
			break;
		case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
			getParameters();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE:
			isSystemProcedure();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE:
			getTransactionMode();
			break;
		case SybasesqlmodelPackage.SYBASE_AUTHORIZED_OBJECT__PRIVILEGES:
		    getPrivileges();
		    break;
		}
		return super.eIsSet(eFeature);
	}

	public Database getCatalogDatabase() {
		return getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public EList getParameters() {
		synchronized (parametersLoaded) {
			if (!parametersLoaded.booleanValue())
            {
				loadParameters();
				parametersLoaded = Boolean.TRUE;
            }
		}
		return super.getParameters();
	}

	protected void loadParameters() {
        new SybaseASEParameterLoader(this).loadParameterInfo(super.getParameters());
	}
	
	public EList getPrivileges() {
	    synchronized (privilegesLoaded) {
	        if (!privilegesLoaded.booleanValue())
	        {
	            loadPrivileges();
                privilegesLoaded = Boolean.TRUE;
	        }
	    }
	    return super.getPrivileges();
	}
	
	protected void loadPrivileges() {
        super.getPrivileges().clear();
        SybaseASECatalog catalog = (SybaseASECatalog)getSchema().getCatalog();
        List privileges = SybaseASECatalogUtils.getPrivileges(this, catalog);
        super.getPrivileges().addAll(privileges);
	}
	
	
	/**
	 * 
	 * @param proc
	 * @return the procedure name append with group number
	 */
	public static String getASEProcedureName(SybaseASEProcedure proc)
	{
	    String procName = proc.getName();
	    int groupNumb = proc.getGroupNumber();
	    return procName + ";" + groupNumb;
	}
	
	private Boolean sourceLoaded = Boolean.FALSE;
	private Boolean parametersLoaded = Boolean.FALSE;
	private Boolean privilegesLoaded = Boolean.FALSE;
	private Boolean isSystemProcLoaded = Boolean.FALSE;
	private Boolean transactionModeLoaded = Boolean.FALSE;

    public class SybaseASEParameterLoader {

        protected Routine routine;
        public SybaseASEParameterLoader(Routine routine)
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
            String oldCatalog = null;
            try
            {
                oldCatalog = conn.getCatalog();
                Schema schema = routine.getSchema();
                Database database = schema.getCatalog().getDatabase();
                conn.setCatalog(schema.getCatalog().getName());
                stmt = conn.prepareStatement(ASESQLs.PARAMETERS_QUERY);
                //String qualName = SQLUtil.quote(schema.getName() + "." + routine.getName(), "'");
                String qualName = schema.getName() + "." + routine.getName();
                stmt.setString(1, qualName);
                rs = stmt.executeQuery();
                while(rs.next())
                {
                    //COLUMN_NAME,COLUMN_TYPE,DATA_TYPE,TYPE_NAME,PRECISION,LENGTH,SCALE,NULLABLE,REMARKS, char_length
                    String parmName = rs.getString(1);
                    int paramType = rs.getInt(2);
                    int dataType = rs.getInt(3);
                    String typeName = rs.getString(4);
                    int precision = rs.getInt(5);
                    int length = rs.getInt(6);
                    int scale = rs.getInt(7);
                    boolean nullable = rs.getBoolean(8);
                    String remarks = rs.getString(9);
                    int iCharSize = rs.getInt(10);                    
                    
                    SybaseParameter param = SybasesqlmodelFactory.eINSTANCE.createSybaseParameter();
                    param.setName(parmName);
                    if(dataType <= 100)
                    {
                        DatabaseDefinition dbDefn = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
                        PredefinedDataType datatype = SybaseASECatalogUtils.getASEPredefinedType(length, precision, scale, iCharSize, typeName, dbDefn);
                        param.setDataType(datatype);
                    }
                    else
                    {
                        DataType domain = SybaseASECatalogUtils
                                .getSpecifiedUserDefinedDatatype(schema.getCatalog(), typeName);
                        param.setDataType(domain);
                    }
                    ParameterMode mode = null;
                    JDBCParameterType pType = JDBCParameterType.IN_LITERAL;
                    //ase out parameters are inouts indeed
                    if(paramType == 2)
                    {
                        mode = ParameterMode.INOUT_LITERAL;
                        pType = JDBCParameterType.IN_OUT_LITERAL;
                    }
                    else
                    {
                        mode = ParameterMode.IN_LITERAL;
                    }
                    param.setMode(mode);
                    param.setNullable(nullable);
                    param.setJDBCParameterType(pType);
                    param.setDescription(remarks);
                    
                    paramContaintmentList.add(param);
                }
            }
            catch (SQLException e) {
                JDBCASEPlugin.getDefault().log(e);
            }
            finally
            {
                SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
            }
            routine.eSetDeliver(deliver);
        }
        
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
	private boolean isNeedRefresh()
	{
	    if (sourceLoaded.booleanValue() || parametersLoaded.booleanValue() || privilegesLoaded.booleanValue()
                || transactionModeLoaded.booleanValue())
        {
            return true;
        }
        else
        {
            return false;
        }
	}
}
