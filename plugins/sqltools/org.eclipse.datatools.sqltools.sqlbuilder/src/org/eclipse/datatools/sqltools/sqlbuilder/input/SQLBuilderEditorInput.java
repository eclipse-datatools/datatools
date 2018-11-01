/*******************************************************************************
 * Copyright © 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - enhancement to maintain SQB UI control state
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

/**
 * This class implements the <code>ISQLEditorInput</code> interface using a string
 * for the SQL statement itself. This class is provided as a convenience for callers
 * of the SQL Builder who want to open it with a SQL statement as text.
 * 
 * There are constructors which allow you to pass in ISQLEditorConnectionInfo or
 * IConnectionProfile objects.
 * 
 * There are constructors which allow you to pass in an existing SQL statement in a
 * SQLStatementInfo object or to create new SQL statements by passing in
 * an integer to specify the statement type.  The value must be
 * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
 * STATEMENT_TYPE constants.
 * 
 * @author Jeremy Lindop
*/
public class SQLBuilderEditorInput implements ISQLBuilderEditorInput {

    /** Contains connection information associated with this object. */
    private ISQLEditorConnectionInfo _connectionInfo;
    
	/** Contains the SQLStatement */
	private ISQLStatementInfo  _sqlStatementInfo;
	
    /** Contains OmitSchemaInfo associated with this object. */
    private IOmitSchemaInfo _omitSchemaInfo;

	private IWindowStateInfo _windowStateInfo;
	private ISQLBuilderEditorInputUsageOptions _inputUsageOptions;

    /*
     * Statement type is used for creating new statements. The value must be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.
     */
    protected int _statementType = StatementHelper.STATEMENT_TYPE_SELECT;
    

    /**
     * Creates an instance of this class with the given ConnectionInfo
     * and StatementType. This constructor is used to create
     * new SQL statements.
     * 
     * @param connectionInfo the ConnectionInfo
     * @param statementType  statement type is used for creating new statements.
     * The value must be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.

     */
    public SQLBuilderEditorInput(ISQLEditorConnectionInfo connectionInfo, int statementType) {
    	this(connectionInfo, null,  null);
    	_statementType = statementType;
    }

    /**
     * Creates an instance of this class with the given ConnectionProfile
     * and StatementType.  This constructor is used to create
     * new SQL statements.
     * 
     * @param connectionProfile the ConnectionProfile
     * @param statementType  statement type is used for creating new statements.
     * The value must be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.

     */
    public SQLBuilderEditorInput(IConnectionProfile connectionProfile, int statementType) {
    	this(connectionProfile, null,  null);
    	_statementType = statementType;
    }

    /**
     * Creates an instance of this class with the given ConnectionInfo and
     * SQLStatementInfo.  This constructor should be used to create a
     * SQLBuilderEditorInput based on an existing SQL statement.
     * The SQLStatementInfo may optionally contain a SQLDialectInfo object to
     * specify the SQL dialect.
     * 
     * @param connectionInfo the ConnectionInfo
     * @param sqlStatementInfo the SQLStatementInfo
     */
    public SQLBuilderEditorInput(ISQLEditorConnectionInfo connectionInfo,
    		ISQLStatementInfo sqlStatementInfo) {
    	this(connectionInfo, sqlStatementInfo, null);
    }
    
    /**
     * Creates an instance of this class with the given ConnectionProfile and
     * SQLStatementInfo.  This constructor should be used to create a
     * SQLBuilderEditorInput based on an existing SQL statement.
     * The SQLStatementInfo may optionally contain a SQLDialectInfo object to
     * specify the SQL dialect.
     * 
     * @param connectionProfile the ConnectionProfile
     * @param sqlStatementInfo the SQLStatementInfo
     */
    public SQLBuilderEditorInput(IConnectionProfile connectionProfile,
    		ISQLStatementInfo sqlStatementInfo) {
    	this(connectionProfile, sqlStatementInfo, null);
    }
    
   /**
     * Creates an instance of this class with the given ConnectionInfo and SQLStatementInfo.
     * This constructor should be used to create a
     * SQLBuilderEditorInput based on an existing SQL statement.
     * The SQLStatementInfo may optionally contain a SQLDialectInfo object to
     * specify the SQL dialect.
     * 
     * @param connectionInfo the ConnectionInfo
     * @param sqlStatementInfo the SQLStatementInfo
     * @param omitSchemaInfo the OmitSchemaInfo
     */
    public SQLBuilderEditorInput(ISQLEditorConnectionInfo connectionInfo,
    		ISQLStatementInfo sqlStatementInfo,
    		IOmitSchemaInfo omitSchemaInfo) {
    	_connectionInfo = connectionInfo;
        
   		_sqlStatementInfo = sqlStatementInfo;
    	
    	if (omitSchemaInfo != null){
    		_omitSchemaInfo = omitSchemaInfo;
    	}
    	else {
            _omitSchemaInfo = new OmitSchemaInfo();
            _omitSchemaInfo.initFromPreferences();
    	}
    }

