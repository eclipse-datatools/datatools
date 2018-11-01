/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IOfflineConnection;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.IServerVersionProvider;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.VersionProviderConnection;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent.IChangedProperty;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.ResourceUtil;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.CatalogUtil;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.DatabaseProviderHelper;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;


public class ConnectionInfoImpl extends VersionProviderConnection implements ConnectionInfo, IOfflineConnection {
	
	private static final String PASSWORD = "password"; //$NON-NLS-1$
	private static final String USER = "user"; //$NON-NLS-1$

	private DatabaseDefinition definition;
	private String loadingPath = ""; //$NON-NLS-1$
	private String driverClassName = ""; //$NON-NLS-1$
	private String url = ""; //$NON-NLS-1$
	private Properties properties = new Properties();
	private String name;
	private String databaseName = null;
	private String identifierQuoteString = null;
    private String databaseProductVersion = null;
	private Connection sharedConnection = null;
	private Database sharedDatabase = null;
	private Collection listeners = new LinkedList();
	private Hashtable filters = null;
	private Collection filterListeners = new LinkedList();
	private Collection projects = null;
	private boolean detectDefinition = false;
	private IConnection jdbcConnection;
	private Throwable connectException;
	private IPropertySetListener profilePropertyListener = new IPropertySetListener() {

		public void propertySetChanged(IPropertySetChangeEvent event) {
			if (ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID.equals(event
					.getPropertySetType())) {
				processFilterChanges(event);
			}
		}
	};
	
	public static final String TECHNOLOGY_ROOT_KEY = "jdbc"; //$NON-NLS-1$
	
	/* package */static IPath getConnectionDirectory(String name) {
		IPath path = RDBCorePlugin.getDefaultStateLocation();
		path = path.append(ConnectionInfo.CONNECTION);
		if(name != null) path = path.append(name + "/"); //$NON-NLS-1$
		return path;
	}
	
	/* package */static File getConnectionFile(String name) {
		IPath connectionPath = getConnectionDirectory(name);
		return connectionPath.append("cache.xmi").toFile();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if(this.name == null) throw new IllegalStateException();
		if(name == null) throw new NullPointerException();
		if(name.trim().equals("")) throw new IllegalArgumentException(); //$NON-NLS-1$
//		ConnectionManagerImpl mgr = (ConnectionManagerImpl) RDBCorePlugin.getDefault().getConnectionManager();
//		if(mgr.getConnectionInfo(name) != null) throw new IllegalArgumentException();
//		mgr.rename(this.name, name);
		this.name = name;
	}
	
	public DatabaseDefinition getDatabaseDefinition() {
		return this.definition;
	}

	public String getLoadingPath() {
		return this.loadingPath;
	}

	public void setLoadingPath(String path) {
		this.loadingPath = path;
	}

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public void setDriverClassName(String className) {
		this.driverClassName = className;
	}

