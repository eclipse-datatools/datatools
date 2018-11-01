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

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;

/**
 * This interface is a mix-in interface to be used with an <code>IEditorInput</code> 
 * to add additional information to the editor input for the SQL Builder.
 */
public interface ISQLBuilderEditorInput extends ISQLEditorInput {

	/**
	 * Gets the SQL Statement for this input - the contents of the input.
	 * 
	 * @return String the SQL Statement for this input.
	 */
	public String getSQL();
	
	/**
     * Gets information about whether or not to omit the default schema name from SQL generated in
     * the SQL Builder.
     * 
     * return IOmitSchemaInfo the <code>IOmitSchemaInfo</code> belong to this <code>ISQLBuilderEditorInput</code>.
     */
    public IOmitSchemaInfo getOmitSchemaInfo();
       
	/**
     * Sets information about whether or not to omit the default schema name from SQL generated in
     * the SQL Builder.
     * 
     * @param IOmitSchemaInfo the <code>IOmitSchemaInfo</code> to be set.
     */
    public void setOmitSchemaInfo(IOmitSchemaInfo omitSchemaInfo);

	/**
	 * Gets the <code>ISQLEditorConnectionInfo</code> associated with this input.
	 * 
	 * @return the current <code>ISQLEditorConnectionInfo</code> object
	 */
	public ISQLEditorConnectionInfo getConnectionInfo();

	/**
	 * Sets the <code>ISQLEditorConnectionInfo</code> associated with this input to the given 
	 * object.
	 * 
	 * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to set
	 */
	public void setConnectionInfo( ISQLEditorConnectionInfo connInfo );
		
    /**
     * Gets the window state information which stores the control states of
     * the SQL Query Builder.
     */
    public IWindowStateInfo getWindowStateInfo();
    
	/**
     * Sets the window state information.
     * 
     * @param IOmitSchemaInfo the <code>IOmitSchemaInfo</code> to be set.
     */
    public void setWindowStateInfo( IWindowStateInfo windowStateInfo );
       
    /**
     * Gets the usage options of the editor input. 
     * The options may be configured independent of the existence of specific input info.
     */
    public ISQLBuilderEditorInputUsageOptions getInputUsageOptions( );

    /**
     * Sets the usage options of the editor input.
     * 
     * @param ISQLBuilderEditorInputUsageOptions the <code>ISQLBuilderEditorInputUsageOptions</code> to be set.
     */
    public void setInputUsageOptions( ISQLBuilderEditorInputUsageOptions options );

}
