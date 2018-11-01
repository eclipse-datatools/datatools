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

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.FileUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This class implements the <code>ISQLEditorInput</code> interface using a
 * <code>FileEditorInput</code> as the base.  In addition, this class contains 
 * fields and methods that are specifically for launching the SQL Builder.  This class  
 * is provided as a convenience for callers of the SQL Builder who want to open the 
 * SQL Builder on a file.  
 */
public class SQLBuilderFileEditorInput extends FileEditorInput implements ISQLBuilderEditorInput {

	/** Contains the SQLStatement */
	private String fSQLStatement;
    /** Contains connection information associated with this object. */
    private ISQLEditorConnectionInfo fConnInfo;
    /** Contains OmitSchemaInfo associated with this object. */
    private IOmitSchemaInfo fOmitSchemaInfo;

    private IWindowStateInfo fWindowStateInfo;
    
	private ISQLBuilderEditorInputUsageOptions fInputUsageOptions;
    
    /**
     * Creates an instance of this class with the given file.
     * 
     * @param aFile the file to associate with this input
     */
    public SQLBuilderFileEditorInput(IFile aFile) {
        super(aFile);
        
        ISQLEditorConnectionInfo connectionInfo = SQLFileUtil.getConnectionInfo(aFile);
        setConnectionInfo(connectionInfo);
        
        IOmitSchemaInfo omitSchemaInfo = SQLFileUtil.getOmitSchemaInfo(aFile);
        setOmitSchemaInfo(omitSchemaInfo);
    }

	/**
	 * Gets the <code>ISQLEditorConnectionInfo</code> associated with this input.
	 * 
	 * @return the current <code>ISQLEditorConnectionInfo</code> object
	 */
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return fConnInfo;
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
			fConnInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
    	}
		else
		{
			fConnInfo = connInfo;
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
			fOmitSchemaInfo = new OmitSchemaInfo();
			fOmitSchemaInfo.initFromPreferences();
    	}
		else
		{
			fOmitSchemaInfo = omitSchemaInfo;
		}
	}

	
	/**
	 * Gets the <code>IOmitSchemaInfo</code> associated with this input.
	 * 
	 * @return the current <code>IOmitSchemaInfo</code> object
	 */
	public IOmitSchemaInfo getOmitSchemaInfo() {
		return fOmitSchemaInfo;
	}

	/**
	 * Gets the SQLStatement contained in this input's File
	 */
	public String getSQL() {
		if (fSQLStatement == null){
	        try {
	        	fSQLStatement =  FileUtil.getFileContents(getFile());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return fSQLStatement;
	}
    
    public boolean isConnectionRequired()
    {
        return false;
    }

    public String getId()
    {
        return toString();
    }

    public IWindowStateInfo getWindowStateInfo() {
    	return fWindowStateInfo;
    }
    
    public void setWindowStateInfo( IWindowStateInfo windowStateInfo ) {
    	fWindowStateInfo = windowStateInfo;
    }
	
	public ISQLBuilderEditorInputUsageOptions getInputUsageOptions() {
		return fInputUsageOptions;
	}
    
    public void setInputUsageOptions( ISQLBuilderEditorInputUsageOptions options ) {
        fInputUsageOptions = options;
    }
    
}
