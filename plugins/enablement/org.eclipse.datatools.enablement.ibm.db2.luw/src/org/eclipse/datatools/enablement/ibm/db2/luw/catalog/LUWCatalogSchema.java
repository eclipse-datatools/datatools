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

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.DB2LUWPluginActivator;
//import com.ibm.datatools.db2.luw.DB2LUWPlugin;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.LUWCatalogIndex.IndexUniqueRule;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus;
import org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType;
import org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType;
import org.eclipse.datatools.enablement.ibm.db2.model.OriginType;
import org.eclipse.datatools.enablement.ibm.db2.model.ReoptType;
import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl;
import org.eclipse.datatools.enablement.ibm.db2.util.DB2Version;
import org.eclipse.datatools.enablement.ibm.util.IConnectionFilter;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.GenerateType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
//bgp import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.CatalogCache;

public class LUWCatalogSchema extends DB2SchemaImpl implements ICatalogObject {
	private static OlapCatalogProvider olapCatalogProvider = null;
	private boolean tablesLoaded = false;
	private boolean routinesLoaded = false;
	private boolean jarsLoaded = false;
	private boolean xsrObjsLoaded = false;
	private boolean sequencesLoaded = false;
	private boolean userDefinedTypesLoaded = false;
	private boolean indicesLoaded = false;
	private boolean triggersLoaded = false;
	private boolean olapobjectLoaded = false;
	private boolean privilegeLoaded = false;
	private boolean packagesLoaded = false;
	private boolean moduleLoaded = false;
	private boolean globalVariableLoaded = false;
	private HashMap cachedTables = new HashMap();
	private HashMap cachedDB2Packages = new HashMap();

	public static OlapCatalogProvider getOlapCatalogProvider() {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "luwolapProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		if(extensions.length == 1) {
			IConfigurationElement[] configElements = extensions[0].getConfigurationElements();
			try {
				olapCatalogProvider = (OlapCatalogProvider) configElements[0].createExecutableExtension("class"); //$NON-NLS-1$
			}
			catch(Exception e) {
			}
		}
		return olapCatalogProvider;
	}
	
	
	public synchronized void refresh() {
        // set to unloaded status to force reload next time
		this.tablesLoaded = false;
		this.routinesLoaded = false;
		this.jarsLoaded = false;		
		this.indicesLoaded = false;
		this.triggersLoaded = false;
		this.sequencesLoaded = false;
		this.userDefinedTypesLoaded = false;
		if (this.olapobjectLoaded) {
			this.olapObjects.clear();
			this.olapobjectLoaded = false;
		}
		this.xsrObjsLoaded = false;
		this.privilegeLoaded = false;
		if (this.packagesLoaded) {
			this.packages.clear();
			this.packagesLoaded = false;
		}
		
		this.moduleLoaded = false;
		this.globalVariableLoaded = false;

		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getDatabase();
	}

	public EList getTables() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getTables();
		} else {
			if(!this.tablesLoaded) this.loadTables();
			return this.tables;
		}
	}
	
	public EList getRoutines() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getRoutines();
		} else {
			if(!this.routinesLoaded) this.loadRoutines();
			return this.routines;
		}
	}
		
	public EList getFederatedStoredProcedures() {
		if(!this.routinesLoaded) this.loadRoutines();
		return this.routines;
	}

//  TODO Jar support: add check for additional vendors after RDA ships:
//	public EList getJars() {
//		DB2Version db2Version = new DB2Version(this.getConnection());
//		if(!this.jarsLoaded && 
//			db2Version.isUNO() && db2Version.isAtLeast(9))
//				this.loadJars();
//		else
//		   jars = super.getJars();
//		this.jarsLoaded = true;
//		return this.jars;	   
//	}

	public EList getXsrObjects() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getXsrObjects();
		} else {
			DB2Version db2Version = new DB2Version(this.getConnection());
			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getDatabase());
			if(!this.xsrObjsLoaded && 
				!getName().toUpperCase().startsWith("SYS") &&     //$NON-NLS-1$
				db2Version.isUNO() && databaseDefinition.supportsXML())
					this.loadXsrObjects();
			else
				xsrObjects = super.getXsrObjects();
			this.xsrObjsLoaded = true;
			return this.xsrObjects;
		}
	}	
	
	public EList getSequences() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getSequences();
		} else {
			if(!this.sequencesLoaded) this.loadSequences();
			return this.sequences;
		}
	}

	public EList getUserDefinedTypes() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getUserDefinedTypes();
		} else {
			if(!this.userDefinedTypesLoaded) this.loadUserDefinedTypes();
			return this.userDefinedTypes;
		}
	}

	public synchronized EList getTriggers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getTriggers();
		} else {
			if(!this.triggersLoaded) this.loadTriggers();
			return this.triggers;
		}
	}
	
	public EList getIndices(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getIndices();
		} else {
			if (!this.indicesLoaded) this.loadIndices();
			return this.indices;
		}
	}
	
	public synchronized EList getOlapObjects() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getOlapObjects();
		} else {
			if(!this.olapobjectLoaded) this.loadOlapobjects();
			return this.olapObjects;
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

	public EList getPackages() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getPackages();
		} else {
			if (!this.packagesLoaded) this.loadPackages();
			return this.packages;
		}
	}

	public EList getModules() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getModules();
		} else {
			if (!this.moduleLoaded) this.loadModules();
			return this.modules;
		}
	}
	
	public EList getGlobalVariables(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getGlobalVariables();
		} else {
			if (!this.globalVariableLoaded) this.loadGlobaleVaraibles();
			return this.globalVariables;
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == DB2ModelPackage.DB2_SCHEMA__TABLES) {
			this.getTables();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__ROUTINES) {
			this.getRoutines();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__JARS) {
			this.getJars();
		}		
		else if(id == DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS) {
			this.getXsrObjects();
		}				
		else if(id == DB2ModelPackage.DB2_SCHEMA__SEQUENCES) {
			this.getSequences();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__USER_DEFINED_TYPES) {
			this.getUserDefinedTypes();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__TRIGGERS) {
			this.getTriggers();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__INDICES) {
			this.getIndices();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS) {
			this.getOlapObjects();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__PRIVILEGES) {
			this.getPrivileges();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__PACKAGES) {
			this.getPackages();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__MODULES) {
			this.getModules();
		}
		else if(id == DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES) {
			this.getGlobalVariables();
		}
		return super.eIsSet(eFeature);
	}
	
	public static void loadTables(Connection connection, EList tableList, LUWCatalogSchema schema, int options) throws SQLException {
		LUWCatalogDatabase database  = (LUWCatalogDatabase)schema.getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

		HashMap cachedElement = schema.getCachedTable();
		schema.iniCachedTable();

//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isLoaded( CatalogCache.TABLES ) ) {
//				return;
//			}
//			
//			cache.setLoaded( CatalogCache.TABLES );
//bgp>		}

		Object[] oldList = tableList.toArray();

		tableList.clear();

		boolean isBatchLoad = database.isBatchLoad();
		
		if ((options & DatabaseREProvider.EXCLUDE_TABLES)== DatabaseREProvider.EXCLUDE_TABLES
				&& (options & DatabaseREProvider.EXCLUDE_VIEWS) == DatabaseREProvider.EXCLUDE_VIEWS
				&& (options & DatabaseREProvider.EXCLUDE_SYNONYMS)== DatabaseREProvider.EXCLUDE_SYNONYMS){
				return;
		}

		boolean hasFilter = false;
		String filterStr = ""; //$NON-NLS-1$

		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(ModelHelper.getDatabase(schema));
		ConnectionFilter tableFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.TABLE_FILTER); //$NON-NLS-1$
		if (tableFilter == null) {  //default table filter
			tableFilter = connectionInfo.getFilter(ConnectionFilter.TABLE_FILTER);
		}
		ConnectionFilter viewFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.VIEW_FILTER); //$NON-NLS-1$
		if (viewFilter == null) {   //default view filter
			viewFilter = connectionInfo.getFilter(ConnectionFilter.VIEW_FILTER);
		}
		ConnectionFilter aliasFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.ALIAS_FILTER); //$NON-NLS-1$
		if (aliasFilter == null) {	//default alias filter
			aliasFilter = connectionInfo.getFilter(ConnectionFilter.ALIAS_FILTER);
		}
		ConnectionFilter mqtFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.MQT_FILTER); //$NON-NLS-1$
		if (mqtFilter == null) {	//default mqt filter
			mqtFilter = connectionInfo.getFilter(ConnectionFilter.MQT_FILTER);
		}
		ConnectionFilter nicknameFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.NICKNAME_FILTER); //$NON-NLS-1$
		if (nicknameFilter == null) {	//default nickname filter
			nicknameFilter = connectionInfo.getFilter(ConnectionFilter.NICKNAME_FILTER);
		}
		
		hasFilter = tableFilter != null || viewFilter != null || aliasFilter != null || mqtFilter != null || nicknameFilter != null;
		if (hasFilter) {
			if ((options & DatabaseREProvider.EXCLUDE_TABLES)!= DatabaseREProvider.EXCLUDE_TABLES){
				if (tableFilter != null) {
					filterStr += CatalogLoadUtil.getFilterString(tableFilter.getPredicate(), "TABNAME")  + " AND TYPE='T' "; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					filterStr += "TYPE='T' "; //$NON-NLS-1$
				}
			}
			
			if ((options & DatabaseREProvider.EXCLUDE_VIEWS)!= DatabaseREProvider.EXCLUDE_VIEWS){
				if (viewFilter != null) {
					if (filterStr.length() >0) filterStr += " OR "; //$NON-NLS-1$
					filterStr += CatalogLoadUtil.getFilterString(viewFilter.getPredicate(), "TABNAME") + " AND TYPE='V' "; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					filterStr += " OR TYPE='V' "; //$NON-NLS-1$
				}
			}
			
			if ((options & DatabaseREProvider.EXCLUDE_SYNONYMS)!= DatabaseREProvider.EXCLUDE_SYNONYMS){
				if (aliasFilter != null) {
					if (filterStr.length() >0) filterStr += " OR "; //$NON-NLS-1$
					filterStr += CatalogLoadUtil.getFilterString(aliasFilter.getPredicate(), "TABNAME") + " AND TYPE='A' "; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					filterStr += " OR TYPE='A' "; //$NON-NLS-1$
				}
			}
			if ((options & DatabaseREProvider.EXCLUDE_VIEWS)!= DatabaseREProvider.EXCLUDE_VIEWS){
				if (mqtFilter != null) {
					if (filterStr.length() >0) filterStr += " OR "; //$NON-NLS-1$
					filterStr += CatalogLoadUtil.getFilterString(mqtFilter.getPredicate(), "TABNAME") + " AND TYPE='S' "; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					filterStr += " OR TYPE='S' "; //$NON-NLS-1$
				}
			}
			if ((options & DatabaseREProvider.EXCLUDE_TABLES)!= DatabaseREProvider.EXCLUDE_TABLES){
				if (nicknameFilter != null) {
					if (filterStr.length() >0) filterStr += " OR "; //$NON-NLS-1$
					filterStr +=  CatalogLoadUtil.getFilterString(nicknameFilter.getPredicate(), "TABNAME")  + " AND TYPE='N' "; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					filterStr += " OR TYPE='N' "; //$NON-NLS-1$
				}
			}
			filterStr =  filterStr.length()>0? (" AND (" + filterStr+")"):""; //$NON-NLS-1$
		}

		String query = "SELECT TABNAME, TYPE,REMARKS ,PARTITION_MODE,TBSPACE, INDEX_TBSPACE,LONG_TBSPACE,DATACAPTURE, COMPRESSION" +
						", TABSCHEMA" +		
						" FROM SYSCAT.TABLES";
		
