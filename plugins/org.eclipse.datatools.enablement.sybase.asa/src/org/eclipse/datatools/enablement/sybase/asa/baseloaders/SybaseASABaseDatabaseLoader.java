package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseGroup;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBasePreDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseUser;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseWebService;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class SybaseASABaseDatabaseLoader {

	protected ICatalogObject catalogObject;
	protected SybaseASABaseDatabase database;

	private SoftReference schemaLoaderRef;
	
	public SybaseASABaseDatabaseLoader(SybaseASABaseDatabase catalogDatabase)
	{
		this.catalogObject = (ICatalogObject)catalogDatabase;
		this.database = catalogDatabase;
	}
	
	protected JDBCSchemaLoader createSchemaLoader() {
		return new ASABaseSchemaLoader(catalogObject);
	}
	
	protected SybaseASABaseDBSpace createCatalogDBSpace()
	{
		return new SybaseASACatalogBaseDBSpace();
	}
	
	protected SybaseASABaseGroup createCatalogGroup() {
		return new SybaseASACatalogBaseGroup(catalogObject);
	}
	
	protected SybaseASABaseUser createCatalogUser()
	{
		return new SybaseASACatalogBaseUser(catalogObject);
	}
	
	protected SybaseASABaseEvent createCatalogEvent()
	{
		return new SybaseASACatalogBaseEvent();
	}
	
//	protected Schema createCatalogSchema()
//	{
//		return new SybaseASACatalogBaseSchema();
//	}
	
	final public void loadSchemas(EList schemaConstainmentList) {
		try {
			boolean deliver = database.eDeliver();
			database.eSetDeliver(false);
			
			List existingSchemas = new ArrayList(schemaConstainmentList.size());
			existingSchemas.addAll(schemaConstainmentList);
			getSchemaLoader().clearSchemas(schemaConstainmentList);
			getSchemaLoader().loadSchemas(schemaConstainmentList, existingSchemas);

			database.eSetDeliver(deliver);
		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	private JDBCSchemaLoader getSchemaLoader() {
		if (schemaLoaderRef == null || schemaLoaderRef.get() == null) {
			schemaLoaderRef = new SoftReference(createSchemaLoader());
		}
		return (JDBCSchemaLoader) schemaLoaderRef.get();
	}
	
	static public class ASABaseSchemaLoader extends JDBCSchemaLoader {

		public ASABaseSchemaLoader(ICatalogObject catalogObject) {
			super(catalogObject, null);
		}

		protected Schema createSchema() {
			return new SybaseASACatalogBaseSchema();
		}
	}
	
	//TODO:
	public void loadWebServices(EList webserviceConstraintmentList)
	{
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);
		webserviceConstraintmentList.clear();
		try {
			ResultSet r = getWSs();
			if (r != null) {
				while(r.next()) {				
					long serviceId = r.getLong("service_id"); //$NON-NLS-1$
					String serviceName = r.getString("service_name"); //$NON-NLS-1$
					String serviceType = r.getString("service_type"); //$NON-NLS-1$
					String authRequired = r.getString("auth_required"); //$NON-NLS-1$
					String secureRequired = r.getString("secure_required"); //$NON-NLS-1$
					String urlPath = r.getString("url_path"); //$NON-NLS-1$
					String userName = r.getString("User"); //$NON-NLS-1$
					String parameter = r.getString("parameter"); //$NON-NLS-1$
					String statement = r.getString("statement"); //$NON-NLS-1$
					String remarks = r.getString("remarks"); //$NON-NLS-1$
					
					SybaseASAWebService ws = new SybaseASACatalogBaseWebService();
					if (serviceName != null) 
						ws.setName(serviceName);
					ws.setService_id(serviceId);
					if (serviceType != null)
						ws.setService_type(serviceType);
					if (authRequired != null)
						ws.setAuth_required(authRequired);
					if (secureRequired != null)
						ws.setSecure_required(secureRequired);
					if (urlPath != null) 
						ws.setUrl_path(urlPath);
					if (userName != null)
						ws.setUser_name(userName);
					if (parameter != null)
						ws.setParameter(parameter);
					if (statement != null)
						ws.setStatement(statement);
					if (remarks != null)
					{
						ws.getComments().add(remarks);
					}
					if (!hasWS(webserviceConstraintmentList, serviceId)) {
						if (ws.eIsProxy()) {
							EcoreUtil.resolve(ws, database);
						}
						webserviceConstraintmentList.add(ws);
					}
				}
			}
			if (r != null)
				r.close();
		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		database.eSetDeliver(deliver);
	}
	
	private boolean hasWS ( EList list, long serviceID ) {
		boolean result = false;
		for (int i = 0; i < list.size(); i++) {
			SybaseASAWebService ws = (SybaseASAWebService) list.get(i);
			if (ws.getService_id() == serviceID) {
				return true;
			}
		}
		return result;
	}
	
	private ResultSet getWSs ( ) {
		Connection connection = catalogObject.getConnection();
		try {
			String query = "select service_id, service_name, service_type,auth_required," +  //$NON-NLS-1$
				"secure_required, url_path, User_name(user_id)'User'," + //$NON-NLS-1$
				"parameter, statement, remarks  from syswebservice where service_type = 'SOAP' and " + //$NON-NLS-1$
				"statement like 'call%' order by service_id"; //$NON-NLS-1$
			Statement s = connection.createStatement();
			ResultSet r2 = s.executeQuery(query); 
			return r2;
		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		return null;
	}

	final public void loadDataTypes(EList datatypeConstainmentList) 
	{
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);	
		
		datatypeConstainmentList.clear();
		
		try {
			List list = SybaseASACatalogUtils.getDBDatatypes(database.getName(), catalogObject.getConnection());
			for (int i = 0; i < list.size(); i++) {
				final String typename = (String) list.get(i);
				SybaseASACatalogBasePreDefinedType datatype = new SybaseASACatalogBasePreDefinedType();
				datatype.setName(typename);
				datatypeConstainmentList.add(datatype);
			}
		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		database.eSetDeliver(deliver);
	}

	final public void loadEvents(EList eventContainmentList) 
	{
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);
		
		List existingEvents = new ArrayList(eventContainmentList.size());
		existingEvents.addAll(eventContainmentList);
		eventContainmentList.clear();
		
		Connection conn = catalogObject.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ASASQLs.QUERY_EVENTS);
			while(rs.next()) {				
				String eventName = rs.getString("event_name"); //$NON-NLS-1$
				String creator = rs.getString("user_name");
				SybaseASABaseEvent event;
				Object element = SybaseASACatalogUtils.findElement(existingEvents,eventName);

				Schema schema = (Schema)SybaseASACatalogUtils.findElement(database.getSchemas(), creator);
				
				if (element != null) {
					eventContainmentList.add(element);
					((SybaseASABaseEvent)element).setEventCreator(schema);
					((ICatalogObject)element).refresh();
				} else {
					event = createCatalogEvent();
					event.setName(eventName);
					event.setEventCreator(schema);
					eventContainmentList.add(event);
				}
				
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		database.eSetDeliver(deliver);
	}
	

	final public void loadAuthIds(EList authIdContainmentList) {
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);

		List existingAuths = new ArrayList(authIdContainmentList.size());
		existingAuths.addAll(authIdContainmentList);
		authIdContainmentList.clear();
		
		Connection conn = catalogObject.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ASASQLs.QUERY_USER_GROUP);
			while(rs.next())
			{
				String authIdName = rs.getString(1);
				boolean isGroup = rs.getString(2).equals("Y");
				SybaseAuthorizationIdentifier id = null;
				if(isGroup)
				{
					EClass clazz = SQLAccessControlPackage.eINSTANCE.getGroup();
					id = (SybaseASABaseGroup) SybaseASACatalogUtils.findElement(existingAuths, authIdName, clazz);
					if(id == null)
					{
						id = createCatalogGroup();
						id.setName(authIdName);
                        id.setSqlContainer(database);
						authIdContainmentList.add(id);
					}
					else
					{
						authIdContainmentList.add(id);
						((ICatalogObject)id).refresh();
					}
				}
				else
				{
					EClass clazz = SQLAccessControlPackage.eINSTANCE.getUser();
					id = (SybaseASABaseUser) SybaseASACatalogUtils.findElement(existingAuths, authIdName, clazz);
					if(id == null)
					{
						id = createCatalogUser();
						id.setName(authIdName);
                        id.setSqlContainer(database);
						authIdContainmentList.add(id);
					}
					else
					{
						authIdContainmentList.add(id);
						((ICatalogObject)id).refresh();
					}
				}
			}
		}
		catch(SQLException e)
		{
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}

		database.eSetDeliver(deliver);
	}

	public void loadDbSpaces(EList dbspaceContainmentList) {
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);
		
		List existingDBSpaces = new ArrayList(dbspaceContainmentList.size());
		existingDBSpaces.addAll(dbspaceContainmentList);
		dbspaceContainmentList.clear();
		
		Connection conn = catalogObject.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ASASQLs.QUERY_DBSPACES);
			while(rs.next())
			{
				String dbSpaceName = rs.getString(1);
				SybaseASABaseDBSpace dbSpace = (SybaseASABaseDBSpace)SybaseASACatalogUtils.findElement(existingDBSpaces, dbSpaceName);
				if(dbSpace == null)
				{
					dbSpace = createCatalogDBSpace();
					dbSpace.setName(dbSpaceName);
					dbspaceContainmentList.add(dbSpace);
				}
				else
				{
					dbspaceContainmentList.add(dbSpace);
					((ICatalogObject)dbSpace).refresh();
				}
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		database.eSetDeliver(deliver);
	}
	
	final public void loadDbInfo1() {
		
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);
		
		Connection conn = catalogObject.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = createDbInfo1ResultSet(conn);
			stmt = rs.getStatement();
			while(rs.next())
			{
				processDbInfo1ResultSet(rs);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		database.eSetDeliver(deliver);		
	}

	protected ResultSet createDbInfo1ResultSet(Connection conn) throws SQLException
	{
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(ASASQLs.QUERY_DB_INFO1);
	}
	
	protected void processDbInfo1ResultSet(ResultSet rs) throws SQLException
	{
		String fileName = rs.getString(1);
        String logFileName = rs.getString(2);
        String mirrorLogFileName = rs.getString(3);
        int pageSize = rs.getInt(4);
        String encryptionSpec = rs.getString(5);
        boolean isCaseSensitive = rs.getString(6).equalsIgnoreCase("ON");
        boolean hasBlankPadding = rs.getString(7).equalsIgnoreCase("ON");
        String collationLabel = rs.getString(8);
        boolean isCheckSum = rs.getString(9).equalsIgnoreCase("ON");
        Boolean isPasswordSensitive = null; // for ASA10, password always case sensitive
        if(!this.database.isBaseOnASA10())
        {
            isPasswordSensitive = Boolean.valueOf(rs.getString(10).equalsIgnoreCase("ON"));
        }
        database.setDatabaseFileName(fileName);
        database.setLogFileName(logFileName);
        database.setMirrorFileName(mirrorLogFileName);
        database.setPageSize(pageSize);
        database.setCaseSensitive(isCaseSensitive);
        database.setBlankPaddingOn(hasBlankPadding);
        database.setCollation(collationLabel);
        database.setCheckSumOn(isCheckSum);
        database.setPasswordCaseSensitive(isPasswordSensitive);
        
        EncryptionInfo encryInfo = SybaseasabasesqlmodelFactory.eINSTANCE.createEncryptionInfo();
		if(encryptionSpec == null || encryptionSpec.length() == 0 || encryptionSpec.equalsIgnoreCase("None"))
			encryInfo = null;
        else if(encryptionSpec.equalsIgnoreCase("Simple"))
            encryInfo.setAlgorithm(null);
        else if(encryptionSpec.equalsIgnoreCase("AES"))
        	encryInfo.setAlgorithm("AES");
        else if(encryptionSpec.equalsIgnoreCase("AES_FIPS"))
        	encryInfo.setAlgorithm("AES_FIPS");
        else if(encryptionSpec.equalsIgnoreCase("MDSR"))
        	encryInfo.setAlgorithm("MDSR");
		//we can not get the KEY value
		database.setEncryptionInfo(encryInfo);
	}
	
	final public void loadDbInfo2() {
		boolean deliver = database.eDeliver();
		database.eSetDeliver(false);
		
		Connection conn = catalogObject.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = createDbInfo2ResultSet(conn);
			stmt = rs.getStatement();
			while(rs.next())
			{
                processDbInfo2ResutSet(rs);
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		
		database.eSetDeliver(deliver);
	}
	
	protected ResultSet createDbInfo2ResultSet(Connection conn) throws SQLException
	{
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(ASASQLs.QUERY_DB_INFO2);
	}
	
	protected void processDbInfo2ResutSet(ResultSet rs) throws SQLException
	{
		String jdkVersion1 = rs.getString(1);
        boolean isJarSupport = rs.getString(2).equalsIgnoreCase("Y");
        boolean isJconnectSupport = rs.getString(3).equalsIgnoreCase("Y");
        
        JavaSupportType jst = null;
        if(jdkVersion1 != null || isJarSupport)
        {
			//TODO: not sure about jdk version retrieving
            if ("1.3".equals(jdkVersion1))
            {
                jst = JavaSupportType.JDK13_LITERAL;
            }
            else if ("1.18".equals(jdkVersion1))
            {
                jst = JavaSupportType.JDK118_LITERAL;
            }
            else
            {              
                jst = JavaSupportType.ON_LITERAL;  
                
            }
        }
        else
        {
        	jst = JavaSupportType.OFF_LITERAL; 
        }
        database.setJavaSupport(jst);
        database.setJConnectOn(isJconnectSupport);
	}
}
