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

import java.math.BigInteger;
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
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogStatistics;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWCatalogMessages;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogFederatedProcedure extends FederatedProcedureImpl implements ICatalogObject,IDatabaseObject{
	public void refresh() {
		this.loaded = false;
		if (this.parameterLoaded){
			this.parameters.clear();
			this.parameterLoaded = false;
		}
		
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}

		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
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
	
	public ICatalogObject[] getImpacted(){
		if (!this.impactsLoaded) {
			this.impacts = this.getImpactedObjects();
			this.impactsLoaded = true;
		}
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
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
	
	public Collection getStatistics(){
		if (!this.statisticsLoaded) {
			this.statistics = LUWCatalogFederatedProcedure.getStatistics(this.getConnection(), this);
			this.statisticsLoaded = true;
		} 
		return this.statistics;
	}
	
	public static Collection getStatistics(Connection connection, Routine routine){
		Collection statistics = new ArrayList();
		try {
			String query = "SELECT IOS_PER_INVOC,INSTS_PER_INVOC,IOS_PER_ARGBYTE,INSTS_PER_ARGBYTE" +
				",PERCENT_ARGBYTES,INITIAL_IOS,INITIAL_INSTS,CARDINALITY,SELECTIVITY" +
				" FROM SYSSTAT.ROUTINES" + 
				" WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
				" AND ROUTINENAME ='" + LUWUtil.getIdentifier(routine.getName()) + "'" +
				" AND SPECIFICNAME ='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
				" FOR FETCH ONLY";
			
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				CatalogStatistics stats = null;

				final float ios_per_Invoc = r.getFloat("IOS_PER_INVOC");
				if (ios_per_Invoc != -1) {
					stats = new CatalogStatistics("IOS_PER_INVOC",LUWCatalogMessages.STAT_IOS_PER_INVOC,LUWCatalogMessages.STAT_IOS_PER_INVOC_DES,ios_per_Invoc,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float insts_per_Invoc = r.getFloat("INSTS_PER_INVOC");
				if (insts_per_Invoc != -1) {
					stats = new CatalogStatistics("INSTS_PER_INVOC",LUWCatalogMessages.STAT_INSTS_PER_INVOC,LUWCatalogMessages.STAT_INSTS_PER_INVOC_DES,insts_per_Invoc,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float ios_per_Argbyte = r.getFloat("IOS_PER_ARGBYTE");
				if (ios_per_Argbyte != -1) {
					stats = new CatalogStatistics("IOS_PER_ARGBYTE",LUWCatalogMessages.STAT_IOS_PER_ARGBYTE,LUWCatalogMessages.STAT_IOS_PER_ARGBYTE_DES,ios_per_Argbyte,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float insts_per_Argbyte = r.getFloat("INSTS_PER_ARGBYTE");
				if (insts_per_Argbyte != -1) {
					stats = new CatalogStatistics("INSTS_PER_ARGBYTE",LUWCatalogMessages.STAT_INSTS_PER_ARGBYTE,LUWCatalogMessages.STAT_INSTS_PER_ARGBYTE_DES,insts_per_Argbyte,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
				
				final int percent_Argbyte = r.getInt("PERCENT_ARGBYTES");
				if (percent_Argbyte != -1) {
					stats = new CatalogStatistics("PERCENT_ARGBYTES",LUWCatalogMessages.STAT_PERCENT_ARGBYTE,LUWCatalogMessages.STAT_PERCENT_ARGBYTE_DES,percent_Argbyte,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}


				final float initial_ios = r.getFloat("INITIAL_IOS");
				if (initial_ios != -1) {
					stats = new CatalogStatistics("INITIAL_IOS",LUWCatalogMessages.STAT_INITIAL_IOS,LUWCatalogMessages.STAT_INITIAL_IOS_DES,initial_ios,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
				
				final float initial_insts = r.getFloat("INITIAL_INSTS");
				if (initial_insts != -1) {
					stats = new CatalogStatistics("INITIAL_INSTS",LUWCatalogMessages.STAT_INITIAL_INSTS,LUWCatalogMessages.STAT_INITIAL_INSTS_DES,initial_insts,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
				
				final BigInteger card = r.getBigDecimal("CARDINALITY").toBigInteger();
				if (card.intValue() != -1) {
					stats = new CatalogStatistics("CARDINALITY",LUWCatalogMessages.STAT_ROUTINE_CARDINALITY,LUWCatalogMessages.STAT_ROUTINE_CARDINALITY_DES,card,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float selectivity= r.getFloat("SELECTIVITY");
				if (selectivity != -1) {
					stats = new CatalogStatistics("SELECTIVITY",LUWCatalogMessages.STAT_SELECTIVITY,LUWCatalogMessages.STAT_SELECTIVITY_DES,selectivity,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			//e.printStackTrace();
		}
		return statistics;
	}

	
	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		if(!this.loaded) this.load();
		return this.getSchema().getDatabase();		
	}
	
	public boolean isDeterministic() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.isDeterministic();
		} else {
			if(!this.loaded) this.load();
			return super.isDeterministic();
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
	
	public DataAccess getSqlDataAccess() {
		if(!this.loaded) this.load();
		return this.sqlDataAccess;
	}

	public String getRemoteServer(){
		if(!this.loaded) this.load();
		return this.remoteServer;
	}
	
	private synchronized void load() {
		if(this.loaded) return;
		this.loaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Connection connection = this.getConnection();
        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());
        String version = definition.getVersion();
        float ver = 8.0f;
        try {
            ver = Float.parseFloat(version.substring(1));
            if(ver < 9 ){
            	//no federated stored procedures prior to V9
            	return;
            }
        }
        catch (NumberFormatException e) { 
//        	DB2LUWPlugin.getDefault().log("LUWCatalogFederatedProcedure:load: " + e.getMessage(), null); //$NON-NLS-1$
        }
        
		try {
			Statement s = connection.createStatement();
			
            //get the federated SP options
			ResultSet r = s.executeQuery("SELECT OPTION, SETTING" +
				                         " FROM SYSIBM.SYSROUTINEOPTIONS" +
	                                     " WHERE ROUTINEID=" + routineID);

			while(r.next()) {
                String option = r.getString("OPTION");
                String setting = r.getString("SETTING");
                
                if(option.equals("REMOTE_UNIQUE_ID")){
                	this.setRemoteUniqueId(setting);
                }else if (option.equals("REMOTE_SERVER")){
                	this.setRemoteServer(setting);
                }else if (option.equals("SERVER")){
                	this.setFederatedServerByName(setting);
                }else if (option.equals("REMOTE_SCHEMA")){
                	this.setRemoteSchema(setting);
                }else if (option.equals("REMOTE_PACKAGE")){
                	this.setRemotePackage(setting);
                }else if (option.equals("REMOTE_PROCEDURE")){
                	this.setRemoteProcedureName(setting);
                }else if (option.equals("REMOTE_NUMPARM")){
                	this.setNumberOfParameters(Integer.decode(setting).intValue());
                }else if (option.equals("RESULT_SETS_TO_CLIENT")){
                	this.setResultSetsToClient(setting);
                }else if (option.equals("REMOTE_REFCURSORS")){
                	this.setNumberOfRefCursors(Integer.decode(setting).intValue());
                }else if (option.equals("ALL_RESULT_SETS_TO_CALLER")){
                	this.setAllResultSetsToCaller(setting);
                }else if (option.equals("IS_REMOTE_FUNCTION")){
                	this.setOracleRemoteFunction(setting);
                }
	      
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
//        	DB2LUWPlugin.getDefault().log("LUWCatalogFederatedProcedure:load: " + e.getMessage(), null); //$NON-NLS-1$
		}

		this.eSetDeliver(deliver);
	}

    
	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			EList dependencyList = super.getDependencies();

			LUWCatalogProcedure.loadDependencies(this.getConnection(), dependencyList, this);
			
			if (this.getFederatedServer() != null) {
				final Database database = this.getSchema().getDatabase();
				final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
				final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(this.getFederatedServer());
				dependencyList.add(dep);
			}

		}
		catch(Exception e) {
//        	DB2LUWPlugin.getDefault().log("LUWCatalogFederatedProcedure:loadDependencies: " + e.getMessage(), null); //$NON-NLS-1$
		}
		
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadParameters() {
		if(this.parameterLoaded) return;
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogFederatedProcedure.loadParameters(this.getConnection(), super.getParameters(), this);
		}
		catch(Exception e) {
//        	DB2LUWPlugin.getDefault().log("LUWCatalogFederatedProcedure:loadParameters: " + e.getMessage(), null); //$NON-NLS-1$
			System.out.println(e.toString());
		}
		
		this.parameterLoaded = true;
		this.eSetDeliver(deliver);		
	}
	

	public static void loadParameters(Connection connection, EList parameterList, LUWCatalogFederatedProcedure fedSP) throws SQLException {
		final Database database = fedSP.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
//		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		String query="SELECT PARMNAME,ROWTYPE,LOCATOR,TYPESCHEMA,TYPENAME,LENGTH,SCALE,CODEPAGE,REMARKS,TARGET_TYPESCHEMA,TARGET_TYPENAME,ORDINAL" + //$NON-NLS-1$
				" FROM SYSIBM.SYSROUTINEPARMS" + //$NON-NLS-1$
				" WHERE ROUTINE_ID=" + LUWCatalogFederatedProcedure.getRoutineID(fedSP) +   //$NON-NLS-1$ //$NON-NLS-2$
				" ORDER BY ORDINAL"; //$NON-NLS-1$

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		try {
			while(r.next()) {
				
				final LUWCatalogFederatedParameter parameter = new LUWCatalogFederatedParameter();
				final String name = r.getString("PARMNAME"); //$NON-NLS-1$
				parameter.setName(name);

				final String rowtype = r.getString("ROWTYPE").trim(); //$NON-NLS-1$
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

				String typeName = r.getString("TYPENAME"); //$NON-NLS-1$
				if (typeName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
					int length = r.getInt("LENGTH"); //$NON-NLS-1$
					if (length ==4) typeName="REAL"; //$NON-NLS-1$
					else typeName="DOUBLE"; //$NON-NLS-1$
				}
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					
					if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
						final int codePage = r.getInt(8);
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					else if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
						final int codePage = r.getInt(8);
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}

					
					PredefinedDataType 	type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						final int length = r.getInt("LENGTH"); //$NON-NLS-1$
						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					else if(typeDefinition.isPrecisionSupported()) {
						final int length = r.getInt("LENGTH"); //$NON-NLS-1$
						EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					
					if(typeDefinition.isScaleSupported()) {
						final int length = r.getInt("SCALE"); //$NON-NLS-1$
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					
					parameter.setContainedType(type);
				}
				else {
					if (typeName.equals("REFERENCE")){ //$NON-NLS-1$
						final String typeSchemaName = r.getString("TARGET_TYPESCHEMA").trim(); //$NON-NLS-1$
						final String udtName = r.getString("TARGET_TYPENAME"); //$NON-NLS-1$
						parameter.setReferencedType(LUWCatalogFederatedProcedure.getUserDefinedType(fedSP,typeSchemaName,udtName));
					} else {
						final String typeSchemaName = r.getString("TYPESCHEMA").trim(); //$NON-NLS-1$
						parameter.setReferencedType(LUWCatalogFederatedProcedure.getUserDefinedType(fedSP,typeSchemaName,typeName));
					}
				}
				
				parameter.setDescription(r.getString("REMARKS")); //$NON-NLS-1$
				parameter.setFederatedProcedure(fedSP);
				parameterList.add(parameter);
				int ordinal = r.getInt("ORDINAL");
				LUWCatalogFederatedParameter.loadParameterOptions(connection, parameter, 
						                       LUWCatalogFederatedProcedure.getRoutineID(fedSP),
						                       ordinal);
			}
		}
		catch(Exception e) {
//        	DB2LUWPlugin.getDefault().log("LUWCatalogFederatedProcedure:loadParameters: " + e.getMessage(), null); //$NON-NLS-1$
		}
			
		r.close();
		s.close();

	}
	
	
	public static Schema getSchema(LUWCatalogFederatedProcedure fedSP, String schemaName) {
		Schema s = fedSP.getSchema();
		if(s.getName().trim().equals(schemaName.trim())) return s;
		Database d = s.getDatabase();
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().trim().equals(schemaName.trim())) return s;			
		}

		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(d);

		return schema;
	}
	
	public static Table getTable(LUWCatalogFederatedProcedure fedSP, String schemaName, String tableName) {
		Schema schema = LUWCatalogFederatedProcedure.getSchema(fedSP, schemaName);
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}

		Table table = new LUWCatalogTable();
		table.setName(tableName);
		table.setSchema(schema);

		return table;
	}
	
	public static int getRoutineID(LUWCatalogFederatedProcedure fedSP){
		if(!fedSP.loaded) fedSP.load();
		return fedSP.routineID;
	}
		
	public static Routine getRountine(LUWCatalogFederatedProcedure fedSP, String schemaName, String routineName) {
		Schema schema = LUWCatalogFederatedProcedure.getSchema(fedSP, schemaName);
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine r = (Routine) it.next();
			if(r.getName().equals(routineName)) return r;			
		}

		Routine r = new LUWCatalogFederatedProcedure();
		r.setName(routineName);
		r.setSchema(schema);

		return r;
	}

	public static UserDefinedType getUserDefinedType(LUWCatalogFederatedProcedure fedSP, String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogFederatedProcedure.getSchema(fedSP, schemaName);
		Iterator it = schema.getUserDefinedTypes().iterator();
		while(it.hasNext()) {
			UserDefinedType userDefinedType = (UserDefinedType) it.next();
			if(userDefinedType.getName().equals(userDefinedTypeName)) return userDefinedType;			
		}

		UserDefinedType userDefinedType = new LUWCatalogDistinctUserDefinedType();
		userDefinedType.setName(userDefinedTypeName);
		userDefinedType.setSchema(schema);
		
		return userDefinedType;		
	}
	
	public void setRoutineID(int id){
		this.routineID=id;
	}
	
	public DataAccess getSQLDataAccess(){
		if(!this.loaded) this.load();
		return sqlDataAccess;
	}
	
	public void setSQLDataAccess(DataAccess da){
		sqlDataAccess = da;
	}
	
	public boolean isExternalAction(){
		if(!this.loaded) this.load();
		return externalAction;
	}
	
	public void setExternalAction(String ea){
		if(ea.trim().equals("E")){
			externalAction=true;
		}
	}
	
	private void setAllResultSetsToCaller(String setting) {
		if(setting.trim().equals("Y")){
			setAllResultSetsToCaller(true);
		}else{
			setAllResultSetsToCaller(false);
		}
		
	}
	
	public LUWCatalogFederatedServer getFederatedServer() {
		if(!this.loaded) this.load();		
		return server;
	}
	
	private void setFederatedServerByName(String setting) {
		Database db = getSchema().getDatabase();
		if(db instanceof LUWCatalogDatabase){
			EList servers = ((LUWCatalogDatabase)db).getServers();
			Iterator itrServers = servers.iterator();
			while(itrServers.hasNext()){
				SQLObject obj = (SQLObject)itrServers.next();
				if(obj instanceof LUWCatalogFederatedServer && 
				   obj.getName().trim().equals(setting.trim())){
					server = (LUWCatalogFederatedServer)obj;
					setRemoteServer(server.getName());
					break;
				}
			}
		}
		
	}
	
	private void setOracleRemoteFunction(String setting) {
		if(setting.equals("Y")){
			remoteFunction = true;
		}		
	}
	
	public boolean isOracleRemoteFunction() {
		return remoteFunction;		
	}
	
	


	private LUWCatalogFederatedServer server=null;
	private boolean loaded = false;
	private boolean dependencyLoaded = false;
	private boolean parameterLoaded = false;
    private int routineID=0;
    private boolean externalAction=false;
    private boolean remoteFunction=false;
    
    private boolean statisticsLoaded = false;
	private boolean impactsLoaded = false;
    private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();
}
