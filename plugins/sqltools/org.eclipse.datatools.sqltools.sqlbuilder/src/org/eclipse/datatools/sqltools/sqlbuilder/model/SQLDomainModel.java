/*******************************************************************************
 * Copyright © 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - fix for BZ 319927
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionAdapter;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.DataTypeResolver;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.TableReferenceResolver;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.util.RSCCoreUIUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLDBUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class provides a central access point for state and environment information
 * for a SQL statement being edited by the SQL Builder.  It also provides access to
 * services such as parsing.
 */
public class SQLDomainModel {

    private AdapterFactoryEditingDomain editingDomain;
    private Connection jdbcConnection;
    private QueryStatement sqlStatement;
    private VendorHelper vendorHelper;
    private IFile sqlFileResource;
    private IProject project;
    private ISQLEditorConnectionInfo connectionInfo;
    private IOmitSchemaInfo _omitSchemaInfo;
    private String currentSchema; // current schema name
    private String initialSource; // content of IFile, may be invalid statement
    private boolean unmatchedSource; // whether or not the source matches the Model
    private Database database;
    private DatabaseDefinition dbDefinition;
    private boolean lDirty = false;
    private String improperStatement;
    private SQLQuerySourceFormat sqlSourceFormat = null;
    private List postParseProcessorList = null;
    private static Hashtable templateSQLTable = new Hashtable(6);
    
    /** Statement source for saving to project */
    private String editorText;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLDomainModel() {
    }

    /**
     * Constructs an instance of this class with the given initial query statement
     * and database.
     * 
     * @param aStatement the query statement to use
     * @param aDatabase the database to use
     */
    public SQLDomainModel(QueryStatement aStatement, Database aDatabase) {
        sqlStatement = aStatement;
        database = aDatabase;
    }

   
    /**
     * Gets the editing domain for this statement.
     * 
     * @return the editing domain object
     */
    public AdapterFactoryEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Sets the editing domain for this statement.
     * 
     * @param anEditingDomain the editing domain to use
     */
    public void setEditingDomain(AdapterFactoryEditingDomain anEditingDomain) {
        editingDomain = anEditingDomain;
    }

    /**
     * Gets the adapter factory for this statement.
     * @return the adapter factory object
     */
    public AdapterFactory getAdapterFactory() {
        return SQLBuilderPlugin.getAdapterFactory();
    }

    /**
     * Creates and returns a content provider for this statement, based on the
     * current adapter factory.
     * 
     * @return the content provider
     */
    public AdapterFactoryContentProvider createContentProvider() {
        return new SQLAdapterFactoryContentProvider(getAdapterFactory());
    }

    /**
     * Creates and returns a label provider for this statement, based on the 
     * current adapter factory.
     * 
     * @return the label provider
     */
    public AdapterFactoryLabelProvider createLabelProvider() {
        return new AdapterFactoryLabelProvider(getAdapterFactory());
    }

    /**
     * Gets the current query statement.
     * 
     * @return the current query statement object
     */
    public QueryStatement getSQLStatement() {
        return sqlStatement;
    }

    /** 
     * Sets the current query statement.  This is intended for use by the QueryViewer.
     * 
     * @param query the query statement to set
     */
    public void setSQLStatement(QueryStatement query) {
        sqlStatement = query;
    }