	public String getURL() {
		return this.url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public Properties getProperties() {
		return this.properties;
	}

	
//	public Connection connect() throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
//		Connection cnn = ((ConnectionManagerImpl) RDBCorePlugin.getDefault().getConnectionManager()).connect(this);
//		if(cnn != null && this.detectDefinition) {
//			this.detectDefinition = false;
//			DatabaseDefinition def = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().recognize(cnn);
//			if(def != null) {
//				this.definition = def;
//			}
//		}
//		return cnn;
//	}

	public String getUserName() {
		return (String) this.properties.get(USER);
	}

	public void setUserName(String id) {
		this.properties.put(USER, id);
	}

	public String getPassword() {
		return (String) this.properties.get(PASSWORD);		
	}

	public void setPassword(String password) {
		this.properties.put(PASSWORD, password);
	}

	public String getDatabaseName() {
		return this.databaseName;
	}

	public void setDatabaseName(String databasename) {
		this.databaseName = databasename;
	}

    public String getIdentifierQuoteString() {
        return this.identifierQuoteString;
    }

    public void setIdentifierQuoteString(String quote) {
        this.identifierQuoteString = quote;
    }
    
    public String getDatabaseProductVersion() {
       return this.databaseProductVersion;
    }
    
    public void setDatabaseProductVersion(String prodVersion) {
       this.databaseProductVersion = prodVersion;
    }
    
	public void setSharedConnection(Connection connection) {
		if(connection == null) {
			removeSharedConnection();
		}
		else {
			try {
				if(connection.isClosed()) throw new IllegalStateException();
			}
			catch(Throwable o) {
				throw new IllegalStateException();
			}
			
			if(this.sharedConnection == null) {
				this.sharedConnection = connection;
				Collection c = new LinkedList();
				c.addAll(this.listeners);
				Iterator it = c.iterator();
				while(it.hasNext()) {
					ConnectionSharingListener l = (ConnectionSharingListener) it.next();
					try {
						l.sharedConnectionAdded(this, connection);
					}
					catch(Throwable o) {
						this.removeConnectionSharingListener(l);
					}
				}
			}
			else {
				throw new IllegalStateException();
			}			
		}
	}
	
	public IConnection getJDBCConnectionWrapper()
	{
		return jdbcConnection;
	}
	
	/**
	 * Same effect as calling removeSharedConnection(), 
	 * followed by setSharedConnection()
	 * but this does not throw an IllegalStateException
	 * 
	 * Its effect is to simply switch out the underlying
	 * Connection object and notify any listeners
	 * 	
	 * @param connection, the Connection object to be replaced
	 */
	public void replaceSharedConnection(Connection connection)
	{
		Connection existingSharedConnection = sharedConnection;
		Collection c = new LinkedList();
		c.addAll(listeners);
		Iterator removingIterator = c.iterator();
		while(removingIterator.hasNext()) {
			ConnectionSharingListener l = (ConnectionSharingListener) removingIterator.next();
			try {
				l.sharedConnectionRemove(this, existingSharedConnection);
			}
			catch(Throwable o) {
				this.removeConnectionSharingListener(l);
			}
		}
		
		sharedConnection = connection;
			
		Iterator addingIterator = c.iterator();
		while(addingIterator.hasNext()) {
			ConnectionSharingListener l = (ConnectionSharingListener) addingIterator.next();
			try {
				l.sharedConnectionAdded(this, connection);
			}
			catch(Throwable o) {
				this.removeConnectionSharingListener(l);
			}
		}
	}
	

	public Connection getSharedConnection() {
		return this.sharedConnection;
	}

	public void removeSharedConnection() {
		if(this.sharedConnection == null) throw new IllegalStateException();
		Connection connection = this.sharedConnection;
		this.sharedConnection = null;
		Collection c = new LinkedList();
		c.addAll(this.listeners);
		Iterator it = c.iterator();
		while(it.hasNext()) {
			ConnectionSharingListener l = (ConnectionSharingListener) it.next();
			try {
				l.sharedConnectionRemove(this, connection);
			}
			catch(Throwable o) {
				this.removeConnectionSharingListener(l);
			}
		}		
	}

	public void setSharedDatabase(Database database) {
		if(database == null) {
			removeSharedDatabase();
		}
		else {
			if(this.sharedDatabase == null) {
				this.sharedDatabase = database;
				
				DatabaseConnectionRegistry.getInstance().registerConnectionForDatabase(this,database);

				Collection c = new LinkedList();
				c.addAll(this.listeners);
				Iterator it = c.iterator();
				while(it.hasNext()) {
					ConnectionSharingListener l = (ConnectionSharingListener) it.next();
					try {
						l.sharedDatabaseAdded(this, database);
					}
					catch(Throwable o) {
						this.removeConnectionSharingListener(l);
					}
				}
			}
			else {
				throw new IllegalStateException();				
			}
		}
	}

	public Database getSharedDatabase() {
		return this.sharedDatabase;
	}

	public void removeSharedDatabase() {
		if(this.sharedDatabase == null) throw new IllegalStateException();
		Database database = this.sharedDatabase;

		DatabaseConnectionRegistry.getInstance().unregisterConnectionForDatabase(database);

		this.sharedDatabase = null;
		Collection c = new LinkedList();
		c.addAll(this.listeners);
		Iterator it = c.iterator();
		while(it.hasNext()) {
			ConnectionSharingListener l = (ConnectionSharingListener) it.next();
			try {
				l.sharedDatabaseRemove(this, database);
			}
			catch(Throwable o) {
				this.removeConnectionSharingListener(l);
			}
		}				
	}

	public boolean addConnectionSharingListener(ConnectionSharingListener listener) {
		if(listener == null) throw new NullPointerException();
		Iterator it = this.listeners.iterator();
		while(it.hasNext()) {
			if(listener == it.next()) {
				return false;
			}
		}
		
		listeners.add(listener);
		return true;
	}

	public boolean removeConnectionSharingListener(ConnectionSharingListener listener) {
		if(listener == null) throw new NullPointerException();
		Iterator it = this.listeners.iterator();
		while(it.hasNext()) {
			if(listener == it.next()) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public void cacheDatabase(Database database) throws IOException {
		if(this.name == null) throw new IllegalStateException();
	    initConnectionDirectory();
		OutputStream out = new FileOutputStream(getConnectionFile(name));
		Resource r = new XMIResourceImpl();
		r.getContents().add(database);
		ResourceUtil.resolveDanglingReferences(r);
		Map options = new HashMap();
		options.put(XMIResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		r.save(out, options);
		r.getContents().clear();
	}

	public Database getCachedDatabase() {
		if(this.name == null) throw new IllegalStateException();
		initConnectionDirectory();
		File file = getConnectionFile(name);
		if(file.exists()) {
			Resource r = new XMIResourceImpl();
			try {
				InputStream in = new FileInputStream(file);
				r.load(in, (Map) null);
				EList l = r.getContents();
				Database database = (Database) l.get(0);
				l.clear();
				return database;
			}
			catch(Throwable o) {
			    o.printStackTrace();
				// ignore all exceptions
			}
		}
		return null;
	}

	public long getCachedDatabaseTimestamp() {
		if(this.name == null) throw new IllegalStateException();
	    IPath path = initConnectionDirectory();
		path = path.append("cache.xmi"); //$NON-NLS-1$
		File file = path.toFile();
		if(file.exists()) {
			return file.lastModified();
		}
		return 0;
	}

	void setDatabaseDefinition(DatabaseDefinition def) {
		this.definition = def;
	}
	
	private IPath initConnectionDirectory() {
		IPath path = getConnectionDirectory(name);
		File dir = path.toFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}	
		return path;
	}

	public void addFilter(String key, ConnectionFilter filter){
		if (this.filters == null) this.loadFilterInfo();
		
		if (this.filters.containsKey(key)) {
			this.filters.remove(key);
		}
		
		this.filters.put(key, filter);
		try {
			this.saveFilterInfo();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		fireFilterAdded(key);
	}
	
	public void removeFilter(String key){
		if (this.filters == null) this.loadFilterInfo();

		if (this.filters.containsKey(key)){
			this.filters.remove(key);
			try {
				this.saveFilterInfo();
			}catch(Exception e) {
			}
			
			fireFilterRemoved(key);
		}
	}

	public ConnectionFilter getFilter(String key){
		if (this.filters == null) this.loadFilterInfo();
		if (key != null) {
			if (this.filters.containsKey(key)){
				return (ConnectionFilter) this.filters.get(key);
			}
		}
		return null;
	}

	public Iterator getFilters(){
		List filters = new LinkedList();

		Enumeration e = this.filters.elements();
		while (e.hasMoreElements()) {
			filters.add(e.nextElement());
		}
		
		return filters.iterator();
	}

	public IProject[] getDependentProjects() {
		if(this.projects == null) {
			loadDependentProjects();
		}
		IProject[] p = new IProject[this.projects.size()];
		this.projects.toArray(p);
		return p;
	}
	
	public void addDependentProject(IProject proj) {
		if(this.projects == null) {
			loadDependentProjects();
		}
		this.projects.add(proj);
		this.saveDependentProjects();
	}
	
	public void removeDependentProject(IProject proj) {
		if(this.projects == null) {
			loadDependentProjects();
		}
		this.projects.remove(proj);
		this.saveDependentProjects();
	}
	
	void onSQLException(Connection connection, SQLException exception) {
		Collection c = new LinkedList();
		c.addAll(this.listeners);
		Iterator it = c.iterator();
		while(it.hasNext()) {
			ConnectionSharingListener l = (ConnectionSharingListener) it.next();
			try {
				l.onSQLException(this, connection, exception);
			}
			catch(Throwable o) {
				this.removeConnectionSharingListener(l);
			}
		}			    
	}
	
	private void loadDependentProjects() {
		this.projects = new HashSet();
		
		IPath path = initConnectionDirectory();
		path = path.append("projects"); //$NON-NLS-1$
		File file = path.toFile();
		if(!file.exists()) return;
		
		try {
			InputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			LinkedList l = (LinkedList) objectInput.readObject();
			objectInput.close();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			Iterator i = l.iterator();
			while(i.hasNext()) {
				String name = (String) i.next();
				IProject p = root.getProject(name);
				if(p != null) this.projects.add(p);
			}
		}
		catch(Exception e) {
			// ignore all exceptions
		}
	}
	
	private void saveDependentProjects() {
		IPath path = initConnectionDirectory();
		path = path.append("projects"); //$NON-NLS-1$
		File file = path.toFile();
		
		LinkedList l = new LinkedList();
		Iterator i = this.projects.iterator();
		while(i.hasNext()) {
			l.add(((IProject) i.next()).getName());
		}
		
		try {
			OutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(l);
			objectOutput.close();
		}
		catch(IOException e) {
			// ignore all exceptions
		}
	}

	private void saveFilterInfo() throws IOException, FileNotFoundException {
		if (this.name == null || this.name.equals("")) return; //$NON-NLS-1$
		
		IPath path = initConnectionDirectory();
		String	filename = ConnectionInfo.FILTER + "." + ConnectionInfo.INFO_FILE_EXTENSION; //$NON-NLS-1$ //$NON-NLS-2$
		path = path.append(filename);
		File file = path.toFile();
		
		OutputStream fileOutput = new FileOutputStream(file);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		objectOutput.writeObject(this.filters);
		objectOutput.close();
	}
	
	private void loadFilterInfo() {
		Properties props = getConnectionProfile().getProperties(ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID);
		this.filters = new Hashtable();
		for (Iterator it = props.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String filterID = (String) entry.getKey();
			if (filterID != null) {
				String predicate = (String) entry.getValue();
				if (predicate != null && predicate.length() > 0) {
					ConnectionFilter filter = new ConnectionFilterImpl(
							predicate);
					filters.put(filterID, filter);
				}
			}
		}
	}

	public boolean addFilterListener(ConnectionFilterListener listener) {
		if(listener == null) throw new NullPointerException();
		Iterator it = this.filterListeners.iterator();
		while(it.hasNext()) {
			if(listener == it.next()) return false;
		}

		this.filterListeners.add(listener);
		return true;
	}

	public boolean removeFilterListener(ConnectionFilterListener listener) {
		if(listener == null) throw new NullPointerException();
		Iterator it = this.filterListeners.iterator();
		while(it.hasNext()) {
			if(listener == it.next()) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public void discoverDatabaseDefinitionWhenConnect() {
		this.detectDefinition = true;
	}
	
	public boolean isDiscoverDatabaseDefinitionWhenConnectEnabled () {
		return this.detectDefinition;
	}

    public ConnectionInfoImpl(IConnectionProfile profile, Class factoryClass) {
        super(profile, factoryClass);
        String vendor = null;
        String version = null;
        String databaseName = null;
        try{
            vendor = profile.getBaseProperties().getProperty(
                IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
            version = profile.getBaseProperties().getProperty(
                IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
            databaseName = profile.getBaseProperties().getProperty(
                    IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID);
            if (databaseName == null || databaseName.trim().length() == 0) {
            	databaseName = profile.getName();
           	}
        } catch (Exception e){
            e.printStackTrace();
        }
        DatabaseDefinitionRegistry defRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition dbDef = defRegistry.getDefinition(vendor, version);
        if (dbDef == null){
        	dbDef = defRegistry.getDefinition("Generic JDBC", "1.0");
        }
        this.setDatabaseDefinition(dbDef);
        this.setDatabaseName(databaseName);
        
        this.name = profile.getName();
    }
    
	public ConnectionInfoImpl(final IConnectionProfile profile, Class factoryClass, boolean createConnection) {
		this(profile, factoryClass);
        
        if (createConnection)
        {
        	initializeJDBCConnection();
        }
        else
        {
            jdbcConnection = null;
            Database database = getCachedDatabase();
            if (database != null) {
            	setSharedDatabase(database);
            }
        }
        profile.addPropertySetListener(profilePropertyListener);
	}
	
	private void initializeJDBCConnection() {
		IConnectionProfile profile = getConnectionProfile();
		jdbcConnection = profile.createConnection(Connection.class.getName());
		connectException = jdbcConnection.getConnectException();
		Connection connection = (Connection) jdbcConnection.getRawConnection();
		if (connection != null) {
			DatabaseDefinition detectedDBDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().recognize(connection);
			if(detectedDBDefinition != null) {
				Properties props = copyProperties(profile.getProperties(getConnectionProfile().getProviderId()));
				props.setProperty(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID, detectedDBDefinition.getProduct());
				props.setProperty(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID, detectedDBDefinition.getVersion());				
				profile.setProperties(getConnectionProfile().getProviderId(), props);
				this.setDatabaseDefinition(detectedDBDefinition);
			}
			this.setSharedConnection(connection);
	        new DatabaseProviderHelper().setDatabase(new ConnectionAdapter(this,connection),
	                this, this.getDatabaseName());
		}
	}
	
	private Properties copyProperties(Properties properties) {
		Properties copy = new Properties();
		Enumeration propertyKeys = properties.keys();
		while (propertyKeys.hasMoreElements()) {
			Object key = propertyKeys.nextElement();
			copy.put(key, properties.get(key));
		}
		return copy;
	}
	
	private void processFilterChanges(IPropertySetChangeEvent event) {
		if (this.filters == null) this.loadFilterInfo();
		for (Iterator it = event.getChangedProperties().values().iterator(); it.hasNext();) {
			IChangedProperty changedProperty = (IChangedProperty)it.next();
			if (changedProperty.getNewValue() == null) {
				// filter removed
				filters.remove(changedProperty.getID());
				fireFilterRemoved(changedProperty.getID());
			}
			else if (changedProperty.getOldValue() == null) {
				// filter added
				filters.put(changedProperty.getID(), new ConnectionFilterImpl(changedProperty.getNewValue()));
				fireFilterAdded(changedProperty.getID());
			}
			else {
				// filter changed
				filters.put(changedProperty.getID(), new ConnectionFilterImpl(changedProperty.getNewValue()));
				fireFilterChanged(changedProperty.getID());
			}
		}
	}
	
	private void fireFilterRemoved(String filterID) {
		Collection cachedListener = new LinkedList();
		cachedListener.addAll(filterListeners);
		Iterator it = cachedListener.iterator();
		while (it.hasNext()) {
			ConnectionFilterListener l = (ConnectionFilterListener) it.next();
			try {
				l.connectionFilterRemoved(filterID);
			}
			catch (Throwable e) {
				RDBCorePlugin.getDefault().getLog().log(
						new Status(Status.ERROR, RDBCorePlugin.getSymbolicName(), -1, 
								e.getLocalizedMessage() == null ? new String()
								: e.getLocalizedMessage(), e));
			}
		}
	}

	private void fireFilterAdded(String filterID) {
		Collection cachedListener = new LinkedList();
		cachedListener.addAll(filterListeners);
		Iterator it = cachedListener.iterator();
		while (it.hasNext()) {
			ConnectionFilterListener l = (ConnectionFilterListener) it.next();
			try {
				l.connectionFilterAdded(filterID);
			}
			catch (Throwable e) {
				RDBCorePlugin.getDefault().getLog().log(
						new Status(Status.ERROR, RDBCorePlugin.getSymbolicName(), -1, 
								e.getLocalizedMessage() == null ? new String()
								: e.getLocalizedMessage(), e));
			}
		}
	}

	private void fireFilterChanged(String filterID) {
		Collection cachedListener = new LinkedList();
		cachedListener.addAll(filterListeners);
		Iterator it = cachedListener.iterator();
		while (it.hasNext()) {
			ConnectionFilterListener l = (ConnectionFilterListener) it.next();
			try {
				l.connectionFilterAdded(filterID);
			}
			catch (Throwable e) {
				RDBCorePlugin.getDefault().getLog().log(
						new Status(Status.ERROR, RDBCorePlugin.getSymbolicName(), -1, 
								e.getLocalizedMessage() == null ? new String()
								: e.getLocalizedMessage(), e));
			}
		}
	}

	private void test(){
		
		Database db = this.getSharedDatabase();
		Iterator schemasIter = db.getSchemas().iterator();
		
		while (schemasIter.hasNext()){
			Schema schema = (Schema)schemasIter.next();
			System.out.println("Schema " + schema.getName() + " has tables:"); //$NON-NLS-1$ //$NON-NLS-2$
			EList tables = schema.getTables();
			Iterator tableIter = tables.iterator();
			while(tableIter.hasNext()){		
			System.out.println(((Table)tableIter.next()).getName());
			}	
		}
	}
	
	public void close() {
		getConnectionProfile().removePropertySetListener(
				profilePropertyListener);
		if (getSharedDatabase() != null) {
			setSharedDatabase(null);
		}
		if (getSharedConnection() != null) {
			setSharedConnection(null);
		}
		if (jdbcConnection != null) {
			jdbcConnection.close();
			jdbcConnection = null;
		}
	}

	public Throwable getConnectException() {
		return connectException;
	}

	public String getProviderName() {
		return jdbcConnection == null ? getConnectionProfile().getProperties(
				ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID)
				.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME)
				: ((IServerVersionProvider) jdbcConnection).getProviderName();
	}

	public Version getProviderVersion() {
		return jdbcConnection == null ? Version
				.valueOf(getConnectionProfile()
						.getProperties(
								ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID)
						.getProperty(
								ConnectionProfileConstants.PROP_SERVER_VERSION))
				: ((IServerVersionProvider) jdbcConnection)
						.getProviderVersion();
	}

	protected String getTechnologyRootKey() {
		return TECHNOLOGY_ROOT_KEY;
	}

	public String getTechnologyName() {
		return jdbcConnection == null ? getConnectionProfile()
				.getProperties(
						ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID)
				.getProperty(
						ConnectionProfileConstants
								.createTechnologyNameKey(getTechnologyRootKey()))
				: ((IServerVersionProvider) jdbcConnection).getTechnologyName();
	}

	public Version getTechnologyVersion() {
		return jdbcConnection == null ? Version
				.valueOf(getConnectionProfile()
						.getProperties(
								ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID)
						.getProperty(
								ConnectionProfileConstants
										.createTechnologyVersionKey(getTechnologyRootKey())))
				: ((IServerVersionProvider) jdbcConnection)
						.getTechnologyVersion();
	}

	public Object getRawConnection() {
		return this;
	}

	public void attach(IProgressMonitor monitor) throws CoreException {
		initializeJDBCConnection();
		monitor.done();
	}

	public void detach(IProgressMonitor monitor) throws CoreException {
		save(monitor);
		removeSharedConnection();
	}

	public boolean isWorkingOffline() {
		return connectException == null && sharedConnection == null
				&& sharedDatabase != null;
	}

	public void save(IProgressMonitor monitor) throws CoreException {
		monitor
				.beginTask(
						"Save Offline SQL Model for " + getName(), 100);
		monitor.worked(5);
		new CatalogUtil().load(sharedDatabase, monitor, 90);
		if (!monitor.isCanceled()) {
			monitor.subTask(""); //$NON-NLS-1$
			try {
				cacheDatabase(sharedDatabase);
			}
			catch (IOException e) {
				Status status = new Status(Status.ERROR, RDBCorePlugin.getSymbolicName(), -1,
						"Error saving offline SQL Model for {0}.", e);
				throw new CoreException(status);
			}
		}
		else {
			throw new CoreException(Status.CANCEL_STATUS);
		}
	}

}
