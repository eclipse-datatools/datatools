/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.externalfile;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorInputFactory;
import org.eclipse.ui.internal.editors.text.JavaFileEditorInput;

/**
 * 
 * @author Hui Cao
 * 
 */
public class ExternalSQLFileEditorInput extends JavaFileEditorInput implements
		ISQLEditorInput {

	/** Contains connection information associated with this object. */
    private ISQLEditorConnectionInfo fConnInfo;
    
	public ExternalSQLFileEditorInput(IFileStore fileStore) {
		super(fileStore);
		setConnectionInfo(SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO);
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
     * Returns the id of the element factory which should be used to re-create this
     * object.
     * 
     * @see org.eclipse.ui.IPersistableElement#getFactoryId()
     */
    public String getFactoryId() {
        return SQLEditorInputFactory.ID_FACTORY;
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

}