//<bgp		if ( cache.isBatchLoading() ) {
//			query += " WHERE 1=1";
//		}
//bgp>		else {
			query += " WHERE TABSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
//bgp		}
	
		if (hasFilter) {
			query += filterStr;
		} else {
			String types =""; 
			if ((options & DatabaseREProvider.EXCLUDE_TABLES)!= DatabaseREProvider.EXCLUDE_TABLES)
				types += "'T','N','G'";
			if ((options & DatabaseREProvider.EXCLUDE_VIEWS) != DatabaseREProvider.EXCLUDE_VIEWS)
				types += (types.length() > 0) ?   ",'V','S'" :  "'V','S'";
			if ((options & DatabaseREProvider.EXCLUDE_SYNONYMS)!= DatabaseREProvider.EXCLUDE_SYNONYMS)
				types += (types.length() > 0) ?   ",'A'" :  "'A'";

			query +=  " AND TYPE IN(" + ((types.length() > 0) ?  types   :  "") + ")";
		}

		Statement s = null;
		ResultSet r = null;
		try {
			s = connection.createStatement();
			r = s.executeQuery(query);
			while(r.next()) {
				final String schemaName = r.getString( "TABSCHEMA" ).trim();

//<bgp				if ( cache.isBatchLoading() && !schemaName.equals( schema.getName()) ) {
//					if ( null == cache.findSchema( database, schemaName ) ) {
//						// The container is filtered out, so go on
//						continue;
//					}
//
//					// Get the right schema so we can populate the old cache below
//					schema = (LUWCatalogSchema)database.getSchema( schemaName );
//bgp>				}

				final String tableName = r.getString(1);
				final String type      = r.getString(2);

				EClass metaclass=null;
				if (type.equals("T")){ //$NON-NLS-1$
					metaclass = LUWPackage.eINSTANCE.getLUWTable();
				} else if (type.equals("V")){ //$NON-NLS-1$
					metaclass = LUWPackage.eINSTANCE.getLUWView();
				}else if(type.equals("A")) { //$NON-NLS-1$
					metaclass = DB2ModelPackage.eINSTANCE.getDB2Alias();
				}else if(type.equals("S")) { //$NON-NLS-1$
					metaclass = LUWPackage.eINSTANCE.getLUWMaterializedQueryTable();
				}else if(type.equals("N")) { //$NON-NLS-1$
					metaclass = LUWPackage.eINSTANCE.getLUWGenericNickname();
				}

				Object element = LUWCatalogSchema.findTable( cachedElement, oldList, tableName,metaclass );
				Table table;

				if (element != null) {
					table = (Table) element;
					tableList.add(table);
					((ICatalogObject)table).refresh();
				} else {
					if(type.equals("T")) { //$NON-NLS-1$
						table = new LUWCatalogTable();
						
						final String partitionMode = r.getString(4);
						((LUWCatalogTable)table).setPartitionMode(partitionMode);
						
						final String tspName = r.getString(5);
						((LUWCatalogTable)table).setTablespaceName(tspName);

						final String indexTbspaceName = r.getString(6);
						((LUWCatalogTable)table).setIndexTbspaceName(indexTbspaceName);

						final String longTbspaceName = r.getString(7);
						((LUWCatalogTable)table).setlongTbspaceName(longTbspaceName);
						
						final String dataCapture = r.getString("DATACAPTURE");
						if (dataCapture.equals("N")) {
							((LUWCatalogTable)table).setDataCapture(DataCaptureType.NONE_LITERAL);
						} else {
							((LUWCatalogTable)table).setDataCapture(DataCaptureType.CHANGES_LITERAL);
						}
						
						final String compression = r.getString("COMPRESSION");
						if ("V".equals(compression)) {
							((LUWCatalogTable)table).setValueCompression(true);
						} else if ("R".equals(compression)) {
							((LUWCatalogTable)table).setRowCompression(true);
						} else if ("B".equals(compression)) {
							((LUWCatalogTable)table).setValueCompression(true);
							((LUWCatalogTable)table).setRowCompression(true);
						} else {
							((LUWCatalogTable)table).setValueCompression(false);
							((LUWCatalogTable)table).setRowCompression(false);
						}
							
					}
					else if(type.equals("V")) { //$NON-NLS-1$
						table = new LUWCatalogView();
					}
					else if(type.equals("A")) { //$NON-NLS-1$
						table = new LUWCatalogAlias();
					}
					else if(type.equals("S")) { //$NON-NLS-1$
						table = new LUWCatalogMaterializedQueryTable();
						final String partitionMode = r.getString(4);
						((LUWCatalogMaterializedQueryTable)table).setPartitionMode(partitionMode);

						final String compression = r.getString("COMPRESSION");
						if ("V".equals(compression)) {
							((LUWCatalogMaterializedQueryTable)table).setValueCompression(true);
							((LUWCatalogMaterializedQueryTable)table).setRowCompression(false);
						} else if ("R".equals(compression)) {
							((LUWCatalogMaterializedQueryTable)table).setRowCompression(true);
							((LUWCatalogMaterializedQueryTable)table).setValueCompression(false);
						} else if ("B".equals(compression)) {
							((LUWCatalogMaterializedQueryTable)table).setValueCompression(true);
							((LUWCatalogMaterializedQueryTable)table).setRowCompression(true);
						} else {
							((LUWCatalogMaterializedQueryTable)table).setValueCompression(false);
							((LUWCatalogMaterializedQueryTable)table).setRowCompression(false);
						}


					}
					else if(type.equals("N")) { //$NON-NLS-1$
						table = new LUWCatalogNickname();
						((LUWCatalogNickname)table).setAsBatchLoad(isBatchLoad);
					}
					else continue;

					table.setName(tableName);
					
					final String description = r.getString("REMARKS"); //$NON-NLS-1$
					table.setDescription(description);

//<bgp					if ( cache.isBatchLoading() ) {
//						cache.cacheSchemaTable( schemaName, tableName, table );
//					}
//bgp>					else {
						tableList.add(table);
						schema.cacheTable(tableName, table);
//bgp					}

				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (r != null) {
				r.close();	
			}
			if (s != null) {
				s.close();	
			}
		}
	}
	
	private synchronized void loadTables() {
		if(this.tablesLoaded) return;
		this.tablesLoaded = true;

		EList tableList = super.getTables();
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogSchema.loadTables(connection,tableList,this, ((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		LUWCatalogDatabase database = (LUWCatalogDatabase)getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() ) {
//			Collection<SQLObject> tables = CatalogCache.getCache( database ).getTables( this );
//
//			tableList.clear();
//			tableList.addAll( tables );
//bgp>		}
		
		this.eSetDeliver(deliver);
	}

	public static void loadRoutines(Connection connection, EList routineList, Schema schema, int options) throws SQLException {
		String version = connection.getMetaData().getDatabaseProductVersion();
		if (version.startsWith("SQL07")) return;  //don't support v7

		Object[] oldList = routineList.toArray();
		routineList.clear();
		
		if ((options & DatabaseREProvider.EXCLUDE_ROUTINES)== DatabaseREProvider.EXCLUDE_ROUTINES) return;
		
		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(ModelHelper.getDatabase(schema));
		ConnectionFilter spFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.STORED_PROCEDURE_FILTER); //$NON-NLS-1$
		if (spFilter == null) {	//default sp filter
			spFilter = connectionInfo.getFilter(ConnectionFilter.STORED_PROCEDURE_FILTER);
		}
				
		ConnectionFilter fedspFilter = connectionInfo.getFilter(schema.getName()+"::"+IConnectionFilter.FEDERATED_STORED_PROCEDURE_FILTER); //$NON-NLS-1$
		if (fedspFilter == null) {	//default sp filter
			fedspFilter = connectionInfo.getFilter(IConnectionFilter.FEDERATED_STORED_PROCEDURE_FILTER);
		}
		
		ConnectionFilter udfFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.USER_DEFINED_FUNCTION_FILTER); //$NON-NLS-1$
		if (udfFilter == null) {	//default udf filter
			udfFilter = connectionInfo.getFilter(ConnectionFilter.USER_DEFINED_FUNCTION_FILTER);
		}
		String filterStr = ""; //$NON-NLS-1$
		boolean hasFilter = spFilter != null || udfFilter != null || fedspFilter != null;
		if (hasFilter) {
			if (spFilter != null) {
				filterStr += "( " + CatalogLoadUtil.getFilterString(spFilter.getPredicate(), "ROUTINENAME")  + " AND ROUTINETYPE='P' AND ORIGIN<>'F') "; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else
			{
				filterStr += "(ROUTINETYPE='P' and ORIGIN<>'F')"; //$NON-NLS-1$
			}
						
			if (fedspFilter != null) {
				filterStr += " OR (" + CatalogLoadUtil.getFilterString(fedspFilter.getPredicate(), "ROUTINENAME")  + " AND ROUTINETYPE='P' AND ORIGIN='F') "; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else
			{
				filterStr += "OR (ROUTINETYPE='P' and ORIGIN='F')"; //$NON-NLS-1$
			}

			if (udfFilter != null) {
				if (filterStr.length() >0) filterStr += " OR "; //$NON-NLS-1$
				filterStr += "(" + CatalogLoadUtil.getFilterString(udfFilter.getPredicate(), "ROUTINENAME")  + " AND ROUTINETYPE='F') "; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else
			{
				filterStr += " OR ROUTINETYPE='F' "; //$NON-NLS-1$
			}

			filterStr =  " AND (" + filterStr; //$NON-NLS-1$
			filterStr += ") "; //$NON-NLS-1$

		} 
		
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(schema));

		String query = "SELECT ROUTINENAME, ROUTINETYPE,FUNCTIONTYPE,SPECIFICNAME,LANGUAGE,PARAMETER_STYLE,"
		            + "\"DETERMINISTIC\" as D, SQL_DATA_ACCESS, EXTERNAL_ACTION,"   //$NON-NLS-1$
					+ " CHAR(CREATE_TIME) AS CT, CHAR(ALTER_TIME) AS AT, REMARKS, ROUTINEID, ORIGIN ";
		if (ModelHelper.isSupportPL_SQL(databaseDefinition)){
			query += " ,DIALECT ";
		} else {
			query += " ,'' AS DIALECT ";
		}
		
		query   += " FROM SYSCAT.ROUTINES" //$NON-NLS-1$
		    	+ " WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
				+ " AND ORIGIN<>'S'"; //$NON-NLS-1$

		if (ModelHelper.isSupportModule(databaseDefinition)){
			query += " AND ROUTINEMODULENAME IS NULL";
		}
		
		if (hasFilter) {
			query += filterStr;
		}

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		
		try {
			while(r.next()) {
				final String routineName = r.getString(1);

				final String routineType = r.getString(2);
				
				final String origin = r.getString("ORIGIN");
				
				final String dialect = r.getString("DIALECT");
				boolean isPlsql = false;
				if (dialect != null && dialect.trim().equals("PL/SQL")) {
					isPlsql = true;
				}

				Routine routine;
				
				EClass metaclass=null;
				if (routineType.equals("P")){ //$NON-NLS-1$
					metaclass = SQLRoutinesPackage.eINSTANCE.getProcedure();
				} else if (routineType.equals("F")){ //$NON-NLS-1$
					metaclass = SQLRoutinesPackage.eINSTANCE.getUserDefinedFunction();
				}else if(routineType.equals("M")) { //$NON-NLS-1$
					metaclass = SQLRoutinesPackage.eINSTANCE.getMethod();
				}

				Object element = LUWCatalogSchema.findElement(oldList,routineName,metaclass);
				if (element != null) {
					routine = (Routine) element;
					((ICatalogObject)routine).refresh();
				} else {
					if(routineType.equals("F")) { //$NON-NLS-1$
						routine = new LUWCatalogUserDefinedFunction();
						final String funcType= r.getString("FUNCTIONTYPE"); //$NON-NLS-1$
						if (funcType.equals("T"))  //$NON-NLS-1$
							((LUWCatalogUserDefinedFunction)routine).setLUWFunctionType(LUWCatalogUserDefinedFunction.FunctionTypeEnumeration.TABLE_FUNCTION);
						else if (funcType.equals("C"))  //$NON-NLS-1$
							((LUWCatalogUserDefinedFunction)routine).setLUWFunctionType(LUWCatalogUserDefinedFunction.FunctionTypeEnumeration.COLUMN_FUNCTION);
						else if (funcType.equals("R"))  //$NON-NLS-1$
							((LUWCatalogUserDefinedFunction)routine).setLUWFunctionType(LUWCatalogUserDefinedFunction.FunctionTypeEnumeration.ROW_FUNCTION);
						else 
							((LUWCatalogUserDefinedFunction)routine).setLUWFunctionType(LUWCatalogUserDefinedFunction.FunctionTypeEnumeration.SCALAR_FUNCTION);

						if (origin.equals("M")){
							((LUWCatalogUserDefinedFunction)routine).setOrigin(OriginType.TEMPLATE_LITERAL);
						} else if (origin.equals("U")){
							((LUWCatalogUserDefinedFunction)routine).setOrigin(OriginType.SOURCE_LITERAL);
						} else {
							((LUWCatalogUserDefinedFunction)routine).setOrigin(OriginType.NONE_LITERAL);
						}

						if (isPlsql) {
							((LUWCatalogUserDefinedFunction)routine).setDialect(SourceDialect.PLSQL_LITERAL);
						} else {
							((LUWCatalogUserDefinedFunction)routine).setDialect(SourceDialect.DB2SQLPL_LITERAL);
						}

					}
					else if(routineType.equals("P")) { //$NON-NLS-1$
						if(!origin.equals("F")){
							routine = new LUWCatalogProcedure();
							
							if (isPlsql) {
								((LUWCatalogProcedure)routine).setDialect(SourceDialect.PLSQL_LITERAL);
							} else {
								((LUWCatalogProcedure)routine).setDialect(SourceDialect.DB2SQLPL_LITERAL);
							}
						}else{
							//federated SP
							routine = new LUWCatalogFederatedProcedure();
							LUWCatalogFederatedProcedure fedRoutine = (LUWCatalogFederatedProcedure)routine;
							
							int routineID = r.getInt("ROUTINEID");
							fedRoutine.setRoutineID(routineID);
							
							String deterministic = r.getString("D");
							if(deterministic.equals("Y")) routine.setDeterministic(true); //$NON-NLS-1$
							else routine.setDeterministic(false);
							
							final String sql_data_access = r.getString("SQL_DATA_ACCESS"); //$NON-NLS-1$
			                // 'C' - CONTAINS SQL, 'M' - MODIFIES SQL DATA, 'N' - No SQL, 'R' - READS SQL DATA
			                if (sql_data_access.equals("C")) fedRoutine.setSQLDataAccess(DataAccess.CONTAINS_SQL_LITERAL); //$NON-NLS-1$
			                else if (sql_data_access.equals("M")) fedRoutine.setSQLDataAccess(DataAccess.MODIFIES_SQL_DATA_LITERAL); //$NON-NLS-1$
			                else if (sql_data_access.equals("N")) fedRoutine.setSQLDataAccess(DataAccess.NO_SQL_LITERAL); //$NON-NLS-1$
			                else fedRoutine.setSQLDataAccess(DataAccess.READS_SQL_DATA_LITERAL);
			                
			                String externalAction = r.getString("EXTERNAL_ACTION");
			                fedRoutine.setExternalAction(externalAction);

							
						}
					}
					else if(routineType.equals("M")) { //$NON-NLS-1$
						continue;
					}
					else {
						continue;
					}
					routine.setName(routineName);
				}
				final String language = r.getString("LANGUAGE").trim(); //$NON-NLS-1$
				routine.setLanguage(language);
				final String paramStyle = r.getString("PARAMETER_STYLE").trim(); //$NON-NLS-1$
				routine.setParameterStyle(paramStyle);
				final String createdTS = r.getString("CT"); //$NON-NLS-1$
				routine.setCreationTS(createdTS);
				final String alteredTS = r.getString("AT"); //$NON-NLS-1$
				routine.setLastAlteredTS(alteredTS);
				
				final String remarks = r.getString("REMARKS"); //$NON-NLS-1$
				routine.setDescription(remarks);
				
				final String specificName= r.getString("SPECIFICNAME"); //$NON-NLS-1$
				routine.setSpecificName(specificName);
				if(!origin.equals("F")){
					routineList.add(routine);
				}else{
					routineList.add(routine);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		r.close();
		s.close();
	}	
	
	private synchronized void loadRoutines() {
		if(this.routinesLoaded) return;
		this.routinesLoaded = true;

		EList routineList = super.getRoutines();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Connection connection = this.getConnection();
		try {
			LUWCatalogSchema.loadRoutines(connection,routineList,this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	public static void loadJars(Connection connection, EList jarList, Schema schema, int options) throws SQLException {
		Object[] oldList = jarList.toArray();
		jarList.clear();
				
		if ((options & DatabaseREProvider.EXCLUDE_ROUTINES)== DatabaseREProvider.EXCLUDE_ROUTINES) return;
		
		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(ModelHelper.getDatabase(schema));
		ConnectionFilter jarFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.JAR_FILTER); //$NON-NLS-1$
		if (jarFilter == null) {	//default jar filter
			jarFilter = connectionInfo.getFilter(ConnectionFilter.JAR_FILTER);
		}
		String filterStr = ""; //$NON-NLS-1$
		if (jarFilter != null) {
			filterStr += " AND " + CatalogLoadUtil.getFilterString(jarFilter.getPredicate(), "JAR_ID") ;
		} 
		
		String query = "SELECT JAR_ID, DEFINER, ALTEREDTS, CREATEDTS"  //$NON-NLS-1$
					+ " FROM SYSIBM.SYSJAROBJECTS" //$NON-NLS-1$
					+ " WHERE JARSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
		
		if (jarFilter != null) {
		   // TODO implement jar filters
//			query += filterStr;
		}
		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		
		try {
			while(r.next()) {
				final String jarID = r.getString("JAR_ID"); //$NON-NLS-1$

				final String definer = r.getString("DEFINER"); //$NON-NLS-1$
				
				DB2Jar jar;
				
				EClass metaclass=DB2ModelPackage.eINSTANCE.getDB2Jar();
				Object element = LUWCatalogSchema.findElement(oldList,jarID,metaclass);
				if (element != null) {
				    // we already have the model object in the schema
					jar = (DB2Jar) element;
					((ICatalogObject)jar).refresh();
				} else {
				    // create the model object
				   jar = new LUWCatalogJar();
				   jar.setName(jarID);
				}
				// populate the model object
				jar.setOwner(definer);
				final String createdTS = r.getString("CREATEDTS"); //$NON-NLS-1$
				jar.setCreatedTS(createdTS);
				final String alteredTS = r.getString("ALTEREDTS"); //$NON-NLS-1$
				jar.setAlteredTS(alteredTS);
								
				jarList.add(jar);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		r.close();
		s.close();
	}		
	
	public static void loadXMLSchemas(Connection connection, EList xsrObjList, Schema schema) throws SQLException {
		Object[] oldList = xsrObjList.toArray();
		xsrObjList.clear();

		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(ModelHelper.getDatabase(schema));
		
		// delete all previously cached xsd sources in this schema recursively.
		// this way all cached xsd files will be deleted even if the actual objects on the database
		// have been deleted outside of the tooling. 
		// TODO sschaer 
		// not yet implemented: if a whole schema is deleted outside of the tooling, then the
		// cached xsd sources of that schema are not deleted because this LUWCatalogSchema is not created.
		String rootPath = getXSDSourceCacheRoot(connectionInfo, schema);
		deleteXSDSourceCache(rootPath);
		
		ConnectionFilter xsrObjectFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.XSR_OBJECTS_FILTER); //$NON-NLS-1$
		if (xsrObjectFilter == null) {	//default jar filter
			xsrObjectFilter = connectionInfo.getFilter(ConnectionFilter.XSR_OBJECTS_FILTER);
		}
		String filterStr = ""; //$NON-NLS-1$
		if (xsrObjectFilter != null) {
			filterStr += CatalogLoadUtil.getFilterString(xsrObjectFilter.getPredicate(), "OBJECTNAME")  + " AND OBJECTTYPE='S' ";		    //$NON-NLS-1$ //$NON-NLS-2$
		   filterStr =  " AND (" + filterStr; //$NON-NLS-1$
		   filterStr += ") "; //$NON-NLS-1$
		} 
		
		String query = "SELECT OBJECTNAME, OBJECTTYPE, DECOMPOSITION, REMARKS, STATUS"  //$NON-NLS-1$
					+ " FROM SYSCAT.XSROBJECTS" //$NON-NLS-1$
					+ " WHERE OBJECTSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
		
		if (xsrObjectFilter != null) {
			query += filterStr;
		}
				
		Statement s = null;
		ResultSet r = null;
		
		try {
			s = connection.createStatement();
			r = s.executeQuery(query);
			
			while(r.next()) {
				final String xsrObjectName = r.getString("OBJECTNAME"); //$NON-NLS-1$
				final String objectType = r.getString("OBJECTTYPE");				 //$NON-NLS-1$
				
				DB2XMLSchema xmlSchema;
				
				EClass metaclass=null;
				
				if (objectType.equals("S")) //$NON-NLS-1$
					metaclass = DB2ModelPackage.eINSTANCE.getDB2XMLSchema();
				
				if (metaclass != null)
				{
					Object element = LUWCatalogSchema.findElement(oldList,xsrObjectName,metaclass);
					if (element != null) {
					    // we already have the model object in the schema
						xmlSchema = (DB2XMLSchema) element;
						((ICatalogObject)xmlSchema).refresh();
					} else {
					    // create the model object
						xmlSchema = new LUWCatalogXmlSchema();
						xmlSchema.setName(xsrObjectName);
					}
					// populate the model object
					final String decomposition = r.getString("DECOMPOSITION"); //$NON-NLS-1$
					if (decomposition.equalsIgnoreCase("Y"))                    //$NON-NLS-1$
						xmlSchema.setDecomposition(DB2XMLSchemaDecomposition.ENABLED_LITERAL);
					else if (decomposition.equalsIgnoreCase("N"))                //$NON-NLS-1$
						xmlSchema.setDecomposition(DB2XMLSchemaDecomposition.NOT_ENABLED_LITERAL);
					else if (decomposition.equalsIgnoreCase("X"))                 //$NON-NLS-1$
						xmlSchema.setDecomposition(DB2XMLSchemaDecomposition.INOPERATIVE_LITERAL);
									
					final String status = r.getString("STATUS"); //$NON-NLS-1$
					if (status.equalsIgnoreCase("C"))                    //$NON-NLS-1$
						xmlSchema.setStatus(DB2XMLSchemaStatus.COMPLETE_LITERAL);
					else if (status.equalsIgnoreCase("I"))                //$NON-NLS-1$
						xmlSchema.setStatus(DB2XMLSchemaStatus.INCOMPLETE_LITERAL);
					else if (status.equalsIgnoreCase("R"))                 //$NON-NLS-1$
						xmlSchema.setStatus(DB2XMLSchemaStatus.REPLACE_LITERAL);					
					else if (status.equalsIgnoreCase("T"))                 //$NON-NLS-1$
						xmlSchema.setStatus(DB2XMLSchemaStatus.TEMPORARY_LITERAL);
					
					final String comment = r.getString("REMARKS");  //$NON-NLS-1$
					xmlSchema.setDescription(comment);
			
					xsrObjList.add(xmlSchema);
				}
			}
		}catch (Exception e) {
			DB2LUWPluginActivator.getInstance().log("LUWCatalogSchema.loadXMLSchemas()",IStatus.ERROR,e); //$NON-NLS-1$
		}
		finally
		{
			r.close();
			s.close();			
		}
	}		

	private synchronized void loadJars() {
	   if(this.jarsLoaded) return;
	   this.jarsLoaded = true;

	   EList jarList = super.getJars();
	   
	   boolean deliver = this.eDeliver();
	   this.eSetDeliver(false);
	   Connection connection = this.getConnection();
	   try {
	      
	      LUWCatalogSchema.loadJars(connection,jarList,this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
	   }
	   catch (Exception e) {
			e.printStackTrace();
	   }
	   this.eSetDeliver(deliver);
	}

	private synchronized void loadXsrObjects() {
		   if(this.xsrObjsLoaded) return;
		   EList xsrObjList = super.getXsrObjects();
		   
		   boolean deliver = this.eDeliver();
		   this.eSetDeliver(false);
		   Connection connection = this.getConnection();
		   try {
		      
		      LUWCatalogSchema.loadXMLSchemas(connection,xsrObjList,this);
		   }
		   catch (Exception e) {
				e.printStackTrace();
		   }
		   this.eSetDeliver(deliver);
		}	
	
	public static void loadSequences(Connection connection, EList sequenceList, Schema schema, int options) throws SQLException {
		Object[] oldList = sequenceList.toArray();
		sequenceList.clear();

		if ((options & DatabaseREProvider.EXCLUDE_SEQUENCE)== DatabaseREProvider.EXCLUDE_SEQUENCE) return;
		
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(schema));
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
		
		
		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(ModelHelper.getDatabase(schema));
		ConnectionFilter sequenceFilter = connectionInfo.getFilter(schema.getName()+"::" +ConnectionFilter.SEQUENCE_FILTER); //$NON-NLS-1$
		if (sequenceFilter == null) {	//default sequence filter
			sequenceFilter = connectionInfo.getFilter(ConnectionFilter.SEQUENCE_FILTER);
		}
		
		String filterStr = ""; //$NON-NLS-1$
		boolean hasFilter = sequenceFilter != null;
		if (hasFilter) {
			filterStr =  " AND " + CatalogLoadUtil.getFilterString(sequenceFilter.getPredicate(), "SEQNAME") ;
		} 
		
		// The SYSCAT.SEQUENCES catalog table not only contains Sequences
		// but also contains Sequence Alias's. These Alias's should not be
		// loaded and handled as part of Sequences.
		// Column SEQTYPE - A = Alias, I = Identity sequence, S = Sequence
		// WHERE clause will be added to only load I and S and omit A
		String query = "SELECT SEQNAME, REMARKS, DATATYPEID, PRECISION, INCREMENT, START, MAXVALUE, MINVALUE, CYCLE, CACHE" //$NON-NLS-1$
			+ " FROM SYSCAT.SEQUENCES "  //$NON-NLS-1$
			+ " WHERE SEQSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'"  //$NON-NLS-1$ //$NON-NLS-2$
			+ " AND ORIGIN='U'" //$NON-NLS-1$
			+ " AND (SEQTYPE = 'S' OR SEQTYPE = 'I')"; //$NON-NLS-1$

		if (hasFilter) {
			query += filterStr;
		}
		
		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				LUWCatalogSequence sequence;
				final String seqName = r.getString(1);
				
				EClass metaclass=SQLSchemaPackage.eINSTANCE.getSequence();

				Object element = LUWCatalogSchema.findElement(oldList, seqName,metaclass);
				if (element != null){
					sequence = (LUWCatalogSequence) element;
					sequence.refresh();
				} else {
					sequence = new LUWCatalogSequence();
					sequence.setName(seqName);
				}
				
				final String remarks = r.getString(2);
				sequence.setDescription(remarks);
				final int dataTypeId = r.getInt(3);
				sequence.setDataTypeId(dataTypeId);
				final int precision = r.getInt(4);
				sequence.setPrecision(precision);
				DB2IdentitySpecifier identity = (DB2IdentitySpecifier) factory.create(DB2ModelPackage.eINSTANCE.getDB2IdentitySpecifier());
				identity.setGenerationType(GenerateType.ALWAYS_GENERATED_LITERAL);
				identity.setIncrement(r.getBigDecimal(5).toBigInteger());
				identity.setStartValue(r.getBigDecimal(6).toBigInteger());
				identity.setMaximum(r.getBigDecimal(7).toBigInteger());
				identity.setMinimum(r.getBigDecimal(8).toBigInteger());
				final String cycle = r.getString(9);
				if(cycle.equals("Y")) identity.setCycleOption(true); //$NON-NLS-1$
				else identity.setCycleOption(false);
				
				final int cache = r.getInt(10);
				identity.setCache(cache);
				
				sequence.setIdentity(identity);
				sequenceList.add(sequence);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		r.close();
		s.close();
		
	}	
	private synchronized void loadSequences() {
		if(this.sequencesLoaded) return;
		this.sequencesLoaded = true;

		EList sequenceList = super.getSequences();
		Connection connection = this.getConnection();
		if(connection == null) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogSchema.loadSequences(connection,sequenceList,this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadTriggers() {
		if(this.triggersLoaded) return;
		this.triggersLoaded = true;
 		super.getTriggers();
 		
 		LUWCatalogDatabase database = (LUWCatalogDatabase)getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() ) {
//			return;
//bgp> 		}

		Connection connection = this.getConnection();
		
		int options = ((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions();		
		if ((options & DatabaseREProvider.EXCLUDE_TRIGGERS)== DatabaseREProvider.EXCLUDE_TRIGGERS) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT DISTINCT TABSCHEMA, TABNAME FROM SYSCAT.TRIGGERS WHERE TRIGSCHEMA='" + LUWUtil.getIdentifier(this.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			while(r.next()) {
				final String tabSchema = r.getString(1).trim();
				final String tabName   = r.getString(2);
				Table table = this.getTable(tabSchema, tabName);
				if (table!= null)table.getTriggers();
			}
			r.close();
			s.close();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);		
	}
	
	private synchronized void loadIndices() {
		if(this.indicesLoaded) return;
		this.indicesLoaded = true;

		super.getIndices().clear();
 		
		LUWCatalogDatabase database  = (LUWCatalogDatabase) this.getCatalogDatabase();
		if (!database.isBatchLoad()) return;

		int options = ((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions();		
		if ((options & DatabaseREProvider.EXCLUDE_INDEXES)== DatabaseREProvider.EXCLUDE_INDEXES) return;
 		
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			String query = "SELECT INDEXTYPE, INDSCHEMA,TABNAME, INDNAME, REMARKS, UNIQUERULE, PCTFREE, SYSTEM_REQUIRED, USER_DEFINED "
				+ "FROM SYSCAT.INDEXES" //$NON-NLS-1$
				+ " WHERE TABSCHEMA='" + LUWUtil.getIdentifier(this.getName()) + "'";

			ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(database);
			ConnectionFilter tableFilter = connectionInfo.getFilter(this.getName()+"::"+ConnectionFilter.TABLE_FILTER); //$NON-NLS-1$
			if (tableFilter == null) {  //default table filter
				tableFilter = connectionInfo.getFilter(ConnectionFilter.TABLE_FILTER);
			}
	        
			String filterStr = ""; //$NON-NLS-1$
			if (tableFilter != null) {
				query += " AND " + CatalogLoadUtil.getFilterString(tableFilter.getPredicate(), "TABNAME");
			}
			
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			int typeOrdinal = r.findColumn("INDEXTYPE"); //$NON-NLS-1$
			int idxSchemOrdinal = r.findColumn("INDSCHEMA"); //$NON-NLS-1$
			int tableOrdinal = r.findColumn("TABNAME"); //$NON-NLS-1$
			int nameOrdinal = r.findColumn("INDNAME"); //$NON-NLS-1$
			int remarkOrdinal = r.findColumn("REMARKS"); //$NON-NLS-1$
			int ruleOrdinal = r.findColumn("UNIQUERULE"); //$NON-NLS-1$
			int pctfreeOrdinal = r.findColumn("PCTFREE");   //$NON-NLS-1$
			int systemRequiredOrdinal = r.findColumn("SYSTEM_REQUIRED"); //$NON-NLS-1$
			int userDefinedOrdinal = r.findColumn("USER_DEFINED");
			
			while(r.next()) {
				final String indName = r.getString(nameOrdinal);
				
				Index index = null;
				final String type = r.getString(typeOrdinal);
				if(type.equals("REG ")) { //$NON-NLS-1$
					index = new LUWCatalogIndex();
					
				}
				else if(type.equals("CLUS")) { //$NON-NLS-1$
					index = new LUWCatalogIndex();
					index.setClustered(true);

				}
				else if(type.equals("DIM ")) { //$NON-NLS-1$
					index = new LUWCatalogIndex();	
					((LUWCatalogIndex)index).setIndexType(DB2IndexType.DIMENSION_LITERAL);
				}
				else if(type.equals("BLOK")) { //$NON-NLS-1$
					index = new LUWCatalogIndex();	
					((LUWCatalogIndex)index).setIndexType(DB2IndexType.BLOCK_LITERAL);
				}
				else {
					continue;
				}

				index.setName(indName);
				
				final String remarks = r.getString(remarkOrdinal);
				index.setDescription(remarks);
				
				final String uniqueRule = r.getString(ruleOrdinal);
				if(!uniqueRule.equals("D")) index.setUnique(true); //$NON-NLS-1$
				if ("P".equalsIgnoreCase(uniqueRule))  ((LUWCatalogIndex)index).setIndexUniqueRule(IndexUniqueRule.PRIMARYKEY);

				
				final int pctfree = r.getInt(pctfreeOrdinal);
				if(pctfree == -1) index.setFillFactor(10);
				else index.setFillFactor(pctfree);
				
				
				final int systemRequred = r.getInt(systemRequiredOrdinal);
				if (index instanceof LUWIndex) {
					if (systemRequred != 0) {
						((LUWIndex)index).setSystemRequired(true);
					} else {
						((LUWIndex)index).setSystemRequired(false);
					}
				}
				
				final int userDefined = r.getInt(userDefinedOrdinal);
				if (userDefined == 0) {
					index.setSystemGenerated(true);
				}
				else {
					index.setSystemGenerated(false);
				}
				
				final String tabName = r.getString(tableOrdinal).trim();
				Table table = this.getTable(this.getName(),tabName);
				if (table != null) {
					index.setTable(table);
				}

				final String schemaName = r.getString(idxSchemOrdinal).trim();
				Schema indexSchema =LUWCatalogSchema.getSchema(this,schemaName);
				if (indexSchema != null) {
					index.setSchema(indexSchema);
				}
				
			}
			r.close();
			s.close();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);		
	}
	
	private synchronized void loadOlapobjects() {
		if(this.olapobjectLoaded) return;
		this.olapobjectLoaded = true;

		EList olapList = super.getOlapObjects();
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			OlapCatalogProvider provider = getOlapCatalogProvider();
			if (provider != null){
				Iterator it = provider.getOlapobjects(connection,this).iterator();
				while(it.hasNext()) {
					Object bp = it.next();
					olapList.add(bp);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadUserDefinedTypes() {
		if(this.userDefinedTypesLoaded) return;
		this.userDefinedTypesLoaded = true;

		EList userDefinedTypeList = super.getUserDefinedTypes();
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogSchema.loadUserDefinedTypes(connection,userDefinedTypeList,this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);
	}
	
	
	
	public static void loadUserDefinedTypes(Connection connection, EList userDefinedTypeList, Schema schema, int options) throws SQLException {
		Object[] oldList = userDefinedTypeList.toArray();
		userDefinedTypeList.clear();

		if ((options & DatabaseREProvider.EXCLUDE_USER_DEFINED_TYPE)== DatabaseREProvider.EXCLUDE_USER_DEFINED_TYPE) return;

		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(schema));

		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(ModelHelper.getDatabase(schema));
		ConnectionFilter udtFilter = connectionInfo.getFilter(schema.getName()+"::"+ConnectionFilter.USER_DEFINED_TYPE_FILTER); //$NON-NLS-1$
		if (udtFilter == null) {	//default udt filter
			udtFilter = connectionInfo.getFilter(ConnectionFilter.USER_DEFINED_TYPE_FILTER);
		}
		String filterStr = ""; //$NON-NLS-1$
		boolean hasFilter = udtFilter != null;
		if (hasFilter) {
			filterStr += " AND " + CatalogLoadUtil.getFilterString(udtFilter.getPredicate(), "TYPENAME");
		} 

		String query = "";
		if (ModelHelper.isSupportArrayDataType(databaseDefinition)){
			query = "SELECT TYPENAME, METATYPE, REMARKS, SOURCESCHEMA, SOURCENAME, LENGTH" + //$NON-NLS-1$
			",SCALE, CODEPAGE, INSTANTIABLE, FINAL, ARRAY_LENGTH "; //$NON-NLS-1$
		} else {
			query = "SELECT TYPENAME, METATYPE, REMARKS, SOURCESCHEMA, SOURCENAME, LENGTH" + //$NON-NLS-1$
			",SCALE, CODEPAGE, INSTANTIABLE, FINAL, 0 AS ARRAY_LENGTH ";//$NON-NLS-1$
		}
	
		query += " FROM SYSCAT.DATATYPES " + //$NON-NLS-1$
				" WHERE TYPESCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
	
		if (hasFilter) {
			query += filterStr;
		}

		if (ModelHelper.isSupportModule(databaseDefinition)){
			query += " AND TYPEMODULENAME IS NULL";
		}

		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		try {
			while(r.next()) {
				
				String typeName = r.getString(1);
				UserDefinedType type = null;
				
				EClass metaclass=SQLDataTypesPackage.eINSTANCE.getUserDefinedType();

				Object element = LUWCatalogSchema.findElement(oldList, typeName,metaclass);

				if (element != null){
					type = (UserDefinedType) element;
					((ICatalogObject)type).refresh();
					
				} else {
					String metaType = r.getString(2);
					if(metaType.equals("T") ) { //$NON-NLS-1$
						type = LUWCatalogSchema.constructDistintUserDefinedType(r,schema);
					}
					else if(metaType.equals("R")) { //$NON-NLS-1$
						type = LUWCatalogSchema.constructStructuredUserDefinedType(r);
					}
					else if(metaType.equals("A")) { //$NON-NLS-1$
						type = LUWCatalogSchema.constructArrayDataType(r,schema);
					}
					else if(metaType.equals("F")) { //$NON-NLS-1$
						type = new LUWCatalogRowDataType();
					}
					else if(metaType.equals("C")) { //$NON-NLS-1$
						type = new LUWCatalogCursorDataType();
					}
					else if(metaType.equals("S")) { //$NON-NLS-1$
						continue;
					}
					else {
						continue;
					}
					
					type.setName(typeName);
				}

				String remarks = r.getString(3);
				type.setDescription(remarks);
				userDefinedTypeList.add(type);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		r.close();
		s.close();			
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
			LUWCatalogSchema.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	
	public static void loadPrivileges(Connection connection, EList privilegeList,Schema schema,String granteeFilter) throws SQLException {
		final Database database = ModelHelper.getDatabase(schema);
		
		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
					",ALTERINAUTH,CREATEINAUTH,DROPINAUTH" +
					" FROM SYSCAT.SCHEMAAUTH" + 
					" WHERE SCHEMANAME='" + LUWUtil.getIdentifier(schema.getName()) + "'";

		if (granteeFilter != null && !"".equals(granteeFilter)) query += " AND " + granteeFilter;

		ResultSet r = s.executeQuery(query);
		try {
			String userName = connection.getMetaData().getUserName();
			while(r.next()) {
				final String grantorId = r.getString("GRANTOR").trim();
				AuthorizationIdentifier grantor = LUWCatalogDatabase.getAuthorizationId(database, grantorId,null);
				final String granteeId = r.getString("GRANTEE").trim();
				AuthorizationIdentifier grantee = null;
				final String granteeType = r.getString("GRANTEETYPE");
				if ("G".equals(granteeType)) {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "G");
				} else if ("R".equals(granteeType)) {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "R");
				} else {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "U");
				}
				
				LUWCatalogPrivilege privilege = null;
				boolean isSystemGranted  = granteeId.equalsIgnoreCase(userName);
				final String alterAuth = r.getString("ALTERINAUTH");
				if (alterAuth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_ALTERIN);
					if (alterAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				final String deleteAuth = r.getString("CREATEINAUTH");
				if (deleteAuth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CREATEIN);
					if (deleteAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				
				final String indexAuth = r.getString("DROPINAUTH");
				if (indexAuth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_DROPIN);
					if (indexAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
	}

	private synchronized void loadPackages() {
		if(this.packagesLoaded) return;
		this.packagesLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogSchema.loadPackages(this.getConnection(),super.getPackages(), this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	public static void loadPackages(Connection connection, EList packageList,LUWCatalogSchema schema) throws SQLException {
		schema.iniCachedDb2Package();

		//do not RE package
		if(((LUWCatalogDatabase)schema.getCatalogDatabase()).isBatchLoad()) return;

        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(schema.getCatalogDatabase());
        String version = definition.getVersion();
        float ver = 8.0f;
        try {
            ver = Float.parseFloat(version.substring(1));
        }
        catch (NumberFormatException e) {            
        }

        String query = "SELECT PKGNAME, VALID, PKGVERSION, DEFAULT_SCHEMA, FUNC_PATH," +		//$NON-NLS-1$
        		" REOPTVAR, ISOLATION, HEX(UNIQUE_ID) AS UID, CHAR(LAST_BIND_TIME) AS LBTS," +	//$NON-NLS-1$
        		" BOUNDBY, BLOCKING, TOTAL_SECT, QUERYOPT, EXPLAIN_SNAPSHOT, REMARKS";	        //$NON-NLS-1$
        if (ver <9.0) {
        	query += " ,DEFINER AS OWNER";
        } else {
        	query += " ,OWNER";
        }
		
		
		// PKGVERSION has been available since V8.1 and we don't support connections to V7
        query += " FROM SYSCAT.PACKAGES" + 														//$NON-NLS-1$
				" WHERE PKGSCHEMA=" + LUWUtil.getSingleQuotedString(schema.getName()) +			//$NON-NLS-1$
				" FOR FETCH ONLY WITH UR";														//$NON-NLS-1$

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		try {
			while(r.next()) {
				LUWCatalogDatabasePackage dbpackage = new LUWCatalogDatabasePackage();

				final String pkgName = r.getString("PKGNAME").trim();					//$NON-NLS-1$
				dbpackage.setName(pkgName);
				final String pkgValid = r.getString("VALID").trim();					//$NON-NLS-1$
				dbpackage.setValid(pkgValid);
				final String pkgVersion = r.getString("PKGVERSION").trim();				//$NON-NLS-1$
				dbpackage.setVersion(pkgVersion);
				final String pkgDefaultSchema = r.getString("DEFAULT_SCHEMA").trim();	//$NON-NLS-1$
				dbpackage.setDefaultSchema(pkgDefaultSchema);
				final String pkgSqlPath = r.getString("FUNC_PATH").trim();				//$NON-NLS-1$
				dbpackage.setSqlPath(pkgSqlPath);
				final String pkgReoptVar = r.getString("REOPTVAR");						//$NON-NLS-1$
				if ("A".equals(pkgReoptVar)){											//$NON-NLS-1$
					dbpackage.setReoptVar(ReoptType.ALWAYS_LITERAL);
				} else if ("N".equals(pkgReoptVar)) {									//$NON-NLS-1$
					dbpackage.setReoptVar(ReoptType.NONE_LITERAL);
				} else if ("O".equals(pkgReoptVar)) {									//$NON-NLS-1$
					dbpackage.setReoptVar(ReoptType.ONCE_LITERAL);
				}
				final String pkgIsolation = r.getString("ISOLATION");					//$NON-NLS-1$
				if ("CS".equals(pkgIsolation)){											//$NON-NLS-1$
					dbpackage.setIsolation(IsolationLevelType.CURSOR_STABILITY_LITERAL);
				} else if ("RR".equals(pkgIsolation)) {									//$NON-NLS-1$
					dbpackage.setIsolation(IsolationLevelType.REPEATABLE_READ_LITERAL);
				} else if ("RS".equals(pkgIsolation)) {									//$NON-NLS-1$
					dbpackage.setIsolation(IsolationLevelType.READ_STABILITY_LITERAL);
				} else if ("UR".equals(pkgIsolation)) {									//$NON-NLS-1$
					dbpackage.setIsolation(IsolationLevelType.UNCOMMITTED_READ_LITERAL);
				} else {
					// The isolation level was not set at the package level
					dbpackage.setIsolation(null);
				}

				final String pkgUniqueID = r.getString("UID").trim();					//$NON-NLS-1$
				dbpackage.setUniqueID(pkgUniqueID);
				final String pkgLastBindTS = r.getString("LBTS").trim();				//$NON-NLS-1$
				dbpackage.setLastBindTS(pkgLastBindTS);

				final String pkgBoundBy = r.getString("BOUNDBY");
				dbpackage.setBinder(pkgBoundBy);
				final String pkgOwner = r.getString("OWNER");
				dbpackage.setCreator(pkgOwner);
				final String pkgBlocking = r.getString("BLOCKING");
				if ("B".equals(pkgBlocking)) {
					dbpackage.setCursorBlock(CursorBlockType.BLOCK_ALL_CURSORS_LITERAL);	
				}
				else if ("N".equals(pkgBlocking)) {
					dbpackage.setCursorBlock(CursorBlockType.NO_BLOCKING_LITERAL);
				}
				else if ("U".equals(pkgBlocking)) {
					dbpackage.setCursorBlock(CursorBlockType.BLOCK_UNAMBIGUOUS_CURSORS_LITERAL);
				}
				
				final String pkgTotalSect = r.getString("TOTAL_SECT");
				dbpackage.setNumberOfSections(Integer.parseInt(pkgTotalSect));
				final String pkgQueryOpt = r.getString("QUERYOPT");
				dbpackage.setOptimizationClass(Integer.parseInt(pkgQueryOpt));
				final String pkgExplainSnapShot = r.getString("EXPLAIN_SNAPSHOT");
				if ("A".equals(pkgExplainSnapShot)) {
					dbpackage.setExplainSnapshot(ExplainSnaphotType.ALL_LITERAL);	
				}
				else if ("N".equals(pkgExplainSnapShot)) {
					dbpackage.setExplainSnapshot(ExplainSnaphotType.NO_LITERAL);
				}
				else if ("R".equals(pkgExplainSnapShot)) {
					dbpackage.setExplainSnapshot(ExplainSnaphotType.REOPT_LITERAL);
				}
				else if ("Y".equals(pkgExplainSnapShot)) {
					dbpackage.setExplainSnapshot(ExplainSnaphotType.REOPT_LITERAL);
				}
				
				final String remarks = r.getString("REMARKS");
				dbpackage.setDescription(remarks);

				// Add and cache the package
				packageList.add(dbpackage);
				schema.cacheDB2Package(pkgName, pkgUniqueID, dbpackage);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		r.close();
		s.close();
	}

	private synchronized void loadModules() {
		if(this.moduleLoaded) return;
		this.moduleLoaded = true;

		EList modules = super.getModules();
		modules.clear();

		final Database database = this.getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);

		if (!ModelHelper.isSupportModule(databaseDefinition)) return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			ModuleCatalogProvider moduleProvider = ((LUWCatalogDatabase)this.getCatalogDatabase()).getCatalogModuleProvider();
			if (moduleProvider != null){
				Iterator it = moduleProvider.getModules(this.getConnection(), this).iterator();
				while(it.hasNext()) {
					LUWModule module = (LUWModule) it.next();
					modules.add(module);
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadGlobaleVaraibles() {
		if(this.globalVariableLoaded) return;
		this.globalVariableLoaded = true;

		EList varaibles = super.getGlobalVariables();
		varaibles.clear();

		final Database database = this.getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);

		if (!ModelHelper.isSupportGlobalVariable(databaseDefinition)) return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {

			String query = "SELECT VARNAME,TYPESCHEMA,TYPENAME,LENGTH,SCALE,CODEPAGE,READONLY,REMARKS,DEFAULT"
				+ " FROM SYSCAT.VARIABLES " //$NON-NLS-1$
				+ " WHERE VARSCHEMA='" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
				+ " AND VARMODULENAME is NULL"; //$NON-NLS-1$

			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				LUWCatalogGlobalVariable var = new LUWCatalogGlobalVariable();
				final String name = r.getString("VARNAME");
				var.setName(name);
				
				final String remarks =  r.getString("REMARKS");
				var.setDescription(remarks);
				
				String typeName = r.getString("TYPENAME");
				
				if (typeName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
					int length = r.getInt("LENGTH");
					if (length ==4) typeName="REAL"; //$NON-NLS-1$
					else typeName="DOUBLE"; //$NON-NLS-1$
					
				}
				
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					
					if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
						final int codePage = r.getInt("CODEPAGE");
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					else if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
						final int codePage = r.getInt("CODEPAGE");
						if(codePage == 0) {
							if (typeName.equals("LONG VARCHAR")){
								typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("LONG VARCHAR FOR BIT DATA"); //$NON-NLS-1$
							} else {
								typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
							}
						}
					}

					
					PredefinedDataType type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						final int length = r.getInt("LENGTH");
						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
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
						final int length = r.getInt("SCALE");
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					
					var.setContainedType(type);
				}
				else {
					if (typeName.equals("REFERENCE")){ //$NON-NLS-1$
						final String typeSchemaName = r.getString("TARGET_TYPESCHEMA").trim(); //$NON-NLS-1$
						final String udtName = r.getString("TARGET_TYPENAME"); //$NON-NLS-1$
						var.setReferencedType(LUWCatalogSchema.getUserDefinedType(this,typeSchemaName,udtName));
					} else {
						final String typeSchemaName = r.getString("TYPESCHEMA").trim();
						var.setReferencedType(LUWCatalogSchema.getUserDefinedType(this,typeSchemaName,typeName));
					}
				}
				
				final String readOnly = r.getString("READONLY");
				if ("C".equalsIgnoreCase(readOnly)) var.setIsConstant(true);
				
				final String defaultValue = r.getString("DEFAULT");
				var.setDefaultValue(defaultValue);


				varaibles.add(var);
			} 
			r.close();
			s.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private static DistinctUserDefinedType constructDistintUserDefinedType(ResultSet r,Schema schema) throws SQLException {
		DistinctUserDefinedType type = new LUWCatalogDistinctUserDefinedType();
		String sourceName = r.getString(5);
		if (sourceName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
			int length = r.getInt(6);
			if (length ==4) sourceName="REAL"; //$NON-NLS-1$
			else sourceName="DOUBLE"; //$NON-NLS-1$
		}

		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(schema));
		PredefinedDataTypeDefinition sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(sourceName);

		if (sourceTypeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
			final int codePage = r.getInt(8);
			if(codePage == 0) {
				sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
			}
		}
		else if (sourceTypeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
			final int codePage = r.getInt(8);
			if(codePage == 0) {
				if (sourceName.equals("LONG VARCHAR")){
					sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("LONG VARCHAR FOR BIT DATA"); //$NON-NLS-1$
				} else {
					sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
				}
				
				
			}
		}
		
		
		PredefinedDataType sourceType = databaseDefinition.getPredefinedDataType(sourceTypeDefinition);
		if(sourceTypeDefinition.isLengthSupported()) {
			final int length = r.getInt(6);
			EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
			sourceType.eSet(feature, new Integer(length));
		}
		else if(sourceTypeDefinition.isPrecisionSupported()) {
			if (sourceName.equals("TIMESTAMP")) {
				int length = r.getInt(7);
				EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
				sourceType.eSet(feature, new Integer(length));
			}
			else {
				int length = r.getInt(6);
				if (sourceName.equals("DECFLOAT")) {
					if (length == 8) length = 16;
					else length = 34;
				}
				EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
				sourceType.eSet(feature, new Integer(length));
			}
		}
		
		if(sourceTypeDefinition.isScaleSupported()) {
			final int length = r.getInt(7);
			EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
			sourceType.eSet(feature, new Integer(length));
		}
			
		type.setPredefinedRepresentation(sourceType);
		return type;
	}
	
	private static StructuredUserDefinedType constructStructuredUserDefinedType(ResultSet r) throws SQLException {
		StructuredUserDefinedType type = new LUWCatalogStructuredUserDefinedType();
		final String instantiable = r.getString(9);
		if(instantiable.equals("Y")) type.setInstantiable(true); //$NON-NLS-1$
		else  type.setInstantiable(false);

		final String f = r.getString(10);
		if(f.equals("Y")) type.setFinal(true); //$NON-NLS-1$
		else type.setFinal(false);
		
		return type;
	}
	
	private static LUWArrayDataType constructArrayDataType(ResultSet r,Schema schema) throws SQLException {
		LUWArrayDataType arrayType = new LUWCatalogArrayDataType();
		String sourceName = r.getString("SOURCENAME");
		if (sourceName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
			int length = r.getInt("LENGTH");
			if (length ==4) sourceName="REAL"; //$NON-NLS-1$
			else sourceName="DOUBLE"; //$NON-NLS-1$
		}

		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition((schema.getDatabase()));
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		ElementType elmentType = (ElementType) factory.create(SQLDataTypesPackage.eINSTANCE.getElementType());

		PredefinedDataTypeDefinition sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(sourceName);

		if (sourceTypeDefinition != null) {
			if (sourceTypeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
				final int codePage = r.getInt("CODEPAGE");
				if(codePage == 0) {
					sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
				}
			}
			else if (sourceTypeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
				final int codePage = r.getInt("CODEPAGE");
				if(codePage == 0) {
					if (sourceName.equals("LONG VARCHAR")){
						sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("LONG VARCHAR FOR BIT DATA"); //$NON-NLS-1$
					} else {
						sourceTypeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
					}
				}
			}
			
			PredefinedDataType sourceType = databaseDefinition.getPredefinedDataType(sourceTypeDefinition);
			if(sourceTypeDefinition.isLengthSupported()) {
				final int length = r.getInt("LENGTH");
				EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
				sourceType.eSet(feature, new Integer(length));
			}
			else if(sourceTypeDefinition.isPrecisionSupported()) {
				if (sourceName.equals("TIMESTAMP")) {
					int length = r.getInt("SCALE");
					EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
					sourceType.eSet(feature, new Integer(length));
				}
				else {
					int length = r.getInt("LENGTH");
					if (sourceName.equals("DECFLOAT")) {
						if (length == 8) length = 16;
						else length = 34;
					}
					EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
					sourceType.eSet(feature, new Integer(length));
				}
			}
			
			if(sourceTypeDefinition.isScaleSupported()) {
				final int scale = r.getInt("SCALE");
				EStructuralFeature feature = sourceType.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
				sourceType.eSet(feature, new Integer(scale));
			}
			elmentType.setDataType(sourceType);
		}
		else {
				final String typeSchemaName = r.getString("SOURCESCHEMA").trim();
				DataType sourceType = LUWCatalogSchema.getUserDefinedType(schema,typeSchemaName,sourceName);
				elmentType.setDataType(sourceType);
		}
		
		arrayType.setElementType(elmentType);
		
		final int arrayLen = r.getInt("ARRAY_LENGTH");
		arrayType.setMaxCardinality(arrayLen);
		
		return arrayType;
	}
	
	private String getWhereClause() {
		return " WHERE TABSCHEMA='" + LUWUtil.getIdentifier(this.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public Table getTable(String schema, String tableName) {
		Schema s = LUWCatalogSchema.getSchema(this,schema);
		EList tables = s.getTables();
		
		if (s instanceof LUWCatalogSchema) {
			Table table = ((LUWCatalogSchema) s).getCachedTable(tableName);
			if (table != null) return table;
		}

		Iterator it = tables.iterator();
		while(it.hasNext()) {
			Table t = (Table) it.next();
			if(t.getName().equals(tableName)) return t;
		}

		return null;
	}
	
	public DB2Package getDB2Package(String name, String uniqueID) {
		if (!this.packagesLoaded) {
			this.getPackages();
		}
		DB2Package pkg = (DB2Package) this.cachedDB2Packages.get(getDB2PackageHashKey(name, uniqueID));
		return pkg;
	}

	private static Schema getSchema(Schema schema, String schemaName) {
		if(schema.getName().equals(schemaName)) return schema;
		Database d = schema.getDatabase();
		if (d instanceof LUWCatalogDatabase){
			Schema s = ((LUWCatalogDatabase)d).getSchema(schemaName);
			if (s != null) return s; 
		} else {
			Iterator it = d.getSchemas().iterator();
			while(it.hasNext()) {
				Schema s = (Schema) it.next();
				if(s.getName().equals(schemaName)) return s;
			}
		}

		Schema newSchema = new LUWCatalogSchema();
		newSchema.setName(schemaName);
		newSchema.setDatabase(d);

		if (d instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)d).cacheSchema(newSchema);
		}		
		return newSchema;
	}

	private void cacheTable(String name, Table table){
		this.cachedTables.put(name,table);
	}
	
	private Table getCachedTable(String name){
		return (Table)this.cachedTables.get(name);
	}

	private HashMap getCachedTable(){
		return this.cachedTables;
	}

	private void iniCachedTable(){
		this.cachedTables.clear();
	}

	private void cacheDB2Package(String name, String uniqueID, DB2Package pkg) {
		this.cachedDB2Packages.put(getDB2PackageHashKey(name, uniqueID), pkg);
	}
	
	private void iniCachedDb2Package() {
		this.cachedDB2Packages.clear();
	}

	private String getDB2PackageHashKey(String name, String uniqueID) {
		// It is important to quote the name to make the key unique
        name = LUWUtil.getSingleQuotedString(name);
        return (name + uniqueID);
	}

	protected static Object findElement(Object[] list, String name,EClass metaclass){
		Object object = null;
		for (int i = 0; i < list.length; i++){
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name) && sqlObject.eClass() == metaclass && sqlObject instanceof ICatalogObject){
				object = list[i];
				break;
			}
		}
		return object;
	}
	
	private static Object findTable(HashMap cachedElements,Object[] list, String name,EClass metaclass){
		SQLObject sqlObject = (SQLObject)cachedElements.get(name);
		if (sqlObject != null && sqlObject.eClass() == metaclass && sqlObject instanceof ICatalogObject) return sqlObject;
		return LUWCatalogSchema.findElement(list,name,metaclass);
	}
	
	/**
	 * constructs path for cached xsd sources.
	 * the path is unique for connection,db,dbschema
	 * @param connectionInfo 
	 * @param schema
	 * @return the rootpath of where xsd sources for this schema will be cached
	 */
	public static String getXSDSourceCacheRoot(ConnectionInfo connectionInfo, Schema schema){
        StringBuffer rootPath = new StringBuffer(DB2LUWPluginActivator.getInstance().getStateLocation().toOSString()).append(File.separator);
        rootPath.append(connectionInfo.getName()).append(File.separator);
        rootPath.append(ModelHelper.getDatabase(schema).getName()).append(File.separator);
        rootPath.append(schema.getName()).append(File.separator);
        return rootPath.toString();
	}
	
 	/**
 	 * deletes the cached xsd sources recursively starting from the 
 	 * given rootPath.
  	 * Deletes also all empty directories.
 	 * @param rootPath the path to start deleting from
 	 */
	private static void deleteXSDSourceCache(String rootPath){
		File file = new File(rootPath);
		File[] files = files = file.listFiles();
		deleteFiles(files);
	}
	
	/**
	 * deletes all existing xsd sources (with XSD_EXTENSION file extension) and empty directories
	 * for the given array of files.
	 * @param files
	 */
	private static void deleteFiles(File[] files){
		if (files == null) return;
		int length = files.length;
		for (int i=0;i<length;i++){
			File file = files[i];
			if (file.isFile() && file.getName().endsWith(LUWCatalogXmlSchema.XSD_EXTENSION)){
				file.delete();
			} else if(file.isDirectory()){
				deleteFiles(file.listFiles());
				if (file.listFiles().length == 0){
					file.delete();
				}
			}
		}
	}
	
	public void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogSchema.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
		}
		this.eSetDeliver(deliver);
	}

	private static UserDefinedType getUserDefinedType(Schema currentSchema, String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogSchema.getSchema(currentSchema,schemaName);
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

}
