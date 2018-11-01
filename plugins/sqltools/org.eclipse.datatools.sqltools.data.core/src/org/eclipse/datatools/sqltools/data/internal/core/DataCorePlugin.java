/*******************************************************************************
 * Copyright 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.common.DefaultColumnDataAccessor;
import org.eclipse.datatools.sqltools.data.internal.core.common.IColumnDataAccessor;
import org.osgi.framework.BundleContext;

import com.ibm.icu.util.StringTokenizer;


/**
 * The main plugin class to be used in the desktop.
 */
public class DataCorePlugin extends Plugin
{
	//The shared instance.
	private static DataCorePlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
    private static final String SQ = "'"; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String SQ2 = "`"; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String DQ = "\""; //$NON-NLS-1$ //$NON-NLS-2$

	//public static final String ID = "org.eclipse.wst.rdb.data.core"; //$NON-NLS-1$
	public static final String ID = "org.eclipse.datatools.sqltools.data.core"; //$NON-NLS-1$
	
    // TEMPORARY:  Starting with JDBC 4.0 (Java 6), JDBC uses Types.SQLXML (value 2009) instead of Types.OTHER 
    // for the datatype of XML columns.  We compile with Java 5, so we can't use that constant.  However we can 
    // be run with Java 6, so we might encounter the JDBC 4.0 value. Therefore we define a stand-in constant here 
    // for the real JDBC 4.0 value.  This temporary constant can be removed and its usage replaced with the real 
    // JDBC constant when we know that this code is being compiled with Java 6. 
    public static final int Types_SQLXML = 2009; 
    
	protected Vector columnDataAccessors;
	
