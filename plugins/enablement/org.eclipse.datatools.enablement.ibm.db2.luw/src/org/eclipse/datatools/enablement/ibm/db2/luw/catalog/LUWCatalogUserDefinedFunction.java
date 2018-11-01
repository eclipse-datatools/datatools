/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
//bgp import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.CatalogCache;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelFactory;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Source;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UserDefinedFunctionImpl;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogUserDefinedFunction extends DB2UserDefinedFunctionImpl implements ICatalogObject ,IDatabaseObject{

   private static final long serialVersionUID = -9150711511025438670L;

   public void refresh() {
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		if (this.parameterLoaded) {
			this.parameters.clear();
			this.parameterLoaded = false;
		}
		this.privilegeLoaded = false;
		
		if (loaded) {
			this.extendedOptions.clear();
			this.loaded = false;
		}
		
		RefreshManager.getInstance().referesh(this);
	}	

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();		
	}

	public void refresh(int refreshType){
		if ((IDatabaseObject.IMPACTS & refreshType)  == IDatabaseObject.IMPACTS) {
			this.impacts.clear();
			this.impactsLoaded = false;
		}
		if ((IDatabaseObject.STATISTICS & refreshType)  == IDatabaseObject.STATISTICS) {
			this.statistics.clear();
			this.statisticsLoaded = false;
		}
	}

	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
		}
	}

	public EList getParameters() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getParameters();
		} else {
			if(!this.parameterLoaded) this.loadParameters();
			return this.parameters;
		}
	}
	
	public RoutineResultTable getReturnTable(){
		if(!this.parameterLoaded) this.loadParameters();
		return this.returnTable;
	}
	
	public Parameter getReturnScalar(){
		if(!this.parameterLoaded) this.loadParameters();
		return this.returnScalar;
	}
	
	public Parameter getReturnCast(){
		if(!this.parameterLoaded) this.loadParameters();
		return this.returnCast;
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isExternalAction()
     */
    public boolean isExternalAction() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
	        return super.isExternalAction();
		} else {
	        if(!this.loaded) this.load();
	        return super.isExternalAction();
		}
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.wst.rdb.internal.models.sql.routines.Routine#getSource()
     */
    public Source getSource() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
	        return super.getSource();
		} else {
	        if(!this.loaded) this.load();
	        return super.getSource();
		}
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.wst.rdb.internal.models.sql.routines.Routine#getSqlDataAccess()
     */
    public DataAccess getSqlDataAccess() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
	        return super.getSqlDataAccess();
		} else {
	        if(!this.loaded) this.load();
	        return super.getSqlDataAccess();
		}
    }

	/* (non-Javadoc)
	 * @see org.eclipse.wst.rdb.internal.models.sql.routines.Routine#getLanguage()
	 */
	public String getLanguage() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getLanguage();
		} else {
			if(!this.loaded) this.load();
			return super.getLanguage();
		}
	}

	public EList getPrivileges() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getPrivileges();
		} else {
			if (!this.privilegeLoaded) this.loadPrivileges();
			return this.privileges;
		}
	}
	
	public EList getExtendedOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getExtendedOptions();
		} else {
		   if(!this.loaded) this.load();
			return super.extendedOptions;
		}
	}

	public String getExternalName() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getExternalName();
		} else {
			   if(!this.loaded) this.load();
			return super.externalName;
		}
	}

	public boolean isImplicitSchema() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.isImplicitSchema();
		} else {
			if ( !this.loaded )  this.load();
			return super.implicitSchema;
		}
	}
	
	
	public ICatalogObject[] getImpacted(){
		if (!this.impactsLoaded) {
			this.impacts = this.getImpactedObjects();
			this.impactsLoaded = true;
		}
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
	}
	
	public Collection getStatistics(){
		if (!this.statisticsLoaded) {
			this.statistics = LUWCatalogProcedure.getStatistics(this.getConnection(), this);
			this.statisticsLoaded = true;
		}
		return this.statistics;
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DEPENDENCIES) {
			this.getDependencies();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PARAMETERS) {
			this.getParameters();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_TABLE) {
			this.getReturnTable();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_SCALAR) {
			this.getReturnScalar();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_CAST) {
			this.getReturnCast();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PRIVILEGES) {
			this.getPrivileges();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EXTENDED_OPTIONS) {
			this.getExtendedOptions();
		}
		else if(id == DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EXTERNAL_NAME) {
			this.getExternalName();
		}

		
		return super.eIsSet(eFeature);
	}

	public byte getLUWFunctionType() {
		return this.luwFunctionType;
	}
	
	public void setLUWFunctionType(byte type) {
		this.luwFunctionType = type;
	}

	/**
	 * Load miscellaneous information about the UDF
	 */
	private synchronized void load() {
        if(this.loaded) return;
        this.loaded = true;

        Database database = getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

		String propkey = getSchema().getName() + "." + getName(); //$NON-NLS-1$

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        // Make sure this collection is initialized so we can add to it later
		super.getExtendedOptions();

//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isPropertyCacheLoaded( PROP_UDF_VALUES ) ) {
//				loadInfoFromCache( cache, propkey );
//				this.eSetDeliver(deliver);        
//	
//				return;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_UDF_VALUES );
//bgp>		}

        Connection connection = this.getConnection();

        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());
        String version = definition.getVersion();
        float ver = 8.0f;
        try {
            ver = Float.parseFloat(version.substring(1));
        }
        catch (NumberFormatException e) {            
        }
        
        String query = null;
        if (ver < 8.0f) {
            query = "SELECT LANGUAGE,"   //$NON-NLS-1$
                    + " CASE VARIANT WHEN 'Y' THEN 'N' WHEN 'N' THEN 'Y' END AS DETERMINISTIC," //$NON-NLS-1$
                    + " SIDE_EFFECTS AS EXTERNAL_ACTION, CONTAINS_SQL AS SQL_DATA_ACCESS, TYPE AS FUNCTIONTYPE," //$NON-NLS-1$
                    + " BODY AS TEXT,PARALLELIZABLE AS PARALLEL, '' AS DEBUG_MODE,'' AS IMPLEMENTATION, '' AS CLASS," //$NON-NLS-1$
                    + " FUNCSCHEMA, FUNCNAME, SPECIFICNAME" //$NON-NLS-1$
                    + " FROM SYSCAT.FUNCTIONS"; //$NON-NLS-1$

//<bgp            if ( !cache.isBatchLoading() ) {
//	            query += " WHERE FUNCSCHEMA='" + this.getSchema().getName() + "'" //$NON-NLS-1$ //$NON-NLS-2$
//	            		+ " AND FUNCNAME='" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
//	                    + " AND SPECIFICNAME='" + this.getSpecificName() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
//bgp>            }
        }
        else {    
            query = "SELECT LANGUAGE, DETERMINISTIC, SQL_DATA_ACCESS, EXTERNAL_ACTION, FUNCTIONTYPE," //$NON-NLS-1$
            		+ " TEXT, PARALLEL, DEBUG_MODE,IMPLEMENTATION, CLASS," //$NON-NLS-1$
                    + " ROUTINESCHEMA AS FUNCSCHEMA, ROUTINENAME AS FUNCNAME, SPECIFICNAME" //$NON-NLS-1$
                    + " FROM SYSCAT.ROUTINES";  //$NON-NLS-1$

//<bgp            if ( !cache.isBatchLoading() ) {
//	            query += " WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "' AND ROUTINENAME='" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//	                    + " AND SPECIFICNAME='" + LUWUtil.getIdentifier(this.getSpecificName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
//bgp>            }
        }

        Statement s = null;
        ResultSet r = null;

        try {
           s = connection.createStatement();
           r = s.executeQuery(query);

           while(r.next()) {
        	   LoadInfo info = new LoadInfo();
        	   
   				info.schemaname = r.getString( "FUNCSCHEMA" ).trim(); //$NON-NLS-1$
   				info.funcname = r.getString( "FUNCNAME" ).trim(); //$NON-NLS-1$
                info.DETERMINISTIC = r.getString("DETERMINISTIC"); //$NON-NLS-1$
                info.SQL_DATA_ACCESS = r.getString("SQL_DATA_ACCESS"); //$NON-NLS-1$
                info.EXTERNAL_ACTION = r.getString("EXTERNAL_ACTION"); //$NON-NLS-1$
                info.FUNCTIONTYPE = r.getString("FUNCTIONTYPE"); //$NON-NLS-1$
                info.TEXT = r.getString("TEXT"); //$NON-NLS-1$
                info.PARALLEL = r.getString("PARALLEL"); //$NON-NLS-1$
                info.DEBUG_MODE = r.getString("DEBUG_MODE"); //$NON-NLS-1$
                info.IMPLEMENTATION = r.getString("IMPLEMENTATION"); //$NON-NLS-1$
       			info.CLASS = r.getString( "CLASS" ); //$NON-NLS-1$
       			
//<bgp       			if ( cache.isBatchLoading() ) {
//       				cache.cacheProperty( PROP_UDF_VALUES, info.getKey(), info );
//       			}
//bgp>       			else {
					info.setValues( this );
//bgp       			}
            }
        }
        catch (Exception e) {
			e.printStackTrace();
        }
        finally {
           if (r != null) {
              try { r.close(); }
              catch (SQLException e) { }
              r = null;
           }
           if (s != null) {
              try { s.close(); }
              catch (SQLException e) { }
              s = null;
           }
        }

