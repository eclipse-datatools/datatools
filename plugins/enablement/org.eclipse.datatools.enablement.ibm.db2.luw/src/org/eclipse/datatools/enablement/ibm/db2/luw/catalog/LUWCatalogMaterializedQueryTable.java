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
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWDdlParser;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl;
import org.eclipse.datatools.enablement.ibm.util.IRowCountCache;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogMaterializedQueryTable extends LUWMaterializedQueryTableImpl implements IRowCountCache,ICatalogObject,IDatabaseObject {
	public void refresh() {
		this.columnsLoaded = false;
		this.indexLoaded = false;
		
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		
		this.partitionKeyLoaded = false;
		
		if (this.dataPartitionLoaded) {
			this.dataPartitions.clear();
			this.dataPartitionLoaded = false;
		}
		
		this.tablespaceLoaded = false;
		this.loaded = false;
		this.privilegeLoaded = false;
		this.rowCountLoaded = false;
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

//	public RefreshType getRefresh() {
//		if (!this.loaded) this.load();
//		return this.refresh;
//	}
//
//	public MaintenanceType getMaintainedBy() {
//		if (!this.loaded) this.load();
//		return this.maintainedBy;
//	}
//	
	public QueryExpression getQueryExpression() {
		if (!this.loaded) this.load();
		return this.queryExpression;
	}
	
	public EList getColumns() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getColumns();
		} else {
			if(!this.columnsLoaded) this.loadColumns();
			return this.columns;
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

	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
		}
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
	
	public LUWPartitionKey getPartitionKey() {
		if (!this.partitionKeyLoaded) this.loadPartitionKey();
		return this.partitionKey;
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
		if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COLUMNS) {
			this.getColumns();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX) {
			this.getIndex();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DEPENDENCIES) {
			this.getDependencies();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE) {
			this.getRegularDataTableSpace();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE) {
			this.getLOBDataTableSpace();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE) {
			this.getIndexDataTableSpace();
		}
//		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__MAINTAINED_BY) {
//			this.getMaintainedBy();
//		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__QUERY_EXPRESSION) {
			this.getQueryExpression();
		}
//		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REFRESH) {
//			this.getRefresh();
//		} 
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY) {
			this.getPartitionKey();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS) {
			this.getDataPartitions();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY) {
			this.getDataPartitionKey();
		}
		else if(id == LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PRIVILEGES) {
			this.getPrivileges();
		}
		
		return super.eIsSet(eFeature);
	}
	
	public void setPartitionMode(String partitionMode) {
		this.partitionMode = partitionMode;
	}
	
	public String getPartitionMode() {
		return this.partitionMode;
	}
	
	/*
	private synchronized void loadProperties() {
		if(this.propertiesLoaded) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogTable.loadProperties(this.getConnection(),this);
		}
		catch (Exception e) {
		}

		this.propertiesLoaded = true;
		this.eSetDeliver(deliver);		
	}
	*/
	
	private synchronized void loadColumns() {
		if(this.columnsLoaded) return;
		this.columnsLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			LUWCatalogTable.loadColumns(this.getConnection(), super.getColumns(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadIndexes() {
		if(this.indexLoaded) return;
		this.indexLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadIndexes(this.getConnection(), super.getIndex(), this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogView.loadDependencies(this.getConnection(), super.getDependencies(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadTablespace() {
		if(this.tablespaceLoaded) return;
		this.tablespaceLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadTablespace(this.getConnection(),this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	private synchronized void load() {
		if(this.loaded) return;
		this.loaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	

		try {
			Connection connection = this.getConnection();
			
	
			DataModelElementFactory factory = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()).getDataModelElementFactory();

			String query = "SELECT REFRESH,PROPERTY " + //$NON-NLS-1$
					" FROM SYSCAT.TABLES " + //$NON-NLS-1$
					" WHERE TABSCHEMA ='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
					" AND TABNAME ='" + LUWUtil.getIdentifier(this.getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
					" AND TYPE='S'"; //$NON-NLS-1$
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {

//				if (r.getString("REFRESH").trim().equalsIgnoreCase("D")) { //$NON-NLS-1$ //$NON-NLS-2$
//					this.setRefresh(RefreshType.DEFERRED_LITERAL);				
//				} else {
//					this.setRefresh(RefreshType.IMMEDIATE_LITERAL);				
//				}
//				
//				if (r.getString("PROPERTY").trim().equalsIgnoreCase("Y")) { //$NON-NLS-1$ //$NON-NLS-2$
//					this.setMaintainedBy(MaintenanceType.USER_LITERAL);
//				} else {
//					this.setMaintainedBy(MaintenanceType.SYSTEM_LITERAL);
//				}
				
				
			}
			r.close();
			

			String querySQL = "SELECT TEXT " + //$NON-NLS-1$
					" FROM SYSCAT.VIEWS" + //$NON-NLS-1$
					" WHERE VIEWSCHEMA='" + this.getSchema().getName() + "'" + //$NON-NLS-1$ //$NON-NLS-2$
					" AND VIEWNAME='" + this.getName()+ "'"; //$NON-NLS-1$ //$NON-NLS-2$

			r = s.executeQuery(querySQL);

			String text="";
			while(r.next()) {
			
				String expression = r.getString("TEXT").trim(); //$NON-NLS-1$
				if (expression != null){
					text += expression.trim() + " ";
					//remove un-readable character
					text = text.replaceAll("[\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u000b\u000c\u000e\u000f" +
		               		"\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f]", " ");
				}
			}

			QueryExpression queryExpression = (QueryExpression) factory.create(SQLExpressionsPackage.eINSTANCE.getQueryExpressionDefault());
			LUWDdlParser ddlParser = new LUWDdlParser(RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()));
			queryExpression.setSQL(ddlParser.parseMQT(text).getQueryExpression().getSQL());
			this.setQueryExpression(queryExpression);

			r.close();
			s.close();

		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
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

	
	private synchronized void loadDataPartitions() {
		if(this.dataPartitionLoaded) return;
		this.dataPartitionLoaded = true;

		super.getDataPartitions();
		
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

	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogTable.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedPackages(connection, this));
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
			e.printStackTrace();
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
	
	
	private boolean columnsLoaded = false;
	private boolean indexLoaded = false;
	private boolean dependencyLoaded = false;	
	private boolean tablespaceLoaded = false;
	private boolean partitionKeyLoaded = false;
	private boolean dataPartitionLoaded = false;
	private boolean loaded = false;
	private boolean privilegeLoaded = false;
	private boolean impactsLoaded = false;
	private boolean statisticsLoaded = false;
	private boolean rowCountLoaded = false;

	private String partitionMode = "";
	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();
	private String rowCount = null;

}