	/**
	 * The constructor.
	 */
	public DataCorePlugin() {
		super();
		plugin = this;
		try {
			//resourceBundle = ResourceBundle.getBundle("org.eclipse.datatools.sqltools.data.internal.core.CorePluginResources"); //$NON-NLS-1$	
			resourceBundle = ResourceBundle.getBundle("org.eclipse.datatools.sqltools.data.internal.core"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		listColumnDataAccessors();
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static DataCorePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = DataCorePlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	/**
	 * Method writeLog.
	 * @param severity - the severity; one of IStatus.OK, IStatus.ERROR, IStatus.INFO, or IStatus.WARNING
	 * @param code - the plug-in-specific status code, or OK
	 * @param message - a human-readable message, localized to the current locale
	 * @param exception- a low-level exception, or null if not applicable
	 */
	public void writeLog(int severity, int code, String message, Throwable exception) {
		if (message == null)
			message = ""; //$NON-NLS-1$
		getLog().log(
				new Status(severity, getBundle().getSymbolicName(), code, message, exception));
	}
	
    public static String getQualifiedTableName(Table table) { 
    	Database db = table.getSchema().getCatalog() != null ?
    			table.getSchema().getCatalog().getDatabase():
    			table.getSchema().getDatabase();

        RDBCorePlugin plugin = RDBCorePlugin.getDefault();
        DatabaseDefinition dbDefinition = 
            plugin.getDatabaseDefinitionRegistry().getDefinition(db);

        if (dbDefinition.supportsSchema()) {
            return quoteIdentifier(db, table.getSchema().getName())
                    + "." + quoteIdentifier(db, table.getName()); //$NON-NLS-1$
        } else {
            return quoteIdentifier(db, table.getName()); //$NON-NLS-1$
        }
    }
    
    public static String getQualifiedUDTName(UserDefinedType udt)
    {
    	Database db = udt.getSchema().getCatalog() != null ?
    			udt.getSchema().getCatalog().getDatabase():
    			udt.getSchema().getDatabase();
                
        RDBCorePlugin plugin = RDBCorePlugin.getDefault();
        DatabaseDefinition dbDefinition = 
            plugin.getDatabaseDefinitionRegistry().getDefinition(db);

        if (dbDefinition.supportsSchema()) {
            return quoteIdentifier(db, udt.getSchema().getName()) 
            + "." + quoteIdentifier(db, udt.getName()); //$NON-NLS-1$
          } else {
            return quoteIdentifier(db, udt.getName()); //$NON-NLS-1$
          }
    }
    
    public static String quoteIdentifier(Database db, String s)
    {
    	String delim = null;
    	if (db instanceof ICatalogObject) {
    		Connection conn = ((ICatalogObject)db).getConnection();
    		if (conn != null) {
    			try {
					delim = conn.getMetaData().getIdentifierQuoteString();
				}
				catch (SQLException e) {
					// we fall back to the db def below
				}
    		}
    	}
    	if (delim == null) {
    		DatabaseDefinitionRegistry dbDefRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
    		DatabaseDefinition dbDef =  dbDefRegistry.getDefinition(db);
    		delim = dbDef.getIdentifierQuoteString();
    	}
        if (!SQ.equals(delim) && !delim.equals(SQ2)){
            delim = DQ; 
        }
        return delim + doubleStringDelim(s, delim) + delim;
    }
    
    /**
     * Gets the identifier and add quote string if neccessary 
     * Note:  This is a work around solution for quoteIdentifier(Database, String)
     * because the databaseDefinition.getIdentifierQuoteString() does not return the
     * correct identifier quote string.
     * @param conn the JDBC connection
     * @param s the string to append any identifier quotes
     * @return the quoted identifier
     */
    public static String quoteIdentifier(Connection conn, String s)
    {
    	try
    	{
    		String delim = conn.getMetaData().getIdentifierQuoteString().trim();    		
    		return delim + doubleStringDelim(s, delim) + delim;
    	}
    	catch (Exception ex)
    	{
    		String message = ex.getMessage();
    		if (message == null)
    			message = ""; //$NON-NLS-1$
    		DataCorePlugin.getDefault().writeLog(IStatus.ERROR, 0, message, ex);    		
    	}
    	return s;
    }
    
    
    public static String doubleStringDelim(String s, String delim)
	{
        if (delim==null||delim.length()==0)
            return s;
        
        int i = s.indexOf(delim);
        if(i != -1) {
            StringBuffer sb = new StringBuffer(s);            
            int j;
            for(; i != -1; i = sb.toString().indexOf(delim, j)) {
                sb = sb.insert(i, delim);
                j = i + 2*delim.length();
            }
            return sb.toString();
        } else
            return s;
	}
    
    protected void listColumnDataAccessors()
    {
    	columnDataAccessors = new Vector();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(ID + ".columnDataAccessors"); //$NON-NLS-1$
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension.getConfigurationElements();
			for (int j=0; j<elements.length; ++j)
				columnDataAccessors.add(new ColumnDataAccessorExtension(elements[j]));
		}
	}
    
    public IColumnDataAccessor newColumnDataAccessor(Column sqlCol) throws Exception
    {
    	IColumnDataAccessor acc;
    	// some cases the data type is not supported by the Model
    	DataType type = sqlCol.getDataType();
    	if (type == null) {
    		//String id = "org.eclipse.wst.rdb.data.core "; //$NON-NLS-1$    		
    		String columnName = sqlCol.getName(); 
    		String message = Messages.getString("DataCorePlugin.Unsupported",
    				new Object[] {columnName}); //$NON-NLS-1$
    		Status status = new Status (IStatus.ERROR, ID, 1, message, null);
    		throw new CoreException (status);
    	}
    	
    	try {	    	
    		Database db = sqlCol.getTable().getSchema().getCatalog() != null ?
    				sqlCol.getTable().getSchema().getCatalog().getDatabase():
    				sqlCol.getTable().getSchema().getDatabase();	    	
	    	String vendor = db.getVendor();
	    	String version = db.getVersion();	    	
	    	String dataType = sqlCol.getDataType().getName();
	    	String distinctUDTColType = "";//added for Distinct user-defined types
	    	
	    	if(type instanceof DistinctUserDefinedType)
	    		distinctUDTColType="DISTINCT";
	    	
	    	ColumnDataAccessorExtension element = null;
	    	int score = -1;
	    	
	    	Iterator it = columnDataAccessors.iterator();
	    	while (it.hasNext()) {
	    		ColumnDataAccessorExtension curElement = (ColumnDataAccessorExtension)it.next();
				if ( curElement.matches(vendor, version, dataType) && 
					  (element==null || curElement.getScore()>element.getScore()) ) {
					element = curElement;
					score = curElement.getScore();
				}// DB2, Informix and Sybase have distinct user defined type which is not returned by sqlCol.getDataType();
				else if ( curElement.matches(vendor, version, distinctUDTColType) && 
					  (element==null || curElement.getScore()>element.getScore()) ) {
					element = curElement;
					score = curElement.getScore();
				}
	    	}
	    	
	    	if (element!=null)
	    		acc = element.createInstance();
	    	else
	    		acc = new DefaultColumnDataAccessor();
	    	
    	} catch (CoreException ex) {
    		writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
    		acc = new DefaultColumnDataAccessor();
    	}
    	acc.initialize(sqlCol);
		return acc;
    }
  
}

class ColumnDataAccessorExtension
{	
	protected IConfigurationElement element;
	protected String vendors;
	protected String versions;
	protected String dataTypes;
	protected int score;
	
	public ColumnDataAccessorExtension(IConfigurationElement element)
	{
		this.element = element;
		vendors = element.getAttribute("vendor"); //$NON-NLS-1$
		versions = element.getAttribute("version"); //$NON-NLS-1$
		dataTypes = element.getAttribute("dataType"); //$NON-NLS-1$
		score = ((vendors!=null)?1:0) + ((versions!=null)?1:0) + ((dataTypes!=null)?1:0);
	}
	
	public boolean matches(String vendor, String version, String dataType)
	{
		if (this.vendors!=null && !contains(this.vendors, vendor))
			return false;
		else if (this.versions!=null && !contains(this.versions,version))
			return false;
		else if (this.dataTypes!=null && !contains(this.dataTypes,dataType))
			return false;
		else
			return true;
	}
	
	protected boolean contains(String values, String value)
	{
		StringTokenizer st = new StringTokenizer(values, ",", false); //$NON-NLS-1$
		while (st.hasMoreElements()) {
			String s = st.nextToken();
			if (value.equals(s.trim()))
				return true;
		}
		return false;
	}
	
	public IColumnDataAccessor createInstance() throws CoreException
	{
		return (IColumnDataAccessor)element.createExecutableExtension("class"); //$NON-NLS-1$
	}
	
	public int getScore()
	{
		return score;
	}
}