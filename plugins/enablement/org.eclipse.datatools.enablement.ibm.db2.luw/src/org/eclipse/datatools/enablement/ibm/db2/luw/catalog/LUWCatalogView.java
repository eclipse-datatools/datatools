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
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWDdlParser;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogView extends LUWViewImpl implements ICatalogObject,IDatabaseObject {
	public void refresh() {
//		this.propertiesLoaded = false;
		this.columnsLoaded = false;
		this.triggerLoaded = false;
		this.viewLoaded = false;
		
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		this.privilegeLoaded = false;
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
	}

	public EList getColumns() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getColumns();
		} else {
			if(!this.columnsLoaded) this.loadColumns();
			return this.columns;
		}
	}
	
	public QueryExpression getQueryExpression() {
		if(!this.viewLoaded) this.loadView();
		return this.queryExpression;
	}
	
	public CheckType getCheckType() {
		if(!this.viewLoaded) this.loadView();
		return this.checkType;
	}
	
	public boolean isUpdatable() {
		if(!this.viewLoaded) this.loadView();
		return this.updatable;
	}

	public boolean isInsertable() {
		if(!this.viewLoaded) this.loadView();
		return this.updatable;
	}
	
	public EList getTriggers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getTriggers();
		} else {
			if(!this.triggerLoaded) this.loadTriggers();
			return this.triggers;
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
		return new ArrayList();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_VIEW__COLUMNS) {
			this.getColumns();
		}
		else if(id == LUWPackage.LUW_VIEW__CHECK_TYPE) {
			this.getCheckType();
		}
		else if(id == LUWPackage.LUW_VIEW__QUERY_EXPRESSION) {
			this.getQueryExpression();
		}
		else if(id == LUWPackage.LUW_VIEW__TRIGGERS) {
			this.getTriggers();
		}
		else if(id == LUWPackage.LUW_VIEW__DEPENDENCIES) {
			this.getDependencies();
		}
		else if(id == LUWPackage.LUW_VIEW__UPDATABLE) {
			this.isUpdatable();
		}
		else if(id == LUWPackage.LUW_VIEW__PRIVILEGES) {
			this.getPrivileges();
		}
		
		return super.eIsSet(eFeature);
	}

	/*
	
	private synchronized void loadProperties() {
		if(this.propertiesLoaded) return;
		Connection connection = this.getConnection();
		if(connection == null) return;
		
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

	private synchronized void loadTriggers() {
		if(this.triggerLoaded) return;
		this.triggerLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogTable.loadTriggers(this.getConnection(), super.getTriggers(), this,((LUWCatalogDatabase)this.getCatalogDatabase()).getLoadOptions());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	/**
	 * Load miscellaneous info for this view
	 */
	private synchronized void loadView() {
		if(this.viewLoaded) return;
		this.viewLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		final Database database = getCatalogDatabase();
//bgp		final CatalogCache cache = CatalogCache.getCache( database );
		final String propkey = getSchema().getName() + "." + getName(); //$NON-NLS-1$

//<bgp		if ( cache.isBatchLoading() ) {
//			if ( cache.isPropertyCacheLoaded( PROP_VIEW_VIEWINFO ) ) {
//				setViewInfoFromCache( cache, propkey );
//
//				this.eSetDeliver(deliver);
//				return;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_VIEW_VIEWINFO );
//bgp>		}

		try {
			Connection connection = this.getConnection();
			Statement s = connection.createStatement();

			String query = "SELECT VIEWCHECK, READONLY,VALID, TEXT, VIEWSCHEMA, VIEWNAME" //$NON-NLS-1$
				+ " FROM SYSCAT.VIEWS"; //$NON-NLS-1$
			
//bgp			if ( !cache.isBatchLoading() ) {
				query += " WHERE VIEWSCHEMA='" //$NON-NLS-1$
						+ LUWUtil.getIdentifier(this.getSchema().getName())
						+ "' AND VIEWNAME='" //$NON-NLS-1$
						+ LUWUtil.getIdentifier(this.getName()) + "'"; //$NON-NLS-1$
//bgp			}

			query += " ORDER BY"; //$NON-NLS-1$
			
//<bgp			if ( cache.isBatchLoading() ) {
//				query += " VIEWSCHEMA, VIEWNAME,"; //$NON-NLS-1$
//bgp>			}

			query += " SEQNO"; //$NON-NLS-1$

			ResultSet r = s.executeQuery(query);
			ViewInfo info = null;

			while (r.next()) {
				final String sname = r.getString( "VIEWSCHEMA" ).trim(); //$NON-NLS-1$
				final String vname = r.getString( "VIEWNAME" ).trim(); //$NON-NLS-1$

				if ( info == null || sname != info.viewSchemaName || vname != info.viewSchemaName ) {
					info = new ViewInfo();
					
					info.viewSchemaName = sname;
					info.viewName = vname;
					
//<bgp					if ( cache.isBatchLoading() ) {
//						cache.cacheProperty( PROP_VIEW_VIEWINFO, info.getKey(), info );
//bgp>					}

					info.VIEWCHECK = r.getString(1);
					info.READONLY = r.getString(2);
					info.VALID = r.getString("VALID"); //$NON-NLS-1$
					
					info.TEXT = new StringBuilder();
				}

				String text = r.getString("TEXT"); //$NON-NLS-1$
				
				if ( text != null ) {
					info.TEXT.append( text.trim() );
				}
			}

			r.close();
			s.close();

			if ( info != null ) {
//<bpg				if ( cache.isBatchLoading() ) {
//					cache.cacheProperty( PROP_VIEW_VIEWINFO, info.getKey(), info );
//				}
//bgp>				else {
					info.setViewInfo( this );
//bgp				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
//<bgp		if ( cache.isBatchLoading() ) {
//			setViewInfoFromCache( cache, propkey );
//bgp>		}

		this.eSetDeliver(deliver);
	}

	/** Class for caching miscellaneous information about views */
	private static class ViewInfo {
		String viewSchemaName;
		String viewName;
		String VIEWCHECK;
		String READONLY;
		String VALID;
		StringBuilder TEXT;

		/**
		 * Get the key for cache lookup
		 * 
		 * @return The key
		 */
		public String getKey() {
			return this.viewSchemaName + "." + this.viewName; //$NON-NLS-1$
		}

		/**
		 * Update a view with information contained here
		 * 
		 * @param view
		 */
		public void setViewInfo( LUWCatalogView view ) {
			if ( "N".equals( this.VIEWCHECK) ) { //$NON-NLS-1$
				view.setCheckType( CheckType.NONE_LITERAL );
			}
			else if ( "L".equals( this.VIEWCHECK) ) { //$NON-NLS-1$
				view.setCheckType(CheckType.LOCAL_LITERAL);
			}
			else if ( "C".equals( this.VIEWCHECK) ) { //$NON-NLS-1$
				view.setCheckType(CheckType.CASCADED_LITERAL);
			}

			view.updatable = "N".equals( this.READONLY ); //$NON-NLS-1$
			view.operative = "X".equalsIgnoreCase( this.VALID ); //$NON-NLS-1$
			
			//remove un-readable character
			String text = this.TEXT.toString().replaceAll(
					"[\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u000b\u000c\u000e\u000f" + //$NON-NLS-1$
               		"\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f]", //$NON-NLS-1$
               		" "); //$NON-NLS-1$

			DatabaseDefinitionRegistry dbregistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
			DatabaseDefinition dbdef = dbregistry.getDefinition( view.getCatalogDatabase() );
			
			if ( view.queryExpression == null ) {
				DataModelElementFactory factory = dbdef.getDataModelElementFactory();
				view.setQueryExpression( (QueryExpression)factory.create(SQLExpressionsPackage.eINSTANCE.getQueryExpressionDefault()) );
			}

			LUWDdlParser ddlParser = new LUWDdlParser( dbdef );
			ddlParser.parseView( view, text );
		}
	}

//<bgp	/**
//	 * Update this view with information contained in the cache
//	 * 
//	 * @param cache
//	 * @param key
//	 */
//	private void setViewInfoFromCache( CatalogCache cache, String key ) {
//		ViewInfo info = (ViewInfo)cache.getCachedProperty( PROP_VIEW_VIEWINFO, key );
//
//		if ( info != null ) {
//			info.setViewInfo( this );
//		}
//bgp>	}
	
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

	public static Schema getSchema(Table table, String schemaName) {
		return LUWCatalogTable.getSchema(table,schemaName);
	}
	
	public static Table getTable(Table t, String schemaName, String tableName) {
		Schema schema = LUWCatalogView.getSchema(t, schemaName);
		
		if (schema instanceof LUWCatalogSchema) {
			return  ((LUWCatalogSchema)schema).getTable(schemaName,tableName);
		} 
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}
		return null;		
	}
		
	public static Routine getRountine(Table table, String schemaName, String specificName) {
		Schema schema = LUWCatalogView.getSchema(table, schemaName);
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine routine = (Routine) it.next();
			if(specificName.equals(routine.getSpecificName())) return routine;			
		}
		return null;
	}

	public static Index getIndex(Table table, String schemaName, String indexName) {
		Schema schema = LUWCatalogView.getSchema(table, schemaName);
		Iterator it = schema.getIndices().iterator();
		while(it.hasNext()) {
			Index index = (Index) it.next();
			if(index.getName().equals(indexName)) return index;			
		}

		return null;
	}

	public static UserDefinedType getUserDefinedType(Table table, String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogView.getSchema(table, schemaName);
		Iterator it = schema.getUserDefinedTypes().iterator();
		while(it.hasNext()) {
			UserDefinedType userDefinedType = (UserDefinedType) it.next();
			if(userDefinedType.getName().equals(userDefinedTypeName)) return userDefinedType;			
		}
		
		return null;		
	}

	public static TableConstraint getTableConstraint(Table table, String schemaName,String  tableName, String constName) {
		Table t = LUWCatalogView.getTable(table, schemaName,tableName);
		if (!(t instanceof BaseTable)) return null;
		Iterator it = ((BaseTable)t).getConstraints().iterator();
		while(it.hasNext()) {
			TableConstraint constraint = (TableConstraint) it.next();
			if(constraint.getName().equals(constName)) return constraint;			
		}

		return null;
	}
	
	public static Trigger getTrigger(Table table, String schemaName, String tableName,String triggerName) {
		Table t = LUWCatalogView.getTable(table, schemaName, tableName);
		Iterator it = t.getTriggers().iterator();
		while(it.hasNext()) {
			Trigger r = (Trigger) it.next();
			if(r.getName().equals(triggerName)) return r;			
		}

		return null;
	}

	public static DB2Package getDb2Package(Table table, String schemaName, String pkgName, String pkgUniqueID) {
		Schema schema = LUWCatalogView.getSchema(table, schemaName);
		if (schema instanceof LUWCatalogSchema) {
			return  ((LUWCatalogSchema)schema).getDB2Package(pkgName, pkgUniqueID);
		} 
		return null;		
	}

	
	public static void loadDependencies(Connection connection, EList dependencyList, Table table) throws SQLException {
		final Database database = table.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery("SELECT BSCHEMA, BNAME, BTYPE FROM SYSCAT.TABDEP WHERE TABSCHEMA='" //$NON-NLS-1$
					+ LUWUtil.getIdentifier(table.getSchema().getName()) + "' AND TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			while(r.next()) {
				final String bschema = r.getString(1).trim();
				final String bname   = r.getString(2);
				final String btype   = r.getString(3);
				SQLObject obj = null;
				if(btype.equals("A")) {	//alias //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);
				}
				else if(btype.equals("F")) {	//function //$NON-NLS-1$
					obj = LUWCatalogView.getRountine(table, bschema, bname);					
				}
				else if(btype.equals("N")) {	//nickname //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);					
				}
				else if(btype.equals("O")) {	//privilege //$NON-NLS-1$
					continue;
				}
				else if(btype.equals("I")) {	//index //$NON-NLS-1$
					obj = LUWCatalogView.getIndex(table, bschema, bname);										
				}
				else if(btype.equals("R")) {	//structure type //$NON-NLS-1$
					obj = LUWCatalogView.getUserDefinedType(table, bschema, bname);										
				}
				else if(btype.equals("S")) {	//MQT //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);					
				}
				else if(btype.equals("T")) {	//table //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);					
				}
				else if(btype.equals("U")) {	//typed table //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);
				}
				else if(btype.equals("V")) {	//view //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);					
				}
				else if(btype.equals("W")) {	//typed view //$NON-NLS-1$
					obj = LUWCatalogView.getTable(table, bschema, bname);					
				}
				else {
					continue;
				}
				
				if(obj == null) continue;
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(obj);
				dependencyList.add(dep);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		r.close();
		s.close();
	}
		
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogTable.getImpactedAlias(connection, this));
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
		}
		this.eSetDeliver(deliver);
	}

	private static final String PROP_VIEW_VIEWINFO = "PROP_VIEW_VIEWINFO"; //$NON-NLS-1$

	private boolean columnsLoaded = false;
	private boolean viewLoaded = false;
	private boolean triggerLoaded = false;
	private boolean dependencyLoaded = false;
	private boolean privilegeLoaded = false;
	private boolean updatable;
	private boolean impactsLoaded = false;
	private Collection impacts = new ArrayList();
}
