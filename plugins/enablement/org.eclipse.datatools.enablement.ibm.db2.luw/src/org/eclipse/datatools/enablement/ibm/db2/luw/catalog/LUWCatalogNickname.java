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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericNicknameImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.util.TableToRemoteDataSetHelper;
import org.eclipse.datatools.enablement.ibm.util.ConnectionProfileUtility;
import org.eclipse.datatools.enablement.ibm.util.IRowCountCache;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogNickname extends LUWGenericNicknameImpl implements IRowCountCache,ICatalogObject,IDatabaseObject{

	public void refresh() {
		this.columnsLoaded = false;
		this.constraintLoaded = false;
		this.serverLoaded = false;
		this.optionsLoaded = false;
		this.indexLoaded = false;
		this.remoteDataSetLoaded = false;
		this.remoteTableDependencyLoaded = false;
		this.remoteTableDependencyList.clear();
		this.privilegeLoaded = false;
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
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
			this.rowCountLoaded = false;
		}
	}

	public EList getColumns() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getColumns();
		} else {
			if(!this.columnsLoaded) this.loadColumns();
			return this.columns;
		}
	}

	public LUWServer getServer() {
		if(!this.serverLoaded) this.loadServer();
		return this.server;
	}

	public EList getOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getOptions();
		} else {
			if(!this.optionsLoaded) this.loadOptions();
			return super.getOptions();
		}
	}