    /**
     * Gets template SQL for the given statement type.  
     * Note: this method uses getTemplateSQL() in StatementHelper and caches the template 
     * sql for all the types.
     * 
     * @param type the type of the statement as defined in <code>StatementHelper<code>
     * @return the template SQL or null if the type is not defined
     */
    public String getTemplateSQL(int type) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry( new Object[] { new Integer( type ) } );
        }
        
        String templateSQL = (String) templateSQLTable.get(new Integer(type));
        
        /* If we don't already have template SQL for the statement type, create and cache it.
         */
        if (templateSQL == null) {
            templateSQL = StatementHelper.getTemplateSQL( type );
            if (templateSQL != null) {
                templateSQLTable.put( new Integer( type ), templateSQL.trim() );
            }
        }
        
        return (String) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( templateSQL );
    }

    private static void initTemplateSQLTable() {
        int[] stmtTypes = new int[] {
                StatementHelper.STATEMENT_TYPE_SELECT,
                StatementHelper.STATEMENT_TYPE_INSERT,
                StatementHelper.STATEMENT_TYPE_UPDATE,
                StatementHelper.STATEMENT_TYPE_DELETE,
                StatementHelper.STATEMENT_TYPE_FULLSELECT,
                StatementHelper.STATEMENT_TYPE_WITH
        };
        for (int i = 0; i < stmtTypes.length; i++) {
            int stmtType = stmtTypes[i];
            String templateSQL = StatementHelper.getTemplateSQL( stmtType );
            if (templateSQL != null) {
                templateSQLTable.put( new Integer( stmtType ), templateSQL.trim() );
            }
        }
    }
    
    /**
     * Gets the template SQL for the current statement. 
     * 
     * @return the template SQL
     */
    public String getTemplateSQLForCurrentStatement() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry( null );
        
        String templateSQL = null;
        int type = StatementHelper.getStatementType(sqlStatement);
        if (type != -1) {
            templateSQL = getTemplateSQL(type);
        }
        
        return (String) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( templateSQL );
    }

    /**
     * Replaces the content of the current statement with the content of the given
     * statement without replacing the current statement object itself.
     * 
     * @param newStmt the statement with the new content
     */
    public void replaceStatementContents( QueryStatement newStmt) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry( new Object[] { newStmt } );
        }
        
        String name = sqlStatement.getName();
        String label = sqlStatement.getLabel();

        if (sqlStatement instanceof QueryInsertStatement && newStmt instanceof QueryInsertStatement) {
            InsertHelper.replaceStatementContents((QueryInsertStatement) sqlStatement, (QueryInsertStatement) newStmt);
        }
        else if (sqlStatement instanceof QueryUpdateStatement && newStmt instanceof QueryUpdateStatement) {
            UpdateHelper.replaceStatementContents((QueryUpdateStatement) sqlStatement, (QueryUpdateStatement) newStmt);
        }
        else if (sqlStatement instanceof QueryDeleteStatement && newStmt instanceof QueryDeleteStatement) {
            DeleteHelper.replaceStatementContents((QueryDeleteStatement) sqlStatement, (QueryDeleteStatement) newStmt);
        }
        else if (sqlStatement instanceof QuerySelectStatement && newStmt instanceof QuerySelectStatement) {
            SelectHelper.replaceStatementContents((QuerySelectStatement) sqlStatement, (QuerySelectStatement) newStmt);
        }
        
        /* Retain the name and label of the old statment. */
        newStmt.setName(name);
        newStmt.setLabel(label);
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Clears the current statement to its template form.
     */
    public void clearStatementToTemplate() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry( null );
                
        int type = StatementHelper.getStatementType(sqlStatement);
        QueryStatement newStmt = StatementHelper.createQueryStatement(type, "");
        newStmt.getSourceInfo().setSqlFormat(getSqlSourceFormat());         
        replaceStatementContents(newStmt);
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Marks this statement as "dirty" (that is, has unsaved changes).
     * 
     * @param dirty true when there are unsaved changes, otherwise false
     */
    public void setDirty(boolean dirty) {
        lDirty = dirty;
    }

    /**
     * Gets whether or not this statement is "dirty" (that is, has unsaved changes).
     * 
     * @return true when there are unsaved changes, otherwise false
     */
    public boolean isDirty() {
        return lDirty;
    }

    /**
     * Gets the file resource associated with this statement, if any.
     * 
     * @return the file resource for this statement, or null if none
     */
    public IFile getIFile() {
        return sqlFileResource;
    }

    /**
     * Sets the file resource for this statement to the given object.
     * 
     * @param resource the file resource to set
     */
    public void setIFile(IFile aFileResource) {
        sqlFileResource = aFileResource;
    }

    /**
     * Shows the current parse errors for this statement in an error dialog.
     * 
     * @param exception an exception indicating the error
     */
    public static void showParseErrors(Exception exception) {
        String pluginId = SQLBuilderPlugin.getPlugin().getBundle().getSymbolicName();
        if (exception instanceof SQLParserException) {
            String title = Messages._UI_PARSE_ERROR_ENCOUNTERED;
            String message = ((SQLParserException) exception).getMessage();
            Status status = new Status(IStatus.ERROR, pluginId, 0, message, null);
            ErrorDialog.openError(Display.getCurrent().getActiveShell(), title, message, status);

        }
        else if (exception instanceof SQLParserInternalException) {
            // TODO: handle this kind of exception
        }
    }

    /**
     * Initializes the SQL statement in the SQL Builder from the content of the given file.
     * 
     * @param fileResource the <code>IFile</code> containing the source SQL
     * @return true when the statement is parsable, otherwise false
     * @throws Exception when error occurs reading the content of the file resource
     */
    public boolean openFileResource(IFile fileResource) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { fileResource });
        }
        
        boolean retval = true;
        sqlFileResource = fileResource;

        String statementName = fileResource.getName();
        String strSQL = readContentsToString(fileResource);  // [RATLC01124214] bgp 11Aug2006
        
        return initializeFromString(strSQL, statementName);
        
    }
    
    /**
     * Initializes the SQL statement in the SQL Builder from the contents of the given
     * storage (non-file) resource object.
     * 
     * @param storageResource the <code>IStorage</code> resource containing the source SQL 
     * @return true when the statement was initialized successfully, otherwise false
     * @throws Exception when an error occurs obtaining the content of the storage resource
     */
    public boolean openStorageResource(IStorage storageResource) throws Exception {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { storageResource });
        }
        
        boolean retval = true;
        String strSQL = null;

        /* Get the content of the storage resource. */
        InputStream iStream = storageResource.getContents();

        /* Get the content as a string.  We do this by reading bytes from the input 
         * stream to a kind of output stream that we can convert directly to a string.
         * 
         * (Note: it seems like converting an input stream to a string is a much harder 
         * thing to do than it should be.  There must be a better way than this.) */
        int iSize = iStream.available();
        byte buff[] = new byte[iSize];
        OutputStream oStream = new ByteArrayOutputStream(iSize);
        int k;
        while ((k = iStream.read(buff)) != -1) {
            oStream.write(buff, 0, k);
        }
        strSQL = oStream.toString();
        iStream.close();
        oStream.close();

        return initializeFromString(strSQL, storageResource.getName());
    }
    
    // RATLC01136221 bbn 10Jan2007 - new method
    /**
     * Initializes the SQL statement in the SQL Builder from the content of the given string.
     * 
     * @param strSQL the source SQL string
     * @return true when the statement is parsable, otherwise false
     */
    public boolean initializeFromString(String strSQL, String statementName) {
        boolean retval = true;
        strSQL = strSQL.trim();
        initialSource = strSQL;
        try {
            sqlStatement = parse(strSQL);
        }
        catch (Exception e) {
            // check if the error stmt is a template to begin with
            if (templateSQLTable == null || templateSQLTable.size() < 6) {
                initTemplateSQLTable();                
            }
            strSQL = strSQL.replaceAll("\r", "");
            if (!templateSQLTable.containsValue(strSQL)) {
                retval = false;
                unmatchedSource = true;
                SQLBuilderPlugin.getPlugin().getLogger().writeLog(e);
            }
        }
        if (sqlStatement != null) {
        	if (statementName != null){
        		sqlStatement.setName(statementName);
        	}
        }
        else {
            sqlStatement = getDefaultStatementFromSQL(strSQL, statementName);
            // If the SQL is just select, insert, update or delete, set unmatchedSource to false
            if (isSQLStatementTypeKeyword(strSQL)){
            	unmatchedSource = false;
            }
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(retval);
    }
    
    /**
     * Initializes the SQL statement in the SQL Builder from the statement type.
     * 
     * @param statement type Statement type is used for creating new statements. The value must be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.
 
     * @return true
     */
    public boolean initializeFromType(int statementType) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { Integer.toString(statementType)} );
        }
        
        boolean retval = true;
             
        if (templateSQLTable == null || templateSQLTable.size() < 6) {
            initTemplateSQLTable();                
        }
        sqlStatement = getDefaultStatementFromStatementType(statementType, null);
    
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(retval);
    }

    // [RATLC01124214] bgp 11Aug2006 - new method
    /**
     * Gets the contents of the given file resource as a string.
     * @param fileResource the file resource to use
     * @return the file resource content string
     */
    protected String readContentsToString(IFile fileResource) {
        return WorkbenchUtility.readFileContentsToString(fileResource, true);
    }


    /**
     * Gets a JDBC Connection object for the connection to the database.
     * @return the connection, or null if a connection is not available
     */
    public Connection getConnection() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);
        
        try {
            if (jdbcConnection == null) {
                // Get the current connection from the Database object.
                Database db = getDatabase();
                if (db instanceof ICatalogObject) {
                    jdbcConnection = ((ICatalogObject) db).getConnection();
                }
            }
            if (jdbcConnection != null) {
                if (jdbcConnection.isClosed()) { //Checks if connection is lost 
                	ConnectionInfo connInfo = ((ConnectionAdapter) jdbcConnection).getConnectionInfo();
                    if (connInfo != null) {
                        jdbcConnection = connInfo.getSharedConnection(); //try to reconnect using the existing info
                    }
                }
            }
        }
        catch (Exception e) {
            SQLBuilderPlugin.getPlugin().getLogger().writeLog(e);
            jdbcConnection = null;
        }

        return (Connection) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(jdbcConnection);
    }

    /**
     * Sets the connection for this SQL statement.
     * @param connection the connection object to set
     */