//<bgp        if ( cache.isBatchLoading() ) {
//        	loadInfoFromCache( cache, propkey );
//bgp>        }

        this.eSetDeliver(deliver);        
    }

	/** Object containing UDF information for the cache */
	private static class LoadInfo {
		String schemaname;
		String funcname;
        String DETERMINISTIC;
        String SQL_DATA_ACCESS;
        String EXTERNAL_ACTION;
        String FUNCTIONTYPE;
        String TEXT;
        String PARALLEL;
        String DEBUG_MODE;
        String IMPLEMENTATION;
        String CLASS;

        /**
         * Get the key for cache lookup
         * 
         * @return The key
         */
        public String getKey() {
        	return this.schemaname + "." + this.funcname; //$NON-NLS-1$
        }

        /**
         * Update a UDF with values from the cache
         * 
         * @param func
         */
        public void setValues( LUWCatalogUserDefinedFunction func ) {
    		func.setDeterministic( this.DETERMINISTIC.equals("Y") ); //$NON-NLS-1$
            func.setExternalAction( this.EXTERNAL_ACTION .equals("E")); //$NON-NLS-1$
    		func.setFunctionType( this.FUNCTIONTYPE );
    		func.setAllowParallel( "Y".equals( this.PARALLEL ) ); //$NON-NLS-1$

    		DataAccess da;
            // 'C' - CONTAINS SQL, 'M' - MODIFIES SQL DATA, 'N' - No SQL, 'R' - READS SQL DATA
            if (this.SQL_DATA_ACCESS.equals("C")) da = DataAccess.CONTAINS_SQL_LITERAL; //$NON-NLS-1$
            else if (this.SQL_DATA_ACCESS.equals("M")) da = DataAccess.MODIFIES_SQL_DATA_LITERAL; //$NON-NLS-1$
            else if (this.SQL_DATA_ACCESS.equals("N")) da = DataAccess.NO_SQL_LITERAL; //$NON-NLS-1$
            else da = DataAccess.READS_SQL_DATA_LITERAL;
    		func.setSqlDataAccess( da );

    		if ( this.TEXT != null )
    		{
    			// remove un-readable character
    			this.TEXT = this.TEXT
    					.replaceAll(
    							"[\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u000b\u000c\u000e\u000f" //$NON-NLS-1$
    									+ "\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f]", //$NON-NLS-1$
    							" " ); //$NON-NLS-1$
    			DB2Source source = DB2ModelFactory.eINSTANCE.createDB2Source();
    			source.setBody( this.TEXT );
    			func.setSource( source );
    		}

            DB2ExtendedOptions extOpts = DB2ModelFactory.eINSTANCE.createDB2ExtendedOptions(); 
    		extOpts.setForDebug( "ALLOW".equalsIgnoreCase( this.DEBUG_MODE ) ); //$NON-NLS-1$
    		EList extList = func.getExtendedOptions();
    		extList.add( extOpts );

    		if ( "JAVA".equals( func.getLanguage() ) ) //$NON-NLS-1$
    		{
    			func.setJavaExternalName( this.CLASS, this.IMPLEMENTATION );
    		}
    		else if ( !"SQL".equals( func.getLanguage() ) ) //$NON-NLS-1$
    		{
    			func.setExternalName( this.IMPLEMENTATION );
    		}

    		func.updateImplicitSchemaFlag();
        }
	}