    /**
     * Creates an instance of this class with the given ConnectionProfile, SQLStatementInfo
     * and OmitSchemaInfo.
     * 
     * This constructor should be used to create a
     * SQLBuilderEditorInput based on an existing SQL statement.
     * The SQLStatementInfo may optionally contain a SQLDialectInfo object to
     * specify the SQL dialect.
     * 
     * @param connectionProfile the ConnectionProfile
     * @param sqlStatementInfo the SQLStatementInfo
     * @param omitSchemaInfo the OmitSchemaInfo
     */
    public SQLBuilderEditorInput(IConnectionProfile connectionProfile,
    		ISQLStatementInfo sqlStatementInfo,
    		IOmitSchemaInfo omitSchemaInfo) {
    	_connectionInfo = new SQLBuilderConnectionInfo(connectionProfile);
	
   		_sqlStatementInfo = sqlStatementInfo;
    	
    	if (omitSchemaInfo != null){
    		_omitSchemaInfo = omitSchemaInfo;
    	}
    	else {
            _omitSchemaInfo = new OmitSchemaInfo();
            _omitSchemaInfo.initFromPreferences();
    	}
    }
    
	/**
	 * Gets the <code>ISQLEditorConnectionInfo</code> associated with this input.
	 * 
	 * @return the current <code>ISQLEditorConnectionInfo</code> object
	 */
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return _connectionInfo;
	}

	/**
	 * Sets the <code>ISQLEditorConnectionInfo</code> associated with this input to the given 
	 * object.
	 * 
	 * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to set
	 */
	public void setConnectionInfo( ISQLEditorConnectionInfo connInfo ) {
		//the connection info must not be null
		if (connInfo == null)
    	{
			_connectionInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
    	}
		else
		{
			_connectionInfo = connInfo;
		}
	}
	
	/**
	 * Gets the <code>IConnectionProfile</code> associated with this input.
	 * 
	 * @return the current <code>IConnectionProfile</code> object
	 */
	public IConnectionProfile getConnectionProfile() {
		if (_connectionInfo != null){
			return _connectionInfo.getConnectionProfile();
		}
		else {
			return null;
		}
	}

	/**
	 * Sets the <code>IConnectionProfile</code> associated with this input to the given 
	 * object.
	 * 
	 * @param connInfo the <code>IConnectionProfile</code> object to set
	 */
	public void setConnectionProfile( IConnectionProfile connProfile) {
		//the connection info must not be null
		if (connProfile == null)
    	{
			_connectionInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
    	}
		else
		{
			_connectionInfo = new SQLBuilderConnectionInfo(connProfile);
		}
	}
	
	/**
	 * Sets the <code>OmitSchemaInfo</code> associated with this input to the given 
	 * object.
	 * 
	 * @param omitSchemaInfo the <code>OmitSchemaInfo</code> object to set
	 */
	public void setOmitSchemaInfo( IOmitSchemaInfo omitSchemaInfo ) {
		if (omitSchemaInfo == null)
    	{
			_omitSchemaInfo = new OmitSchemaInfo();
			_omitSchemaInfo.initFromPreferences();
    	}
		else
		{
			_omitSchemaInfo = omitSchemaInfo;
		}
	}

	
	/**
	 * Gets the <code>IOmitSchemaInfo</code> associated with this input.
	 * 
	 * @return the current <code>IOmitSchemaInfo</code> object
	 */
	public IOmitSchemaInfo getOmitSchemaInfo() {
		return _omitSchemaInfo;
	}

	/**
	 * Sets the <code>SQLStatementInfo</code> associated with this input to the given 
	 * object.
	 * 
	 * @param sqlStatementInfo the <code>ISQLStatementInfo</code> object to set
	 */
	public void setSQLStatementInfo( ISQLStatementInfo sqlStatementInfo ) {
		_sqlStatementInfo = sqlStatementInfo;
	}

	
	/**
	 * Gets the <code>ISQLStatementInfo</code> associated with this input.
	 * 
	 * @return the current <code>ISQLStatementInfo</code> object
	 */
	public ISQLStatementInfo getSQLStatementInfo() {
		return _sqlStatementInfo;
	}

	/**
	 * Gets the SQLStatement contained in this input's File
	 */
	public String getSQL() {
		if (_sqlStatementInfo != null){
			return _sqlStatementInfo.getSQL();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Gets the StatementType for in this input.
	 * 
     * Statement type is used for creating new statements. The value must be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.
	 */
	public int getStatementType(){
		return _statementType;
	}

	/**
	 * Sets the StatementType for in this input.
	 * 
     * Statement type is used for creating new statements. The value must be
     * one of  {@link org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper}'s
	 * STATEMENT_TYPE constants.
	 */
	public void setStatementType(int statementType){
		_statementType = statementType;
	}

	public String getId() {
        return null;
	}

	public boolean isConnectionRequired() {
        return false;
	}

	public boolean exists() {
		return true;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
        StringBuffer sb = new StringBuffer(30);
        if (_connectionInfo != null) {
        	sb.append(_connectionInfo.getName());
        }
        return sb.toString();        

	}

	public Object getAdapter(Class adapter) {
		return null;
	}

    public IWindowStateInfo getWindowStateInfo() {
    	return _windowStateInfo;
    }
    
    public void setWindowStateInfo( IWindowStateInfo windowStateInfo ) {
    	_windowStateInfo = windowStateInfo;
    }
    
    public ISQLBuilderEditorInputUsageOptions getInputUsageOptions() {
        return _inputUsageOptions;
    }
    
    public void setInputUsageOptions( ISQLBuilderEditorInputUsageOptions options ) {
		_inputUsageOptions = options;
	}
	
}