/*
    public void setConnection(Connection connection) {
        this.jdbcConnection = connection;
    }
*/
    /**
     * Gets the Database object that corresponds to this SQL statement.
     * @return the database object
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Sets the <code>Database</code> object associated with this statement to the
     * given object.
     * 
     * @param database the database object to set  
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Gets the current <code>DatabaseDefinition</code> object.
     * @return the database definition object
     */
    public DatabaseDefinition getDatabaseDefinition() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        // If we don't have a database definition, try to get one from the ISQLEditorConnectionInfo.
        // If we don't have a ISQLEditorConnectionInfo, try getting it from the Database.
        if (dbDefinition == null) {
            if (connectionInfo != null) {
            	dbDefinition = SQLDBUtil.getDatabaseDefinition(connectionInfo);
            }
            if (dbDefinition == null) {
                Database db = getDatabase();
                if (db != null) {
                    dbDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db);
                }
            }
        }
        return (DatabaseDefinition) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(dbDefinition);
    }

    /**
     * Gets the project associated with this statement.
     * 
     * @return the current project
     */
    public IProject getProject() {
        return project;
    }

    /**
     * Sets the project associated with this statement to the given project.
     * 
     * @param proj the project to set
     */
    public void setProject(IProject proj) {
        project = proj;
    }

    /**
     * Gets the <code>ISQLEditorConnectionInfo</code> object associated with this statement.
    * 
     * @return ISQLEditorConnectionInfo the model's connection info object
     */
    public ISQLEditorConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    /**
     * Sets the <code>ISQLEditorConnectionInfo</code> object associated with this statement to 
     * the given object.
     *  
     * @param info the connection info object to set
     */
    public void setConnectionInfo(ISQLEditorConnectionInfo info) {
        connectionInfo = info;
    }

    /**
     * Sets the <code>OmitSchemaInfo</code> object associated with this statement to 
     * the given object.
     *  
     * @param info the OmitSchemaInfo object to set
     */
    public void setOmitSchemaInfo(IOmitSchemaInfo info) {
        _omitSchemaInfo = info;
    }

    /**
     * Gets the <code>OmitSchemaInfo</code> object associated with this statement
     *  
     * @return OmitSchemaInfo the model's OmitSchemaInfo object
     */
    public IOmitSchemaInfo getOmitSchemaInfo() {
        return _omitSchemaInfo;
    }

    /**
     * Saves the current statement in the file resource associated with this editor.
     * 
     * @return true when save is successful, otherwise false
     */
    public boolean save() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        boolean retStatus = false;
        IFile fileResource = getIFile();
        String encoding = ResourcesPlugin.getEncoding();
        if (fileResource != null) {             
            try {
                // Get the file encoding directly from the file if we can.
                encoding = fileResource.getCharset();
            }
            catch (CoreException e) {
               // ignore, leave encoding as default
            }
            ByteArrayInputStream inpStream = null;
            try {
                if (fileResource.exists()) {
                    StringBuffer sb = new StringBuffer();
                    
                    // [wsdbu00057891] bgp 23Nov2005 - begin
                    if (isProper() == true) {
                        sb.append(getSQLStatement().getSQL());
                    }
                    else {
                        String improperStmt = getImproperStatement();
                        if (improperStmt != null) {
                            sb.append(improperStmt);
                        }
                        else {
                            // If we don't have an "improper statement" after all, use 
                            // whatever is currently in the model.
                            sb.append(getSQLStatement().getSQL());
                        }
                    }
                    // [wsdbu00057891] bgp 23Nov2005 - end
                    
                    inpStream = new ByteArrayInputStream(sb.toString().getBytes(encoding));
                    fileResource.setContents(inpStream, true, false, null);
                    fileResource.setCharset(encoding, new NullProgressMonitor());
                }
                else {
                    File sysFile = fileResource.getLocation().toFile();
                    if (sysFile.exists()) {
                        // TODO: handle error if Eclipse thinks file doesn't exist
                        // but the file system does.
                    }
                    else {
                        StringBuffer sb = new StringBuffer();
                        if (editorText != null && !editorText.equals("")) {
                            sb.append(editorText);
                        }
                        else {
                            sb.append(getSQLStatement().getSQL());
                        }
                        inpStream = new ByteArrayInputStream(sb.toString().getBytes(encoding));
                        fileResource.create(inpStream, false, null);
                        fileResource.setCharset(encoding, new NullProgressMonitor());
                    }
                }
                retStatus = true;
            }
            catch (Exception e) {
                SQLBuilderPlugin.getPlugin().getLogger().writeLog(e);

                Display disp = Display.getCurrent();
                if (disp != null) {
                    Shell shell = disp.getActiveShell();
                    String title = Messages.Editor_error_save_title;
                    String msg = Messages.Editor_error_save_message;
                    if (getIFile() != null && getIFile().isReadOnly()) {
                        // put up readonly message
                        msg += Messages.Editor_error_save_readonly + " " + getIFile().getFullPath(); //$NON-NLS-1$
                    }
                    else {
                        msg += Messages.Editor_error_save_failed + " " + getIFile().getFullPath(); //$NON-NLS-1$
                    }
                    MessageDialog.openError(shell, title, msg);
                }
                retStatus = false;
            }
            finally {
                if (inpStream != null) {
                    try {
                        inpStream.close();
                    }
                    catch (IOException e) {
                        // ignore, not much we can do at this point
                    }
                }
            }
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(retStatus);
    }
    
    /**
     * Sets the text to be used to save to project
     * @param text the text to be used for saving to project
     */
    public void setEditorText(String text) {
    	editorText = text;
    }

    /**
     * Gets the current vendor information object for this database.
     * 
     * @return the current vendor helper object
     */
    public VendorHelper getVendor() {
        if (vendorHelper == null) {
            vendorHelper = new VendorHelper(getDatabase());
        }
        return vendorHelper;
    }
    
    /**
     * Resets the VendorHelper to associate with a new database.
     * This is need for cases where no connection is available when this
     * class is first instantiated.
     * @param db the new database to create the VendorHelper with
     * @return the newly created VendorHelper, or null if db is null
     */
    public VendorHelper resetVendor(Database db) {
    	if (db != null) {
    		vendorHelper = new VendorHelper(db);
    		return vendorHelper;
    	}
    	return null;
    }

    /**
     * Gets whether or not there is currently an "improper" (failed to parse)
     * statement.
     * 
     * @return true when there is an improper statement, otherwise false
     */
    public boolean isProper() {
        if (improperStatement == null) {
            return true;
        }
        return false;
    }

    /**
     * Sets the given string as the "improper" (failed to parser) statement.
     * 
     * @param improperStmt the statement string to set
     */
    public void setImproperStatement(String improperStmt) {
        improperStatement = improperStmt;
    }

    /**
     * Gets the current "improper" (failed to parse) statement, if there is one.
     * 
     * @return the current improper statement, or null if none
     */
    public String getImproperStatement() {
        return improperStatement;
    }

 
    /**
     * Sets the current schema
     * 
     * @param the current schema to set
     */
    public void setCurrentSchema() {
    	// Set currentSchema
    	if (_omitSchemaInfo.getUseAUIDAsCurrentSchema()){
    		currentSchema = getUserName();
    	}
    	else {
    		currentSchema = _omitSchemaInfo.getCurrentSchema();
    	}
    	
    	// Set SQLQuerySourceFormat omitSchema
    	SQLQuerySourceFormat sourceFormat = getSqlSourceFormat();
        if (_omitSchemaInfo.getOmitCurrentSchema()) {
            sourceFormat.setOmitSchema(currentSchema);
        }
        else {
            sourceFormat.setOmitSchema(null);
        }
    }

    /**
     * Gets the current schema.
     * 
     * @return the current schema value
     */
    public String getCurrentSchema() {
        return currentSchema;
    }

    //FIXME   rename this method to clearly distinguish it from getDefaultSchema() 
    /**
     * Returns default schema for the database associated with the current project.
     * @return
     */
