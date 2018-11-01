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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogStatistics;
//bgp import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.CatalogCache;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWCatalogMessages;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogIndex extends LUWIndexImpl implements ICatalogObject,IDatabaseObject {
	public void refresh() {
		if (this.memberLoaded) {
			this.members.clear();
			this.memberLoaded = false;
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
		return this.getTable().getSchema().getDatabase();		
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

	public EList getMembers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getMembers();
		} else {
			if(!this.memberLoaded) this.loadMembers();
			return this.members;
		}
	}

	public EList getIncludedMembers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getIncludedMembers();
		} else {
			if(!this.memberLoaded) this.loadMembers();
			return this.includedMembers;
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
		if (!this.statisticsLoaded) {
			this.statistics = LUWCatalogIndex.getStatistics(this.getConnection(), this);
			this.statisticsLoaded = true;
		}
		return this.statistics;
	}
	
	public LUWTableSpace getTablespace() {
		if (!this.tablespaceLoaded)
			loadTablespace();
		return this.tablespace;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == DB2ModelPackage.DB2_INDEX__MEMBERS) {
			this.getMembers();
		}
		else if(id == DB2ModelPackage.DB2_INDEX__INCLUDED_MEMBERS) {
			this.getIncludedMembers();
		}
		else if(id == DB2ModelPackage.DB2_INDEX__PRIVILEGES) {
			this.getPrivileges();
		}
		return super.eIsSet(eFeature);
	}
	
	private void loadMembers() {
		if(this.memberLoaded) return;
		this.memberLoaded = true;
		EList memberList = super.getMembers();
		EList includedMemberList = super.getIncludedMembers();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogIndex.loadMembers(this.getConnection(),memberList,includedMemberList, this);
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
			LUWCatalogIndex.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadTablespace() {
		if( this.tablespaceLoaded ) {
			return;
		}

		this.tablespaceLoaded = true;

		// Only supported for LUW 9.x and above
		try
		{
			String version = getSchema().getDatabase().getVersion().substring( 1 );

			if ( Float.parseFloat( version ) < 9.1 )
			{
				return;
			}
		}
		catch ( Exception e )
		{
			return;
		}

		EList tablespaces = ((LUWDatabase)getCatalogDatabase()).getTablespaces();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogIndex.loadTablespace( getConnection(), tablespaces, this );
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		this.eSetDeliver(deliver);
	}

	/**
	 * Load the members of an index
	 * 
	 * @param connection
	 * @param members
	 * @param includedMembers
	 * @param index
	 * @throws SQLException
	 */
	protected static void loadMembers(Connection connection, EList members, EList includedMembers,Index index)throws SQLException  {
		final Schema schema = index.getSchema();
		final Database database = ModelHelper.getDatabase(schema);

//bgp		CatalogCache cache = CatalogCache.getCache( database );
		String propkey = schema.getName() + "." + index.getName(); //$NON-NLS-1$
		
//<bgp		// TODO N.B. With very large databases, this uses excessive memory
//		// we need a way to detect that and throttle the cache
//		boolean caching = false && cache.isBatchLoading();
//		
//		if ( caching ) {
//			if ( cache.isPropertyCacheLoaded( PROP_INDEX_MEMBERS )) {
//				loadMembersFromCache( cache, index, propkey, members, includedMembers );
//				return;
//			}
//			
//			cache.setPropertyCacheLoaded( PROP_INDEX_MEMBERS );
//bgp>		}

		String query = "SELECT COLNAME, COLORDER, INDSCHEMA, INDNAME" //$NON-NLS-1$
				+ " FROM SYSCAT.INDEXCOLUSE"; //$NON-NLS-1$

//bgp		if ( !caching ) {
			query += " WHERE INDSCHEMA='" + LUWUtil.getIdentifier(index.getSchema().getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$
						+ " AND INDNAME='" + LUWUtil.getIdentifier(index.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
//bgp		}
		
		query += " ORDER BY"; //$NON-NLS-1$
		
//<bgp		if ( caching ) {
//			query += " INDSCHEMA, INDNAME,"; //$NON-NLS-1$
//bgp>		}

		query += " COLSEQ"; //$NON-NLS-1$
		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		
		MembersInfo info = null;

		try {
			while(r.next()) {
				final String schemaName = r.getString( "INDSCHEMA" ).trim();  //$NON-NLS-1$
				final String indexName = r.getString( "INDNAME" ).trim();  //$NON-NLS-1$

				if ( info == null
						|| !info.idxSchemaName.equals( schemaName )
						|| !info.indexName.equals( indexName ) ) {
					info = new MembersInfo();
					info.idxSchemaName = schemaName;
					info.indexName = indexName;

//<bgp					if ( caching ) {
//						cache.cacheProperty( PROP_INDEX_MEMBERS, info.getKey(), info );
//bgp>					}
				}

				final String colName = r.getString(1); 
				final String order = r.getString(2);

				info.addMember( colName, order );
			}

//bgp			if ( !caching && info != null ) {
//bgp            info.setMembers( cache, index, members, includedMembers );
	          if (info != null ) {
				info.setMembers(index, members, includedMembers );
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
		
//<bgp		if ( caching ) {
//			loadMembersFromCache( cache, index, propkey, members, includedMembers );
//bgp>		}
	}

	/** Object for caching information about index members */
	private static class MembersInfo {
		/** Object for caching information about one  index member */
		private static class MemberInfo {
			/** The column name */
			String colName;
			/** The sort order */
			String order;

			/**
			 * Constructor
			 * 
			 * @param colName
			 * @param order
			 */
			public MemberInfo( String colName, String order ) {
				this.colName = colName;
				this.order = order;
			}
		}

		/** The schema containing the index */
		String idxSchemaName;
		/** The index we are caching members of */
		String indexName;
		/** Index members */
		List<MemberInfo> members = new ArrayList<MemberInfo>();

		/**
		 * Get the key for cache lookup
		 * 
		 * @return The key
		 */
		public String getKey() {
			return this.idxSchemaName + "." + this.indexName; //$NON-NLS-1$
		}

		/**
		 * Add a member to the index
		 * 
		 * @param colName
		 * @param order
		 */
		public void addMember( String colName, String order ) {
			this.members.add( new MemberInfo( colName, order ) );
		}

		/**
		 * Update an index
		 * 
		 * @param index
		 * @param members
		 * @param includedMembers
		 */
		public void setMembers(Index index,	List<IndexMember> members, List<IndexMember> includedMembers ) {
			Database database = index.getSchema().getDatabase();

			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

			Object[] memberList = members.toArray();
			members.clear();
			Object[] includedMemberList = includedMembers.toArray();
			includedMembers.clear();

			for ( MemberInfo minfo : this.members ) {
				IndexMember member = LUWCatalogIndex.findIndexMember( memberList, minfo.colName );

				if ( member == null ) {
					member = LUWCatalogIndex.findIndexMember( includedMemberList, minfo.colName );

					if ( member == null ) {
						member = (IndexMember)factory.create(
								SQLConstraintsPackage.eINSTANCE.getIndexMember() ); 
						Column col = LUWCatalogIndex.getColumn( index, minfo.colName );
						member.setColumn( col );
					}
				}
				
				if ( minfo.order.equals( "A" ) ) { //$NON-NLS-1$
					member.setIncrementType( IncrementType.ASC_LITERAL );
					members.add(member);
				}
				else if ( minfo.order.equals( "D" ) ) { //$NON-NLS-1$
					member.setIncrementType( IncrementType.DESC_LITERAL );
					members.add(member);
				}
				else if ( minfo.order.equals( "I" ) ) { //$NON-NLS-1$
					includedMembers.add( member );
				}
			}
		}
	}

//<bgp	/**
//	 * Set an index members from the cache
//	 * 
//	 * @param cache
//	 * @param index
//	 * @param key
//	 * @param members
//	 * @param includedMembers
//	 */
//	private static void loadMembersFromCache( CatalogCache cache, Index index, String key,
//			List<IndexMember> members, List<IndexMember> includedMembers ) {
//		MembersInfo info = (MembersInfo)cache.getCachedProperty( PROP_INDEX_MEMBERS, key );
//		
//		if ( info != null ) {
//			info.setMembers( cache, index, members, includedMembers );
//		}
//bgp>	}
	
	public static void loadPrivileges(Connection connection, EList privilegeList,Index index,String granteeFilter) throws SQLException {
		final Schema schema = index.getSchema();
		final Database database = ModelHelper.getDatabase(schema);

		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
					",CONTROLAUTH" +
					" FROM SYSCAT.INDEXAUTH" + 
					" WHERE INDSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'" +
					" AND INDNAME='" + LUWUtil.getIdentifier(index.getName()) + "'" ;
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
				
				boolean isSystemGranted = granteeId.equalsIgnoreCase(userName);
				final String alterAuth = r.getString("CONTROLAUTH");
				if (alterAuth.equals("N")) {
				} else {
					LUWCatalogPrivilege privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CONTROL);
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
	
	public static void loadTablespace(Connection connection, EList tablespaceList, Index index) throws SQLException {
		final LUWIndex luwindex = (LUWIndex)index;
		Statement s = connection.createStatement();
		String query = "SELECT T.TBSPACE" + //$NON-NLS-1$
					" FROM SYSIBM.SYSINDEXES I, SYSIBM.SYSTABLESPACES T" +  //$NON-NLS-1$
					" WHERE I.NAME='" //$NON-NLS-1$
					+ LUWUtil.getIdentifier(luwindex.getName())
					+ "' AND I.CREATOR='" //$NON-NLS-1$
					+ luwindex.getTable().getSchema().getName().trim()
					+ "' AND I.TBSPACEID=T.TBSPACEID"; //$NON-NLS-1$
		ResultSet r = s.executeQuery(query);

		try {
			while (r.next())
			{
				final String tbspaceName = r.getString("TBSPACE"); //$NON-NLS-1$
				
				for (Iterator iter = tablespaceList.iterator(); iter.hasNext(); ) {
					LUWTableSpace tbspace = (LUWTableSpace)iter.next();
					
					if (tbspace.getName().equals( tbspaceName ))
					{
						luwindex.setTablespace( tbspace );
						tbspace.getIndexes().add( luwindex );
						break;
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
	}

	private DatabaseDefinition getDatabaseDefinition() {
		Database d = this.getSchema().getDatabase();
		return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(d);
	}
	
	private static Column getColumn(Index index, String columnName) {
		Table table = index.getTable();
		if (table instanceof LUWCatalogTable) {
			Column c = ((LUWCatalogTable)table).getColumn(columnName);
			if (c!= null) return c;
		} 

		Iterator it = table.getColumns().iterator();
		while(it.hasNext()) {
			Column c = (Column) it.next();
			if(c.getName().equals(columnName)) return c;
		}
	
		Column column = new LUWCatalogColumn();
		column.setName(columnName);
		column.setTable(table);
		Database database = table.getSchema().getDatabase();
		final DatabaseDefinition def = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		CharacterStringDataType dataType = (CharacterStringDataType) def.getPredefinedDataType("Char"); //$NON-NLS-1$
		dataType.setLength(5);
		column.setContainedType(dataType);

		return column;
		
	}

	private static IndexMember findIndexMember(Object[] list, String colName){
		IndexMember member = null;
		for (int i = 0; i < list.length; i++){
			Column column = ((IndexMember)list[i]).getColumn();
			if (column.getName().equals(colName)){
				member = (IndexMember)list[i];
				break;
			}
		}
		return member;
	}
	
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogIndex.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogIndex.getImpactedConstraints(connection, this));
		impacts.addAll(LUWCatalogIndex.getImpactedPackages(connection, this));

		return impacts;
	}
	
	
	protected static Collection getImpactedTables(Connection connection, Index index) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT TABNAME,TABSCHEMA" +
					" FROM SYSCAT.TABDEP" +
					" where BTYPE='I'" +
					" AND BNAME='" + LUWUtil.getIdentifier(index.getName())+"'" +
					" AND BSCHEMA ='" + LUWUtil.getIdentifier(index.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Table t = LUWCatalogView.getTable(index.getTable(), schemaName, tabName);
				if (t !=  null) {
					impacts.add(t);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedConstraints(Connection connection, Index index) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT CONSTNAME,TABNAME,TABSCHEMA" +
					" FROM SYSCAT.CONSTDEP" +
					" WHERE BTYPE='I'" +
					" AND BNAME='" + LUWUtil.getIdentifier(index.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(index.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String constName = r.getString("CONSTNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				TableConstraint constraint = LUWCatalogView.getTableConstraint(index.getTable(), schemaName, tabName, constName);
				if (constraint !=  null) {
					impacts.add(constraint);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedPackages(Connection connection, Index index) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT PKGNAME, PKGSCHEMA, HEX(UNIQUE_ID) AS UID" +
					" FROM SYSCAT.PACKAGEDEP" +
					" WHERE BTYPE = 'I'" +
					" AND BNAME='" + LUWUtil.getIdentifier(index.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(index.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String pkgName = r.getString("PKGNAME").trim();
				final String schemaName = r.getString("PKGSCHEMA").trim();
				final String pkgUniqueID = r.getString("UID").trim();
				DB2Package pkg = LUWCatalogView.getDb2Package(index.getTable(), schemaName, pkgName, pkgUniqueID);
				if (pkg !=  null) {
					impacts.add(pkg);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return impacts;
	}

	
	public static Collection getStatistics(Connection connection, Index index){
		Collection statistics = new ArrayList();
		try {
			
	        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(index.getTable().getSchema().getDatabase());
	        String version = definition.getVersion();
	        float ver = 8.0f;
	        try {
	            ver = Float.parseFloat(version.substring(1));
	        }
	        catch (NumberFormatException e) {            
	        }
	        
	        String query = "";
	        if (ver < 9.0f) {
				query = "SELECT I.STATS_TIME,S.NLEAF, S.NLEVELS,S.FIRSTKEYCARD,S.FIRST2KEYCARD,S.FIRST3KEYCARD" +
						",S.FIRST4KEYCARD,S.FULLKEYCARD,S.CLUSTERRATIO,S.CLUSTERFACTOR,S.SEQUENTIAL_PAGES,S.DENSITY,S.PAGE_FETCH_PAIRS" +
						",S.NUMRIDS,S.NUMRIDS_DELETED,S.NUM_EMPTY_LEAFS,S.AVERAGE_RANDOM_FETCH_PAGES" +
						",S.AVERAGE_RANDOM_PAGES,S.AVERAGE_SEQUENCE_GAP,S.AVERAGE_SEQUENCE_FETCH_GAP" +
						",S.AVERAGE_SEQUENCE_PAGES,S.AVERAGE_SEQUENCE_FETCH_PAGES" +
						",-1 AS AVGPARTITION_CLUSTERRATIO, -1 AS AVGPARTITION_CLUSTERFACTOR, '' AS AVGPARTITION_PAGE_FETCH_PAIRS" +
						",-1 AS DATAPARTITION_CLUSTERFACTOR, -1 AS INDCARD" +
						" FROM SYSSTAT.INDEXES S, SYSCAT.INDEXES I" +
						" WHERE S.INDSCHEMA = I.INDSCHEMA" +
						" AND S.INDNAME = I.INDNAME" +
						" AND S.INDSCHEMA='" + LUWUtil.getIdentifier(index.getSchema().getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
						" AND S.INDNAME='" + LUWUtil.getIdentifier(index.getName()) + "'" +	 //$NON-NLS-1$ //$NON-NLS-2$
						" FOR FETCH ONLY";
	        } else {
				query = "SELECT I.STATS_TIME,S.NLEAF, S.NLEVELS,S.FIRSTKEYCARD,S.FIRST2KEYCARD,S.FIRST3KEYCARD" +
					",S.FIRST4KEYCARD,S.FULLKEYCARD,S.CLUSTERRATIO,S.CLUSTERFACTOR,S.SEQUENTIAL_PAGES,S.DENSITY,S.PAGE_FETCH_PAIRS" +
					",S.NUMRIDS,S.NUMRIDS_DELETED,S.NUM_EMPTY_LEAFS,S.AVERAGE_RANDOM_FETCH_PAGES" +
					",S.AVERAGE_RANDOM_PAGES,S.AVERAGE_SEQUENCE_GAP,S.AVERAGE_SEQUENCE_FETCH_GAP" +
					",S.AVERAGE_SEQUENCE_PAGES,S.AVERAGE_SEQUENCE_FETCH_PAGES" +
					",S.AVGPARTITION_CLUSTERRATIO,S.AVGPARTITION_CLUSTERFACTOR,S.AVGPARTITION_PAGE_FETCH_PAIRS" +
					",S.DATAPARTITION_CLUSTERFACTOR,S.INDCARD" +
					" FROM SYSSTAT.INDEXES S, SYSCAT.INDEXES I" +
					" WHERE S.INDSCHEMA = I.INDSCHEMA" +
					" AND S.INDNAME = I.INDNAME" +
					" AND S.INDSCHEMA='" + LUWUtil.getIdentifier(index.getSchema().getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
					" AND S.INDNAME='" + LUWUtil.getIdentifier(index.getName()) + "'" +	 //$NON-NLS-1$ //$NON-NLS-2$
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
				
				final int nLeaf = r.getInt("NLEAF");
				if (nLeaf  != -1) {
					stats = new CatalogStatistics("NLEAF",LUWCatalogMessages.STAT_NLEAF,LUWCatalogMessages.STAT_NLEAF_DES,nLeaf,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final int nLevel = r.getInt("NLEVELS");
				if (nLevel != -1) {
					stats = new CatalogStatistics("NLEVELS",LUWCatalogMessages.STAT_NLEVELS,LUWCatalogMessages.STAT_NLEVELS_DES,nLevel,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final BigInteger firstKeyCard =  r.getBigDecimal("FIRSTKEYCARD").toBigInteger();
				if (firstKeyCard.intValue() != -1) {
					stats = new CatalogStatistics("FIRSTKEYCARD",LUWCatalogMessages.STAT_FIRST_KEYCARD,LUWCatalogMessages.STAT_FIRST_KEYCARD_DES,firstKeyCard,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final BigInteger first2KeyCard = r.getBigDecimal("FIRST2KEYCARD").toBigInteger();
				if (firstKeyCard.intValue() != -1) {
					stats = new CatalogStatistics("FIRST2KEYCARD",LUWCatalogMessages.STAT_FIRST2_KEYCARD,LUWCatalogMessages.STAT_FIRST2_KEYCARD_DES,first2KeyCard,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final BigInteger first3KeyCard = r.getBigDecimal("FIRST3KEYCARD").toBigInteger();;
				if (first3KeyCard.intValue() != -1) {
					stats = new CatalogStatistics("FIRST3KEYCARD",LUWCatalogMessages.STAT_FIRST3_KEYCARD,LUWCatalogMessages.STAT_FIRST3_KEYCARD_DES,first3KeyCard,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final BigInteger first4KeyCard = r.getBigDecimal("FIRST4KEYCARD").toBigInteger();;
				if (first4KeyCard.intValue() != -1) {
					stats = new CatalogStatistics("FIRST4KEYCARD",LUWCatalogMessages.STAT_FIRST4_KEYCARD,LUWCatalogMessages.STAT_FIRST4_KEYCARD_DES,first4KeyCard,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final BigInteger fullKeyCard = r.getBigDecimal("FULLKEYCARD").toBigInteger();;
				if (fullKeyCard.intValue() != -1) {
					stats = new CatalogStatistics("FULLKEYCARD",LUWCatalogMessages.STAT_FULL_KEYCARD,LUWCatalogMessages.STAT_FULL_KEYCARD_DES,fullKeyCard,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final int clusterRatio = r.getInt("CLUSTERRATIO");
				if (clusterRatio != -1) {
					stats = new CatalogStatistics("CLUSTERRATIO",LUWCatalogMessages.STAT_CLUSTER_RATIO,LUWCatalogMessages.STAT_CLUSTER_RATIO_DES,clusterRatio,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float clusterFactor = r.getFloat("CLUSTERFACTOR");
				if (clusterFactor != -1) {
					stats = new CatalogStatistics("CLUSTERFACTOR",LUWCatalogMessages.STAT_CLUSTER_FACTOR,LUWCatalogMessages.STAT_CLUSTER_FACTOR_DES,clusterFactor,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final int sequentialPages = r.getInt("SEQUENTIAL_PAGES");
				if (sequentialPages != -1) {
					stats = new CatalogStatistics("SEQUENTIAL_PAGES",LUWCatalogMessages.STAT_SEQUENTIAL_PAGES,LUWCatalogMessages.STAT_SEQUENTIAL_PAGES_DES,sequentialPages,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final int density = r.getInt("DENSITY");
				if (density != -1) {
					stats = new CatalogStatistics("DENSITY",LUWCatalogMessages.STAT_DENSITY,LUWCatalogMessages.STAT_DENSITY_DES,density,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final String fetchPair = r.getString("PAGE_FETCH_PAIRS");
				if (fetchPair != null && !"".equals(fetchPair)) {
					stats = new CatalogStatistics("PAGE_FETCH_PAIRS",LUWCatalogMessages.STAT_PAGE_FETCH_PAIRS,LUWCatalogMessages.STAT_PAGE_FETCH_PAIRS_DES,LUWUtil.getSingleQuotedString(fetchPair),"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final BigInteger numberRID = r.getBigDecimal("NUMRIDS").toBigInteger();;
				if (numberRID.intValue() != -1) {
					stats = new CatalogStatistics("NUMRIDS",LUWCatalogMessages.STAT_NUMRIDS,LUWCatalogMessages.STAT_NUMRIDS_DES,numberRID,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final BigInteger numberRIDDeleted = r.getBigDecimal("NUMRIDS_DELETED").toBigInteger();;
				if (numberRIDDeleted.intValue() != -1) {
					stats = new CatalogStatistics("NUMRIDS_DELETED",LUWCatalogMessages.STAT_NUMRIDS_DELETED,LUWCatalogMessages.STAT_NUMRIDS_DELETED_DES,numberRIDDeleted,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final BigInteger numEmptyLeafs = r.getBigDecimal("NUM_EMPTY_LEAFS").toBigInteger();;
				if (numEmptyLeafs.intValue() != -1) {
					stats = new CatalogStatistics("NUM_EMPTY_LEAFS",LUWCatalogMessages.STAT_NUM_EMPTY_LEAFS,LUWCatalogMessages.STAT_NUM_EMPTY_LEAFS_DES,numEmptyLeafs,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float avgRandomFetchPages = r.getFloat("AVERAGE_RANDOM_FETCH_PAGES");
				if (avgRandomFetchPages != -1) {
					stats = new CatalogStatistics("AVERAGE_RANDOM_FETCH_PAGES",LUWCatalogMessages.STAT_AVERAGE_RANDOM_FETCH_PAGES,LUWCatalogMessages.STAT_AVERAGE_RANDOM_FETCH_PAGES_DES,avgRandomFetchPages,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float avgRandomPages = r.getFloat("AVERAGE_RANDOM_PAGES");
				if (avgRandomPages != -1) {
					stats = new CatalogStatistics("AVERAGE_RANDOM_PAGES",LUWCatalogMessages.STAT_AVERAGE_RANDOM_PAGES,LUWCatalogMessages.STAT_AVERAGE_RANDOM_PAGES_DES,avgRandomPages,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float avgSequenceGap = r.getFloat("AVERAGE_SEQUENCE_GAP");
				if (avgSequenceGap != -1) {
					stats = new CatalogStatistics("AVERAGE_SEQUENCE_GAP",LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_GAP,LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_GAP_DES,avgSequenceGap,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float avgSequenceFetchGap = r.getFloat("AVERAGE_SEQUENCE_FETCH_GAP");
				if (avgSequenceFetchGap != -1) {
					stats = new CatalogStatistics("AVERAGE_SEQUENCE_FETCH_GAP",LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_FETCH_GAP,LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_FETCH_GAP_DES,avgSequenceFetchGap,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float avgSequencePage = r.getFloat("AVERAGE_SEQUENCE_PAGES");
				if (avgSequencePage != -1) {
					stats = new CatalogStatistics("AVERAGE_SEQUENCE_PAGES",LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_PAGES,LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_PAGES_DES,avgSequencePage,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float avgSequenceFetchPage = r.getFloat("AVERAGE_SEQUENCE_FETCH_PAGES");
				if (avgSequenceFetchPage != -1) {
					stats = new CatalogStatistics("AVERAGE_SEQUENCE_FETCH_PAGES",LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_FETCH_PAGES,LUWCatalogMessages.STAT_AVERAGE_SEQUENCE_FETCH_PAGES_DES,avgSequenceFetchPage,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final int partClusterRatio= r.getInt("AVGPARTITION_CLUSTERRATIO");
				if (partClusterRatio != -1) {
					stats = new CatalogStatistics("AVGPARTITION_CLUSTERRATIO",LUWCatalogMessages.STAT_AVERAGE_PARTITION_CLUSTERRATIO,LUWCatalogMessages.STAT_AVERAGE_PARTITION_CLUSTERRATIO_DES,partClusterRatio,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final float partClusterFactor = r.getFloat("AVGPARTITION_CLUSTERFACTOR");
				if (partClusterFactor != -1) {
					stats = new CatalogStatistics("AVGPARTITION_CLUSTERFACTOR",LUWCatalogMessages.STAT_AVERAGE_PARTITION_CLUSTERFACTOR,LUWCatalogMessages.STAT_AVERAGE_PARTITION_CLUSTERFACTOR_DES,partClusterFactor,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final String partFetchPair = r.getString("AVGPARTITION_PAGE_FETCH_PAIRS");
				if (partFetchPair != null && !"".equals(partFetchPair)) {
					stats = new CatalogStatistics("AVGPARTITION_PAGE_FETCH_PAIRS",LUWCatalogMessages.STAT_AVERAGE_PARTITION_PAGE_FETCH_PAIRS,LUWCatalogMessages.STAT_AVERAGE_PARTITION_PAGE_FETCH_PAIRS_DES,partFetchPair,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

				final float dataClusterFactor = r.getFloat("DATAPARTITION_CLUSTERFACTOR");
				if (dataClusterFactor != -1) {
					stats = new CatalogStatistics("DATAPARTITION_CLUSTERFACTOR",LUWCatalogMessages.STAT_DATAPARTITION_CLUSTERFACTOR,LUWCatalogMessages.STAT_DATAPARTITION_CLUSTERFACTOR_DES,dataClusterFactor,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}
				
				final BigInteger indCard = r.getBigDecimal("INDCARD").toBigInteger();;
				if (indCard.intValue() != -1) {
					stats = new CatalogStatistics("INDCARD",LUWCatalogMessages.STAT_INDCARD,LUWCatalogMessages.STAT_INDCARD_DES,indCard,"SYSSTAT.INDEXES");
					statistics.add(stats);
				}

			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return statistics;
	}
	
	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogIndex.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
		}
		this.eSetDeliver(deliver);
	}
	
	protected void setIndexUniqueRule(IndexUniqueRule rule){
		this.uniqueRule = rule;
	}
	
	protected IndexUniqueRule getIndexUniqueRule(){
		return this.uniqueRule;
	}
	
	protected enum IndexUniqueRule {
		DUPLICATE,
		UNIQUE,
		PRIMARYKEY
	};

	private static final String PROP_INDEX_MEMBERS = "PROP_INDEX_MEMBERS"; //$NON-NLS-1$

	private boolean memberLoaded = false;
	private boolean privilegeLoaded = false;
	private boolean statisticsLoaded = false;
	private boolean impactsLoaded = false;
	private boolean tablespaceLoaded = false;
	
	private IndexUniqueRule uniqueRule;

	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();

}