//temp disable	
//	public RemoteDataSet getRemoteDataSet() {
//		if(!this.remoteDataSetLoaded) this.loadRemoteDataSet();
//		return this.remoteDataSet;
//	}
	
	public EList getConstraints() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getConstraints();
		} else {
			if(!this.constraintLoaded) this.loadConstraints();
			return this.constraints;
		}
	}

	public EList getIndex() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getIndex();
		} else {
			if (!this.indexLoaded) this.loadIndexes();
			return this.index;
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
	
	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
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
			this.statistics = LUWCatalogTable.getStatistics(this.getConnection(), this);
			this.statisticsLoaded = true;
		}
		return this.statistics;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_NICKNAME__COLUMNS) {
			this.getColumns();
		}
		else if(id == LUWPackage.LUW_NICKNAME__SERVER) {
			this.getServer();
		}
		else if(id == LUWPackage.LUW_NICKNAME__OPTIONS) {
			this.getOptions();
		}
		else if(id == LUWPackage.LUW_NICKNAME__DESCRIPTION) {
			this.getDescription();
		}
		else if(id == LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET) {
			this.getRemoteDataSet();
		}else if(id == LUWPackage.LUW_NICKNAME__CONSTRAINTS) {
			this.getConstraints();
		}else if(id == LUWPackage.LUW_NICKNAME__INDEX) {
			this.getIndex();
		}else if(id == LUWPackage.LUW_NICKNAME__PRIVILEGES) {
			this.getPrivileges();
		}
		else if(id == LUWPackage.LUW_NICKNAME__DEPENDENCIES) {
			this.getDependencies();
		}
	
		return super.eIsSet(eFeature);
	}

	private synchronized void loadColumns() {
		if(this.columnsLoaded) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			LUWCatalogTable.loadColumns(this.getConnection(), super.getColumns(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.columnsLoaded = true;
		this.eSetDeliver(deliver);
	}

	private synchronized void loadOptions() {
		if(this.optionsLoaded) return;
		this.optionsLoaded = true;

		EList options = super.getOptions();
		options.clear();
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			String query = "SELECT OPTION, CAST(SUBSTR(SETTING, 1, 128) AS VARCHAR(128)) FROM SYSCAT.TABOPTIONS WHERE TABSCHEMA='"  //$NON-NLS-1$
				+ LUWUtil.getIdentifier(this.getSchema().getName()) + "' AND TABNAME='" + LUWUtil.getIdentifier(this.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
				
			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			try {
				while(r.next()) {
					final String name = r.getString(1).trim();
					String value = r.getString(2).trim();
					LUWOption option = LUWFactory.eINSTANCE.createLUWOption();
					option.setName(name);

                    // Check to see if the setting is 255 bytes.  If it is, we need to get the rest
                    // of it from the pack descriptor
                    if (value.length() == 255 && getCatalogDatabase().getVersion().equalsIgnoreCase("V8.2")) {
                    	value = getOptionFromPackDescriptor(name);
                    }
					option.setValue(value);
					options.add(option);
				}
			}
			catch (Exception e) {
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	//  This method will get the option value from the pack descriptor, which is a place that lies
	//  outside of the realm of the DB2 system catalogs.
	private synchronized String getOptionFromPackDescriptor(String option) {

	    String value = new String("");
	    String stmtText;
	    int returnCode;

		CallableStatement stmt=null;
		ResultSet rs=null;

		try {
		    stmtText = "{CALL SYSPROC.LIST_NN_LONG_OPTS(?,?,?,?)}";
		    stmt = this.getConnection().prepareCall(stmtText);
		    //register the IN parameters
		    stmt.setString(1,this.getName());
		    stmt.setString(2,this.getSchema().getName());
		    stmt.setString(3,option);
		    stmt.registerOutParameter(4,Types.INTEGER);

		    stmt.execute();
		    returnCode = stmt.getInt(4);
		    rs = stmt.getResultSet();

		    if (rs == null) {
		        // System.out.println("Result set is NULL...");
		    }
		    else {
		        while (rs.next()) {
		           value = rs.getString(1);
		        }
		    }
		    if ( rs != null ) rs.close();
		    if ( stmt != null ) stmt.close();
	    } // end try
		catch (SQLException sqle) {
//		    sqle.printStackTrace();
		    // Insure that we have closed the result set
		    try {
		       if (rs != null) {
		          rs.close(); // close result set
		          if ( stmt != null ) stmt.close();
		       }
		    } catch (Exception exc) {
		       exc.printStackTrace();
		    }
		}

		return value;
    }

	private synchronized void loadServer() {
		if(this.serverLoaded) return;
		this.serverLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		Iterator it = this.getOptions().iterator();
		while(it.hasNext()) {
			LUWOption option = (LUWOption) it.next();
			if(option.getName().equals("SERVER")) { //$NON-NLS-1$
				Iterator severIt = ((LUWCatalogDatabase) this.getCatalogDatabase()).getServers().iterator();
				while(severIt.hasNext()) {
					Object aServer = severIt.next(); 
					//The list of servers are a combination of LUWDiscoveredSever and LUWCatalogFederatedServer
					//Only the LUWCatalogFederatedServer should be casted.
					if(aServer instanceof LUWCatalogFederatedServer) { 
						LUWCatalogFederatedServer remoteServer = (LUWCatalogFederatedServer) /*severIt.next()*/aServer;
						if(remoteServer.getName().equals(option.getValue())) {
							this.setGenericServer(remoteServer);
							break;
						}
					}
				}
				break;
			}
		}
		
		this.eSetDeliver(deliver);
	}
/*
 private synchronized void loadRemoteDataSet() {
	
		if(this.remoteDataSetLoaded) return;
		
		RemoteServer rs = this.getServer().getRemoteServer();

		if(rs instanceof RelationalRemoteServer) {
			String remoteSchemaName = null;
			String remoteTableName = null;
			
			Iterator it = this.getOptions().iterator();
			while(it.hasNext()) {
				LUWOption option = (LUWOption) it.next();
				if(option.getName().equals("REMOTE_SCHEMA")) {
					remoteSchemaName = option.getValue();
				}
				else if(option.getName().equals("REMOTE_TABLE")) {
					remoteTableName = option.getValue();				
				}
			}
			//here you want to load rrDataset object
			Table remoteTable = getRemoteTable(((RelationalRemoteServer)rs).getDatabase(), remoteSchemaName, remoteTableName);
			if(remoteTable instanceof RemoteDataSet) {
				((RemoteDataSet) remoteTable).getNickname();
			}
		}
	}
*/	
	


	private synchronized void loadRemoteDataSet() {
		//TODO:Added by GEETIKA: new implementation of LoadRemoteDataSet
		//if dataset isnt loaded, force load of remote table, this
		//will create the dataset object. then get the dataset object
		//and create link from dataset object to nickname.
		if (this.remoteDataSetLoaded) {
			return;
		}
		this.remoteDataSetLoaded = true;

		LUWServer luwServer = this.getServer();
		
		if (luwServer != null) {
			RemoteServer rs = luwServer.getRemoteServer();
	
			if (rs instanceof RelationalRemoteServer) {
				String remoteSchemaName = null;
				String remoteTableName = null;
				RelationalRemoteServer relationalRS = (RelationalRemoteServer) rs;
				Database db = relationalRS.getDatabase();
	
				Iterator it = this.getOptions().iterator();
				while (it.hasNext() && (remoteSchemaName == null || remoteTableName == null)) {
					LUWOption option = (LUWOption) it.next();
					if (option.getName().equals("REMOTE_SCHEMA")) { //$NON-NLS-1$
						remoteSchemaName = option.getValue();
					} 
					else if (option.getName().equals("REMOTE_TABLE")) { //$NON-NLS-1$
						remoteTableName = option.getValue();
					}
				}
				
				if (remoteSchemaName == null) {
					ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(luwServer.getWrapper().getLUWDatabase());
               IConnectionProfile profile = connectionInfo.getConnectionProfile();
					remoteSchemaName = ConnectionProfileUtility.getUID(profile);
				}
				
				if (remoteSchemaName != null && remoteTableName != null) {
					//here you want to load rrDataset object
					if (db != null) {
						BaseTable remoteTable = getRemoteTable (relationalRS.getDatabase(),
								remoteSchemaName, remoteTableName);
						if (remoteTable != null) {
							this.setRemoteDataSetFromTable(remoteTable);
						}
						else {
							//shows a warning in the error log
//							String msg = DB2LUWPlugin.getResourceString("datatools.db2.luw.RemoteDataSetLoadingError");
//							String[] subs = new String[] {relationalRS.getName(), remoteSchemaName, remoteTableName,
//								this.getSchema().getName(), this.getName()};
//							DB2LUWPlugin.getDefault().log(MessageFormat.format(msg, subs), IStatus.WARNING, null);
						}
					}
				}
			}
		}
		else {
			//nickname is defined on a non-relational datasource
		}
		
		//we set this variable to true even if the data set is not loaded, meaning we tried to load it.
		//this avoids trying to load several times when there is a problem during the loading (eg, the remote
		//server is down)
	}
	
	/**
	 * @param remoteTable
	 */
	public void setRemoteDataSetFromTable(BaseTable remoteTable) {
		LUWCatalogRelationalRemoteDataSet rds = (LUWCatalogRelationalRemoteDataSet) TableToRemoteDataSetHelper
				.getRemoteDataSet(remoteTable);
		if (rds == null) {
			rds = new LUWCatalogRelationalRemoteDataSet();
			rds.setName(this.getName());
			rds.setRemoteSchemaName(remoteTable.getSchema().getName());
			rds.setRemoteTableName(remoteTable.getName());
			rds.setTable(remoteTable);
			this.setRemoteDataSet(rds);
		}
		rds.getNickname().add(this);
	}

	private synchronized void loadConstraints() {
		if(this.constraintLoaded) return;
		this.constraintLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadConstraints(this.getConnection(), super.getConstraints(), this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadIndexes() {
		if(this.indexLoaded) return;
		this.indexLoaded = true;
		EList indexes = super.getIndex();
		
		if (this.batchLoad) return;   //if batchload
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadIndexes(this.getConnection(), indexes, this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	public static Schema getRemoteSchema(Database database, String schemaName) {
		Iterator it = database.getSchemas().iterator();
		//try database.getSchemas != null, then do iterator();
		while(it.hasNext()) {
			Schema s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
		return null;
	}
	
	public static BaseTable getRemoteTable(Database database, String schemaName, String tableName) {
		Schema schema = getRemoteSchema(database, schemaName);
		//what if schema is excel? check if db vendor is excel, and then what?
		if(schema == null) return null;
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return (BaseTable) table;			
		}
		return null;
	}
	
	public List getRemoteTableDependencies() {
		if(!this.remoteTableDependencyLoaded) this.loadRemoteTableDependencies();
		return this.remoteTableDependencyList;
	}
	
	private synchronized void loadRemoteTableDependencies() {
		if(this.remoteTableDependencyLoaded) return;
		this.remoteTableDependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			final Database database = this.getSchema().getDatabase();
			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
				
			LUWCatalogRelationalRemoteDataSet rrds = (LUWCatalogRelationalRemoteDataSet) getRemoteDataSet();
			if (rrds != null)
			{
				SQLObject obj = rrds.getTable();
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(obj);
				remoteTableDependencyList.add(dep);
			}
		}
		catch(Exception e) {
//			e.printStackTrace();
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
			LUWCatalogTable.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		EList dependencyList = super.getDependencies();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			if (this.getServer() != null) {
				final Database database = this.getSchema().getDatabase();
				final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
				final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(this.getServer());
				dependencyList.add(dep);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}

	protected void setAsBatchLoad(boolean batchLoad){
		this.batchLoad = batchLoad;
	}

	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogTable.getImpactedAlias(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedPackages(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedConstraints(connection, this));
		return impacts;
	}

	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
		}
		this.eSetDeliver(deliver);
	}

	public String getRowCountString() {
		if (!rowCountLoaded) {
			this.rowCount = LUWCatalogTable.loadRowCount(this.getConnection(), this);
			this.rowCountLoaded = true;
		}
		return this.rowCount;
	}
	
	public void setRowCountString(String count) {
		rowCount = count;
		rowCountLoaded = true;
	}
	
	private List remoteTableDependencyList = new LinkedList ();
	private boolean columnsLoaded = false;
	private boolean serverLoaded = false;
	private boolean remoteDataSetLoaded = false;
	private boolean optionsLoaded = false;	
	private boolean constraintLoaded = false;
	private boolean indexLoaded = false;
	private boolean remoteTableDependencyLoaded = false;
	private boolean batchLoad = false;
	private boolean privilegeLoaded = false;
	private boolean impactsLoaded = false;
	private boolean statisticsLoaded = false;
	private boolean rowCountLoaded = false;
	private boolean dependencyLoaded = false;
	
	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();
	private String rowCount = null;
}