/*
    public String getDefaultSchemaName() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        List schemaList = DatabaseHelper.getSchemaList(database);
        Schema schema = null;
        String schemaName = "";
        if (getVendor().isDB2()) {
            // FIXME: wsdbu00037431,wsdbu00036477: should the user name be in SQL format of catalog format?
            // I expect it to be catalog format, but then it needs to be converted from the user input and stored correctly!
            // I don't expect db2admin here, I expect DB2ADMIN otherwise the user name would have to be inputed delimited
            // ["db2admin"] as opposed to [db2admin]
            // the conversion should be done in the connectionInfo or before, remove the conversion here!
            schemaName = System.getProperty("user.name");
            schemaName = SQLIdentifier.convertAuthID(schemaName, getConnectionInfo());
        }
        else {
            // FIXME: This is a hack! 3rd element is always default schema???
            if (schemaList.size() > 2) {
                schema = (Schema) schemaList.get(2);
                schemaName = schema.getName();
            }
        }
        return (String) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(schemaName);
    }
*/
    /**
     * Gets a default SQL statement object based on the given SQL string.  It does
     * this by examining the first word of the string.  This is used when the SQL
     * string can't be properly parsed, and we need to reset to a default statement.
     * 
     * @param sql the SQL string to use to determine the statement type
     * @param statementName a name for the new statement
     * @return the default SQL statement object
     */
    public QueryStatement getDefaultStatementFromSQL(String sql, String statementName) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { sql, statementName });
        }

        QueryStatement statement = null;
        sql = sql.toUpperCase();
        
        if (sql.startsWith("INSERT")) {
            statement = StatementHelper.createInsertStatement(statementName);
        }
        else if (sql.startsWith("UPDATE")) {
            statement = StatementHelper.createUpdateStatement(statementName);
        }
        else if (sql.startsWith("DELETE")) {
            statement = StatementHelper.createDeleteStatement(statementName);
        }
        else if (sql.indexOf("UNION") >= 0) {
            statement = StatementHelper.createQueryCombinedStatement(statementName);
        }
        else if (sql.startsWith("SELECT") && sql.indexOf("UNION") < 0) {
            statement = StatementHelper.createQuerySelectStatement(statementName);
        }
        else if (sql.startsWith("WITH")) {
            statement = StatementHelper.createWithStatement(statementName);
        }
        // [wsdbu00075605] bgp 27Apr2006
        /* Make sure that some kind of statement is created.  By default, create
         * a simple SELECT statement. */
        else {
            statement = StatementHelper.createQuerySelectStatement(statementName);
        }
        
        statement.getSourceInfo().setSqlFormat(getSqlSourceFormat());

        return (QueryStatement) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(statement);
    }

    /**
     * Gets a default SQL statement object based on the given statement type. 
     * 
     * @param statementType the statement type
     * @param statementName a name for the new statement. The value should be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.
     * @return the default SQL statement object
     */
    public QueryStatement getDefaultStatementFromStatementType(int statementType, String statementName) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { Integer.toString(statementType), statementName });
        }

        QueryStatement statement = null;

        if (statementType == StatementHelper.STATEMENT_TYPE_INSERT) {
            statement = StatementHelper.createInsertStatement(statementName);
        }
        else if (statementType == StatementHelper.STATEMENT_TYPE_UPDATE) {
            statement = StatementHelper.createUpdateStatement(statementName);
        }
        else if (statementType == StatementHelper.STATEMENT_TYPE_DELETE) {
            statement = StatementHelper.createDeleteStatement(statementName);
        }
        else if (statementType == StatementHelper.STATEMENT_TYPE_FULLSELECT){
            statement = StatementHelper.createQueryCombinedStatement(statementName);
        }
        else if (statementType == StatementHelper.STATEMENT_TYPE_SELECT) {
            statement = StatementHelper.createQuerySelectStatement(statementName);
        }
        else if (statementType == StatementHelper.STATEMENT_TYPE_WITH) {
            statement = StatementHelper.createWithStatement(statementName);
        }
        // Default - create SELECT statement
        else {
            statement = StatementHelper.createQuerySelectStatement(statementName);
        }
        
        statement.getSourceInfo().setSqlFormat(getSqlSourceFormat());

        return (QueryStatement) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(statement);
    }
    
    /**
     * Tests whether the SQL statement contains just the statement keyword, i.e.
     * SELECT, INSERT, UPDATE or DELETE.
     * 
     * @param sql the SQL string to test
     * @return whether the sql is just the statement type keyword
     */
    protected boolean isSQLStatementTypeKeyword(String sql){
    	sql = sql.toUpperCase();
    	
    	if (sql.equals("SELECT") 
        		|| sql.equals("INSERT")
        		|| sql.equals("UPDATE")
        		|| sql.equals("DELETE")
    		)
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    }

    /**
     * Gets an instance of the parser manager to use with this statement.
     * @return the parser manager object
     */
    protected SQLQueryParserManager getParserManager() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        SQLQueryParserManager parserManager = null;
        DatabaseDefinition dbDef = getDatabaseDefinition();
        if (dbDef != null) {
            String dbProduct = getDatabaseDefinition().getProduct();
            String dbVersion = getDatabaseDefinition().getVersion();
            
            /* Create an instance of the ParserManager and TODO: keep the instance. */
            parserManager = SQLQueryParserManagerProvider.getInstance().getParserManager(dbProduct, dbVersion);
            
            // TODO: should only configure the Parser Manager and P3's once, rather than every time
            PostParseProcessorConfiguration p3config = getPostParseProcessorConfiguration();
            SQLQuerySourceFormat srcFormat = getSqlSourceFormat();
            
            parserManager.setSourceFormat(srcFormat);
            parserManager.configPostParseProcessors(p3config);
        }

        return (SQLQueryParserManager) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parserManager);
    }

    /**
     * Parses string SQL statement passed and returns the QueryStatement object.
     * If parse fails it returns null.
     * @param sqlStr the String sql statement
     * @return the generated Query object
     * @throws SQLParserException if the parse was not successful
     * @throws SQLParserInternalException
     */
    public QueryStatement parse(String sqlStr) throws SQLParserException, SQLParserInternalException {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { sqlStr });
        }

        QueryStatement parsedStatement = null;
        SQLQueryParserManager parserManager = getParserManager();
        if (parserManager != null) {
            SQLQueryParseResult parseResult = parserManager.parseQuery(sqlStr);
            if (parseResult != null) {
                parsedStatement = parseResult.getQueryStatement();
            }
        }

        return (QueryStatement) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parsedStatement);
    }

    /**
     * Parses string SQL statement passed and returns the QueryStatement object.
     * If parse fails it returns null.
     * The ErrorList is also returned as a parameter
     * @param sqlStr the String sql statement
     * @param errorList the List to hold the errorList object from parseResult.
     * Can not be null.
     * @return the generated Query object
     * @throws SQLParserException if the parse was not successful
     * @throws SQLParserInternalException
     */
    public QueryStatement parse(String sqlStr, List errorList) throws SQLParserException, SQLParserInternalException {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { sqlStr, errorList });
        }

        QueryStatement parsedStatement = null;
        if (errorList != null) {
            SQLQueryParserManager parserManager = getParserManager();
            if (parserManager != null) {
                SQLQueryParseResult parseResult = parserManager.parseQuery(sqlStr);
                if (parseResult != null) {
                    errorList.clear();
                    errorList.addAll(parseResult.getErrorList());
                    parsedStatement = parseResult.getQueryStatement();
                }
            }
        }

        return (QueryStatement) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parsedStatement);
    }

    /**
     * Parses string SQL statement passed and returns the QueryStatement object.If createObjectTree is false
     * then parser is called for only a syntax check and only the toplevel statement object is constructed
     * If parse fails it returns null.
     * @param sqlStr the String sql statement
     * @param createObjectTree
     * @return the generated Query object
     * @throws SQLParserException if the parse was not successful
     * @throws SQLParserInternalException
     */
    public QueryStatement parse(String sqlStr, boolean createObjectTree) throws SQLParserException, SQLParserInternalException {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { sqlStr, new Boolean(createObjectTree) });
        }

        QueryStatement parsedStatement = null;
        if (createObjectTree) {
            parsedStatement = parse(sqlStr);
        }
        else {
            SQLQueryParserManager parserManager = getParserManager();
            if (parserManager != null) {
                parsedStatement = parserManager.checkSyntax(sqlStr);
            }
        }

        return (QueryStatement) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parsedStatement);
    }

       
    /**
     * Gets the current SQLQuerySourceFormat object.  The SQLQuerySourceFormat object contains
     * parameters that indicate how the SQL for the query model should be generated and parsed.  
     * @return the SQLQuerySourceFormat object 
     */
    public SQLQuerySourceFormat getSqlSourceFormat() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        if (sqlSourceFormat == null) {
            sqlSourceFormat = SQLQuerySourceFormat.copyDefaultFormat();

            // Configure the source format object with the omit schema option.
            String curSchema = getCurrentSchema();
            if (_omitSchemaInfo.getOmitCurrentSchema() == true) {
                sqlSourceFormat.setOmitSchema(curSchema);
            }
            else if (_omitSchemaInfo.getOmitCurrentSchema() != true) {
                sqlSourceFormat.setOmitSchema(null);
            }
            
            // Configure the source format object with the IdentifierQuoteString option
            DatabaseDefinition dbDef = this.getDatabaseDefinition();
            if (dbDef != null && dbDef.getIdentifierQuoteString() != null && dbDef.getIdentifierQuoteString().length() == 1){
            	sqlSourceFormat.setDelimitedIdentifierQuote(dbDef.getIdentifierQuoteString().charAt(0));
            }

            // Configure the AS keyword source format setting based on the connection info's database type
            Database db = getDatabase();
            ISQLEditorConnectionInfo connInfo = getConnectionInfo();
            if (db == null && connInfo != null) {
                db = connInfo.getDatabase();
            }
            
            if ( db != null ) { 
                VendorHelper vendorHelper = new VendorHelper(db);
                if (vendorHelper.isOracle()) {
                    sqlSourceFormat.setGenerateAsKeywordForTableCorrID( false );
                }
            }
        }
        return (SQLQuerySourceFormat) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(sqlSourceFormat);
    }

    /**
     * Sets the current SQLQuerySourceFormat object.  The SQLQuerySourceFormat object contains
     * parameters that indicate how the SQL for the query model should be generated and parsed.
     * @param sqlSourceFormat the SQLQuerySourceFormat object to use
     */
    public void setSqlSourceFormat(SQLQuerySourceFormat sqlSourceFormat) {
        this.sqlSourceFormat = sqlSourceFormat;
    }

    /**
     * Gets the source of a statement for execution (schema qaulified).
     * This is for the omit schema support where the current schema set by the user
     * may not match the SQLID of the server causing a table not found error.
     * @return the schema qualified statement source
     */
    public String getSQLForExecution() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        SQLQuerySourceFormat srcFormat = sqlStatement.getSourceInfo().getSqlFormat();

        if (_omitSchemaInfo.getOmitCurrentSchema()) {
            // tell parser to return schema with statement
            srcFormat.setOmitSchema(null);
        }
        String source = sqlStatement.getSQL();
        resetOmitSchemaToCurrentSchema();

        return (String) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(source);
    }

    /**
     * Sets the omit schema field of the source format to the current schema
     */
    public void resetOmitSchemaToCurrentSchema() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        SQLQuerySourceFormat srcFormat = getSqlSourceFormat();

        if (_omitSchemaInfo.getOmitCurrentSchema()) {
            // reset source format to current schema
            srcFormat.setOmitSchema(currentSchema);
        }

        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Gets the current list of PostParseProcessor objects.  PostParseProcessor objects are
     * used by the parser after a query model is generated to by the parser to modify or
     * verify the generated model before returning it to the caller of the parser.  By
     * default, the list contains a table reference resolver and a data type resolver.
     * @return the current list of PostParseProcessor objects
     */
    public List getPostParseProcessorList() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        /* If we don't already have a P3List, create and initialize it.
         * Otherwise return the existing one. */
        if (postParseProcessorList == null) {
            postParseProcessorList = new ArrayList();

            Database db = getDatabase();
            String currentSchemaName = getCurrentSchema();
            TableReferenceResolver tableReferenceResolver = new TableReferenceResolver(db, currentSchemaName);
            DataTypeResolver dataTypeResolver = new DataTypeResolver();

            postParseProcessorList.add(tableReferenceResolver);
            postParseProcessorList.add(dataTypeResolver);
        }

        return (List) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(postParseProcessorList);
    }
    
    /**
     * Gets the initial source contained in the IFile
     * @return the content of the IFile
     */
    public String getInitialSource() {
    	return initialSource;
    }
    
    /**
     * Gets whether or not the source and the SQL model matches
     * @return true if the source and SQL model does not match, false if they do
     */
    public boolean isUnmatchedSource() {
    	return unmatchedSource;
    }
    
    /**
     * Sets whether or not the source is un-matched
     * @param whether or not the source and the sql model is unmatched
     */
    public void setUnmatchedSource(boolean unmatched) {
    	unmatchedSource = unmatched;
    }

    /**
     * Gets the default schema for the current connection profile.  This is normally the login user id.
     * 
     * @return the default schema
     */
    public String getDefaultSchema() {
        String defaultSchema = null;
        if(connectionInfo != null)
        {
        	defaultSchema = connectionInfo.getDefaultSchemaName();
        	if (defaultSchema == null){
        		defaultSchema = getUserName();
        	}
        }
        return (String)SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(defaultSchema);
    }

    /**
     * Gets the user name for the current connection profile
     */
    public String getUserName() {
        String userName = "";
        if(connectionInfo != null)
        {
    		IConnectionProfile cp = connectionInfo.getConnectionProfile();
    		if (cp != null){
    			Properties props = cp.getBaseProperties();
    			Object objUserName = props.get("org.eclipse.datatools.connectivity.db.username");
    			if (objUserName instanceof String){
    				userName = (String)objUserName;
    				if (userName == null){
    					userName = "";
    				}
    			}
    		}
    	}
        return userName;
    }
    
    /**
     * Gets the configuration object to configure list of PostParseProcessor
     * objects for the parser. PostParseProcessor objects are used by the parser
     * after a query model is generated to by the parser to modify or verify the
     * generated model before returning it to the caller of the parser. By
     * default, the list contains a table reference resolver and a data type
     * resolver.
     * 
     * @return the configuration object for PostParseProcessor objects
     */
    public PostParseProcessorConfiguration getPostParseProcessorConfiguration() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        Database db = getDatabase();
        String currentSchemaName = getCurrentSchema();
        PostParseProcessorConfiguration p3config = new PostParseProcessorConfiguration(db, currentSchemaName);

        return (PostParseProcessorConfiguration) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(p3config);
    }

    /**
     * This inner class extends <code>AdapterFactoryContentProvider</code> in order to
     * provide better change notification. 
     */
    private class SQLAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

        public SQLAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public void notifyChanged(Notification notification) {
            if (viewer == null)
                return;

            Object obj = notification.getNotifier();
            if (obj == null || !(obj instanceof EObject) || RSCCoreUIUtil.chkIfEObjectsMatched((EObject) obj, getSQLStatement(), true) == false)
                return;

            super.notifyChanged(notification);
        }
    }

	public DatabaseIdentifier getDatabaseIdentifier() {
    	String profileName = connectionInfo.getConnectionProfileName();
    	String dbName = connectionInfo.getDatabaseName();
   		return new DatabaseIdentifier(profileName, dbName);
	}
    
	public boolean isConnected(){
		return connectionInfo.isConnected();
	}

	public void setInitialSource(String strSQL) {
		this.initialSource = strSQL;
	}
	
	/**
	 * Gets whether or not the given SQL statement type is supported in the SQL Query Builder.
	 * See the list of statement type constants defined in the {@link StatementHelper} class.
	 * 
	 * @param stmtType the statement type to check
	 * @return true when the statement type is supported, otherwise false
	 */
	public boolean getIsStatementTypeSupported(int stmtType) {
	    boolean isSupported = false;
	    
	    switch (stmtType) {
        case StatementHelper.STATEMENT_TYPE_SELECT:
        case StatementHelper.STATEMENT_TYPE_FULLSELECT:
        case StatementHelper.STATEMENT_TYPE_WITH:
        case StatementHelper.STATEMENT_TYPE_INSERT:
        case StatementHelper.STATEMENT_TYPE_UPDATE:
        case StatementHelper.STATEMENT_TYPE_DELETE:
            isSupported = true;
            break;
        default:
            // do nothing
        }
	    
	    return isSupported;
	}
}