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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogStatistics;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.LUWCatalogIndex.IndexUniqueRule;
//bgp import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.CatalogCache;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWDdlParser;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWCatalogMessages;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableImpl;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.GenerateType;
import org.eclipse.datatools.enablement.ibm.util.IRowCountCache;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogTable extends LUWTableImpl implements IRowCountCache,ICatalogObject,IDatabaseObject {

	public void refresh() {
		this.columnsLoaded = false;
		this.constraintLoaded = false;
		this.indexLoaded = false;
		this.triggerLoaded = false;
		this.partitionKeyLoaded = false;
		
		this.dataPartitionLoaded = false;
		this.tablespaceLoaded = false;

		this.privilegeLoaded = false;
		this.rowCountLoaded = false;

		this.reset();
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

	public synchronized EList getConstraints() {
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
			if(!this.indexLoaded) this.loadIndexes();
			return this.index;
		}
	}

	public EList getTriggers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getTriggers();
		} else {
			if(!this.triggerLoaded) this.loadTriggers();
			return this.triggers;
		}
	}
	
	public LUWPartitionKey getPartitionKey() {
		if (!this.partitionKeyLoaded) this.loadPartitionKey();
		return this.partitionKey;
	}

	public LUWTableSpace getRegularDataTableSpace(){
		if(!this.tablespaceLoaded) this.loadTablespace();
		return this.regularDataTableSpace;
	}

	public LUWTableSpace getIndexDataTableSpace(){
		if(!this.tablespaceLoaded) this.loadTablespace();
		return this.indexDataTableSpace;
	}

	public LUWTableSpace getLOBDataTableSpace(){
		if(!this.tablespaceLoaded) this.loadTablespace();
		return this.lobDataTableSpace;
	}
	
	public EList getDataPartitions(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDataPartitions();
		} else {
			if (!this.dataPartitionLoaded) this.loadDataPartitions();
			return this.dataPartitions;
		}
	}
	
	public LUWDataPartitionKey getDataPartitionKey() {
		if (!this.dataPartitionLoaded) this.loadDataPartitions();
		return this.dataPartitionKey;
	}
	
	/**
	 * This override includes the TableSpaces used in Data Partitions
	 * in the associated Table Spaces list
	 */
	@Override
	public List getTableSpaces() {
		Vector tableSpaces = new Vector();
		tableSpaces.addAll(super.getTableSpaces());   //General table spaces are added in to list with this method ( calls LUWTableImpl.getTableSpaces() )
		if (this.getDataPartitions().size() != 0){	// For DB2 version <9, DataPartitions size will be 0
			Iterator it = this.getDataPartitions().iterator();
			while(it.hasNext()){
				LUWDataPartition dataPartition = (LUWDataPartition)it.next();
				if(!tableSpaces.contains(dataPartition.getRegularDataTableSpace()))
					tableSpaces.add(dataPartition.getRegularDataTableSpace());
				if(!tableSpaces.contains(dataPartition.getLOBDataTableSpace()))
					tableSpaces.add(dataPartition.getLOBDataTableSpace());
			}
		}
		return tableSpaces;
	}

	
	public EList getPrivileges() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getPrivileges();
		} else {
			if (!this.privilegeLoaded) this.loadPrivileges();
			return this.privileges;
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
		if(id == LUWPackage.LUW_TABLE__COLUMNS) {
			this.getColumns();
		}
		else if(id == LUWPackage.LUW_TABLE__CONSTRAINTS) {
			this.getConstraints();
		}
		else if(id == LUWPackage.LUW_TABLE__INDEX) {
			this.getIndex();
		}
		else if(id == LUWPackage.LUW_TABLE__TRIGGERS) {
			this.getTriggers();
		}
		else if(id == LUWPackage.LUW_TABLE__PARTITION_KEY) {
			this.getPartitionKey();
		}
		else if(id == LUWPackage.LUW_TABLE__REGULAR_DATA_TABLE_SPACE) {
			this.getRegularDataTableSpace();
		}
		else if(id == LUWPackage.LUW_TABLE__INDEX_DATA_TABLE_SPACE) {
			this.getIndexDataTableSpace();
		}
		else if(id == LUWPackage.LUW_TABLE__LOB_DATA_TABLE_SPACE) {
			this.getLOBDataTableSpace();
		}
		else if(id == LUWPackage.LUW_TABLE__DATA_PARTITIONS) {
			this.getDataPartitions();
		}
		else if(id == LUWPackage.LUW_TABLE__DATA_PARTITION_KEY) {
			this.getDataPartitionKey();
		}
		else if(id == LUWPackage.LUW_TABLE__PRIVILEGES) {
			this.getPrivileges();
		}
		
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadColumns() {
		if(this.columnsLoaded) return;
		this.columnsLoaded = true;

		EList columns = super.getColumns();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			LUWCatalogTable.loadColumns(this.getConnection(), columns, this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.cacheColumn(columns);
		this.eSetDeliver(deliver);
	}

	private synchronized void loadConstraints() {
		if(this.constraintLoaded) return;
		this.constraintLoaded = true;

		EList constraintList = super.getConstraints();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadConstraints(this.getConnection(), constraintList, this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		LUWCatalogDatabase database = (LUWCatalogDatabase)getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() && cache.isLoaded( CatalogCache.CONSTRAINTS ) ) {
//			Collection<Constraint> constraints = cache.getConstraints( this );
//			
//			constraintList.clear();
//			constraintList.addAll( constraints );
//bgp>		}

		this.eSetDeliver(deliver);
	}

	private synchronized void loadIndexes() {
		if(this.indexLoaded) return;
		this.indexLoaded = true;

		EList indexes = super.getIndex();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadIndexes(this.getConnection(), indexes, this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		LUWCatalogDatabase database = (LUWCatalogDatabase)getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() && cache.isLoaded( CatalogCache.INDEXES ) ) {
//			Collection<Index> idxs = cache.getIndexes( this );
//			
//			indexes.clear();
//			
//			for ( Index index : idxs ) {
//				DB2IndexType indexType = ((LUWCatalogIndex)index).getIndexType();
//				switch (indexType.getValue()) {
//					case DB2IndexType.BLOCK:
//					case DB2IndexType.DIMENSION:
//						indexes.add(0,index);
//						break;
//					default:
//						indexes.add(index);
//				}
//			}
//bgp>		}

		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadTriggers() {
		if(this.triggerLoaded) return;
		this.triggerLoaded = true;

		EList triggerList = super.getTriggers();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadTriggers(this.getConnection(), triggerList, this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		LUWCatalogDatabase database = (LUWCatalogDatabase)getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() && cache.isLoaded( CatalogCache.TRIGGERS ) ) {
//			Collection<Trigger> triggers = cache.getTriggers( this );
//
//			triggerList.clear();
//			triggerList.addAll( triggers );
//bgp>		}

		this.eSetDeliver(deliver);
	}

	private synchronized void loadPartitionKey() {
		if(this.partitionKeyLoaded) return;
		this.partitionKeyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadPartitionKey(this.getConnection(), this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadTablespace() {
		if(this.tablespaceLoaded) return;
		this.tablespaceLoaded = true;
		
		Database database = getCatalogDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	

		try {
//bgp			if ( !cache.isBatchLoading() &&
			if (this.tbspaceName != null || this.indexTbspaceName != null || this.longTbspaceName != null) {
				this.setAssociatedTablespace();
			} else {
				LUWCatalogTable.loadTablespace(this.getConnection(), this);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadDataPartitions() {
		if(this.dataPartitionLoaded) return;
		this.dataPartitionLoaded = true;

		super.getDataPartitions().clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadDataPartitions(this.getConnection(), this);
		}
		catch (Exception e) {
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
			LUWCatalogTable.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	/** Class for caching column information for one table */
	private static class TableColumnInfo {
		/** The table's schema */
		String schemaName;
		/** The name of the table */
		String tableName;
		/** Info about each column in the table */
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();

		/**
		 * Get the key for cache lookup
		 * 
		 * @return The key
		 */
		public String getKey() {
			return this.schemaName + "." + this.tableName; //$NON-NLS-1$
		}

		/**
		 * Update a table with its columns using information found here
		 * 
		 * @param table 
		 * @param columnList 
		 */
		public void setColumnInfoIn( Table table, List<Column> columnList ) {
			Database database = table.getSchema().getDatabase();
			final DatabaseDefinition databaseDefinition = 
				RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

			Object[] list = columnList.toArray();
			columnList.clear();

			for ( ColumnInfo info : this.columns ) {
		        Object element = LUWCatalogTable.findElement(
		        		list, info.columnName, LUWPackage.eINSTANCE.getLUWColumn() );
				
		        LUWCatalogColumn column;
				
				if ( element != null ) {
					column = (LUWCatalogColumn)element;
					column.refresh();
				} else {
					column = new LUWCatalogColumn();
				}	
	
				column.setName( info.columnName );					
				column.setDescription( info.remarks );
	
				if ( info.generated.equals("A") ) { //$NON-NLS-1$
					column.setGenerationType( GenerateType.ALWAYS_LITERAL );
					column.setGenerated( true );
				}
				else if ( info.generated.equals("D") ) { //$NON-NLS-1$
					column.setGenerationType( GenerateType.BY_DEFAULT_LITERAL );
					column.setGenerated( true );
				}
					
				if ( info.isIdentity.equals("Y") ) { //$NON-NLS-1$
					DB2IdentitySpecifier identitySpecifier = (DB2IdentitySpecifier)factory.create(
							DB2ModelPackage.eINSTANCE.getDB2IdentitySpecifier() );
					column.setIdentitySpecifier( identitySpecifier );
				}
				else { 
					column.setIdentitySpecifier( null );
				}
				
				column.setNullable( info.nulls.equals("Y") ); //$NON-NLS-1$
	
				if ( info.typeName.equalsIgnoreCase( "FLOAT" ) ) { //$NON-NLS-1$
					if ( info.length == 4 ) {
						info.typeName = "REAL"; //$NON-NLS-1$
					}
					else {
						info.typeName = "DOUBLE"; //$NON-NLS-1$
					}
				}
				
				PredefinedDataTypeDefinition typeDefinition =
					databaseDefinition.getPredefinedDataTypeDefinition( info.typeName );
					
				if ( typeDefinition != null ) {
					if ( typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER ) {
						if ( info.codePage == 0 ) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(
									"CHAR () FOR BIT DATA" ); //$NON-NLS-1$
						}
					}
					else if ( typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING ) {
						if ( info.codePage == 0 ) {
							if ( info.typeName.equals( "LONG VARCHAR" ) ) { //$NON-NLS-1$
								typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(
										"LONG VARCHAR FOR BIT DATA" ); //$NON-NLS-1$
							}
							else {
								typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(
										"VARCHAR () FOR BIT DATA" ); //$NON-NLS-1$
							}
						}
					}
	
					PredefinedDataType type = databaseDefinition.getPredefinedDataType(typeDefinition);
	
					if ( typeDefinition.isLengthSupported() ) {
						EStructuralFeature feature = type.eClass().getEStructuralFeature( "length" ); //$NON-NLS-1$
						type.eSet( feature, new Integer( info.length ) );
					}
					else if ( typeDefinition.isPrecisionSupported() ) {
						if ( info.typeName.equals("TIMESTAMP") ) { //$NON-NLS-1$
							EStructuralFeature feature = type.eClass().getEStructuralFeature( "fractionalSecondsPrecision"); //$NON-NLS-1$
							type.eSet( feature, new Integer( info.scale ) );
						}
						else {
							if ( info.typeName.equals("DECFLOAT") ) { //$NON-NLS-1$
								if ( info.length == 8 ) {
									info.length = 16;
								}
								else {
									info.length = 34;
								}
							}
	
							EStructuralFeature feature = type.eClass().getEStructuralFeature( "precision" ); //$NON-NLS-1$
							type.eSet( feature, new Integer( info.length ) );
						}
					}
					
					if ( typeDefinition.isScaleSupported() ) {
						EStructuralFeature feature = type.eClass().getEStructuralFeature( "scale" ); //$NON-NLS-1$
						type.eSet( feature, new Integer( info.scale ) );
					}
					
					column.setContainedType(type);
				}
				else {
					if ( info.typeName.equals( "REFERENCE" ) ) { //$NON-NLS-1$
						column.setReferencedType( LUWCatalogTable.getUserDefinedType(
								table, info.targetTypeSchemaName, info.targetUdtName ) );
					}
					else {
						column.setReferencedType( LUWCatalogTable.getUserDefinedType(
								table, info.typeSchemaName, info.typeName ) );
					}
				}
					
				if ( info.generated.equals( " " ) ) { //$NON-NLS-1$
					column.setDefaultValue( info.dflt );
				}
				
				column.setLobLogged( !info.logged.equals("N") ); //$NON-NLS-1$
				column.setLobCompacted( info.compact.equals("Y") ); //$NON-NLS-1$
				
				// Check for row change timestamp
		        if ( info.rowChangeTimestamp.equals("Y") ) { //$NON-NLS-1$
					column.setRowChangeTimestamp( true );	
				}
	
		        columnList.add(column);
			}
		}
	}

	/** Class to cache details about a column */
	private static class ColumnInfo {
		String columnName;
		String remarks;
		String generated;
		String isIdentity;
		String nulls;
		String typeSchemaName;
		String typeName;
		int length;
		int scale;
		int codePage;
		String dflt;
		String targetTypeSchemaName;
		String targetUdtName;
		String logged;
		String compact;
		String rowChangeTimestamp = "";
	}

//<bgp	/**
//	 * Update a table with cached column information
//	 * 
//	 * @param cache
//	 * @param table
//	 * @param columnList
//	 */
//	private static void loadColumnsFromCache( CatalogCache cache, Table table, String key, List<Column> columnList ) {
//		TableColumnInfo tinfo = (TableColumnInfo)cache.getCachedProperty( PROP_TABLE_COLUMNS, key );
//		
//		if ( tinfo != null ) {
//			tinfo.setColumnInfoIn( table, columnList );
//		}
//bgp>	}

	/**
	 * Load the columns for a table
	 * 
	 * @param connection
	 * @param columnList
	 * @param table
	 * @throws SQLException
	 */
	public static void loadColumns(Connection connection, EList columnList, Table table) throws SQLException {
		final Schema schema = table.getSchema();
		final Database database = ModelHelper.getDatabase(schema);
//bgp		CatalogCache cache = CatalogCache.getCache( database );
		final String propkey = schema.getName() + "." + table.getName(); //$NON-NLS-1$
		
//<bgp		// N.B. for large numbers of tables/columns, caching the columns uses too much memory
//		// So at least for now, this caching is disabled
//bgp>		boolean caching = false && cache.isBatchLoading();
		
//<bgp		if ( caching ) {
//			if ( cache.isPropertyCacheLoaded( PROP_TABLE_COLUMNS ) ) {
//				loadColumnsFromCache( cache, table, propkey, columnList );
//				return;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_TABLE_COLUMNS );
//bgp>		}

		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
        String version = databaseDefinition.getVersion();
        float ver = 9.0f;
        try {
            ver = Float.parseFloat(version.substring(1));
        }
        catch (NumberFormatException e) {
        	// Ignore
        }
        
        String query = "SELECT COLNAME, REMARKS, GENERATED, IDENTITY, NULLS," //$NON-NLS-1$
			+ " TYPESCHEMA, TYPENAME, LENGTH, SCALE, CODEPAGE, DEFAULT, TARGET_TYPESCHEMA, TARGET_TYPENAME,"  //$NON-NLS-1$
			+ " LOGGED, COMPACT,"; //$NON-NLS-1$

        if (ver >= 9.5f) {
			query +=  " ROWCHANGETIMESTAMP,"; //$NON-NLS-1$
        }        	

		query += " TABSCHEMA, TABNAME" //$NON-NLS-1$
			+ " FROM SYSCAT.COLUMNS"; //$NON-NLS-1$

//bgp		if ( !caching ) {
			query += " WHERE TABSCHEMA='" +LUWUtil.getIdentifier(table.getSchema().getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
				+ " AND TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
//bgp		}

		query += " ORDER BY TABSCHEMA, TABNAME, COLNO"; //$NON-NLS-1$
		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		TableColumnInfo tinfo = null;

		try {
			while(r.next()) {
				final String schemaName = r.getString( "TABSCHEMA" ).trim(); //$NON-NLS-1$
				final String tableName = r.getString( "TABNAME" ).trim(); //$NON-NLS-1$

				if ( tinfo == null || !tinfo.schemaName.equals( schemaName )
						|| !tinfo.tableName.equals( tableName )) {
					tinfo = new TableColumnInfo();
					
					tinfo.schemaName = schemaName;
					tinfo.tableName = tableName;
					
//<bgp					if ( caching ) {
//						String key = tinfo.getKey();
//						cache.cacheProperty( PROP_TABLE_COLUMNS, key, tinfo );
//bgp>					}
				}

				ColumnInfo info = new ColumnInfo();
				info.columnName = r.getString(1);
				info.remarks = r.getString(2);
				info.generated = r.getString(3);
				info.isIdentity = r.getString(4);
				info.nulls = r.getString(5);
				info.typeSchemaName = r.getString(6).trim();
				info.typeName = r.getString(7);
				info.length = r.getInt(8);
				info.scale = r.getInt(9);
				info.codePage = r.getInt(10);
				info.dflt = r.getString(11);
				String str = r.getString("TARGET_TYPESCHEMA"); //$NON-NLS-1$
				if ( str != null ) {
					info.targetTypeSchemaName = str.trim();
				}
				info.targetUdtName = r.getString("TARGET_TYPENAME"); //$NON-NLS-1$
				info.logged = r.getString("LOGGED"); //$NON-NLS-1$
				info.compact = r.getString("COMPACT"); //$NON-NLS-1$

				if (ver >= 9.5f) {
					info.rowChangeTimestamp = r.getString( "ROWCHANGETIMESTAMP" ); //$NON-NLS-1$
		        }
				
				tinfo.columns.add( info );
			}
			
//bgp			if ( tinfo != null && !caching ) {
	         if ( tinfo != null) {
				tinfo.setColumnInfoIn( table, columnList );
			}
		}
		catch(Exception e) {
			 e.printStackTrace();
		}
		
		r.close();
		s.close();
		
//<bgp		if ( caching ) {
//			loadColumnsFromCache( cache, table, propkey, columnList );
//bgp>		}
	}

	public static void loadProperties(Connection connection, Table table) throws SQLException {

		final String schemaname = table.getSchema().getName();
		final String tablename = table.getName();
		String key = schemaname + "." + tablename; //$NON-NLS-1$
		LUWCatalogDatabase database = (LUWCatalogDatabase)table.getSchema().getDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isPropertyCacheLoaded( PROP_TABLE_PROPERTIES ) ) {
//				Object propobj = cache.getCachedProperty( PROP_TABLE_PROPERTIES, key );
//
//				if (propobj != null) {
//					table.setDescription( (String)propobj );
//				}
//
//				return;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_TABLE_PROPERTIES );
//bgp>		}
		
		String query = "SELECT TABSCHEMA, TABNAME, REMARKS FROM SYSCAT.TABLES"; //$NON-NLS-1$

//bgp		if ( !cache.isBatchLoading() ) {
			query += " WHERE TABSCHEMA='" //$NON-NLS-1$
					+ LUWUtil.getIdentifier( schemaname )
					+ "' AND TABNAME='" //$NON-NLS-1$
					+ LUWUtil.getIdentifier( tablename ) + "'"; //$NON-NLS-1$	
//bgp		}

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				final String sname = r.getString( "TABSCHEMA" ).trim(); //$NON-NLS-1$
				final String tname = r.getString( "TABNAME" ).trim(); //$NON-NLS-1$

//<bgp				if ( cache.isBatchLoading()
//						&& null == cache.findTable( sname, tname, null )) {
//					// The container is filtered out, so go on
//					continue;
//bgp>				}

				final String description = r.getString( "REMARKS" ); //$NON-NLS-1$

//<bgp				if ( cache.isBatchLoading() ) {
//					key = sname + "." + tname; //$NON-NLS-1$
//					cache.cacheProperty(
//							PROP_TABLE_PROPERTIES, key, description );
//					
//					if ( sname.equals( schemaname ) && tname.equals( tablename ))
//					{
//						table.setDescription( description );
//					}
//				}
//bgp>				else {
					table.setDescription( description );
//bgp				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		r.close();
		s.close();
	}

	
	public static void loadTriggers(Connection connection, EList triggerList, Table table, int options) throws SQLException {
		final Schema schema = table.getSchema();
		LUWCatalogDatabase database  = (LUWCatalogDatabase)ModelHelper.getDatabase(schema);
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() ) {
//			if (cache.isLoaded( CatalogCache.TRIGGERS ) ) {
//				return;
//			}
//			
//			cache.setLoaded( CatalogCache.TRIGGERS );
//bgp>		}

		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		
		Object[] list = triggerList.toArray();
		triggerList.clear();

		if ((options & DatabaseREProvider.EXCLUDE_TRIGGERS)== DatabaseREProvider.EXCLUDE_TRIGGERS) return;

		String query = "SELECT TRIGSCHEMA, TRIGNAME, REMARKS, TRIGTIME, TRIGEVENT, GRANULARITY, TEXT,"
				+ " TABSCHEMA, TABNAME"
				+ " FROM SYSCAT.TRIGGERS"; //$NON-NLS-1$
		
//bgp		if ( !cache.isBatchLoading() ) {
			query +=
				" WHERE TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) //$NON-NLS-1$
				+ "' AND TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$ 
//bgp		}

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery( query );

		try{
			while(r.next()) {
				final String schemaName = r.getString( "TABSCHEMA" ).trim(); //$NON-NLS-1$
				final String tableName = r.getString( "TABNAME" ).trim(); //$NON-NLS-1$

//<bgp				if ( cache.isBatchLoading()
//						&& null == cache.findTable( schemaName, tableName, null )) {
//					// The container is filtered out, go on
//					continue;
//bgp>				}
				
				LUWCatalogTrigger trigger;
				final String trigName = r.getString(2);

				EClass metaclass= SQLTablesPackage.eINSTANCE.getTrigger();

				Object element = LUWCatalogTable.findElement(list, trigName,metaclass);
				
				if (element != null){
					trigger = (LUWCatalogTrigger) element;
					triggerList.add(trigger);
					((ICatalogObject)element).refresh();
				} else {
					trigger = new LUWCatalogTrigger();
					trigger.setName(trigName);

//<bgp					if ( cache.isBatchLoading() ) {
//						cache.cacheTrigger(
//								schemaName, tableName, trigName, trigger );
//					}
//bgp>					else {
						triggerList.add(trigger);
//bgp					}
				}

				String text = r.getString(7);
				if (text != null) {
					//remove un-readable character
					text = text.replaceAll("[\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u000b\u000c\u000e\u000f" +
	               		"\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f]", " ");
				}
				final String trigSchema = r.getString(1).trim();
				trigger.setSchema(LUWCatalogTable.getSchema(table, trigSchema));

				final String remarks = r.getString(3);
				trigger.setDescription(remarks);

				final String trigTime = r.getString(4);
				if(trigTime.equals("A")) trigger.setActionTime(ActionTimeType.AFTER_LITERAL); //$NON-NLS-1$
				else if(trigTime.equals("B")) trigger.setActionTime(ActionTimeType.BEFORE_LITERAL); //$NON-NLS-1$
				else if(trigTime.equals("I")) trigger.setActionTime(ActionTimeType.INSTEADOF_LITERAL); //$NON-NLS-1$
				else continue;
				
				final String trigEvent = r.getString(5);
				if(trigEvent.equals("I")) trigger.setInsertType(true); //$NON-NLS-1$
				else if(trigEvent.equals("D")) trigger.setDeleteType(true); //$NON-NLS-1$
				else if(trigEvent.equals("U")) trigger.setUpdateType(true); //$NON-NLS-1$
				else continue;

				final String granularity = r.getString(6);
				if(granularity.equals("S")) trigger.setActionGranularity(ActionGranularityType.STATEMENT_LITERAL); //$NON-NLS-1$
				else if(granularity.equals("R")) trigger.setActionGranularity(ActionGranularityType.ROW_LITERAL); //$NON-NLS-1$
				else continue;

				
				LUWDdlParser ddlParser = new LUWDdlParser(RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(table.getSchema().getDatabase()));
				ddlParser.parseTrigger(trigger,text);
			}
		}
		catch (Exception e) {
			 e.printStackTrace();
		}

		r.close();
		s.close();
	}
	
	public static void loadIndexes(Connection connection, EList indexList, Table table, int options) throws SQLException {
		LUWCatalogDatabase database  = (LUWCatalogDatabase)table.getSchema().getDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isLoaded( CatalogCache.INDEXES ) ) {
//				return;
//			}
//			
//			cache.setLoaded( CatalogCache.INDEXES );
//bgp>		}

		Object[] list = indexList.toArray();
		indexList.clear();
		
		if (database.isBatchLoad()) return;

		if ((options & DatabaseREProvider.EXCLUDE_INDEXES)== DatabaseREProvider.EXCLUDE_INDEXES) return;

		final String sname = table.getSchema().getName();
		final String tname = table.getName();
			
		String q = "SELECT INDEXTYPE, INDSCHEMA, INDNAME, REMARKS, UNIQUERULE, PCTFREE,SYSTEM_REQUIRED,USER_DEFINED," //$NON-NLS-1$
				+ " TABSCHEMA, TABNAME" //$NON-NLS-1$
				+ " FROM SYSCAT.INDEXES"; //$NON-NLS-1$
		
//bgp		if ( !cache.isBatchLoading() ) {
			q += " WHERE TABSCHEMA='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(sname)
				+ "' AND TABNAME='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(tname)
				+ "'"; //$NON-NLS-1$
//bgp		}

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery( q );

		try {
			while(r.next()) {
				final String tableSchemaName = r.getString( "TABSCHEMA" ).trim(); //$NON-NLS-1$
				final String tableName = r.getString( "TABNAME" ).trim(); //$NON-NLS-1$
				final String indSchema = r.getString(2).trim();

//<bgp				if ( cache.isBatchLoading()
//						&& null == cache.findTable( tableSchemaName, tableName, null ) ) {
//					// The index's table is filtered out, so we omit the
//					// index as well
//					continue;
//bgp>				}

				Schema schema = LUWCatalogTable.getSchema(table, indSchema);

				final String indName = r.getString(3);

				Index index = null;
				Object element = LUWCatalogTable.findElement(list,indName,SQLConstraintsPackage.eINSTANCE.getIndex());
				
				if (element != null){
					index = (Index) element;
					((ICatalogObject)element).refresh();
				} else {
					final String type = r.getString(1).trim();
					if(type.equals("REG")) { //$NON-NLS-1$
						index = new LUWCatalogIndex();
					}
					else if(type.equals("CLUS")) { //$NON-NLS-1$
						index = new LUWCatalogIndex();
						index.setClustered(true);
					}
					else if(type.equals("DIM")) { //$NON-NLS-1$
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
					
					index.setSchema(schema);
					index.setName(indName);
				}
				
				
				final String remarks = r.getString(4);
				index.setDescription(remarks);
				
				final String uniqueRule = r.getString(5);
				if(!uniqueRule.equals("D")) index.setUnique(true); //$NON-NLS-1$
				if ("P".equalsIgnoreCase(uniqueRule))  ((LUWCatalogIndex)index).setIndexUniqueRule(IndexUniqueRule.PRIMARYKEY);
				
				
				final int pctfree = r.getInt(6);
				if(pctfree == -1) index.setFillFactor(10);
				else index.setFillFactor(pctfree);
				
				
				final int userDefined = r.getInt("USER_DEFINED");
				if (userDefined == 0) {
					index.setSystemGenerated(true);
				}
				else {
					index.setSystemGenerated(false);
				}
				
				
				final int systemRequred = r.getInt("SYSTEM_REQUIRED");
				if (index instanceof LUWIndex) {
					if (systemRequred != 0) {
						((LUWIndex)index).setSystemRequired(true);
					} else {
						((LUWIndex)index).setSystemRequired(false);
					}
				}
				
//<bgp				if ( cache.isBatchLoading() ) {
//					cache.cacheIndex( tableSchemaName, tableName, index.getSchema().getName(), indName, index );
//				}
//bgp>				else {
					DB2IndexType indexType = ((LUWCatalogIndex)index).getIndexType();
					switch (indexType.getValue()) {
						case DB2IndexType.BLOCK:
						case DB2IndexType.DIMENSION:
							indexList.add(0,index);
							break;
						default:
							indexList.add(index);
//bgp					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
	}
	
	public static void loadConstraints(Connection connection, EList constraintList, Table table) throws SQLException {
		Database database  = table.getSchema().getDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isLoaded( CatalogCache.CONSTRAINTS ) ) {
//				return;
//			}
//			
//			cache.setLoaded( CatalogCache.CONSTRAINTS );
//bgp>		}

		Object[] list = constraintList.toArray();
		constraintList.clear();

		String query = "SELECT DISTINCT A.CONSTNAME, A.REMARKS, A.TYPE, A.ENFORCED, B.TYPE CHECKTYPE," //$NON-NLS-1$
				+ "  A.TABSCHEMA, A.TABNAME" //$NON-NLS-1$ 
				+ " FROM SYSCAT.TABCONST A LEFT OUTER JOIN SYSCAT.CHECKS B "  //$NON-NLS-1$
				+ " ON A.CONSTNAME = B.CONSTNAME"; //$NON-NLS-1$

//bgp		if ( !cache.isBatchLoading() ) {
			query +=
				" WHERE A.TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) //$NON-NLS-1$
				+ "' AND A.TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$ 
//bgp		}

		query += " ORDER BY TYPE DESC"; //$NON-NLS-1$

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				final String schemaName = r.getString( "TABSCHEMA" ).trim(); //$NON-NLS-1$
				final String tableName = r.getString( "TABNAME" ).trim(); //$NON-NLS-1$

//<bgp				if ( cache.isBatchLoading() ) {
//					if ( null == cache.findTable( schemaName, tableName, null ) ) {
//						// The container is filtered out, so go on
//						continue;
//					}
//bgp>				}

				final String constName = r.getString(1);

				Constraint constraint = null;

				final String type = r.getString(3);
				EClass metaclass=null;
				if (type.equals("K")){ //$NON-NLS-1$
					metaclass = SQLConstraintsPackage.eINSTANCE.getCheckConstraint();
				} else if (type.equals("P")){ //$NON-NLS-1$
					metaclass = SQLConstraintsPackage.eINSTANCE.getPrimaryKey();
				}else if(type.equals("U")) { //$NON-NLS-1$
					metaclass = SQLConstraintsPackage.eINSTANCE.getUniqueConstraint();
				}else if(type.equals("F")) { //$NON-NLS-1$
					metaclass = SQLConstraintsPackage.eINSTANCE.getForeignKey();
				}
					
				
				Object sqlElement = LUWCatalogTable.findElement(list,constName,metaclass);
				if (sqlElement != null) {
					constraint = (Constraint)sqlElement;
					((ICatalogObject)constraint).refresh();
				} else {
					if(type.equals("K")) { //$NON-NLS-1$
						final String checktype = r.getString("CHECKTYPE"); //$NON-NLS-1$
						if (checktype != null && checktype.equals("C")) //$NON-NLS-1$
							constraint = new LUWCatalogCheckConstraint();
						else
							continue;
					}
					else if(type.equals("P")) { //$NON-NLS-1$
						constraint = new LUWCatalogPrimaryKey();					
					}
					else if(type.equals("U")) { //$NON-NLS-1$
						constraint = new LUWCatalogUniqueConstraint();
					}
					else if(type.equals("F")) { //$NON-NLS-1$
						constraint = new LUWCatalogForeignKey();
					}
					else {
						continue;
					}
					
					constraint.setName(constName);
				}
				
				final String remarks = r.getString(2);
				constraint.setDescription(remarks);

				final String enforced = r.getString(4);
				if (enforced.equals("Y")){ //$NON-NLS-1$
					constraint.setEnforced(true);
				} else {
					constraint.setEnforced(false);
				}
				
//<bgp				if ( cache.isBatchLoading() ) {
//					cache.cacheConstraint(
//							schemaName, tableName, constName, constraint );
//				}
//bgp>				else {
					constraintList.add(constraint);
//bgp				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
	}

	public static void loadPartitionKey(Connection connection, LUWStorageTable table) throws SQLException {
		try {
			StorageProvider provider = LUWCatalogDatabase.getCatalogStorageProvider();
			if (provider != null){
				LUWPartitionKey partitionKey = provider.getPartitionKey(table);
				table.setPartitionKey(partitionKey);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/** Information about a table's tablespaces for caching */
	private static class TablespaceInfo {
		/** The table's schema */
		String schemaName;
		/** The table's name */
		String tableName;
		/** The tablespace used for regular data */
		String tbspaceName;
		/** The tablespace used for indexes */
		String indexTbspaceName;
		/** The tablespace used for LOBs */
		String longTbspaceName;

		/**
		 * Get the key for cache lookup
		 * 
		 * @return The key
		 */
		public String getKey() {
			return this.schemaName + "." + this.tableName;
		}

		/**
		 * Update a table with information from here (actually update the
		 * tablespaces with this table)
		 * 
		 * @param table
		 */
	    public void setInfoInTable( Table table ) {
//bgp		public void setInfoInTable( CatalogCache cache, Table table ) {
			if ( this.tbspaceName != null ) {
				LUWTableSpace tbSpace = LUWCatalogTable.getTablespace( table, this.tbspaceName );
				if ( tbSpace != null ) {
					tbSpace.getRegularDataTables().add( table );
				}
			}
			
			if ( this.indexTbspaceName != null ) {
				LUWTableSpace tbSpace = LUWCatalogTable.getTablespace( table, this.indexTbspaceName );
				if ( tbSpace != null ) {
					tbSpace.getIndexDataTables().add( table );
				}
			}

			if ( this.longTbspaceName != null ) {
				LUWTableSpace tbSpace = LUWCatalogTable.getTablespace( table, this.longTbspaceName );
				if ( tbSpace != null ) {
					tbSpace.getLOBDataTables().add( table );
				}
			}
		}
	}

//<bgp	/**
//	 * Update a table with cached tablespace info
//	 * 
//	 * @param cache
//	 * @param table
//	 * @param key
//	 */
//	private static void loadTablespaceFromCache(
//			CatalogCache cache, Table table, String key ) {
//		TablespaceInfo info = (TablespaceInfo)cache.getCachedProperty( PROP_TABLE_TABLESPACE, key );
//		
//		if ( info != null ) {
//			info.setInfoInTable( cache, table );
//		}
//bgp>	}

	/**
	 * Load the tablespaces used for a table's data/indexes
	 * 
	 * @param connection
	 * @param table
	 * @throws SQLException
	 */
	public static void loadTablespace(Connection connection, Table table) throws SQLException {
		if (!(table instanceof LUWStorageTable)) return;

		Table luwtable = (Table)table;
		Database database = luwtable.getSchema().getDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );
		String schemaName = LUWUtil.getIdentifier( luwtable.getSchema().getName() );
		String tableName = LUWUtil.getIdentifier( luwtable.getName() );
		String propkey = schemaName + "." + tableName; //$NON-NLS-1$

//bgp		boolean caching = cache.isBatchLoading();
		
//<bgp		if ( caching ) {
//			if ( cache.isPropertyCacheLoaded( PROP_TABLE_TABLESPACE ) ) {
//				loadTablespaceFromCache( cache, luwtable, propkey );
//				return;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_TABLE_TABLESPACE );
//bgp>		}

		String query = "SELECT TBSPACE, INDEX_TBSPACE,LONG_TBSPACE, TABSCHEMA, TABNAME" + //$NON-NLS-1$
					" FROM SYSCAT.TABLES"; //$NON-NLS-1$

//bgp		if ( !caching ) {
			query += " WHERE TABSCHEMA='" + schemaName + "'" + //$NON-NLS-1$ //$NON-NLS-2$
						" AND TABNAME='" + tableName + "'"; //$NON-NLS-1$ //$NON-NLS-2$
//bgp		}

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				final String tbspaceName = r.getString("TBSPACE"); //$NON-NLS-1$
				final String indexTbspaceName = r.getString("INDEX_TBSPACE"); //$NON-NLS-1$
				final String longTbspaceName = r.getString("LONG_TBSPACE"); //$NON-NLS-1$
				
				if ( tbspaceName == null && indexTbspaceName == null && longTbspaceName == null ) {
					continue;
				}

				TablespaceInfo info = new TablespaceInfo();
				info.tbspaceName = tbspaceName;
				info.indexTbspaceName = indexTbspaceName;
				info.longTbspaceName = longTbspaceName;
				
//bgp				if ( !caching ) {
//				info.setInfoInTable( cache, luwtable );
					info.setInfoInTable(luwtable );
//<bgp				}
//				else {
//					info.schemaName = r.getString( "TABSCHEMA" ).trim(); //$NON-NLS-1$
//					info.tableName = r.getString( "TABNAME" ).trim(); //$NON-NLS-1$
//
//					cache.cacheProperty( PROP_TABLE_TABLESPACE, info.getKey(), info );
//bgp>				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		r.close();
		s.close();
		
//<bgp		if ( caching ) {
//			loadTablespaceFromCache( cache, luwtable, propkey );
//bgp>		}
	}
	
	public static void loadDataPartitions(Connection connection, LUWStorageTable table) throws SQLException {
		try {
			EList partList = table.getDataPartitions();
			StorageProvider provider = LUWCatalogDatabase.getCatalogStorageProvider();
			if (provider != null){
				Iterator it = provider.getDataPartition(table).iterator();
				while(it.hasNext()) {
					LUWDataPartition part = (LUWDataPartition) it.next();
					partList.add(part);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public static void loadPrivileges(Connection connection, EList privilegeList,Table table, String granteeFilter) throws SQLException {

		final Schema schema = table.getSchema();
		final Database database = ModelHelper.getDatabase(schema);
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
					",CONTROLAUTH,ALTERAUTH,DELETEAUTH,INDEXAUTH,INSERTAUTH,SELECTAUTH" +
					",REFAUTH,UPDATEAUTH" +
					" FROM SYSCAT.TABAUTH" + 
					" WHERE GRANTOR <> GRANTEE " +
					" AND TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
					" AND TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'";
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
				boolean isSystemGranted = granteeId.equalsIgnoreCase(userName);
				final String controlAuth = r.getString("CONTROLAUTH");
				if ("Y".equals(controlAuth)) {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CONTROL);
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				
				final String alterAuth = r.getString("ALTERAUTH");
				if ("N".equals(alterAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_ALTER);
					if ("G".equals(alterAuth)) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				final String deleteAuth = r.getString("DELETEAUTH");
				if ("N".equals(deleteAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_DELETE);
					if ("G".equals(deleteAuth)) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				
				final String indexAuth = r.getString("INDEXAUTH");
				if ("N".equals(indexAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_INDEX);
					if ("G".equals(indexAuth)) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				final String insertAuth = r.getString("INSERTAUTH");
				if ("N".equals(insertAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_INSERT);
					if ("G".equals(insertAuth)) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				
				final String selectAuth = r.getString("SELECTAUTH");
				if ("N".equals(selectAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_SELECT);
					if ("G".equals(selectAuth)) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				
				final String refAuth = r.getString("REFAUTH");
				if ("N".equals(refAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_REFERENCES);
					if ("G".equals(refAuth)) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				final String updateAuth = r.getString("UPDATEAUTH");
				if ("N".equals(updateAuth)) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_UPDATE);
					if ("G".equals(updateAuth)) {
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
	
	protected static Schema getSchema(Table table, String schemaName) {
		Schema s = table.getSchema();
		if(s.getName().equals(schemaName)) return s;

		Database db = s.getDatabase();
		if (db instanceof LUWCatalogDatabase){
			s = ((LUWCatalogDatabase)db).getSchema(schemaName);
			if (s != null) return s;
		} 
		Iterator it = db.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
	
		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(db);

		if (db instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)db).cacheSchema(schema);
		}
		
		return schema;
	}

	
	public static UserDefinedType getUserDefinedType(Table table, String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogTable.getSchema(table, schemaName);
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
	
	private static LUWTableSpace getTablespace(Table table, String tsNameName) {
		if (! (table instanceof LUWStorageTable)) 
			return null;
		
		LUWDatabase db = (LUWCatalogDatabase) table.getSchema().getDatabase();
		StorageProvider provider = LUWCatalogDatabase.getCatalogStorageProvider();
		if (provider != null){
			return provider.getTablespace(db,tsNameName);
		}
		return null;
	}
	
	private static Object findElement(Object[] list, String name,EClass metaclass){
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
	
	private void cacheColumn(EList columns){
		Iterator iter = columns.iterator();
		while (iter.hasNext()) {
			Column column = (Column) iter.next();
			this.cachedColumn.put(column.getName(),column);
		}
	}

	private void setAssociatedTablespace(){
		if (this.tbspaceName != null){
			LUWTableSpace tbSpace = LUWCatalogTable.getTablespace(this,this.tbspaceName);
			if (tbSpace != null) {
				tbSpace.getRegularDataTables().add(this);
			}
		}

		//Index tablespace
		if (this.indexTbspaceName != null) {
			LUWTableSpace tbSpace = LUWCatalogTable.getTablespace(this,this.indexTbspaceName);
			if (tbSpace != null) {
				tbSpace.getIndexDataTables().add(this);
			}
		}

		if (this.longTbspaceName != null) {
			LUWTableSpace tbSpace = LUWCatalogTable.getTablespace(this,this.longTbspaceName);
			if (tbSpace != null) {
				tbSpace.getLOBDataTables().add(this);
			}
		}
	}
	
	private void reset(){
		this.cachedColumn.clear();
		this.tbspaceName =null;
		this.indexTbspaceName =null;
		this.longTbspaceName =null;
	}
	
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogTable.getImpactedAlias(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedConstraints(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedPackages(connection, this));
		return impacts;
	}

	protected static Collection getImpactedAlias(Connection connection, Table table) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT TABNAME, TABSCHEMA " +
						" FROM SYSCAT.TABLES" +
			" WHERE TYPE='A'" +
			" AND BASE_TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'" +
			" AND BASE_TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
			" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Table t = LUWCatalogView.getTable(table, schemaName, tabName);
				if (t !=  null) {
					impacts.add(t);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedTables(Connection connection, Table table) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT TABNAME, TABSCHEMA" +
					" FROM SYSCAT.TABDEP" +
					" WHERE DTYPE in ('V','S')" +
					" AND BTYPE in ('T','V','S','A','N')" +
					" AND BNAME='" + LUWUtil.getIdentifier(table.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
					"FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Table t = LUWCatalogView.getTable(table, schemaName, tabName);
				if (t !=  null) {
					impacts.add(t);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedRoutines(Connection connection, Table table) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT ROUTINENAME, ROUTINESCHEMA" +
					" FROM SYSCAT.ROUTINEDEP" +
					" WHERE BTYPE in ('T','S','V','A','N')" +
					" AND BNAME='" + LUWUtil.getIdentifier(table.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String routineName = r.getString("ROUTINENAME").trim();
				final String schemaName = r.getString("ROUTINESCHEMA").trim();
				Routine routine = LUWCatalogView.getRountine(table, schemaName, routineName);
				if (routine !=  null) {
					impacts.add(routine);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}
	
	protected static Collection getImpactedTriggers(Connection connection, Table table) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();

			String query = "SELECT DISTINCT A.TABSCHEMA, A.TABNAME, A.TRIGNAME" +
			" FROM SYSCAT.TRIGGERS A, SYSCAT.TRIGDEP B" +
			" WHERE (B.BNAME='" + LUWUtil.getIdentifier(table.getName()) + "'" +
			" AND B.BSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
			" AND BTYPE in ('T','S','V','A','N')" +
			" AND A.TRIGNAME=B.TRIGNAME" +
			" AND A.TRIGSCHEMA=B.TRIGSCHEMA)" +
			" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String trigName = r.getString("TRIGNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Trigger trigger = LUWCatalogView.getTrigger(table, schemaName, tabName,trigName);
				if (trigger !=  null) {
					impacts.add(trigger);
				}
			}
			r.close();
			s.close();
			
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}
	
	protected static Collection getImpactedConstraints(Connection connection, Table table) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT CONSTNAME,TABNAME,TABSCHEMA" +
					" FROM SYSCAT.REFERENCES" +
					" WHERE REFTABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'" +
					" AND REFTABSCHEMA ='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";

			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String constName = r.getString("CONSTNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				TableConstraint constraint = LUWCatalogView.getTableConstraint(table, schemaName, tabName, constName);
				if (constraint !=  null) {
					impacts.add(constraint);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}


	protected static Collection getImpactedPackages(Connection connection, Table table) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT PKGNAME, PKGSCHEMA, HEX(UNIQUE_ID) AS UID" +
					" FROM SYSCAT.PACKAGEDEP" +
					" WHERE BTYPE IN ('T','V','S','A','N')" +
					" AND BNAME='" + LUWUtil.getIdentifier(table.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String pkgName = r.getString("PKGNAME").trim();
				final String schemaName = r.getString("PKGSCHEMA").trim();
				final String pkgUniqueID = r.getString("UID").trim();
				DB2Package pkg = LUWCatalogView.getDb2Package(table, schemaName, pkgName, pkgUniqueID);
				if (pkg !=  null) {
					impacts.add(pkg);
				}
				
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}
	
	public static Collection getStatistics(Connection connection, Table table){
		Collection statistics = new ArrayList();
		try {
			
	        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(table.getSchema().getDatabase());
	        String version = definition.getVersion();
	        float ver = 8.0f;
	        try {
	            ver = Float.parseFloat(version.substring(1));
	        }
	        catch (NumberFormatException e) {            
	        }
	        
	        String query = "";
	        if (ver < 9.0f) {
				query = "SELECT T.STATS_TIME,S.CARD,S.NPAGES, S.FPAGES, S.OVERFLOW, S.ACTIVE_BLOCKS" +
				" ,-1 AS AVGCOMPRESSEDROWSIZE,-1 AS AVGROWCOMPRESSIONRATIO, -1 AS AVGROWSIZE" +
				" ,-1 AS PCTROWSCOMPRESSED, -1 AS PCTPAGESSAVED" +
				" FROM SYSSTAT.TABLES S, SYSCAT.TABLES T" +
				" WHERE S.TABSCHEMA = T.TABSCHEMA" +
				" AND S.TABNAME = T.TABNAME " +
				" AND S.TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
				" AND S.TABNAME ='" + LUWUtil.getIdentifier(table.getName()) + "'" +
				" FOR FETCH ONLY";
	        } else {
				query = "SELECT T.STATS_TIME,S.CARD,S.NPAGES, S.FPAGES, S.OVERFLOW, S.ACTIVE_BLOCKS" +
				" ,S.AVGCOMPRESSEDROWSIZE,S.AVGROWCOMPRESSIONRATIO, S.AVGROWSIZE" +
				" ,S.PCTROWSCOMPRESSED,S.PCTPAGESSAVED" +
				" FROM SYSSTAT.TABLES S, SYSCAT.TABLES T" +
				" WHERE S.TABSCHEMA = T.TABSCHEMA" +
				" AND S.TABNAME = T.TABNAME " +
				" AND S.TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
				" AND S.TABNAME ='" + LUWUtil.getIdentifier(table.getName()) + "'" +
				" FOR FETCH ONLY";
	        }
			
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				CatalogStatistics stats = null;

				final Timestamp stats_time = r.getTimestamp("STATS_TIME");
				if (stats_time != null) {
					stats = new CatalogStatistics("STATS_TIME",LUWCatalogMessages.STAT_TIME,LUWCatalogMessages.STAT_TIME_DES,stats_time,"");
					statistics.add(stats);
				}
				
				final BigInteger card = r.getBigDecimal("CARD").toBigInteger();
				if (card.intValue() != -1) {
					stats = new CatalogStatistics("CARD",LUWCatalogMessages.STAT_CARD,LUWCatalogMessages.STAT_CARD_DES,card,"SYSSTAT.TABLES");
					statistics.add(stats);
				}
				
				final BigInteger npages = r.getBigDecimal("NPAGES").toBigInteger();
				if (npages.intValue() != -1) {
					stats = new CatalogStatistics("NPAGES",LUWCatalogMessages.STAT_NPAGES,LUWCatalogMessages.STAT_NPAGES_DES,npages,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

				final BigInteger fpages =  r.getBigDecimal("FPAGES").toBigInteger();
				if (fpages.intValue() != -1) {
					stats = new CatalogStatistics("FPAGES",LUWCatalogMessages.STAT_FPAGES,LUWCatalogMessages.STAT_FPAGES_DES,fpages,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

				final BigInteger overflow = r.getBigDecimal("OVERFLOW").toBigInteger();
				if (overflow.intValue() != -1) {
					stats = new CatalogStatistics("OVERFLOW",LUWCatalogMessages.STAT_OVERFLOW,LUWCatalogMessages.STAT_OVERFLOW_DES,overflow,"SYSSTAT.TABLES");
					statistics.add(stats);
				}
				
				final BigInteger activeBlocks = r.getBigDecimal("ACTIVE_BLOCKS").toBigInteger();;
				if (activeBlocks.intValue() != -1) {
					stats = new CatalogStatistics("ACTIVE_BLOCKS",LUWCatalogMessages.STAT_ACTIVE_BLOCKS,LUWCatalogMessages.STAT_ACTIVE_BLOCKS_DES,activeBlocks,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

				final int avgCompressedRowSize = r.getInt("AVGCOMPRESSEDROWSIZE");
				if (avgCompressedRowSize != -1) {
					stats = new CatalogStatistics("AVGCOMPRESSEDROWSIZE",LUWCatalogMessages.STAT_AVG_COMPRESSEDROWSIZE,LUWCatalogMessages.STAT_AVG_COMPRESSEDROWSIZE_DES,avgCompressedRowSize,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

				final float avgRowCompressionRatio = r.getFloat("AVGROWCOMPRESSIONRATIO");
				if (avgRowCompressionRatio != -1) {
					stats = new CatalogStatistics("AVGROWCOMPRESSIONRATIO",LUWCatalogMessages.STAT_AVG_ROW_COMPRESSION_RATIO,LUWCatalogMessages.STAT_AVG_ROW_COMPRESSION_RATIO_DES,avgRowCompressionRatio,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

				final int avgRowSize = r.getInt("AVGROWSIZE");
				if (avgRowSize != -1) {
					stats = new CatalogStatistics("AVGROWSIZE",LUWCatalogMessages.STAT_AVG_ROW_SIZE,LUWCatalogMessages.STAT_AVG_ROW_SIZE_DES,avgRowSize,"");
					statistics.add(stats);
				}

				final float pctRowsCompressed = r.getFloat("PCTROWSCOMPRESSED");
				if (pctRowsCompressed != -1) {
					stats = new CatalogStatistics("PCTROWSCOMPRESSED",LUWCatalogMessages.STAT_PCT_ROWS_COMPRESSED,LUWCatalogMessages.STAT_PCT_ROWS_COMPRESSED_DES,pctRowsCompressed,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

				final float pctPagesSaved = r.getFloat("PCTPAGESSAVED");
				if (pctPagesSaved != -1) {
					stats = new CatalogStatistics("PCTPAGESSAVED",LUWCatalogMessages.STAT_PCT_PCT_PAGES_SAVED,LUWCatalogMessages.STAT_PCT_PCT_PAGES_SAVED_DES,pctPagesSaved,"SYSSTAT.TABLES");
					statistics.add(stats);
				}

			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return statistics;
	}

	protected Column getColumn(String columnName){
		return (Column)this.cachedColumn.get(columnName);
	}
	
	void setTablespaceName(String tbspaceName){
		this.tbspaceName = tbspaceName;
	}

	void setIndexTbspaceName(String indexTbspaceName){
		this.indexTbspaceName = indexTbspaceName;
	}
	
	void setlongTbspaceName(String longTbspaceName){
		this.longTbspaceName = longTbspaceName;
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

	public static String loadRowCount(Connection connection, Table table)  {
		Database database = table.getSchema().getDatabase();
//bgp		CatalogCache cache = CatalogCache.getCache( database );

		String schemaname = LUWUtil.getIdentifier(table.getSchema().getName());
		String tablename = LUWUtil.getIdentifier(table.getName());
		String propkey = schemaname + "." + tablename; //$NON-NLS-1$
		
//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isPropertyCacheLoaded( PROP_TABLE_ROWCOUNT ) ) {
//				Object propobj = cache.getCachedProperty(
//						PROP_TABLE_ROWCOUNT, propkey );
//
//				return (String)propobj;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_TABLE_ROWCOUNT );
//bgp>		}

		String rowCount = null;

		try {
			String query = "SELECT T.STATS_TIME,S.CARD,T.TABSCHEMA,T.TABNAME" +
						" FROM SYSSTAT.TABLES S, SYSCAT.TABLES T" +
						" WHERE S.TABSCHEMA = T.TABSCHEMA" +
						" AND S.TABNAME = T.TABNAME ";

//bgp			if ( !cache.isBatchLoading() ) {
				query += " AND S.TABSCHEMA='" + schemaname + "'" +
						" AND S.TABNAME ='" + tablename + "'";
//bgp			}
			
			query += " FOR FETCH ONLY";

			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			
			while(r.next()) {
				String sname = r.getString( "TABSCHEMA" ).trim();
				String tname = r.getString( "TABNAME" ).trim();

//<bgp				if ( cache.isBatchLoading()
//						&& null == cache.findTable( sname, tname, null ) ) {
//					// The container is filtered out, go on
//					continue;
//bgp>				}
				
				String rc = "";
				final BigInteger card = r.getBigDecimal( "CARD" ).toBigInteger();

				if (card.intValue() != -1) {
					final Timestamp stats_time = r.getTimestamp( "STATS_TIME" );

					if ( stats_time != null ) {
						DateFormat date = DateFormat.getDateInstance();
						rc = date.format( stats_time ) + " " + card;
					}
				}

//<bgp				if ( cache.isBatchLoading() ) {
//					propkey = sname + "." + tname; //$NON-NLS-1$
//					cache.cacheProperty( PROP_TABLE_ROWCOUNT, propkey, rc );
//
//					if ( sname.equals( schemaname ) && tname.equals( tablename ) ) {
//						// We need the proper return value for the table argument
//						rowCount = rc;
//					}
//				}
//bgp>				else {
					rowCount = rc;
//bgp				}
			}

			r.close();
			s.close();
		} catch(SQLException e) {
		}

		return rowCount;
	}


	public static final String PROP_TABLE_PROPERTIES = "PROP_TABLE_PROPERTIES"; //$NON-NLS-1$
	public static final String PROP_TABLE_ROWCOUNT = "PROP_TABLE_ROWCOUNT"; //$NON-NLS-1$
	public static final String PROP_TABLE_TABLESPACE = "PROP_TABLE_TABLESPACE"; //$NON-NLS-1$
	private static final String PROP_TABLE_COLUMNS = "PROP_TABLE_COLUMNS"; //$NON-NLS-1$
		
	
//	private boolean propertiesLoaded = false;
	private boolean columnsLoaded = false;
	private boolean constraintLoaded = false;
	private boolean indexLoaded = false;
	private boolean triggerLoaded = false;
	private boolean partitionKeyLoaded = false;
	private boolean dataPartitionLoaded = false;
	private boolean tablespaceLoaded = false;
	private boolean privilegeLoaded = false;
	private boolean statisticsLoaded = false;
	private boolean impactsLoaded = false;
	private boolean rowCountLoaded = false;
	
	private HashMap cachedColumn = new HashMap(); 
	private String partitionMode ="";
	private String tbspaceName=null;
	private String indexTbspaceName	=null;
	private String longTbspaceName=null;
	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();
	private String rowCount = null;
}
