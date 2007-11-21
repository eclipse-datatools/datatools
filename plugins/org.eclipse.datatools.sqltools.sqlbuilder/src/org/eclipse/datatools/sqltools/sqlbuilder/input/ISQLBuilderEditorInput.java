/*******************************************************************************
 * Copyright © 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
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
       
}