//<bgp	/**
//	 * Update this UDF with values from the cache
//	 * 
//	 * @param cache
//	 * @param propkey
//	 */
//	private void loadInfoFromCache( CatalogCache cache, String propkey )
//	{
//		LoadInfo info = (LoadInfo)cache.getCachedProperty( PROP_UDF_VALUES, propkey );
//
//		if ( info != null ) {
//			info.setValues( this );
//		}
//bgp>	}

	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogProcedure.loadDependencies(this.getConnection(), super.getDependencies(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadParameters() {
		if(this.parameterLoaded) return;
		this.parameterLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogUserDefinedFunction.loadParameters(this.getConnection(), super.getParameters(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}
	
	private synchronized void loadPrivileges() {
		if(this.privilegeLoaded) return;
		this.privilegeLoaded = true;

		EList privileges = super.getPrivileges();
		for (Iterator iter= privileges.iterator(); iter.hasNext();){
			Privilege privilege = (Privilege) iter.next();
			privilege.setGrantor(null);
			privilege.setGrantee(null);
		}
		
		privileges.clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogProcedure.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	public static void loadParameters(Connection connection, EList parameterList, LUWCatalogUserDefinedFunction function) throws SQLException {
		final Database database = function.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

        String version = databaseDefinition.getVersion();
        float ver = 8.0f;
        try {
            ver = Float.parseFloat(version);
        }
        catch (NumberFormatException e) {            
        }

        String query = null;
        if (ver < 8.0f) {
            query="SELECT PARMNAME,ROWTYPE,AS_LOCATOR AS LOCATOR,TYPESCHEMA,TYPENAME,LENGTH,SCALE,CODEPAGE, ' ' AS REMARKS,TARGET_TYPESCHEMA,TARGET_TYPENAME" + //$NON-NLS-1$
            " FROM SYSCAT.FUNCPARMS" + //$NON-NLS-1$
            " WHERE FUNCSCHEMA='" + LUWUtil.getIdentifier(function.getSchema().getName()) + "'" +  //$NON-NLS-1$ //$NON-NLS-2$
            " AND FUNCNAME='" + LUWUtil.getIdentifier(function.getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
            " AND SPECIFICNAME='" + function.getSpecificName() + "'" + //$NON-NLS-1$ //$NON-NLS-2$
            " ORDER BY ORDINAL"; //$NON-NLS-1$
        }
        else {
		    query="SELECT PARMNAME,ROWTYPE,LOCATOR,TYPESCHEMA,TYPENAME,LENGTH,SCALE,CODEPAGE,REMARKS,TARGET_TYPESCHEMA,TARGET_TYPENAME" + //$NON-NLS-1$
				" FROM SYSCAT.ROUTINEPARMS" + //$NON-NLS-1$
				" WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(function.getSchema().getName()) + "'" +  //$NON-NLS-1$ //$NON-NLS-2$
				" AND ROUTINENAME='" + LUWUtil.getIdentifier(function.getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
				" AND SPECIFICNAME='" + function.getSpecificName() + "'" + //$NON-NLS-1$ //$NON-NLS-2$
				" ORDER BY ORDINAL"; //$NON-NLS-1$
        }

		Statement s = null;
		ResultSet r = null;
		try {
            s = connection.createStatement();
            r = s.executeQuery(query);
			while(r.next()) {
				boolean isTableFunction = false;

				final String name = r.getString("PARMNAME"); //$NON-NLS-1$
				
				final String rowtype = r.getString("ROWTYPE").trim(); //$NON-NLS-1$
				
				if (rowtype.equals("R") && function.getLUWFunctionType() == LUWCatalogUserDefinedFunction.FunctionTypeEnumeration.TABLE_FUNCTION) //$NON-NLS-1$
					isTableFunction = true;
					
				DataType type=null;
				String typeName = r.getString("TYPENAME"); //$NON-NLS-1$
				if (typeName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
					int length = r.getInt("LENGTH"); //$NON-NLS-1$
					if (length ==4) typeName="REAL"; //$NON-NLS-1$
					else typeName="DOUBLE"; //$NON-NLS-1$
				}

				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					
					if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
						final int codePage = r.getInt("CODEPAGE"); //$NON-NLS-1$
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					else if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
						final int codePage = r.getInt("CODEPAGE"); //$NON-NLS-1$
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}

					type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						int length = r.getInt("LENGTH"); //$NON-NLS-1$
						if (length == 0) length = 1;						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					else if(typeDefinition.isPrecisionSupported()) {
						if (typeName.equals("TIMESTAMP")) {
							int length = r.getInt("SCALE");
							EStructuralFeature feature = type.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
						else {
							int length = r.getInt("LENGTH");
							if (typeName.equals("DECFLOAT")) {
								if (length == 8) length = 16;
								else length = 34;
							}
							EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
					}
					
					if(typeDefinition.isScaleSupported()) {
						final int length = r.getInt("SCALE"); //$NON-NLS-1$
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					
				}
				else {
					if (typeName.equals("REFERENCE")){ //$NON-NLS-1$
						final String typeSchemaName = r.getString("TARGET_TYPESCHEMA").trim(); //$NON-NLS-1$
						final String udtName = r.getString("TARGET_TYPENAME"); //$NON-NLS-1$
						type = LUWCatalogProcedure.getUserDefinedType(function,typeSchemaName,udtName);
					} else {
						final String typeSchemaName = r.getString("TYPESCHEMA").trim(); //$NON-NLS-1$
						type = LUWCatalogProcedure.getUserDefinedType(function,typeSchemaName,typeName);
					}
				}
				

				if (isTableFunction) {
					RoutineResultTable resultTable = function.getReturnTable();
					if (resultTable == null) {
						resultTable  = (RoutineResultTable)factory.create(SQLRoutinesPackage.eINSTANCE.getRoutineResultTable());
						function.setReturnTable(resultTable);
					}
					Column column = SQLTablesFactory.eINSTANCE.createColumn();
					column.setName(name);
					column.setDescription(r.getString("REMARKS")); //$NON-NLS-1$
					column.setDataType(type);
					resultTable.getColumns().add(column);
				}
				else {
					LUWCatalogParameter parameter = new LUWCatalogParameter();
					parameter.setName(name);
	
					if (rowtype.equals("B")){ //$NON-NLS-1$
						parameter.setMode(ParameterMode.INOUT_LITERAL);
					} else if (rowtype.equals("O")) { //$NON-NLS-1$
						parameter.setMode(ParameterMode.OUT_LITERAL);
					} else if (rowtype.equals("P")) { //$NON-NLS-1$
						parameter.setMode(ParameterMode.IN_LITERAL);
					}
	
					final String locator = r.getString("LOCATOR"); //$NON-NLS-1$
					if (locator.equals("Y")) //$NON-NLS-1$
						parameter.setLocator(true);
					else 
						parameter.setLocator(false);

					parameter.setDescription(r.getString("REMARKS")); //$NON-NLS-1$
					parameter.setDataType(type);
					
					if (rowtype.equals("C")) //$NON-NLS-1$
						function.setReturnScalar(parameter);
					else if (rowtype.equals("R")) //$NON-NLS-1$
						function.setReturnCast(parameter);
					else
						parameterList.add(parameter);

				}
				
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        finally {
           if (r != null) {
              try { r.close(); }
              catch (SQLException e) { }
              r = null;
           }
           if (s != null) {
              try { s.close(); }
              catch (SQLException e) { }
              s = null;
           }
        }
	}
	
	public interface FunctionTypeEnumeration {
		public final static byte SCALAR_FUNCTION= 0;
		public final static byte TABLE_FUNCTION= 1;
		public final static byte ROW_FUNCTION= 2;
		public final static byte COLUMN_FUNCTION= 3;
		public final static byte ENUMERATION_LENGTH = 4;
	}		
    
    /**
    * If the routine is SQL, discover if the routine has been created without
    * a schema qualifier. 
    * <p>
    * @param aRoutine The routine to analyze
    * @return <code>true</code> if the routine was created without a schema qualifier.
    */
   private void updateImplicitSchemaFlag() {
      boolean b = false;
      if (this != null) {
         b = this.isImplicitSchema();
         if (!b && this.getLanguage().equalsIgnoreCase("SQL")) { //$NON-NLS-1$
            Source src = this.getSource();
            if (src != null) {
                String body = src.getBody();
                if (body != null) {
                    String[] tokens = body.split(" "); //$NON-NLS-1$
                    for (int i = 0; i < tokens.length; i++) {
                        if (tokens[i].indexOf('.') > -1) {
                            break; // the implicit flag is false by default
                        }
                        else if (tokens[i].indexOf('(') > -1) {
                            b = true;
                            break;
                        }
                    }
                }
            }
         }
      }
      setImplicitSchema(b);
   }
   
   private void setJavaExternalName(String className, String imple){
	   if (className == null || imple == null) return;
	   String exteranlName = className +".";
	   if (imple.indexOf("(") != -1) {
		   exteranlName += imple.substring(0,imple.indexOf("("));
	   } else {
		   exteranlName += imple;
	   }
	   super.externalName = exteranlName;
   }
   
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();

		impacts.addAll(LUWCatalogProcedure.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedConstraints(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedPackages(connection, this));

		return impacts;
	}
	
	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		privileges.clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogProcedure.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	
	private static final String PROP_UDF_VALUES = "PROP_UDF_VALUES"; //$NON-NLS-1$
	
	private boolean dependencyLoaded = false;	
	private boolean parameterLoaded = false;
	private byte luwFunctionType = FunctionTypeEnumeration.SCALAR_FUNCTION;
    private boolean loaded = false;
    private boolean privilegeLoaded = false;
	private boolean statisticsLoaded = false;
	private boolean impactsLoaded = false;
    
	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();


}
